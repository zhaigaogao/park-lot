/**
 * 集合类 基础控件集
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        template = require('template');

    //数据
    /**
     * 数据集合
     * @param {object} options: 配置项
     *   {function} checkSuccess: 检查response是否成功的function,默认检查successSign字段标识
     *   {function} extractResult: 取得response中数据数组的function,默认取得result字段
    */
    var DataList = Backbone.Collection.extend({
		idAttribute: 'id',
		initialize: function (options) {
            if (options.model) {
                this.model = options.model;
            }

            this.checkSuccess = options.checkSuccess || function (res) {
                return res.successSign;
            };
            this.extractResult = options.extractResult || function (res) {
                return res.result;
            };
		},
		parse: function (response, options) {
            if (!this.checkSuccess(response)) {
                this.trigger('failed');
                return [];
            }
            else {
                return this.extractResult(response);
            }
        },
        getCollection: function (filter) {
            //TODO
        }
	});
    /**
     * 分页模型
     * @param {object} options: 配置项
     *   {object} defaults: 初始分页，包括pageNum pageSize total pages,默认值分别为1 15 0 0
     *   {function} extractPage: 取得response中分页数据的function,默认取得page字段
    */
	var PageGuideModel = Backbone.Model.extend({
		defaults: {
			pageNum: 1,
            pageSize: 15,
			total: 0,
			pages: 0
		},
        initialize: function (options) {
            this.pageQuery = new Backbone.Model(options.defaults || this.defaults);
            this.extractPage = options.extractPage || function (res) {
                return res.page;
            };
        },
        setPage: function (response) {
            this.pageQuery.set(this.parse(this.extractPage(response)));

            this.trigger('pageChange');
        },
        getPage: function () {
            return this.pageQuery.toJSON();
        },
		parse: function (attrs) {
			var attrs = _.pick(attrs, 'pageNum', 'total', 'pages');
			return attrs;
		}
	});
    /**
     * 集合数据模型,整合DataList和PageGuideModel
     * @param {object} options: 基于DataList的配置项
     *  {object} pagination: PageGuideModel配置项，其中enable字段标识是否启用分页
    */
    var CollectionStore = Backbone.Model.extend({
        initialize: function (options) {
            this.data = new DataList(options);
            this.queryModel = new Backbone.Model({});
            if (options.pagination && options.pagination.enable) {
                this.pagination = new PageGuideModel(options.pagination);
                this.queryModel.set(this.pagination.getPage());
            }
            if (options.url) {
                this.data.url = options.url;
            }

            this.listenTo(this.data, 'sync', this.sync);
            this.listenTo(this.data, 'failed', this.failed);
            this.listenTo(this.data, 'remove', this.update);
            this.listenTo(this.data, 'destroy', this.update);
        },
        reset: function (models, options) {
            this.data.reset(models, options);
        },
        size: function () {
            return this.data.length;
        },
        query: function (query) {
            this.queryModel.set(query);
            this.update();
        },
        update: function () {
            var query = this.queryModel.toJSON();
            this.data.fetch({
                reset: true,
                type: 'post',
				data: query
            });
        },
        sync: function (list, response, options) {
            if (this.pagination) {
                this.pagination.setPage(response);
            }

            this.trigger('sync');
		},
        failed: function () {
            this.trigger('failed');
        }
    });

    //基础视图
    var noDataTemplate = '<span>暂无数据</span>';
    /**
     * 基础视图,提供子类调用基类override的同名方法的方法
    */
    var BaseView = Backbone.View.extend({
        _super_initialize: function (options) {
            var proto = this._super_invoke('initialize', [options]);
            if (proto.events) {
                this.delegateEvents(proto.events);
            }
        },
        _super_invoke: function (superFn, params) {
            if (!this._super_level) { this._super_level = {}; }
            if (!this._super_level[superFn]) { this._super_level[superFn] = 0; }
            var proto = this.constructor.__super__,
                currentLevel = 0;
            while (proto !== BaseView && typeof proto !== 'undefined') {
                currentLevel++;
                if (proto[superFn] && this._super_level[superFn] < currentLevel) {
                    this._super_level[superFn] = currentLevel;
                    proto[superFn].apply(this, params);
                    break;
                }
                proto = proto.constructor.__super__;
            }

            if (currentLevel >= this._super_level[superFn]) {
                delete this._super_level[superFn];
            }
            return proto;
        }
    });
    /**
     * 集合类基础视图
     * @param {object} options: 配置项
     *  {object} store: CollectionStore配置项
     *  {string | DOMElement | Sizzle} appendTo: 将视图元素添加到的父元素
     *  {object} itemOption: 该视图子项的配置
    */
    var CollectionView = BaseView.extend({
        initialize: function (options) {
            this.store = new CollectionStore(options.store);
            this.items = [];

            this.$content = this.$el;
            if (options.appendTo) {
                $(options.appendTo).append(this.el);
            }

            this.listenTo(this.store, 'sync', this.render);
        },
        render: function () {
            var _this = this;
            this.$content.empty();
            while (this.items.length > 0) { this.items.pop(); }

            if (_this.store.data.length > 0) {
                var elItems = _this.store.data.map(function (item, index, list) {
                    _this.items[index] = new _this.ItemView(_this.itemOption);
                    _this.items[index].bind(item);
                    return _this.items[index].el;
                });

                _this.$content.append(elItems);
            }
            else {
                _this.items[0] = new _this.ItemView(_this.itemOption);
                _this.items[0].noData(_this.noDataModel || {});
                _this.$content.append(_this.items[0].el);
            }
        },
        setData: function (data) {
            this.store.reset(data);
            this.render();
        }
    });
    /**
     * 集合类子项基础视图
     * @param {object} options: 配置项
     *  {template} template: 模板
     *  {template} noDataTemplate: 无数据时的模板,不配置时使用默认模板
     *  {object} defaultSetting: 默认设置,render时合并于model
    */
    var ItemView = BaseView.extend({
        initialize: function (options) {
            this.template = options.template;
            this.noDataTemplate = options.noDataTemplate || this.noDataTemplate || template(noDataTemplate);
            this.defaultSetting = options.defaultSetting || {};
        },
        bind: function (model) {
            this.model = model;
            this.render();
        },
        noData: function (noDataModel) {
            this.$el.html(this.noDataTemplate(noDataModel));
        },
        render: function () {
            var model = _.extend({}, this.defaultSetting, this.model.toJSON());
            this.$el.html(this.template(model));
        },
        setTemplate: function (template) {
            this.template = template;
        },
        setDefaultSetting: function (defaultSetting) {
            this.defaultSetting = defaultSetting;
        }
    });

    //table视图
    /**
     * 表格子项(行)视图
     * @param {object} options: 配置项,同父类
    */
    var TableRowView = ItemView.extend({
        tagName: 'tr',
        noDataTemplate: template('<td colspan="{{colCount}}">' + noDataTemplate + '</td>')
    });
    var tableViewTemplate = '\
        <# if (typeof caption !== "undefined" && caption) { #> \
            <caption></caption> \
        <# } #> \
        <# if (typeof thead !== "undefined" && thead) { #> \
            <thead></thead> \
        <# } #> \
        <# if (typeof tfoot !== "undefined" && tfoot) { #> \
            <tfoot></tfoot> \
        <# } #> \
        <tbody></tbody>\
    ';
    /**
     * 表格视图
     * @param {object} options: 配置项,基于父类
     *   {Backbone.View} rowView: 子项视图,默认TableRowView
     *   {template} caption: 表格标题模板,不配置el时生效
     *   {template} thead: 表格表头模板,不配置el时生效
     *   {template} tfoot: 表格表尾模板,不配置el时生效
     *   {number} colCount: 列数,默认根据表头列数获取
     *   {boolean} sync: 初始化时是否同步数据
    */
    var TableView = CollectionView.extend({
        tagName: 'table',
        ItemView: TableRowView,
        initialize: function (options) {
            this._super_initialize(options);
            this.ItemView = options.rowView || this.ItemView;
            this.itemOption = options.row;

            if (this.$el.context === this.$el[0]) {
                this.$el.addClass('table');
                this.$el.html(template(tableViewTemplate)(options));
                if (options.caption) {
                    this.$('caption').html(options.caption());
                }
                if (options.thead) {
                    this.$('thead').html(options.thead());
                }
                if (options.tfoot) {
                    this.$('tfoot').html(options.tfoot());
                }
            }

            this.$content = this.$tbody = this.$('tbody');
            this.$caption = this.$('caption');
            this.$thead   = this.$('thead');
            this.$tfoot   = this.$('tfoot');

            this.noDataModel = {
                colCount: options.colCount || (this.$thead ? this.$thead.find('tr').eq(0).children().length : 1)
            };

            if (options.sync) {
                this.store.update();
            }
        }
    });

    //list视图
    /**
     * 列表子项(行)视图
     * @param {object} options: 配置项,同父类
    */
    var ListItemView = ItemView.extend({
        tagName: 'li'
    });
    /**
     * 列表视图
     * @param {object} options: 配置项,基于父类
     *   {Backbone.View} itemView: 子项视图,默认ListItemView
     *   {boolean} sync: 初始化时是否同步数据
    */
    var ListView = CollectionView.extend({
        tagName: 'ul',
        ItemView: ListItemView,
        initialize: function (options) {
            this.super_initialize(options);

            this.ItemView = options.itemView || this.itemView;
            this.itemOption = options.item;

            if (options.sync) {
                this.store.update();
            }
        }
    });

    //分页视图
    var pageTemplate = '\
        <div class="pageinfo pull-left">共计：{{total}}条 ,每页：{{pageSize}}条</div>\
        <div class="page-box pull-right">\
            <ul class="pagination clearfix">\
                <li class="{{pageNum <= 1 ? \'disabled\' : \'\'}}">\
                    <a href="javascript:" data-do="first">首页</a>\
                </li>\
                <li class="{{pageNum <= 1 ? \'disabled\' : \'\'}}">\
                    <a href="javascript:" data-do="prev">上一页</a>\
                </li>\
                <# _.each(pagelist, function (page, index) { #>\
                    <# if (index > 0 && (page - pagelist[index - 1]) > 1) { #>\
                        <li><span>...</span></li>\
                    <# } #>\
                    <li class="{{page === pageNum ? \'active\' : \'\'}}">\
                        <a href="javascript:" aria-label="第{{page}}页" data-do="pagechange">{{page}}</a>\
                    </li>\
                <# }) #>\
                <li class="{{pageNum >= pages ? \'disabled\' : \'\'}}">\
                    <a href="javascript:" data-do="next">下一页</a>\
                </li>\
                <li class="{{pageNum >= pages ? \'disabled\' : \'\'}}">\
                    <a href="javascript:" data-do="last">末页</a>\
                </li>\
            </ul>\
        </div>\
    ';
    var getPageList = function (pageNum, pageCount, showCount) {
        var pages = [];
        if (pageCount <= showCount) {
            var i = 1;
            while (i <= pageCount) {
                pages.push(i++);
            }
        } else {
            var radius = Math.floor(showCount / 2), upLimit = pageNum + radius, downLimit = pageNum
                - radius;
            while (upLimit > pageCount) {
                upLimit--;
                downLimit--;
            }
            while (downLimit < 1) {
                upLimit++;
                downLimit++;
            }
            if (upLimit >= pageCount) {
                upLimit = pageCount - 1;
            }
            if (downLimit <= 1) {
                downLimit = 2;
            }

            pages.push(1);
            while (downLimit <= upLimit) {
                pages.push(downLimit++);
            }
            pages.push(pageCount);
        }
        return pages;
    };
    /**
     * 分页视图
     * @param {object} options: 配置项
     *   {template} template: 模板,省略时使用pageTemplate作为默认模板
     *   {object} operationMap: 操作元素配置
    */
    var PaginationView = BaseView.extend({
        showPageCount: 3,
        pageChange: {
            next: function () {
                var page = this.pagination.getPage();
                if (page.pageNum < page.pages) { page.pageNum++; }
                this.store.query(page);
            },
            last: function () {
                var page = this.pagination.getPage();
                page.pageNum = page.pages;
                this.store.query(page);
            },
            prev: function () {
                var page = this.pagination.getPage();
                if (page.pageNum > 1) { page.pageNum--; }
                this.store.query(page);
            },
            first: function () {
                var page = this.pagination.getPage();
                page.pageNum = 1;
                this.store.query(page);
            },
            goto: function (el) {
                var pageNum = el.text();
                var page = this.pagination.getPage();
                if (/^\d+$/.test(pageNum)) {
                    pageNum = parseInt(pageNum);
                    if (pageNum >= 1 && pageNum <= page.pages) {
                        page.pageNum = pageNum;
                        this.store.query(page);
                    }
                }
            }
        },
        initialize: function (options) {
            this.template = options.template || template(pageTemplate);

            this.operationMap = _.extend({
                    next: '[data-do="next"]',
                    last: '[data-do="last"]',
                    prev: '[data-do="prev"]',
                    firs: '[data-do="first"]',
                    goto: '[data-do="pagechange"]'
                }, options.operationMap || {});
        },
        render: function () {
            var page = this.pagination.getPage();
            page.pagelist = getPageList(page.pageNum, page.pages, this.showPageCount);

            this.$el.html(this.template(page));
        },
        click: function (e) {
            var el = $(e.target);
            if (el.is(this.operationMap.next)) { this.pageChange.next.apply(this); }
            else if (el.is(this.operationMap.last)) { this.pageChange.last.apply(this); }
            else if (el.is(this.operationMap.prev)) { this.pageChange.prev.apply(this); }
            else if (el.is(this.operationMap.first)) { this.pageChange.first.apply(this); }
            else if (el.is(this.operationMap.goto)) { this.pageChange.goto.apply(this, [el]); }
        },
        bind: function (collectionview) {
            var store = collectionview.store;
            if (store.pagination) {
                this.store = store;
                this.pagination = store.pagination;
                this.delegateEvents({'click': 'click'});
                this.listenTo(this.pagination, 'pageChange', this.render);

                this.render();
            }
        }
    });

    module.exports = {
        BaseView: BaseView,
        CollectionView: CollectionView,
        CollectionItemView: ItemView,

        TableView: TableView,
        TableRowView: TableRowView,
        ListView: ListView,
        ListItemView: ListItemView,
        PaginationView: PaginationView,

        DataList: DataList,
        PageGuideModel: PageGuideModel,
        CollectionStore: CollectionStore
    };
});

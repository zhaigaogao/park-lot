/**
 * 集合类 扩展控件集
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone');
    var collectionControls = require('./collection-controls');

    /**
     * 扩展子项默认模型,提供常用字段或方法
    */
    var BaseItemSetting = {
        formatDate: function (timeObj, format) {
            var paramDate = new Date(timeObj.time);

            var o = {
                'M+': paramDate.getMonth() + 1,                     //month
                'd+': paramDate.getDate(),                          //day
                'H+': paramDate.getHours(),                         //hour
                'm+': paramDate.getMinutes(),                       //minute
                's+': paramDate.getSeconds(),                       //second
                'q+': Math.floor((paramDate.getMonth() + 3) / 3),   //quarter
                'S': paramDate.getMilliseconds()                    //millisecond
            };
            if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (paramDate.getFullYear() + '').substr(4 - RegExp.$1.length));
            for (var k in o) {
                if (new RegExp('(' + k + ')').test(format)) {
                    format = format.replace(RegExp.$1,
                        RegExp.$1.length == 1 ? o[k] :
                        ('00' + o[k]).substr(('' + o[k]).length));
                }
            }
            return format;
        },
        maxLength: function (str, len) {
            var i = 0, l = 0;
            while (l < len && i < str.length) {
                l += str.charCodeAt(i) > 128 ? 2 : 1;
                i++;
            }

            if (i < str.length - 1) {
                return str.slice(0, i).concat('...');
            }
            else {
                return str;
            }
        }
    };

    /**
     * 可勾选子项视图模型
     * @param {object} options: 配置项,基于父类
     *   {Backbone.View} parentView: 父视图实例
    */
    var CheckableItemModel = {
        events: {
            'change .checkbox': 'check'
        },
        initialize: function (options) {
            this._super_initialize(options);
            this.parentView = options.parentView;
        },
        render: function () {
            this._super_invoke('render');
            this.$checkbox = this.$('.checkbox');
        },
        check: function (e, fromParent) {
            if (!this.$checkbox) { return; }
            var checked = this.$checkbox.prop('checked');
            this.parentView.check && this.parentView.check(this, checked, fromParent);
        },
        change: function (checked, fromParent) {
            if (!this.$checkbox) { return; }
            this.$checkbox.prop('checked', checked).trigger('change', fromParent);
        }
    };
    /**
     * 可勾选集合视图模型
     * @param {object} options: 配置项,基于父类
    */
    var CheckableParentModel = {
        events: {
            'change .checkbox-all': 'checkall'
        },
        initialize: function (options) {
            this._super_initialize(options);
            this.itemOption.parentView = this;
            this.checkedList = new Backbone.Collection;

            this.$checkboxAll = this.$('.checkbox-all');
        },
        render: function () {
            this._super_invoke('render');
            this.checkedList.reset();
        },
        check: function (item, checked, fromParent) {
            if (checked) {
                this.checkedList.add(item.model);
            }
            else {
                this.checkedList.remove(item.model);
            }

            if (fromParent !== true) {
                if (this.checkedList.length === this.store.size()) {
                    this.$checkboxAll.prop('checked', true);
                }
                else {
                    this.$checkboxAll.prop('checked', false);
                }
            }
        },
        checkall: function (e) {
            var checked = this.$checkboxAll.prop('checked');
            _.each(this.items, function (item) {
                item.change(checked, true);
            });
        },
        getCheckedModels: function () {
            return this.checkedList.toJSON();
        }
    };
    //可勾选表格视图及行视图
    var CheckableRowView = collectionControls.TableRowView.extend(CheckableItemModel),
        CheckableTableView = collectionControls.TableView.extend(
            _.extend({}, CheckableParentModel, { ItemView: CheckableRowView })
        );
    //可勾选列表视图及子项视图
    var CheckableListItemView = collectionControls.ListItemView.extend(CheckableItemModel),
        CheckableListView = collectionControls.ListView.extend(
            _.extend({}, CheckableParentModel, { ItemView: CheckableListItemView })
        );

    module.exports = {
        BaseItemSetting: BaseItemSetting,
        CheckableTableView: CheckableTableView,
        CheckableRowView: CheckableRowView,
        CheckableListView: CheckableListView,
        CheckableListItemView: CheckableListItemView
    };
});

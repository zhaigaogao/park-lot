/**
 * 树视图 (基于ztree)
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone');
    var template = require('template');
    require('ztree');
    require('ztreeCheck');

    var TreeView = Backbone.View.extend({
        initialize: function () {
            this.cacheEls();
            this.treeOptions = this.treeOptions();
        },
        treeSetting: {
            view: {
                showLine: false,
                showIcon: false,
                showSelectStyle: true,
                txtSelectedEnable: true
            },
            data: {
                simpleData: {
                    enable: true,
                    rootPId: 0
                }
            }
        },
        treeOptions: function () {
            var methods = ['beforeCheck', 'beforeClick', 'beforeRemove', 'onCheck', 'onClick', 'onRemove'];
            var callback = {};
            _.each(methods, function (method) {
                if (this[method]) callback[method] = _.bind(this[method], this);
            }, this);

            var addDiyDom = this.addDiyDom || function (treeId, node) {
                var tId = node.tId;
                var $node = $('#' + tId);
                var type = node.type;
                $node.addClass('node-item node-' + type);
            };

            var setting = _.clone(this.treeSetting);
            setting.view.addDiyDom = addDiyDom;
            setting.callback = callback;

            return setting;
        },
        addOne: function (model, collection, options) {
            var pId = model.get('pId');
            if (pId == 0) {
                this.tree.addNodes(null, model.toJSON());
            } else {
                var pNode = this.getNodeById(pId);
                if (pNode) {
                    this.tree.addNodes(pNode, model.toJSON());
                }
            }
        },
        removeOne: function (model, collection, options) {
            var id = model.id;
            var node = this.getNodeById(model.id);
            this.tree.removeNode(node);
        },
        createOne: function (attrs) {
            this.collection.add(attrs);
        },
        reset: function (collection, options) {
            this.initTree(collection.toJSON());
        },
        initTree: function (data) {
            if (!_.isArray(data)) data = [];
            this.tree = $.fn.zTree.init(this.$tree, this.treeOptions, data);
            this.treeId = this.tree.setting.treeId;
        },
        cacheEls: function () {
            this.$tree = this.$el;
        },
        remove: function () {
            if (this.tree) $.fn.zTree.destroy(this.treeId);
            TreeView.__super__.remove.apply(this, arguments);
        }
    });

    module.exports = TreeView;
});

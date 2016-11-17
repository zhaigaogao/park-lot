/**
 * 菜单
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore');
    var TreeView = require('./treeview');

    var menuOptions = [
        { id: '1', pId: '1', name: '首页', top: true, icon: 'home',
            url: CONTEXT_PATH + '/index.do'},
        { id: '2', pId: '2', name: '人员管理', top: true, icon: 'user',
            url: CONTEXT_PATH + '/employee/manager.do' },
        { id: '3', pId: '3', name: '车辆管理', top: true, icon: 'th-list',
            url: CONTEXT_PATH + '/car/manager.do' },
        { id: '4', pId: '4', name: '区域管理', top: true, icon: 'road',
            url: CONTEXT_PATH + '/area/manager.do' },
        { id: '5', pId: '5', name: '出入口管理', top: true, icon: 'sort',
            url: CONTEXT_PATH + '/gate/manager.do' },
        { id: '6', pId: '6', name: '预约管理', top: true, icon: 'edit',
            url: CONTEXT_PATH + '/order/manager.do' },
        { id: '7', pId: '7', name: '收费策略', top: true, icon: 'usd',
            url: CONTEXT_PATH + '/charge/manager.do' },
        { id: '8', pId: '8', name: '停车记录', top: true, icon: 'stats',
            url: CONTEXT_PATH + '/parking/manager.do' }
    ];
    var currentUrl = location.url;

    var MenuView = TreeView.extend({
        addDiyDom: function (treeId, node) {
            var tId = node.tId;
            var $node = $('#' + tId);
            var eidtBtn = $('<span class="glyphicon glyphicon-' + node.icon + '"></span>');
            $node.find('.node_name').before(eidtBtn);
            if (currentUrl == node.url) {
                this.getZTreeObj(treeId).selectNode(node);
            }
        }
    });

    var run = function () {
        var menu = new MenuView({
            el: '#menu'
        });
        menu.initTree(menuOptions);
    };

    module.exports = {
        run: run
    };
});

/**
 * 停车记录
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
    template = require('template');
    var alert = require('component/Alert');
    var confirm = require('component/Confirm');
    require('jquery-util');
    require('bootstrap-datetimepicker');
    require('bootstrap-datetimepicker-zh-CN');

    var collectionControls = require('component/collection-controls'),
        collectionExtension = require('component/collection-extension'),
        TableView = collectionControls.TableView,
        TableRowView = collectionControls.TableRowView,
        PaginationView = collectionControls.PaginationView,
        BaseItemSetting = collectionExtension.BaseItemSetting;

    var urls = {
        'parkingrecord': ''
    };

    var testRecords = [{
        carNo: '陕V10086',
        area: 'A区',
        user: '',
        startDate: '2016-10-30 13:30',
        endDate: '2016-10-30 16:30',
        order: '',
        charge: '10.0',
        ingate: '1号口',
        outgate: '3号口'
    }, {
        carNo: '沪B21126',
        area: 'A区',
        user: '',
        startDate: '2016-10-30 14:00',
        endDate: '2016-10-30 15:00',
        order: '',
        charge: '5.0',
        ingate: '2号口',
        outgate: '1号口'
    }, {
        carNo: '苏C32233',
        area: 'B区',
        user: '',
        startDate: '2016-10-30 15:10',
        endDate: '2016-10-30 17:30',
        order: '',
        charge: '10.0',
        ingate: '1号口',
        outgate: '2号口'
    }, {
        carNo: '京D45678',
        area: 'A区',
        user: '',
        startDate: '2016-10-30 15:30',
        endDate: '2016-10-30 16:30',
        order: '',
        charge: '5.0',
        ingate: '1号口',
        outgate: '1号口'
    }, {
        carNo: '陕V10086',
        area: 'A区',
        user: '',
        startDate: '2016-10-31 13:30',
        endDate: '2016-10-31 16:30',
        order: '',
        charge: '10.0',
        ingate: '1号口',
        outgate: '3号口'
    }];

    var run = function () {
        var tableView = new TableView({
            el: '#parking-table',
            store: {
                url: urls.parkingrecord,
                pagination: {
                    enable: true,
                    extractPage: function (res) { return res; }
                },
                checkSuccess: function (res) { return true; }
            },
            row: {
                template: template($('#tmpl-row').html())
            },
            //sync: true
        });
        tableView.setData(testRecords);

        var defaultSetting = {
                format: 'yyyy-mm-dd',
                language: 'zh-CN',
                minView: 2
            };

        var $startDate = $('#startDate');
        var $endDate = $('#endDate');

        $startDate.datetimepicker(defaultSetting);
        $endDate.datetimepicker(defaultSetting);
    };

    module.exports = {
        run: run
    };
});

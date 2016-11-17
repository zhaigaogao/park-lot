/**
 * 首页
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone');
    var template = require('template');
    require('echarts');

    var infoRender = template($('#tmpl-info').html());
    var formatNumber = function (number) {
        var textArr = [];
        while (number > 1000) {
            var n = number % 1000;
            textArr.unshift(n.toString());
            number = (number - n) / 1000;
        }
        textArr.unshift(number);

        return textArr.join(',');
    };
    //window.formatNumber = formatNumber;

    var colors = {
        used: '#c23531',
        order: '#d99c2b',
        unused: '#61a0a8'
    };

    var IndexModel = Backbone.Model.extend({
        defaults: {
            innerUsed: 0,
            innerUnused: 0,
            outerOrder: 0,
            outerOrderUsed: 0,
            outerUsed: 0,
            outerUnused: 0
        }
    });
    var IndexView = Backbone.View.extend({
        initialize: function (options) {
            this.cacheEls();
            this.initCharts();

            this.listenTo(this.model, 'change', this.update);
        },
        cacheEls: function () {
            this.$info = this.$('.parking-info');
            this.$innerChart = this.$('.chart-inner');
            this.$outerChart = this.$('.chart-outer');
        },
        initCharts: function () {
            this.innerChart = echarts.init(this.$innerChart[0]);
            this.outerChart = echarts.init(this.$outerChart[0]);
        },
        update: function (model) {
            var data = model.toJSON();
            this.updateInfo(data);
            this.updateCharts(data);
        },
        updateInfo: function (data) {
            this.$info.html(infoRender({
                model: data,
                formatNumber: formatNumber,
                colors: colors
            }));
        },
        updateCharts: function (data) {
            var innerSum = data.innerUsed + data.innerUnused;
            var innerOption = {
                title: {
                    textAlign: 'right',
                    text: '员工车位数量',
                    subtext: data.innerUnused + '/' + innerSum,
                    right: 0,
                    top: '10%'
                },
                tooltip: {
                    show: false
                },
                color: [colors.used, colors.unused],
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['已用车位','空位']
                },
                series: [{
                    name: '车位数量',
                    type: 'pie',
                    radius: ['50%', '65%'],
                    center: ['25%', '60%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            formatter: '{b}\n{c}',
                            textStyle: {
                                fontSize: '16',
                                fontWeight: 'normal'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        { value: data.innerUsed, name: '已用车位' },
                        { value: data.innerUnused, name: '空位' }
                    ]
                }]
            };

            var outerSum = data.outerOrder + data.outerOrderUsed + data.outerUsed + data.outerUnused;
            var outerOption = {
                title: {
                    textAlign: 'right',
                    text: '外来车辆车位',
                    subtext: data.outerUnused + '/' + outerSum,
                    right: 0,
                    top: '10%'
                },
                tooltip: {
                    show: false
                },
                color: [colors.used, colors.order, colors.unused],
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['已用车位', '预约车位','空位']
                },
                series: [{
                    name: '车位数量',
                    type: 'pie',
                    radius: ['50%', '65%'],
                    center: ['25%', '60%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            formatter: '{b}\n{c}',
                            textStyle: {
                                fontSize: '16',
                                fontWeight: 'normal'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        { value: data.outerOrderUsed + data.outerUsed, name: '已用车位' },
                        { value: data.outerOrder, name: '预约车位' },
                        { value: data.outerUnused, name: '空位' }
                    ]
                }]
            };

            this.innerChart.setOption(innerOption);
            this.outerChart.setOption(outerOption);
        }
    });

    var run = function () {
        var indexModel = new IndexModel;
        var indexView = new IndexView({
            el: '#content',
            model: indexModel
        });
        //test
        indexModel.set({
            innerUsed: 33,
            innerUnused: 45,
            outerOrder: 19,
            outerOrderUsed: 16,
            outerUsed: 37,
            outerUnused: 39
        });
    };

    module.exports = {
        run: run
    };
});

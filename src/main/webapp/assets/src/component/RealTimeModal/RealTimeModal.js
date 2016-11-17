/**
 * 实时进出信息弹出窗
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
define(function (require, exports, module) {
    var $ = require('jquery');
    var Backbone = require('backbone');
    var template = require('template');
    require('bootstrap');

    var tpl = require('./RealTimeModal.tpl');
    var modalRender = template(tpl);

    var RealTimeModel = Backbone.Model.extend({
        defaults: {
            carNumber: '',
            carType: '',
            carUserName: '',
            carUserDept: '',
            carUserPhone: '',
            visitUserName: '',
            visitUserPhone: '',
            visitUserComp: '',
            regUserName: '',
            regUserDept: '',
            regUserPhone: '',
            regInTime: '',
            regOutTime: '',
            retMsg: ''
        },
        sync: function () {
            $.ajax({
                url: CONTEXT_PATH + '/carPark/findLatestCarInfo.do?clientId=1',
                dataType: 'json',
                type: 'post',
                context: this
            }).done(function (resp) {
                if (resp.success) {
                    this.trigger('sync');
                    this.set(resp.model);
                } else {
                    //do something
                }
            }).fail(function () {
                //do something
            });
        }
    });
    var RealTimeModal = Backbone.View.extend({
        template: modalRender,
        className: 'modal fade',
        model: new RealTimeModel,
        initialize: function () {
            this.$el.on('hidden.bs.modal', _.bind(this.onHidden, this));
            this.listenTo(this.model, 'change', _.bind(this.render, this));
            this.listenTo(this.model, 'sync', _.bind(this.refresh, this));
        },
        render: function () {
            var data = this.model.toJSON();
            var markup = this.template({
                model: data
            });

            this.$el.html(markup);
            return this;
        },
        playRefresh: function () {
            if (this.play) return;

            this.play = true;
            this.model.sync();
        },
        pauseRefresh: function () {
            this.play = false;
        },
        refresh: function () {
            var _this = this;
            setTimeout(function () {
                if (_this.play) {
                    _this.model.sync();
                }
            }, 1000);
        },
        show: function () {
            this.$el.modal('show');
            this.playRefresh();
        },
        onHidden: function () {
            this.pauseRefresh();
        },
        hide: function () {
            this.$el.modal('hide');
        }
    });

    module.exports = RealTimeModal;
});

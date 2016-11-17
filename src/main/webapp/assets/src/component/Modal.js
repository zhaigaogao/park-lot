/**
 * File: Modal.js
 * Author: kimvin
 * Date: 2016-04-22
 * Description: 基于 bootstrap 的 modal 实现 Modal View
 * Copyright: 版权所有 (C) 2014 中国移动 杭州研发中心.
 */

define(function (require) {
    var Backbone = require('backbone');
    var template = require('template');
    require('bootstrap');
    var modalRender = template('<div class="alert-modal-dialog modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title">{{title}}</h4></div><div class="modal-body">{{content}}</div><div class="modal-footer"><button type="button" class="btn btn-primary" data-dismiss="modal">确定</button></div></div></div>');
    var Modal = Backbone.View.extend({
        className: 'modal',
        template: modalRender,
        /*
         * 初始化模型对象，添加元素至页面
         */
        initialize: function () {
            this.$el.appendTo(document.body);
            this.$el.on('hidden.bs.modal', _.bind(this.onHidden, this));
            this.model = new Backbone.Model({
                title: '对话框',
                content: ''
            });
        },
        render: function (data) {
            this.model.set(data, {
                silent: true
            });
            data = this.model.toJSON();
            var markup = this.template(data);
            this.$el.html(markup);
            return this;
        },
        add: function () {
            Modal.modals.push(this);
            if (!Modal.starting) {
                Modal.startQueue();
            }
            return this;
        },
        show: function () {
            this.$el.modal('show');
        },
        hide: function () {
            this.$el.modal('hide');
        },
        onHidden: function () {
            this.reset();
            Modal.next();
        },
        reset: function () {
            this.remove();
        }
    }, {
        modals: [],
        starting: false,
        startQueue: function () {
            if (Modal.modals.length > 0) {
                var modal = Modal.modals.shift();
                modal.show();
                Modal.starting = true;
            } else {
                Modal.starting = false;
            }
        },
        next: function () {
            if (Modal.modals.length == 0) {
                Modal.starting = false;
            } else {
                var modal = Modal.modals.shift();
                modal.show();
            }
        }
    });

    return Modal;
});

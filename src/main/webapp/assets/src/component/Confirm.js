/**
 * File: Comfirm.js
 * Author: kimvin
 * Date: 2016-04-22
 * Description: 模拟 confirm
 * Copyright: 版权所有 (C) 2014 中国移动 杭州研发中心.
 */
define(function(require) {
    var Backbone = require('backbone');
    var Modal = require('component/Modal');
    var template = require('component/util/template');

    var modalRender = template('<div class="confirm-modal-dialog modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title">{{title}}</h4></div><div class="modal-body">{{content}}</div><div class="modal-footer"><button type="button" class="btn btn-primary" data-do="ok">确定</button> <button type="button" class="btn btn-default" data-do="cancel">取消</button></div></div></div>');
    /*
     * 确认框
     *
     * 替代浏览器默认的 confirm
     */
    var ConfirmModal = Modal.extend({
        template: modalRender,
        className: 'confirm-modal modal fade',
        attributes: {
            'data-manual': true
        },
        events: {
            'click [data-do="ok"]': 'ok',
            'click [data-do="cancel"]': 'cancel'
        },
        initialize: function() {
            // 调用父类方法
            ConfirmModal.__super__.initialize.apply(this, arguments);
            this.model.set('title', '确认');
        },
        render: function() {
            ConfirmModal.__super__.render.apply(this, arguments);
            return this;
        },
        ok: function() {
            if (_.isFunction(this.okCallback)) {
                this.okCallback.call();
                this.okCallback = null;
            }
            this.hide();
        },
        cancel: function() {
            if (_.isFunction(this.cancelCallback)) {
                this.cancelCallback.call();
                this.cancelCallback = null;
            }
            this.hide();
        },
        reset: function() {
            this.clearContent();
        },
        setCallback: function(ok, cancel) {
            if (_.isFunction(ok)) {
                this.okCallback = ok;
            }
            if (_.isFunction(cancel)) {
                this.cancelCallback = cancel;
            }
            return this;
        },
        clearContent: function() {
            this.$el.empty();
        }
    });

    function proxy(content, ok, cancel) {
        var modal = new ConfirmModal;
        modal.render({
            title: '确认',
            content: content
        }).setCallback(ok, cancel).add();
        return modal;
    }
    return proxy;
});
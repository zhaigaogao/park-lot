/**
 * File: Alert.js
 * Author: kimvin
 * Date: 2016-04-22
 * Description: 模拟 alert
 * Copyright: 版权所有 (C) 2014 中国移动 杭州研发中心.
 */
define(function(require) {
    var Backbone = require('backbone');
    var Modal = require('component/Modal');
    /*
     * 警告框
     *
     * 替代浏览器默认的 alert，可设置定时关闭
     */
    var AlertModal = Modal.extend({
        timer: null,
        className: 'alert-modal modal fade',
        attributes: {
            'data-manual': true
        },
        interval: 0,
        initialize: function() {
            // 调用父类方法
            AlertModal.__super__.initialize.apply(this, arguments);

            this._loop = _.bind(this.loop, this);
            this.model.set('title', '提示');
            this.listenTo(this.model, 'change:title', this.syncTitle);
            this.listenTo(this.model, 'change:content', this.syncContent);
        },
        render: function() {
            AlertModal.__super__.render.apply(this, arguments);
            this._cacheEls();
            return this;
        },
        syncTitle: function(model, value, options) {
            this.$title.html(value);
        },
        syncContent: function(model, value, options) {
            this.$content.html(value);
        },
        delay: function(interval) {
            interval = parseInt(interval, 10) || 0;
            if (interval > 0) {
                this.clearTimeout();
                this.interval = interval;
                this.loop();
            }
            return this;
        },
        loop: function() {
            if (this.interval <= 0) {
                this.hide();
            } else {
                this.model.set('title', '提示（' + this.interval + 's 后关闭）');
                this.interval--;
                this.timer = setTimeout(this._loop, 1000);
            }
        },
        reset: function() {
            this.clearTimeout();
            this.doCallback();
            this.remove();
        },
        setCallback: function(callback) {
            if (_.isFunction(callback)) {
                this.callback = callback;
            }
            return this;
        },
        doCallback: function() {
            if (_.isFunction(this.callback)) {
                this.callback.call();
                this.callback = null;
            }
        },
        clearTimeout: function() {
            this.interval = 0;
            if (this.timer != null) {
                clearTimeout(this.timer);
                this.timer = null;
            }
        },
        _cacheEls: function() {
            this.$title = this.$('.modal-title');
            this.$content = this.$('.modal-body');
        }
    });

    function proxy(content, callback) {
        var modal = new AlertModal;
        modal.render({
            content: content
        }).setCallback(callback).add();
        return modal;
    }
    return proxy;
});
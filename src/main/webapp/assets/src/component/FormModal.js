define(function (require) {
    var $ = require('jquery');
    var Backbone = require('backbone');
    var template = require('template');
    require('bootstrap');
    require('jquery-validate');

    jQuery.validator.addMethod('mobile', function (value, element) {
        var patt = /^[1][3,4,5,7,8][0-9]{9}$/;
        return this.optional(element) || patt.test(value);
    }, '手机号码格式不正确');

    var FormModal = Backbone.View.extend({
        template: template(''),
        className: 'modal fade',
        id: function () {
            return this.cid;
        },
        events: {
            'click [data-do="submit"]': 'submit'
        },
        initialize: function () {
            this.$el.on('hidden.bs.modal', _.bind(this.remove, this));
        },
        render: function () {
            var data = this.model.toJSON();
            data._isNew = this.model.isNew();
            var markup = this.template({
                model: data
            });

            this.$el.html(markup);
            this.cacheEls();
            return this;
        },
        show: function () {
            this.$el.modal('show');
        },
        hide: function () {
            this.$el.modal('hide');
        },
        submit: function () {
            // override
        },
        cacheEls: function () {
            this.$form = this.$('form');

            if (this.validate) {
                this.$form.validate(this.validate);
            }
        }
    });

    return FormModal;
});

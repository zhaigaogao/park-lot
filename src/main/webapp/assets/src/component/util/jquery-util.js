define(function(require) {
    var $ = require('jquery');
    $.fn.extend({
        serializeObject: function() {
            var data = {};
            if (this.length > 0) {
                var element = this[0];
                var fields = $(element).serializeArray();
                var data = {};
                $.each(fields, function(index, field) {
                    var name = field.name;
                    var value = field.value;
                    if (data[name]) {
                        data[name] = [value].concat(data[name]);
                    } else {
                        data[name] = value;
                    }
                });
            }
            return data;
        }
    });
});
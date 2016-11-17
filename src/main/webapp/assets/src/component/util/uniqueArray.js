define(function(require, exports, module) {
    var _ = require('underscore');
    /**
     * 去除数组的重复元素
     *
     * @param {array} list 待遍历的数组
     * @return 不含重复元素的数组
     */
    function uniqueArray(list) {
        list = list || [];
        var temp = {},
            data = [],
            num = list.length;

        _.each(list, function(element, index) {
            typeof(temp[element]) === 'undefined' ? temp[element] = 1: '';
        });

        _.each(temp, function(value, key) {
            data.push(key);
        });
        return data;
    }

    exports.uniqueArray = uniqueArray;
});
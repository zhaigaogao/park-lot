define(function(require, exports, module) {
    var _ = require('underscore');
    /**
     * 按层级遍历数组元素
     * 数组元素具有 children 属性且为数组类型，则对其进行遍历
     *
     * @param {array} list 待遍历的数组
     * @param {object} parent 上级对象
     * @param {function} func 遍历函数
     * @return void
     */
    var walkListLevel = 0;

    function walkList(list, parent, func) {
        if (_.isArray(list) && _.isFunction(func)) {
            walkListLevel++;
            _.each(list, function(element, index, list) {
                // 如果 element 是一个对象
                // 为其添加一个 __level 属性，表示当前元素所在的层级
                // if (typeof element === 'object')
                //     element.__level = walkListLevel;

                func(element, parent, walkListLevel);

                if (element.children && _.isArray(element.children)) {
                    // 如果有下一级，则当前元素作为父级元素
                    walkList(element.children, element, func);
                }
            });
            walkListLevel--;
        }
    }

    return walkList;
});
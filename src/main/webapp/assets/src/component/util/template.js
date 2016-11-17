define(function(require, exports, module) {
	var options = {
        evaluate: /<#([\s\S]+?)#>/g,
        interpolate: /\{\{\{([\s\S]+?)\}\}\}/g,
        escape: /\{\{([^\}]+?)\}\}(?!\})/g
    };
    var empty = _.template('');

    /*
     * 模板编译方法
     * 
     * @param {string} text 模板
     * @param {function} 编译后的模板函数
     */
    function template(text) {
        return _.isString(text) ? _.template(text, options) : empty;
    }

    _.extend(template, {
        empty: empty,
    	options: options
    });

    return template;
});
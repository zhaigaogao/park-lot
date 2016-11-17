//stroage辅助模块
//由于当前浏览器对stroage的实现都只能保存字符串类型
//便于存取时自动转换类型
define(function (require, exports, module) {
    //IE下string缺少startsWith
    if (!'a'.startsWith) {
        String.prototype.startsWith = function (str) {
            return (this.match('^' + str) == str);
        };
    }

    //string number boolean function object null undefined
    var toSetValue = function (val) {
        var newVal = '';
        var valType = typeof val;
        switch (valType) {
            case 'string':
            case 'number':
            case 'boolean':
                newVal = valType.toUpperCase() + ':' + val.toString(); break;
            case 'object':
                newVal = 'JSON:' + JSON.stringify(val); break;
            default:
                newVal = 'NULL:NULL'; break;
        }

        return newVal;
    };
    var toGetValue = function (val) {
        if (typeof val !== 'string') {
            return val;
        }

        var realVal;
        if (val.startsWith('STRING:')) {
            realVal = val.substring(7);
        }
        else if (val.startsWith('NUMBER:')) {
            realVal = val.substring(7);
            if (realVal.indexOf('.') >= 0) { realVal = parseFloat(realVal); }
            else { realVal = parseInt(realVal); }
        }
        else if (val.startsWith('BOOLEAN:')) {
            realVal = val.substring(8);
            realVal = realVal.toUpperCase() === 'TRUE';
        }
        else if (val.startsWith('JSON:')) {
            realVal = val.substring(5);
            realVal = JSON.parse(realVal);
        }
        else {
            realVal = null;
        }
        return realVal;
    };

    var StorageHelper = function (storage) {
        this.storage = storage;
    };
    StorageHelper.prototype = {
        setItem: function (key, value) {
            this.storage.setItem(key, toSetValue(value));
        },
        getItem: function (key) {
            return toGetValue(this.storage.getItem(key));
        },
        removeItem: function (key) {
            this.storage.removeItem(key);
        },
        clear: function () {
            this.storage.clear();
        },
        get length () {
            return this.storage.length;
        }
    };

    module.exports = {
        session: new StorageHelper(sessionStorage),
        local: new StorageHelper(localStorage)
    };
});
define(function(require, exports, module) {
    /**
     * Set a cookie.
     * 
     * The following functions are from Cookie.js class in TinyMCE 3, Moxiecode, used under LGPL.
     * setCookie(), getCookie(), removeCookie()
     *
     * The 'expires' arg can be either a JS Date() object set to the expiration date (back-compat)
     * or the number of seconds until expiration
     * 
     * @param {string} name cookie 的名称
     * @param {string} value cookie 的值
     * @param {Date|Number} expires cookie 的失效时间，可以是一个 JS Date() 对象或秒数
     * @param {string} path cookie 的路径
     * @param {string} domain cookie 所属的域
     * @param {boolean} secure cookie 是否只在 HTTPS 连接下才从客户端传到服务器端
     * @void
     */
    function setCookie(name, value, expires, path, domain, secure) {
        var d = new Date();

        if (typeof(expires) === 'object' && expires.toGMTString) {
            expires = expires.toGMTString();
        } else if (parseInt(expires, 10)) {
            d.setTime(d.getTime() + (parseInt(expires, 10) * 1000)); // time must be in miliseconds
            expires = d.toGMTString();
        } else {
            expires = '';
        }

        document.cookie = name + '=' + encodeURIComponent(value) +
            (expires ? '; expires=' + expires : '') +
            (path ? '; path=' + path : '') +
            (domain ? '; domain=' + domain : '') +
            (secure ? '; secure' : '');
    }
    
    /**
     * Get a cookie.
     *
     * @param {string} name cookie 的名称
     * @return {string} cookie 值
     */
    function getCookie(name) {
        var e, b,
            cookie = document.cookie,
            p = name + '=';

        if (!cookie) {
            return;
        }

        b = cookie.indexOf('; ' + p);

        if (b === -1) {
            b = cookie.indexOf(p);

            if (b !== 0) {
                return null;
            }
        } else {
            b += 2;
        }

        e = cookie.indexOf(';', b);

        if (e === -1) {
            e = cookie.length;
        }

        return decodeURIComponent(cookie.substring(b + p.length, e));
    }
    /**
     * Remove a cookie.
     *
     * This is done by setting it to an empty value and setting the expiration time in the past. 
     * 
     * @param {string} name cookie 的名称
     * @param {string} path cookie 的路径
     * @param {string} domain cookie 所属的域
     * @param {boolean} secure cookie 是否只在 HTTPS 连接下才从客户端传到服务器端
     * @void
     */
    function removeCookie(name, path, domain, secure) {
        setCookie(name, '', -1000, path, domain, secure);
    }

    module.exports = {
        get: getCookie,
        set: setCookie,
        remove: removeCookie
    };

});
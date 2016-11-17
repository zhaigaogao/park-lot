define(['underscore'], function(require, exports, module) {
	var _ = require('underscore');
	/**
	 * 解析 key=val 组合的字符串
	 *
	 * 使用 parseQueryString(queryString, '&', '=', {});
	 *
	 * @param {string} qs 待解析的字符串
	 * @param {string} seq 分隔字符，默认为 &
	 * @param {string} eq 键值之间的字符，默认为 =
	 * @param {object} options 选项
	 * @return {object} 解析后生成的对象
	 */
	function parseQueryString(qs, sep, eq, options) {
		sep = sep || '&';
		eq = eq || '=';

		var obj = {};
		if (!_.isString(qs) || qs.length === 0) {
			return obj;
		}

		var regexp = /\+/g;
		qs = qs.split(sep);

		var maxKeys = 1000;
		if (options && _.isNumber(options.maxKeys)) {
			maxKeys = options.maxKeys;
		}

		var len = qs.length;
		// maxKeys <= 0 则不限制键的个数
		if (maxKeys > 0 && len > maxKeys) {
			len = maxKeys;
		}

		var decode = decodeURIComponent;
		if (options && _.isFunction(options.decodeURIComponent)) {
			decode = options.decodeURIComponent;
		}

		for (var i = 0; i < len; ++i) {
			var x = qs[i].replace(regexp, '%20'),
				idx = x.indexOf(eq),
				kstr, vstr, k, v;

			if (idx >= 0) {
				kstr = x.substr(0, idx);
				vstr = x.substr(idx + 1);
			} else {
				kstr = x;
				vstr = '';
			}

			try {
				k = decode(kstr);
				v = decode(vstr);
			} catch (e) {
				k = decodeURIComponent(kstr, true);
				v = decodeURIComponent(vstr, true);
			}

			if (!_.has(obj, k)) {
				obj[k] = v;
			} else if (_.isArray(obj[k])) {
				obj[k].push(v);
			} else {
				obj[k] = [obj[k], v];
			}
		}
		return obj;
	}

	exports.parseQueryString = parseQueryString;
});

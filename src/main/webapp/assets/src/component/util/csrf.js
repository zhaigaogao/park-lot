define(function(require, exports, module) {
	var $ = require('jquery');
	/**
	 * 防止跨域攻击
	 * 
	 * 需要全局变量：CLIENT_DATE，SERVER_DATE，REQUEST_MARK
	 */
	function getTokenTime() {
		var str = '',
			now = new Date(),
			time = new Date(),
			nowTime = now.getTime();
		var deltaT = CLIENT_DATE.getTime() - SERVER_DATE.getTime();
		var millisec = nowTime - parseInt(deltaT);

		time.setTime(millisec);
		var y = time.getFullYear().toString();
		var M = (time.getMonth() + 1) > 9 ? (time.getMonth() + 1).toString() : '0' + (time.getMonth() + 1);
		var d = time.getDate() > 9 ? time.getDate().toString() : '0' + time.getDate();
		var h = time.getHours() > 9 ? time.getHours().toString() : '0' + time.getHours();
		var m = time.getMinutes() > 9 ? time.getMinutes().toString() : '0' + time.getMinutes();
		var s = time.getSeconds() > 9 ? time.getSeconds().toString() : '0' + time.getSeconds();

		str = y + M + d + h + m + s;

		return REQUEST_MARK + '-' + str;
	}

	$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
		var _token = getTokenTime();
		if (options.dataType === 'json') {
			if (options.type.toLowerCase() == 'post') {
				if (options['data']) {
					options['data'] = options['data'] + '&tokenTime=' + _token;
				} else {
					options['data'] = 'tokenTime=' + _token;
				}
			} else if (options.url.indexOf('tokenTime') < 0) {
				if (options.url.indexOf('?') > 0) {
					options.url += '&tokenTime=' + _token;
				} else {
					options.url += '?tokenTime=' + _token;
				}
			}
		}
	});
	exports.getTokenTime = getTokenTime;
})
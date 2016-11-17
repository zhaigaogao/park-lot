/**
 * 通用部分
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore');

    var RealTimeModal = require('./RealTimeModal/RealTimeModal');

    var realtimeModal;

    $(function () {
        $('#realtime').on('click', function () {
            if (!realtimeModal) {
                realtimeModal = new RealTimeModal();
                realtimeModal.render().$el.appendTo(document.body);
            }
            realtimeModal.show();
        });

        $('#exit').on('click', function () {
            location.href = CONTEXT_PATH + '/login.do';
        });
    });
});

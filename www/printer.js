var exec = require('cordova/exec');

var GEDI = function () {};

GEDI.print = function (content) {
    exec(null, null, 'Printer', 'print', [content]);
};

module.exports = GEDI;

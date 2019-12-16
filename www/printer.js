var exec = require('cordova/exec');

exports.print = function ( content, completeCallback, errorCallback ) {
	exec( completeCallback, errorCallback, 'Printer', 'print2', [content] );
};

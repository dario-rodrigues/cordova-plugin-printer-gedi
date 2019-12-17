var exec = require('cordova/exec');

exports.print = function ( content ) {
	return exec( null, null, 'Printer', 'print', [content] );
};

var exec = require('cordova/exec');

exports.print = function ( content ) {
	exec( null, null, 'Printer', 'print', [content] );
};

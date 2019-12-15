var exec = require('cordova/exec');

module.exports = {
	print: function ( content ) 
	{
		exec( null, null, 'Printer', 'print', [content] );
	};
}

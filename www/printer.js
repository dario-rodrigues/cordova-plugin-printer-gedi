var exec = require('cordova/exec');

var Printer = function() {
};

Printer.print = function(json, completeCallback, errorCallback) {
	alert(json);
    exec(completeCallback, errorCallback, "Printer", "print2", [json]);    
};

module.exports = Printer;

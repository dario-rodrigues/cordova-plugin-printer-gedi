var exec = require('cordova/exec');

var Printer = function() {
};

Printer.print = function(json, completeCallback, errorCallback) {
    alert(exec(completeCallback, errorCallback, "Printer", "print", [json]));  
    alert(completeCallback);	
};

module.exports = Printer;

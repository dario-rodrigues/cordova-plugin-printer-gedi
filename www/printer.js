/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');
   
var Printer = function() {
};

Printer.shrinkView = function(shrink, success) {
    if (shrink !== null && shrink !== undefined) {
        exec(success, null, "Printer", "shrinkView", [shrink]);
    } else {
        exec(success, null, "Printer", "shrinkView", []);
    }
};

Printer.hideFormAccessoryBar = function(hide, success) {
    if (hide !== null && hide !== undefined){
        exec(success, null, "Printer", "hideFormAccessoryBar", [hide]);
    } else {
        exec(success, null, "Printer", "hideFormAccessoryBar", []);
    }
};

Printer.disableScrollingInShrinkView = function(disable, success) {
    if (disable !== null && disable !== undefined) {
        exec(success, null, "Printer", "disableScrollingInShrinkView", [disable]);
    } else {
        exec(success, null, "Printer", "disableScrollingInShrinkView", []);
    }
};

Printer.fireOnShow = function() {
    Printer.isVisible = true;
    cordova.fireWindowEvent('keyboardDidShow');

    if(Printer.onshow) {
	Printer.onshow();
    }
};

Printer.fireOnHide = function() {
    Printer.isVisible = false;
    cordova.fireWindowEvent('keyboardDidHide');

    if(Printer.onhide) {
	Printer.onhide();
    }
};

Printer.fireOnHiding = function() {
    // Automatic scroll to the top of the page
    // to prevent quirks when using position:fixed elements
    // inside WebKit browsers (iOS specifically).
    // See CB-6444 for context.
    if (Printer.automaticScrollToTopOnHiding) {
        document.body.scrollLeft = 0;
    }

    cordova.fireWindowEvent('keyboardWillHide');

    if(Printer.onhiding) {
	Printer.onhiding();
    }
};

Printer.fireOnShowing = function() {
    cordova.fireWindowEvent('keyboardWillShow');

    if(Printer.onshowing) {
	Printer.onshowing();
    }
};

Printer.show = function() {
    exec(null, null, "Printer", "show", []);
};

Printer.hide = function() {
    exec(null, null, "Printer", "hide", []);
};

Printer.print = function(json, success) {
    exec(success, null, "Printer", "print", [json]);
};

Printer.isVisible = false;
Printer.automaticScrollToTopOnHiding = false;

module.exports = Printer;

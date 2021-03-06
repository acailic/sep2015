(function() {
 	"use strict";
	
 	angular
 		.module('acquirer.modal')
 		.controller('ModalController', ModalController);

 	ModalController.$inject = ['$mdDialog'];
 	function ModalController($mdDialog  ) {

 		var mdc = this;
 		 
		mdc.hide = function() {
		    $mdDialog.hide();
		};
		mdc.cancel = function() {
		    $mdDialog.cancel();
		};
		mdc.answer = function(answer) {
		    $mdDialog.hide(answer);
		};
	}
 })();

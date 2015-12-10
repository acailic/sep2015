 (function() {
 	"use strict";
	
 	angular
 		.module('merchant.sale')
 		.controller('SaleController', SaleController);

 	SaleController.$inject = ['$mdDialog', '$mdMedia'];
 	function SaleController($mdDialog, $mdMedia) {
 		var slc = this;

 		slc.showModalPayment = function(ev) {
		    $mdDialog.show({
		      templateUrl: 'app/components/modal/modalPayment.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      fullscreen: $mdMedia('sm') && isc.customFullscreen
		    });
		  };

 	}
 })();

 (function() {
 	"use strict";
	
 	angular
 		.module('merchant.sale')
 		.controller('SaleController', SaleController);

 	SaleController.$inject = ['$mdDialog', '$mdMedia', 'SharedObject'];
 	function SaleController($mdDialog, $mdMedia, SharedObject) {
 		var slc = this;
 		var object = SharedObject.getInsurance();
 		//alert(object);
 		slc.object= object;
 			

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

 (function() {
 	"use strict";
	
 	angular
 		.module('merchant.insurance')
 		.controller('InsuranceController', InsuranceController);

 	InsuranceController.$inject = ['$mdDialog', '$mdMedia'];
 	function InsuranceController($mdDialog, $mdMedia) {
 		var isc = this;

 		isc.showModalTravel = function(ev) {
		    $mdDialog.show({
		      controller: ModalController,
		      controllerAs: 'mdc',
		      templateUrl: 'app/components/modal/modalTravel.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: $mdMedia('sm') && isc.customFullscreen
		    });
		  };

		  isc.showModalHome = function(ev) {
		    $mdDialog.show({
		      controller: ModalController,
		      controllerAs: 'mdc',
		      templateUrl: 'app/components/modal/modalHome.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: $mdMedia('sm') && isc.customFullscreen
		    });
		  };

		  isc.showModalVehicle = function(ev) {
		    $mdDialog.show({
		      controller: ModalController,
		      controllerAs: 'mdc',
		      templateUrl: 'app/components/modal/modalVehicle.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: $mdMedia('sm') && isc.customFullscreen
		    });
		  };
 	}
 })();

function ModalController($mdDialog) {
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

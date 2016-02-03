 (function() {
 	"use strict";
	
 	angular
 		.module('merchant.insurance')
 		.controller('InsuranceController', InsuranceController);

 	InsuranceController.$inject = ['$mdDialog', '$mdMedia','Insurance'];
 	function InsuranceController($mdDialog, $mdMedia, Insurance) {
 		var isc = this;

 		isc.create = function () {
			Insurance.create(isc.insurance);
		};
 	}
 })();

 (function() {
 	"use strict";
	
 	angular
 		.module('merchant.sale')
 		.controller('SaleController', SaleController);

 	SaleController.$inject = ['$mdDialog', '$mdMedia', 'SharedObject', '$timeout'];
 	function SaleController($mdDialog, $mdMedia, SharedObject, $timeout) {
 		var slc = this;
 		//slc.insurance = SharedObject.getInsurance();

 		slc.data_init = {};
 		slc.data_init.travellers = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
 		slc.data_init.human_ages = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
 		slc.data_init.sports = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
 		slc.data_init.cities = [{id:1, name:"Ns"}, {id:2, name:"Bg"}, {id:3, name:"Pg"}];
		slc.data_init.max_values = [{id:1, name:"10.000"}, {id:2, name:"20.0000"}, {id:3, name:"30.0000"}];
		slc.data_init.regions = [{id:1, name:"Amerika"}, {id:2, name:"Evropa"}];
		slc.data_init.brands = [{id:1, name:"Brand1"}, {id:2, name:"Brand2"}, {id:3, name:"Brand3"}];
		slc.data_init.towing = [{id:1, name:"Tow1"}, {id:2, name:"Tow2"}, {id:3, name:"Tow3"}];
		slc.data_init.repair = [{id:1, name:"Rep1"}, {id:2, name:"Rep2"}, {id:3, name:"Rep3"}];
		slc.data_init.accomodation = [{id:1, name:"Acc1"}, {id:2, name:"Acc2"}, {id:3, name:"Acc3"}];
		slc.data_init.alternative = [{id:1, name:"Alt1"}, {id:2, name:"Alt2"}, {id:3, name:"Alt3"}];
		slc.data_init.causalties = [{id:1, name:"Cas1"}, {id:2, name:"Cas2"}, {id:3, name:"Cas3"}];

		slc.insurance = {};
		slc.insurance.travellers = [];
		slc.resetedForm = false;


		slc.someSelected = function (object) { 
				return object && Object.keys(object).some(function (key) { 
					return object[key]; 
				}); 
			};



 		slc.showModalPayment = function(ev) {
		    $mdDialog.show({
		      templateUrl: 'app/components/modal/modalPayment.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      fullscreen: $mdMedia('sm') && isc.customFullscreen
		    });
		  };

		  slc.test = function() {
		    if(slc.saleWizardPart1.$valid){
		    	slc.invalidForm = false;
		    	console.log("validna forma");
			}
		    else{
		    	slc.invalidForm = true;
		    	console.log("nevalidna forma");
			}
		  };

		   slc.test2 = function() {
		    if(slc.saleWizardPart2.$valid){
		    	slc.invalidForm = false;
		    	slc.insurance.travellers.push(slc.insurance.traveller);
		    	slc.insurance.traveller = {};
		    	slc.saleWizardPart2.$setPristine();
			    slc.saleWizardPart2.$setValidity();
			    slc.saleWizardPart2.$setUntouched();
			}
		    else{
		    	slc.invalidForm = true;
		    	console.log("nevalidna forma");


		    	if(!slc.towing){
          				slc.validTowing= false;
          			}else{
          				slc.validTowing= true;
          			}

          			if(!slc.repair){
          				slc.validRepair= false;
          			}else{
          				slc.validRepair= true;
          			}

          			if(!slc.accommodation){
          				slc.validAccommodation= false;
          			}else{
          				slc.validAccommodation= true;
          			}

          			if(!slc.alternative){
          				slc.validAlternative= false;
          			}else{
          				slc.validAlternative= true;
          			}
			}
		  };



 	}
 })();

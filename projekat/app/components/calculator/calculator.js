 (function() {
 	"use strict";
	//ubaciti zavisnost Insurance
 	angular
 		.module('merchant.calculator')
 		.controller('CalculatorController', CalculatorController);

 		CalculatorController.$inject = ['Insurance', 'SharedObject', '$state', '$timeout'];

 		function CalculatorController(Insurance, SharedObject, $state, $timeout) {
 			var calc= this;
 		//  	var promise_idata = Insurance.data_init();	




			// promise_idata.then(function (data) {
			// calc.data_init = data;
			// });

			 calc.insurance = {};
			 calc.insurance.travel = {};
		     calc.insurance.home = {};
		     calc.insurance.vehicle = {};
		     calc.age_number = {};
			 calc.price = {travel:1, home:2, vehicle:3};
			

			 calc.calculate = function () {
			// 	promise_price= Insurance.calculate(calc.insurance);
			// 	promise_price.then(function (data) {
			// 		calc.price = data;
			// 	});

 			calc.travelSubmit = true;

			 };

			
			calc.causalties = [{id:1, name: "pozar"}, {id: 2, name: "poplava"} ];
			//calc.casualty.cause[cause.id]
			calc.checked_required = false;

		
			calc.someSelected = function (object) { 
				return object && Object.keys(object).some(function (key) { 
					return object[key]; 
				}); 
			};


			calc.resetTravel = function(){

				calc.insurance.travel = {};
				calc.age_number = {};

				calc.travelForm.$setPristine();
			   // calc.travelForm.$setValidity();
			    calc.travelForm.$setUntouched();

			};


			calc.resetHome = function(){

				calc.insurance.home = {};
			
				calc.homeForm.$setPristine();
			  //  calc.homeForm.$setValidity();
			    calc.homeForm.$setUntouched();

			};

			calc.resetVehicle = function(){

				calc.insurance.vehicle = {};
			
				calc.vehicleForm.$setPristine();
			//    calc.vehicleForm.$setValidity();
			    calc.vehicleForm.$setUntouched();

			};

			

		    
			// validacija za check-boxove na touch
			/*
			calc.test = function(){
				calc.checked_required = false;
				var test= calc.casualty.cause;
				var c;
				var rez= 0;
				
				
				for(c in test){
				  if(test[c]){
				  	rez++;
				  }
			
				}

				if(rez === 0){
					calc.checked_required =	true;
				}

				 
				console.log(calc.checked_required);
				console.log(rez);
			};
			*/

			

			calc.calculateTravel = function(){
				
				if(calc.travelForm.$valid){
            		
            		calc.invalidTravelForm= false;
         		}else{
          			
          			calc.invalidTravelForm= true;
          		}

          	//	insurance.travel.human_age.value =calc.age_number

			};


			calc.calculateHome = function(){
				
				if(calc.homeForm.$valid){
            		
            		calc.invalidHomeForm= false;
         		}else{
          			
          			calc.invalidHomeForm= true;
          		}

			};



			calc.calculateVehicle = function(){
				
				if(calc.vehicleForm.$valid){
            		
            		calc.invalidVehicleForm= false;
         		}else{
          			
          			calc.invalidVehicleForm= true;

          			if(!calc.towing){
          				calc.validTowing= false;
          			}else{
          				calc.validTowing= true;
          			}

          			if(!calc.repair){
          				calc.validRepair= false;
          			}else{
          				calc.validRepair= true;
          			}

          			if(!calc.accommodation){
          				calc.validAccommodation= false;
          			}else{
          				calc.validAccommodation= true;
          			}

          			if(!calc.alternative){
          				calc.validAlternative= false;
          			}else{
          				calc.validAlternative= true;
          			}
          		}
			};


			calc.sale = function(){
				//SharedObject.setInsurance(insurance);
				//$state.go("main.sale.wizard1");

			};
		

 		}
 	})();
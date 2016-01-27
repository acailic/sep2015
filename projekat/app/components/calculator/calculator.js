 (function() {
 	"use strict";
	//ubaciti zavisnost Insurance
 	angular
 		.module('merchant.calculator')
 		.controller('CalculatorController', CalculatorController);

 		CalculatorController.$inject = ['Insurance', 'SharedObject', '$state', '$timeout'];

 		function CalculatorController(Insurance, SharedObject, $state, $timeout) {
 			var calc= this;
 		 	var promise_idata = Insurance.data_init();	




			promise_idata.then(function (data) {
			calc.data_init = data;
			});

			 calc.insurance = {};
			 calc.insurance.travel = {};
		     calc.insurance.home = {};
		     calc.insurance.vehicle = {};
		     calc.age_number = {};
			 calc.price = {travel:1, home:2, vehicle:3};

			// calc.data_init = {};
	 	// 	calc.data_init.human_ages = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
	 	// 	calc.data_init.sports = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
	 	// 	calc.data_init.cities = [{id:1, name:"Ns"}, {id:2, name:"Bg"}, {id:3, name:"Pg"}];
			// calc.data_init.max_values = [{id:1, name:"10.000"}, {id:2, name:"20.0000"}, {id:3, name:"30.0000"}];
			// calc.data_init.regions = [{id:1, name:"Amerika"}, {id:2, name:"Evropa"}];
			// calc.data_init.brands = [{id:1, name:"Brand1"}, {id:2, name:"Brand2"}, {id:3, name:"Brand3"}];
			// calc.data_init.towing = [{id:1, name:"Tow1"}, {id:2, name:"Tow2"}, {id:3, name:"Tow3"}];
			// calc.data_init.repair = [{id:1, name:"Rep1"}, {id:2, name:"Rep2"}, {id:3, name:"Rep3"}];
			// calc.data_init.accomodation = [{id:1, name:"Acc1"}, {id:2, name:"Acc2"}, {id:3, name:"Acc3"}];
			// calc.data_init.alternative = [{id:1, name:"Alt1"}, {id:2, name:"Alt2"}, {id:3, name:"Alt3"}];
			// calc.data_init.casualties = [{id:1, name:"Cas1"}, {id:2, name:"Cas2"}, {id:3, name:"Cas3"}];
			

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
				calc.selectedCause= {};
				calc.homeForm.$setPristine();
			    calc.homeForm.$setValidity();
			    calc.homeForm.$setUntouched();

			};

			calc.resetVehicle = function(){

				calc.insurance.vehicle = {};
			
				calc.vehicleForm.$setPristine();
			    calc.vehicleForm.$setValidity();
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
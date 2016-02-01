(function() {
 	"use strict";
	//ubaciti zavisnost Insurance
 	angular
 		.module('merchant.calculator')
 		.controller('CalculatorController', CalculatorController);

 		CalculatorController.$inject = ['Insurance', 'SharedObject', '$state', '$timeout'];

 		function CalculatorController(Insurance, SharedObject, $state, $timeout) {
 			var calc= this;


 			calc.checked_required = false;
 			calc.showTravelPrice = false;
 			calc.showVehiclePrice = false;
 			calc.showHomePrice = false;


 			calc.price = {};
 			calc.price.travel_price = 0;
 			calc.price.home_price = 0;
 			calc.price.vehicle_price = 0;

 			var human_ages = [];
 			//pribavljanje potrebnih podataka za inicijalizaciju forme

 

 			var promise_idata = Insurance.data_init();	
			promise_idata.then(function (data) {
					calc.data_init = data;
				angular.forEach(calc.data_init.ages, function (age, index) {
		  			human_ages[index] = {'id': age.id,'value': age.name, 'number_of_people': 0};
		  		});
			});


			var testIns = {};
			 calc.insurance = {};
			 calc.insurance.travel = {};
			 calc.insurance.travel.duration = null;
			 calc.insurance.travel.human_age = [];
			 calc.insurance.travel.start_date = null;
			 calc.insurance.travel.end_date = null;
			 calc.insurance.travel.sport_id = null;
			 calc.insurance.travel.region_id = null;
			 calc.insurance.travel.max_value_id = null;
			 calc.insurance.travel.owner = {};
		 	 calc.insurance.home = {};
		 	 calc.insurance.vehicle = {};
			 calc.insurance.travellers = [];
		 	 calc.age_number = {};
		 	 var promise_price = {};
		     


		    


			calc.insurance.home.duration = null;
			calc.insurance.home.start_date = null;
			calc.insurance.home.end_date = null;
			calc.insurance.home.floor_area = null;
			calc.insurance.home.flat_age = null;
			calc.insurance.home.est_value = null;
			calc.insurance.home.casualty_ids = [];
			calc.insurance.home.owner = {};


			calc.insurance.vehicle.duration = null;
			calc.insurance.vehicle.start_date = new Date();
			calc.insurance.vehicle.end_date = new Date();
			calc.insurance.vehicle.towing_id = null;
			calc.insurance.vehicle.repair_id = null;
			calc.insurance.vehicle.accomodation_id = null;
			calc.insurance.vehicle.alternative_id = null;
			calc.insurance.vehicle.owner = {};
			calc.insurance.vehicle.car = {};




		    //prazan objekat travel koji smjestam u calculate home, pucalo je kada su datumi null
			var travel = {};
			travel.duration = null;
			travel.start_date = new Date();
			travel.end_date = new Date();
			travel.region_id = null;
			travel.sport_id = null;
			travel.max_value_id = null;
			travel.human_age = [];
			travel.owner = {};

			 

			//pomocna funkcija za validaciju liste check-boxova
			calc.someSelected = function (object) { 
				return object && Object.keys(object).some(function (key) { 
					return object[key]; 
				}); 
			};


			calc.resetTravel = function(){

				calc.insurance.travel = {};
				calc.age_number = {};
				calc.price.travel_price = 0 ;
				calc.travelForm.$setPristine();
			  //  calc.travelForm.$setValidity();
			    calc.travelForm.$setUntouched();

			};


			calc.resetHome = function(){

				calc.insurance.home = {};
				calc.selectedCause= {};
				calc.price.home_price = 0 ;
				calc.homeForm.$setPristine();
			//    calc.homeForm.$setValidity();
			    calc.homeForm.$setUntouched();

			};

			calc.resetVehicle = function(){

				calc.insurance.vehicle = {};
				calc.price.vehicle_price = 0 ;
				calc.vehicleForm.$setPristine();
			//    calc.vehicleForm.$setValidity();
			    calc.vehicleForm.$setUntouched();

			};


			calc.calculateTravel = function(){
				
				if(calc.travelForm.$valid){
            		
            		calc.invalidTravelForm= false;
	   				human_ages[0].number_of_people = calc.age_number.number18; 
	   				human_ages[1].number_of_people = calc.age_number.number18_60; 
	   				human_ages[2].number_of_people = calc.age_number.number60; 
	   				calc.insurance.travel.human_age = human_ages;
            		
            		console.log("Salje se putno osiguranje "+calc.insurance.travel);
          			promise_price= Insurance.calculateTravel(calc.insurance.travel);

			 		promise_price.then(function (data) {
			 			calc.price.travel_price = data.travel_price;
			 			console.log("Primljena cijena za putno osiguranje ="+data.travel_price);

			 	//		calc.price.travel_price = 1;
			 			

			 		});
         		}else{
          			
          			calc.invalidTravelForm= true;
          		}

			};


			calc.calculateHome = function(){
				
				if(calc.homeForm.$valid){
            		
            		calc.invalidHomeForm = false;
            		
            		var casualty_ids = [];
            		angular.forEach(calc.selectedCause, function (value, key) {
		        	if(value === true)
		  				casualty_ids.push(parseInt(key));
		        	});

		        	calc.insurance.home.casualty_ids = casualty_ids; 
            		
            		console.log("Salje se osiguranje stana/kuce "+calc.insurance.home);            	
          			promise_price= Insurance.calculateHome(calc.insurance.home);
			 		promise_price.then(function (data) {
			 			calc.price.home_price = data.home_price;

			 			console.log("Primljena cijena za osiguranje kuce/stana ="+data.home_price);
			 		//	calc.price.home_price = 2;
			 		

			 		});

			 		

         		}else{
          			
          			calc.invalidHomeForm= true;
          		}

			};



			calc.calculateVehicle = function(){
				
				if(calc.vehicleForm.$valid){
            		
            		calc.invalidVehicleForm= false;
            		
            		
            		

            		console.log("Salje se osiguranje Pomoc na putu"+calc.insurance.vehicle);
          			promise_price= Insurance.calculateVehicle(calc.insurance.vehicle);
			 		promise_price.then(function (data) {
			 			calc.price.vehicle_price = data.vehicle_price;
			 			console.log("Primljena cijena za osiguranje pomoc na putu ="+data.vehicle_price);
			 			//calc.price.vehicle_price = 3;
			 		
			 		});

			 		
					
         		}else{
          			
          			calc.invalidVehicleForm= true;
          			calc.validateVehicle();
          			


          		}
			};

			calc.validateVehicle = function(){
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


			};

			calc.sale = function(){

				

				if(calc.travelForm.$valid){
            		
            		calc.invalidTravelForm= false;
            	}else{
            		calc.invalidTravelForm= true;

            	}



            	if(calc.flat){//ako je izabrano stambeno 
            			if(calc.homeForm.$valid){
            				calc.invalidHomeForm= false;
            			}else{
            				calc.invalidHomeForm= true;



            			}

            	}

            		
            	if(calc.car){//ako je izabrano pomoc na putu
            			if(calc.vehicleForm.$valid){
            				calc.invalidVehicleForm= false;
            			}else{
            				calc.invalidVehicleForm= true;
            				calc.validateVehicle();
            			}

            	}

            	var goToSale = !calc.invalidTravelForm &&
            					(!calc.car || (!calc.invalidVehicleForm && calc.car)) &&
            					(!calc.flat || (!calc.invalidHomeForm && calc.flat));


            	if(goToSale){
					SharedObject.setInsurance(calc.insurance);
					$state.go("main.sale.wizard1");
				}

			};
		

 		}
 	})();


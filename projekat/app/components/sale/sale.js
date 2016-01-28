(function() {
 	"use strict";
	
 	angular
 		.module('merchant.sale')
 		.controller('SaleController', SaleController);

 	SaleController.$inject = ['$mdDialog', '$mdMedia', 'SharedObject', '$state', '$scope', 'Insurance'];
 	function SaleController($mdDialog, $mdMedia, SharedObject, $state, $scope, Insurance) {
 		var slc = this;
 		// slc.insurance = SharedObject.getInsurance();

 	// 	slc.data_init = {};
 	// 	slc.data_init.travellers = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
 	// 	slc.data_init.human_ages = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
 	// 	slc.data_init.sports = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
 	// 	slc.data_init.cities = [{id:1, name:"Ns"}, {id:2, name:"Bg"}, {id:3, name:"Pg"}];
		// slc.data_init.max_values = [{id:1, name:"10.000"}, {id:2, name:"20.0000"}, {id:3, name:"30.0000"}];
		// slc.data_init.regions = [{id:1, name:"Amerika"}, {id:2, name:"Evropa"}];
		// slc.data_init.brands = [{id:1, name:"Brand1"}, {id:2, name:"Brand2"}, {id:3, name:"Brand3"}];
		// slc.data_init.towing = [{id:1, name:"Tow1"}, {id:2, name:"Tow2"}, {id:3, name:"Tow3"}];
		// slc.data_init.repair = [{id:1, name:"Rep1"}, {id:2, name:"Rep2"}, {id:3, name:"Rep3"}];
		// slc.data_init.accomodation = [{id:1, name:"Acc1"}, {id:2, name:"Acc2"}, {id:3, name:"Acc3"}];
		// slc.data_init.alternative = [{id:1, name:"Alt1"}, {id:2, name:"Alt2"}, {id:3, name:"Alt3"}];
		// slc.data_init.causalties = [{id:1, name:"Cas1"}, {id:2, name:"Cas2"}, {id:3, name:"Cas3"}];



		slc.insurance = {};
		slc.insurance.travellers = [];
		slc.finalView = false;
		slc.addTravellerForm = true;
		slc.checkTravellers = true;
		slc.number_of_travellers = 0;
		var elementExists = false;
		var indexOfEditedElement;
		slc.dates = [];
		slc.travelStartDateMin = new Date();
		var promise_price = {};
		var promise_insurance = {};
		var promise_idata = Insurance.data_init();	

		promise_idata.then(function (data) {
			slc.data_init = data;
			slc.data_init.human_ages = [{id:1, name:"Do 18"}, {id:2, name:"Od 18-60"}, {id:3, name:"Preko 60"}];
			console.log(slc.data_init);
		});

		slc.someSelected = function (object) {
			if (slc.flat) { 
				return object && Object.keys(object).some(function (key) { 
					return object[key]; 
				}); 
			}else
			 	return true;
		};

		slc.selectedIndex = 0;

        $scope.$watch('slc.selectedIndex', function(current, old) {
            switch (current) {
                case 0:
                	

                    $location.url("/saleWizard1");

                    break;
                case 1:
                    $location.url("/saleWizard2");
                    break;
                case 2:
                    $location.url("/saleWizard3");
                    break;
            }
        });

		slc.deleteInsurance = function(insuranceId) {
		  	angular.forEach(slc.insurance.travellers, function (insurance, index) {
	  			if(insurance.id === insuranceId){
	  				slc.insurance.travellers.splice(index,1);
	  			}
	        });
	  	};

	  	slc.editInsurance = function(insuranceId) {
	  		angular.forEach(slc.insurance.travellers, function (insurance, index) {
	  			if(insurance.id === insuranceId){
	  				slc.insuranceTravellerDeepCopy = angular.copy(insurance);
	  				slc.traveller = slc.insuranceTravellerDeepCopy;
	  				indexOfEditedElement = index;
	  			}
	        });
	  	};

	  	slc.saveInsurances = function() {
		  	if(slc.saleWizardPart1.$valid){
		    	slc.invalidForm = false;
		    	$state.go("main.sale.wizard2");
		    	slc.selectedIndex = 1;
			}
		    else{
		    	slc.invalidForm = true;

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

	   	slc.saveTravellers = function() {
		    if(slc.saleWizardPart2.$valid){
		    	slc.invalidForm = false;
		    	elementExists = false;

		    	angular.forEach(slc.insurance.travellers, function (traveller) {
		  			if(traveller.id === slc.insurance.traveller.id){
		  				elementExists = true;		  			}	
		        });

		        if(!elementExists){
		    		slc.traveller.id = slc.insurance.travellers.length+1;
			    	slc.insurance.travellers.push(slc.traveller);
			    }else{
			    	slc.insurance.travellers.splice(indexOfEditedElement, 1);
			    	slc.insurance.travellers.splice(indexOfEditedElement, 0, slc.insuranceTravellerDeepCopy); 
			    }

		    	slc.traveller = {};
		    	slc.insuranceTravellerDeepCopy = {};
		    	slc.saleWizardPart2.$setPristine();
			    slc.saleWizardPart2.$setUntouched();
			    slc.number_of_travellers = slc.insurance.travellers.length;
			    if(slc.insurance.travellers.length === slc.number_of_people){
			  		slc.checkTravellers = false;
			  		slc.addTravellerForm = false;
			  	 	slc.finalView = true;
			  	}
			}
		     else{
		    	slc.invalidForm = true;
			}
	  	};

	  	$scope.$watch('slc.insurance.travel.start_date', function (newValue, oldValue) {
	  		if(slc.insurance.travel !== undefined && slc.insurance.travel.start_date !== undefined){
	  			var date = new Date(slc.insurance.travel.start_date);
	  			slc.travelEndDateMin = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1);
	  		}
	  		slc.resetDatePickers();
		});

		$scope.$watch('slc.insurance.travel.end_date', function (newValue, oldValue) {
	  		if(slc.insurance.travel !== undefined && slc.insurance.travel.end_date !== undefined){
	  			var date = new Date(slc.insurance.travel.end_date);
	  			slc.homeStartDateMax = new Date(date.getFullYear(), date.getMonth(), date.getDate()-1);
	  			slc.vehicleStartDateMax = new Date(date.getFullYear(), date.getMonth(), date.getDate()-1);
	  		}
	  		slc.resetDatePickers();
		});

		slc.resetDatePickers = function(){
			if(slc.insurance.home !== undefined){
	  			if(slc.insurance.home.start_date !== undefined)
	  				slc.insurance.home.start_date = undefined;
	  			if(slc.insurance.home.end_date !== undefined)
	  				slc.insurance.home.end_date = undefined;
	  		}
	  		if(slc.insurance.vehicle !== undefined){
	  			if(slc.insurance.vehicle.start_date !== undefined)
	  				slc.insurance.vehicle.start_date = undefined;
	  			if(slc.insurance.vehicle.end_date !== undefined)
	  				slc.insurance.vehicle.end_date = undefined;
	  		}
		};

		$scope.$watch('slc.insurance.home.start_date', function (newValue, oldValue) {
	  		if(slc.insurance.home !== undefined && slc.insurance.home.start_date !== undefined){
	  			var date = new Date(slc.insurance.home.start_date);
	  			slc.homeEndDateMin = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1);
	  		}
		});

		$scope.$watch('slc.insurance.vehicle.start_date', function (newValue, oldValue) {
	  		if(slc.insurance.vehicle !== undefined && slc.insurance.vehicle.start_date !== undefined){
	  			var date = new Date(slc.insurance.vehicle.start_date);
	  			slc.vehicleEndDateMin = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1);
	  		}
		});

	  	slc.checkFlat = function() {
	  		if(!slc.flat){
	  			slc.insurance.home = {};
	  			angular.forEach(slc.data_init.causalties, function (casualty) {
		  			slc.selectedCause[casualty.id] = false;		  				
		        });
	  		}
	  	};

	  	slc.checkCar = function() {
	  		if(!slc.car){
	  			slc.insurance.vehicle = {};
	  			slc.towing = false;
	  			slc.repair = false;
	  			slc.accommodation = false;
	  			slc.alternative = false;
	  		}
	  	};

	  	slc.calculate = function() {
	  		slc.setDates();

 			var human_age_category_18_counter, human_age_category_18_60_counter, human_age_category_60_counter = 0;
	  		angular.forEach(slc.insurance.travellers, function (traveller) {
	  			if(traveller.human_age_id == 1)
	  				human_age_category_18_counter = human_age_category_18_counter++;		
	  		    else if(traveller.human_age_id == 2)
	  		    	human_age_category_18_60_counter = human_age_category_18_60_counter++;
	  		    else if(traveller.human_age_id == 3)  			
	  		    	human_age_category_60_counter = human_age_category_60_counter++;	
	        });
	        slc.insurance.travel.human_age = [{id:1, name:"Do 18", number_of_people:0}, 
	        			   {id:2, name:"Od 18-60", number_of_people:0}, 
	        			   {id:3, name:"Preko 60", number_of_people:0}];
	        slc.insurance.travel.duration = null;
	        slc.insurance.home = null;
	        slc.insurance.vehicle = null;
	        slc.insurance.travel.owner.city_id = 1;
	        slc.insurance.travel.owner.address = "nesto";
	        slc.human_age = {};
	        slc.human_age.id = 1;
	        slc.human_age.name = "ljudi";
	        slc.human_age.number_of_people = 2;
	  		promise_price = Insurance.calculate(slc.human_age);
		 	promise_price.then(function (data) {
		 		slc.price = data;
		 		// slc.selectedIndex = 2;
		 		// $state.go("main.sale.wizard3"); 
		 		console.log(slc.price);
		 	});
		 	slc.selectedIndex = 2;
		 	$state.go("main.sale.wizard3"); 
	  	};

	  	slc.setDates = function() {
	  		slc.dates.travel_start_date = slc.formatDate(slc.insurance.travel.start_date);
	  		slc.dates.travel_end_date = slc.formatDate(slc.insurance.travel.end_date);
	  		if(slc.insurance.home !== undefined && !angular.equals({}, slc.insurance.home)){
	  			slc.dates.home_start_date = slc.formatDate(slc.insurance.home.start_date); 
	  			slc.dates.home_end_date = slc.formatDate(slc.insurance.home.end_date);
	  		}
	  		if(slc.insurance.vehicle !== undefined && !angular.equals({}, slc.insurance.vehicle)){
	  			slc.dates.vehicle_start_date = slc.formatDate(slc.insurance.vehicle.start_date);
	  			slc.dates.vehicle_end_date = slc.formatDate(slc.insurance.vehicle.end_date);
	  		}
	  	};

	  	slc.formatDate = function(date) {
	  		return date.getDate() +"."+ date.getMonth()+1 +"."+ date.getFullYear()+".";
	  	};

	 	$scope.$watch('slc.number_of_people', function (newValue, oldValue) {
	 	  slc.finalView = false;
	 	  if(slc.insurance.travellers.length !== 0){
			  if(oldValue > newValue){
			    $mdDialog.show({
					    templateUrl: 'app/components/modal/modalTravellerNumber.html',
					    parent: angular.element(document.body),
					    fullscreen: $mdMedia('sm') && isc.customFullscreen,
					    controller: function($scope) {
				        $scope.response = function(value){
					          if(value === true){
					          	slc.insurance.travellers = [];
			  					slc.number_of_travellers = 0;
					          }else
					          	slc.number_of_people = oldValue;
					          slc.addTravellerForm = true;
					          $mdDialog.hide();
					        };
					    }
				   		});
				    }
			  }else{
			  	slc.addTravellerForm = true;
			  	slc.checkTravellers = true;
			  }
		  }
		);

		slc.createInsurance = function() {
			promise_insurance = Insurance.create(slc.insurance);
		 	promise_insurance.then(function (data) {
		 		console.log(data);
		 		$mdDialog.show({
			      templateUrl: 'app/components/modal/modalPayment.html',
			      parent: angular.element(document.body),
			      fullscreen: $mdMedia('sm') && isc.customFullscreen
			    });
		 	});
	  	};
 	}
 })();
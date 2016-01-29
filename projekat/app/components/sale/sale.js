(function() {
 	"use strict";
	
 	angular
 		.module('merchant.sale')
 		.controller('SaleController', SaleController);

 	SaleController.$inject = ['$mdDialog', '$mdMedia', 'SharedObject', '$state', '$scope', 'Insurance', '$location'];
 	function SaleController($mdDialog, $mdMedia, SharedObject, $state, $scope, Insurance, $location) {
 		var slc = this;
 		// slc.insurance = SharedObject.getInsurance();

		slc.insurance = {};
		slc.insurance.travellers = [];
		slc.finalView = false;
		slc.addTravellerForm = true;
		slc.checkTravellers = true;
		slc.number_of_travellers = 0;
		slc.dates = [];
		slc.travelStartDateMin = new Date();
		slc.selectedIndex = 0;
		var elementExists = false;
		var indexOfEditedElement;
		var promise_price = {};
		var promise_insurance = {};
		var human_ages = [];	
		
		var promise_idata = Insurance.data_init();
		promise_idata.then(function (data) {
			slc.data_init = data;
			console.log(slc.data_init);
  			angular.forEach(slc.data_init.ages, function (age, index) {
	  			human_ages[index] = {'id': age.id,'value': age.name, 'number_of_people': 0};
	  		});
		});

		slc.getInsurancesDetails = function() {
		  	if(slc.saleWizardPart1.$valid){
		    	slc.invalidForm = false;
		    	$state.go("main.sale.wizard2");
		    	slc.selectedIndex = 1;
			}
		    else{
		    	slc.invalidForm = true;

	    		if(!slc.towing){
      				slc.validTowing = false;
      			}else{
      				slc.validTowing = true;
      			}

      			if(!slc.repair){
      				slc.validRepair = false;
      			}else{
      				slc.validRepair = true;
      			}

      			if(!slc.accommodation){
      				slc.validAccommodation = false;
      			}else{
      				slc.validAccommodation = true;
      			}

      			if(!slc.alternative){
      				slc.validAlternative = false;
      			}else{
      				slc.validAlternative = true;
      			}
			}
	  	};

	   	slc.getTravellersDetails = function() {
		    if(slc.saleWizardPart2.$valid){
		    	slc.invalidForm = false;
		    	elementExists = false;

		    	angular.forEach(slc.insurance.travellers, function (traveller) {
		  			if(traveller.id === slc.traveller.id){
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

	  	slc.deleteTraveller = function(insuranceId) {
		  	angular.forEach(slc.insurance.travellers, function (insurance, index) {
	  			if(insurance.id === insuranceId){
	  				slc.insurance.travellers.splice(index,1);
	  			}
	        });
	  	};

	  	slc.editTraveller = function(insuranceId) {
	  		angular.forEach(slc.insurance.travellers, function (insurance, index) {
	  			if(insurance.id === insuranceId){
	  				slc.insuranceTravellerDeepCopy = angular.copy(insurance);
	  				slc.traveller = slc.insuranceTravellerDeepCopy;
	  				indexOfEditedElement = index;
	  			}
	        });
	  	};

	  	slc.calculate = function() {
	  		slc.setDates();
	  		slc.setCausualties();
	  		slc.setRisks();

	  		angular.forEach(slc.insurance.travellers, function (traveller) {
	  			angular.forEach(human_ages, function (age) {
		  			if(age.id === traveller.human_age_id)
		  				age.number_of_people++;	
		  		});
	        });
	        slc.insurance.travel.human_age = human_ages;
	        if(slc.flat){
	        	var casualty_ids = [];
		        angular.forEach(slc.selectedCause, function (value, key) {
		        	if(value === true)
		  				casualty_ids.push(parseInt(key));
		        });
		        slc.insurance.home.casualty_ids = casualty_ids;
	    	}

	  		promise_price = Insurance.calculate(slc.insurance);
		 	promise_price.then(function (data) {
		 		slc.price = data;
		 		slc.price.sum_price = slc.price.travel_price + slc.price.home_price + slc.price.vehicle_price;
		 		slc.selectedIndex = 2;
		 	    $state.go("main.sale.wizard3"); 
		 	});
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

	  	slc.setCausualties = function() {
	  		if(slc.flat){
	  			slc.selectedCauses = [];
	  			angular.forEach(slc.selectedCause, function (value, key) {
		        	if(value === true){
		  				angular.forEach(slc.data_init.casualties, function (cause) {
				        	if(cause.id === parseInt(key))
				        		slc.selectedCauses.push({'name': cause.name});	
				        });
				    }
		        });
	  		}
	  	};

	  	slc.setRisks = function() {
	  		if(slc.car){
	  			slc.selectedRisks = [];
	  			if(slc.towing){			
	  				angular.forEach(slc.data_init.towing, function (towing) {
			        	if(towing.id === slc.insurance.vehicle.towing_id)
					    	slc.selectedRisks.push({'name': "Šlepovanje", 'value': towing.name});
		        	});
	  			}
	  			if(slc.repair){			
	  				angular.forEach(slc.data_init.repair, function (repair) {
			        	if(repair.id === slc.insurance.vehicle.repair_id)
					    	slc.selectedRisks.push({'name': "Popravka", 'value': repair.name});
		        	});
	  			}
	  			if(slc.accommodation){			
	  				angular.forEach(slc.data_init.accomodation, function (accommodation) {
			        	if(accommodation.id === slc.insurance.vehicle.accomodation_id)
					    	slc.selectedRisks.push({'name': "Smeštaj", 'value': accommodation.name});
		        	});
	  			}
	  			if(slc.alternative){			
	  				angular.forEach(slc.data_init.alternative, function (alternative) {
			        	if(alternative.id === slc.insurance.vehicle.alternative_id)
					    	slc.selectedRisks.push({'name': "Alternativni prevoz", 'value': alternative.name});
		        	});
	  			}
	  		}
	  	};

	  	slc.formatDate = function(date) {
	  		return date.getDate() +"."+ date.getMonth()+1 +"."+ date.getFullYear()+".";
	  	};

	  	slc.createInsurance = function() {
			promise_insurance = Insurance.create(slc.insurance);
		 	promise_insurance.then(function (data) {
		 		$mdDialog.show({
			      templateUrl: 'app/components/modal/modalPayment.html',
			      parent: angular.element(document.body),
			      fullscreen: $mdMedia('sm') && isc.customFullscreen
			    });
		 		//$location.url(data); bice redirect na Payment		 		
		 	});
	  	};

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

		slc.someSelected = function (object) {
			if (slc.flat) { 
				return object && Object.keys(object).some(function (key) { 
					return object[key]; 
				}); 
			}else
			 	return true;
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


		$scope.$watch('slc.towing', function (newValue, oldValue) {
	  		if(newValue === false && slc.insurance.vehicle !== undefined)
	  			slc.insurance.vehicle.towing_id = {};
		});

		$scope.$watch('slc.repair', function (newValue, oldValue) {
	  		if(newValue === false && slc.insurance.vehicle !== undefined)
	  			slc.insurance.vehicle.repair_id = {};
		});

		$scope.$watch('slc.accommodation', function (newValue, oldValue) {
	  		if(newValue === false && slc.insurance.vehicle !== undefined)
	  			slc.insurance.vehicle.accomodation_id = {};
		});

		$scope.$watch('slc.alternative', function (newValue, oldValue) {
	  		if(newValue === false && slc.insurance.vehicle !== undefined)
	  			slc.insurance.vehicle.alternative_id = {};
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
 	}
 })();
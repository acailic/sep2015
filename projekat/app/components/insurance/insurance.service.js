(function() {
	"use strict";
	
	angular
 		.module('merchant')
 		.factory('Insurance', Insurance);


 	Insurance.$inject = ['$http', '$q'];
 	function Insurance($http, $q){
 		var insuranceService = {

 			data_init: function(){
				var deferred = $q.defer();

				$http({
					url: "https://localhost:8444/spring4/data_init", 
					method: "GET"
				}).success(function (data) {
					deferred.resolve(data);
				}).error(function () {
					alert("Došlo je do greške pri učitavanju inicijalnih podataka");
				});

				return deferred.promise;
			},


			calculate: function(insurance){
				var deferred = $q.defer();
				
				$http({
					url: "https://localhost:8444/spring4/calculate", 
					method: "POST",				
					data : insurance, 
					headers: {
	                    'Content-Type': 'application/json'
	         		}
				}).success(function (data) {
					deferred.resolve(data);
				}).error(function () {
					alert("Došlo je do greške pri kalkulaciji");
				});
				
				return deferred.promise;
			},


			calculateTravel: function(travel){
				var deferred = $q.defer();
				
				$http({
					url: "https://localhost:8444/spring4/calculateTravel", 
					method: "POST",				
					data : travel, 
					headers: {
	                    'Content-Type': 'application/json'
	         		}
				}).success(function (data) {
					deferred.resolve(data);
				}).error(function () {
					alert("Došlo je do greške pri kalkulaciji");
				});
				
				return deferred.promise;
			},


			calculateVehicle: function(vehicle){
				var deferred = $q.defer();
				
				$http({
					url: "https://localhost:8444/spring4/calculateVehicle", 
					method: "POST",				
					data : vehicle, 
					headers: {
	                    'Content-Type': 'application/json'
	         		}
				}).success(function (data) {
					deferred.resolve(data);
				}).error(function () {
					alert("Došlo je do greške pri kalkulaciji");
				});
				
				return deferred.promise;
			},


			calculateHome: function(home){
				var deferred = $q.defer();
				
				$http({
					url: "https://localhost:8444/spring4/calculateHome", 
					method: "POST",				
					data : home, 
					headers: {
	                    'Content-Type': 'application/json'
	         		}
				}).success(function (data) {
					deferred.resolve(data);
				}).error(function () {
					alert("Došlo je do greške pri kalkulaciji");
				});
				
				return deferred.promise;
			},






			create: function(insurance){
				var deferred = $q.defer();
				
				$http({
					url: "https://localhost:8444/spring4/create", 
					method: "POST",
					data : insurance, 
					headers: {
	                    'Content-Type': 'application/json'
	         		}
				}).success(function (data) {
					deferred.resolve(data);
				}).error(function () {
					alert("Došlo je do greške pri kupovini osiguranja");
				});
				
				return deferred.promise;
			}

		};

 		return insuranceService;
 	}
})();
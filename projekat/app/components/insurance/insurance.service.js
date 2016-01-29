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
					url: "http://localhost:8080/spring4/data_init", 
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
					url: "http://localhost:8080/spring4/calculate", 
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


			create: function(insurance){
				var deferred = $q.defer();
				
				$http({
					url: "http://localhost:8080/spring4/create", 
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
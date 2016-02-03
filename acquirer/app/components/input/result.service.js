(function() {
	"use strict";

	angular
		.module('acquirer.input')
		.factory('Result', Result);

	Result.$inject = ['$http', '$q'];
	function Result($http, $q) {
        //ovde nema nista, cisto nako za sad
		 var resultService = {
                sending: function(result){

                   var deferred = $q.defer();
                    $http({
                        url:"http://localhost:8080/spring4/transactionResults",
                        method: "POST",
                        data :   result,
                        headers: {'Content-Type': 'application/json',
                                  //  'Access-Control-Allow-Origin':'http://localhost:8000'
                         } 
                         
                    }).success(function (data) {
                      console.log("Uspesno result poslat." );
                      deferred.resolve(data);

                    }).error(function () {
                         
                        console.log("Došlo je do greške pri slanju rezultata transakcije ka merchantu servisu." );
                        deferred.reject();
                    });
                    return deferred.promise;
                } 

        };
        
        return resultService;

	}
})();
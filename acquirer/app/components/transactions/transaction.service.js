(function() {
	"use strict";

	angular
		.module('acquirer.input')
		.factory('Transaction', Transaction);

	Transaction.$inject = ['$http'];
	function Transaction($http) {
        //ovde nema nista, cisto nako za sad
		 var transactionService = {
                generate: function(transaction){

                    var deferred = $q.defer();
                    $http({
                        //url: "http://localhost:8000/acquirer/transaction", 
                        url:"http://192.168.1.29:8081/Acquirer_back/payment/confirm ",
                        method: "POST",
                        data : transaction
                    }).success(function (data) {
                        deferred.resolve(data);
                    }).error(function () {
                        alert("Došlo je do greške pri generisanju transakcije");
                    });
                    return deferred.promise;
                } 
        };
        
        return transactionService;

	}
})();
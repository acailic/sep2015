(function() {
	"use strict";

	angular
		.module('acquirer.input')
		.factory('Transaction', Transaction);

	Transaction.$inject = ['$http','$q'];
	function Transaction($http, $q) {
        //ovde nema nista, cisto nako za sad
		 var transactionService = {
                generate: function(transaction){

                    var deferred = $q.defer();
                    $http({
                        //url: "http://localhost:8000/acquirer/transaction", 
                        url:"http://localhost:8081/Acquirer_back/payment/confirm",
                        method: "POST",
                        data : transaction,
                        headers: {'Content-Type': 'application/json'
                                   
                         }
                    }).success(function (data) {
                        deferred.resolve(data);
                        console.log("Uspesno generisao transakcije" );
                        console.log(transaction);   
                        console.log("DATA POSLE Uspesno generisao transakcije" );
                        console.log(data);     
                    }).error(function () {
                        console.log("PUKAO JE Došlo je do greške pri generisanju transakcije" );
                        console.log(transaction);
                        //alert("Došlo je do greške pri generisanju transakcije");
                       deferred.reject();
                    });
                    return deferred.promise;
                } 
        };
        
        return transactionService;

	}
})();
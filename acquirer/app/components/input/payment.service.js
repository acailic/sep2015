(function() {
	"use strict";

	angular
		.module('acquirer.input')
		.factory('Payment', Payment);

	Payment.$inject = ['$http'];
	function Payment($http) {
        //ovde nema nista, cisto nako za sad
		 var paymentService = {
                send: function(payment){

                   // var deferred = $q.defer();
                    $http({
                        //url: "http://localhost:8000/acquirer/transaction", 
                        url:"http://192.168.1.29:8081/Acquirer_back/payment/init/2",
                        method: "GET",
                        data : payment,
                        headers: {'Content-Type': 'application/json'}
                    }).success(function (data) {
                    //    deferred.resolve(data);
                    }).error(function () {
                        alert("Došlo je do greške pri slanju payment id.");
                    });
                    //return deferred.promise;
                    return payment;
                } 

        };
        
        return paymentService;

	}
})();
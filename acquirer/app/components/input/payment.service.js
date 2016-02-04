(function() {
	"use strict";

	angular
		.module('acquirer.input')
		.factory('Payment', Payment);

	Payment.$inject = ['$http', '$q'];
	function Payment($http, $q) {
        //ovde nema nista, cisto nako za sad
		 var paymentService = {
                send: function(payment){

                   var deferred = $q.defer();
                    $http({
                        url:"http://localhost:8081/Acquirer_back/payment/init/payment?id="+payment,
                        method: "GET",
                        data :   payment   
                    }).success(function (data) {
                      //  alert(data);
                        console.log("Uspesno slanju payment id." );
                       deferred.resolve(data);

                    }).error(function () {
                        //alert("Došlo je do greške pri slanju payment id.");
                        console.log("Došlo je do greške pri slanju payment id." );
                    });
                    return deferred.promise;
                } 

        };
        
        return paymentService;

	}
})();
(function() {
	"use strict";
	
	angular
		.module('acquirer.transactions')
		.directive('crTransactions', crTransactions);

	function crTransactions() {
		return {
			restrict: "A",
			templateUrl: "app/components/transactions/transactions-row.html"
		};
	}
})();
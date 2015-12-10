(function() {
	"use strict";

	angular
		.module('merchant.sale')
		.config(config);

	config.$inject = ['$stateProvider'];
	function config($stateProvider) {
		$stateProvider
			.state('main.sale', {
				url: '/sale',
				views: {
					'content@': {
						templateUrl: 'app/components/sale/sale.html',
						controller: 'SaleController',
						controllerAs: 'slc'
					}
				}
			});
	}
})();
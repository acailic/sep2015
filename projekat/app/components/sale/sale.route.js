(function() {
	"use strict";

	angular
		.module('merchant.sale')
		.config(config);

	config.$inject = ['$stateProvider'];
	function config($stateProvider) {
		$stateProvider
			.state('main.sale', {
				abstract: true,
				views: {
					'content@': {
						templateUrl: 'app/components/sale/sale.html',
						controller: 'SaleController',
						controllerAs: 'slc'
					}
				}
			})
			.state('main.sale.wizard1', {
				url: '/saleWizard1',
				views: {
					'wizard': {
						templateUrl: 'app/components/sale/sale-wizard-part1.html'
					}
				}
			})
			.state('main.sale.wizard2', {
				url: '/saleWizard2',
				views: {
					'wizard': {
						templateUrl: 'app/components/sale/sale-wizard-part2.html'
					}
				}
			})
			.state('main.sale.wizard3', {
				url: '/saleWizard3',
				views: {
					'wizard': {
						templateUrl: 'app/components/sale/sale-wizard-part3.html'
						
					}
				}
			});
	}
})();
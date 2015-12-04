(function() {
	"use strict";

	angular
		.module('merchant.insurance')
		.config(config);

	config.$inject = ['$stateProvider'];
	function config($stateProvider) {
		$stateProvider
			.state('main.insurance', {
				url: '/insurance',
				views: {
					'content@': {
						templateUrl: 'app/components/insurance/insurance.html',
						controller: 'InsuranceController',
						controllerAs: 'isc'
					}
				}
			});
	}
})();
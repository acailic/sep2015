(function() {
	"use strict";

	angular
		.module('merchant.calculator')
		.config(config);

	config.$inject = ['$stateProvider'];
	function config($stateProvider) {
		$stateProvider
			.state('main.calculator', {
				url: '/calculator',
				views: {
					'content@': {
						templateUrl: 'app/components/calculator/calculator.html',
						controller: 'CalculatorController',
						controllerAs: 'calc'
					}
				}
			});
	}
})();
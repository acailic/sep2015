(function() {
	"use strict";

	angular
		.module('merchant.core')
		.config(config);

	config.$inject = ['$stateProvider', '$urlRouterProvider'];
	function config($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/insurance');

		$stateProvider
			.state('main', {
				abstract: true,
				views: {
					'header': {
						templateUrl: 'app/components/core/header.html'	
					},
					'menu': {
						templateUrl: 'app/components/core/menu.html'	
					}
				}
			})
			.state('main.about', {
				url: '/about',
				views: {
					'content@': {
						templateUrl: 'app/components/core/about.html'
					}
				}
			});
	}
})();
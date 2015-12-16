(function() {
	"use strict";

	angular
		.module('acquirer.core')
		.config(config);

	config.$inject = ['$stateProvider', '$urlRouterProvider'];
	function config($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/home');

		$stateProvider
			.state('main', {
				abstract: true,
				views: {
					'header': {
						templateUrl: 'app/components/acquirer/header.html',
						controller: 'HeaderController',
						controllerAs: 'hc'	
					},
					'menu': {
						templateUrl: 'app/components/acquirer/menu.html'	
					},
					'footer': {
						templateUrl: 'app/components/acquirer/footer.html'	
					}
				}
			})
			.state('main.home', {
				url: '/home',
				views: {
					'content@': {
						templateUrl: 'app/components/acquirer/home.html'
					}
				}
			})
			.state('main.about', {
				url: '/about',
				views: {
					'content@': {
						templateUrl: 'app/components/acquirer/about.html'
					}
				}
			})
			.state('main.input', {
				url: '/input',
				views: {
					'content@': {
						templateUrl: 'app/components/input/input.html'
					}
				}
			})
		 
	}
})();
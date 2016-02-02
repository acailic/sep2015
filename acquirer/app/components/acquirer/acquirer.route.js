(function() {
	"use strict";

	angular
		.module('acquirer.core')
		.config(config);

	config.$inject = ['$stateProvider', '$urlRouterProvider' ];
	function config($stateProvider, $urlRouterProvider ) {
		  
		 

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
						templateUrl: 'app/components/acquirer/menu.html',
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
						templateUrl: 'app/components/acquirer/home.html',
						controller: 'HomeController',
						controllerAs: 'hoc'
					}
				}
			})
			.state('main.about', {
				url: '/about',
				views: {
					'content@': {
						templateUrl: 'app/components/aboutus/aboutus.html',
						controller: 'UserController',
						controllerAs: 'ul'

					}
				}
			})
			
			.state('main.transactions', {
				url: '/transactions',
				views: {
					'content@': {
						templateUrl: 'app/components/transactions/transactions-list2.html',
						controller: 'TransactionsListController',
						controllerAs: 'tlc'
					}
				}
			})
			.state('main.arg', {
				url: '/input/idpayment/:idpayment',
				views: {
					'content@': {
						templateUrl: 'app/components/input/input.html',
						controller: 'InputController',
						controllerAs: 'inc'
					}
				}
			})
			.state('main.input', {
				url: '/input',
				views: {
					'content@': {
						templateUrl: 'app/components/input/input.html',
						controller: 'InputController',
						controllerAs: 'inc'
					}
				}
			});
			 
		 $urlRouterProvider.otherwise('/home');
	}
})();
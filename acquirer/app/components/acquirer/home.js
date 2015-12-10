 (function() {
 	"use strict";
	
 	angular
 		.module('acquirer.core')
 		.controller('HomeController', HomeController);

 	 
 	function HomeController($scope ) {
 		  $scope.imagePath = './assets/images/insurance-app/travel.jpg';
 		  console.log("UCITALO JE KONTROLOER");
	}
 })();
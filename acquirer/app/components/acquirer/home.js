 (function() {
 	"use strict";
	
 	angular
 		.module('acquirer.core')
 		.controller('HomeController',  HomeController);

 	
	 
 	function HomeController() {
 		console.log("UCITALO JE KONTROLOER");
 		 
	}

 	/*angular.module('acquirer').filter('currency', function() {
   				 var currencySymbol = '$';

    				return function(input ) {
			        var out = "";
			        

			            switch( ) {
			                case 'en':
			                     out = input;// google
			                     currencySymbol = 'USD$';
			                     break;
			                 case 'sr-latn':
			                     out = 111 * input; // google
			                     currencySymbol = 'din';
			                     break;     	
			                default: 
			                 	out = input;// google
			                    currencySymbol = 'USD$';
			                    break; 
			            }

			        return out + ' ' + currencySymbol;
   				 }
		}); */

	
 })();
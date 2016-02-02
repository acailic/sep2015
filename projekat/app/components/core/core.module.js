(function() {
	"use strict";
	
	angular
		.module('merchant.core', ['ui.router'])
		.directive('myDirective',function() {
  			return {
  				
  				restrict: 'A',
  				
			    link: function link(scope, element, attrs){

			     scope.$on('$stateChangeStart', 
					function(event, toState, toParams, fromState, fromParams){ 

					if(toState.name === "main.insurance"){
						scope.selected = 0;

					}


					if(toState.name === "main.calculator"){
			    		scope.selected = 1;
			    	}

			    	
			    	if(toState.name === "main.sale.wizard1"){
			    		scope.selected = 2;
			    	}


			    	if(toState.name === "main.about"){
			    		scope.selected = 3;
			    	}



					});
			      
			    

			    }
  			};
		});
})();
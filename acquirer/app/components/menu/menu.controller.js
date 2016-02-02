(function() {
	"use strict";
	
	angular
		.module('acquirer.menu')
		 .controller('MenuController', MenuController);

	MenuController.$inject = ['$scope', '$state'];
	function MenuController($scope, $state){
		console.log("MENU CONTROLLER!!!");
		var mc = this;

		 var tabs = [
          { title: "{{'LABELS.HOME'|translate}}",  content:'main.home'},
          { title: "{{'LABELS.INPUT'|translate}}'",  content:'main.input'},
          { title: "'{{'LABELS.ABOUT'|translate}}'",  content:'main.about'},
       ],
        selected = null,
        previous = null;
		console.log("MENU CONTROLLER!!!");
	 	mc.tabs = tabs;

    	mc.selectedIndex = 0;
		$scope.$watch('selectedIndex', function(current, old){
			/*
	      previous = selected;
	      selected = tabs[current];
	      if ( old + 1 && (old != current)) console.log('Goodbye ' + previous.title + '!');
	      if ( current + 1 )                console.log('Hello ' + selected.title + '!');
	      mc.selectedIndex = current + 1;
	      */
	      console.log("tu sam");

	      if(current === 0){
	      	if($state.current.name === "about" ){
	      		selectedIndex = 2;
	      	}
	      }
   	  	});

   	  	mc.states = {};
		mc.states.activeItem = "'{{'LABELS.HOME'|translate}}'";
		     
	}



})();

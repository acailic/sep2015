  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('DemoCtrl', DemoCtrl);
     
 	function DemoCtrl($scope ) {
 		 console.log("DemoCtrl");
 		 $scope.payment = {
      		 cardholdername: '',
           cardholderlastname: '',
           cardtype: '',
           cardnum:  '' ,
           seccode:  '' ,
           expmonth: '',
           expyear: '', 
    }; 
  
 	
 	}   
 } )();
 
 


  
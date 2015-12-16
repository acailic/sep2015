  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputCtrl', InputCtrl);
     
 	function InputCtrl($scope ) {
 		 console.log("InputCtrl");
 		 $scope.payment = {
           value: 1000,
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
 
 


  
  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputController', InputController);
   

   
  InputController.$inject = ['$mdDialog', '$mdMedia','Transaction',  '$routeParams' ];
 	function InputController($mdDialog, $mdMedia, Transaction,  $routeParams ) {
 		 
      
    var inc = this; 
     //  OVDE BI TREBAO LINK NAZAD DO MERCHANTA
    inc.returnUrl= 'http://google.com'; 
    inc.url_payment = $routeParams.url_payment;
    inc.id_payment = $routeParams.id_payment;
    //inc.transaction = transaction;
      inc.transaction = {
           value: 0,
           cardholdername: '',
           cardholderlastname: '',
           cardtype: '',
           cardnum:  '' ,
           seccode:  '' ,
           expmonth: '',
           expyear: '', 
    }; 
    inc.transaction.value = 1000;

    inc.generatingTransaction = function(value) {
       Transaction.generate(inc.transaction);
    };
    
    inc.showModalPayment = function(ev) {

        $mdDialog.show({
          animate: true,
          controller: ModalController,
          controllerAs: 'mdc',
          templateUrl: 'app/components/modal/success-modal.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose:true,
          fullscreen: $mdMedia('sm') && inc.customFullscreen
        });
    };

    inc.showModalProgress = function(ev) {

        $mdDialog.show({
          animate: true,
          controller: ModalController,
          controllerAs: 'mdc',
          templateUrl: 'app/components/modal/progress-modal.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose:true,
          fullscreen: $mdMedia('sm') && inc.customFullscreen
        });
    };
 
     
 	}
 
 } )();
 
  function ModalController($mdDialog) {
  var mdc = this;

  mdc.hide = function() {
      $mdDialog.hide();
  };
  mdc.cancel = function() {
      $mdDialog.cancel();
  };
  mdc.answer = function(answer) {
      $mdDialog.hide(answer);
  }; 
}  


  
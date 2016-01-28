  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputController', InputController);
   

   
  InputController.$inject = ['$mdDialog', '$mdMedia','Transaction', 'Payment' ,'$stateParams' ];
 	function InputController($mdDialog, $mdMedia, Transaction, Payment,  $stateParams ) {
 		  var inc = this; 
      //inc.$state=$state;

      console.log("USAO JE U KONTROLER");
      console.log($stateParams);
      
       console.log($stateParams.idpayment);
       inc.idpayment = $stateParams.idpayment;
  
      inc.transaction = {
           id: 0,
           amount: 0,
           cardholdername: '',
           cardholderlastname: '',
           cardtype: '',
           cardnum:  '' ,
           seccode:  '' ,
           expmonth: '',
           expyear: '',
    }; 
    inc.transaction.value = 1000;
     //  OVDE BI TREBAO LINK NAZAD DO MERCHANTA
    inc.returnUrl= 'http://google.com'; 
     



    //inc.sendingPayment= function(value){
     var returned_value=Payment.send(inc.id_payment);
    //var returned_value;
    //inc.transaction.id=returned_value.id;
    //inc.transaction.amount=returned_value.amount;
    //inc.transaction.token=returned_value.token;
     
    //inc.transaction = transaction;
      
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


  
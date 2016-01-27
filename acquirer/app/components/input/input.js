  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputController', InputController);
   

   
  InputController.$inject = ['$mdDialog', '$mdMedia','Transaction', 'Payment' ,  '$routeParams' ];
 	function InputController($mdDialog, $mdMedia, Transaction, Payment,  $routeParams ) {
 		 
      console.log("USAO JE U KONTROLOER");
      console.log("id paymenta:"+$routeParams.id_payment );
      

    var inc = this; 
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
           expyear: '', 
    }; 
    inc.transaction.value = 1000;
     //  OVDE BI TREBAO LINK NAZAD DO MERCHANTA
    inc.returnUrl= 'http://google.com'; 
    //inc.url_payment = $routeParams.url_payment;
    inc.id_payment = $routeParams.id_payment;



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


  
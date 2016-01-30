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
           paymentId: 10,
           amount: 10,
           cardholdername: 'aca',
           cardholderlastname: 'ilic',
           pan:  '12341245' ,
           cardSecCode:  '1242' ,
           expmonth: '12',
           expyear: '2011',
           CSRFToken: 'sadas123',

    }; 

    inc.sending_transaction = {
           paymentId: 10,
           amount: 10,
           cardHolderName: 'aca ilic',
           pan:  '12341245' ,
           cardSecCode:  '1242' ,
           cardExpDate : new Date(1,12,2011),
           CSRFToken: '312213321',
           acquirerTimeStamp : new Date(1,12,2011),

    }; 
     //  OVDE BI TREBAO LINK NAZAD DO MERCHANTA
    inc.returnUrl= 'http://google.com'; 

     var onSuccess = function(data){
        console.log("ON SUCCESS PAYMENTA:");
        console.log(data.paymentId);
        console.log(data.amount);
        console.log(data.csrftoken);
        inc.transaction.paymentId=data.paymentId;
        inc.transaction.amount=data.amount;
        inc.transaction.csrftoken=data.csrftoken;
    };

    var onFailure = function(reason){
       console.log("ON onFailure:"+ inc.transaction );
        alert("Failed, " + reason);
    };
   
    var onNotify = function(update){
        alert("Notified: " + update);
    };

    console.log("SALJE ID PAYMENTA :"+ inc.idpayment );
    var promise=Payment.send(inc.idpayment) ;
    promise.then(onSuccess, onFailure); 
    console.log("POSLAO JE!@@" );
   
     
     var onSuccessTransaction = function(data){
        console.log("ON SUCCESS onSuccessTransaction:");
        console.log(data);
       
       
    };

    var onFailureTransaction = function(reason){
       console.log("ON onFailure Transaction:"+ inc.transaction );
        alert("Failed Transaction, " + reason);
    };
   
    var onNotifyTransaction = function(update){
        alert("Notified: " + update);
    };
     
  
    //inc.transaction = transaction;
      
    inc.generatingTransaction = function(value) {
       console.log("GENERATING TRANSACTIONS:" );
       console.log(inc.sending_transaction );
       var retTransaction=Transaction.generate(inc.sending_transaction);
       promise.then(onSuccessTransaction, onFailureTransaction, onNotifyTransaction); 
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


  
  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputController', InputController);
   

   
  InputController.$inject = ['$mdDialog', '$mdMedia','Transaction', 'Payment' ,'$stateParams','$timeout'  ,'Result','$location' ];
 	function InputController($mdDialog, $mdMedia, Transaction, Payment,  $stateParams, $timeout,  Result, $location ) {
 		  var inc = this; 
      //inc.$state=$state;
       inc.transaction = {
             paymentId: 0,
             amount: 0,
             cardholdername: '',
             cardholderlastname: '',
             pan:  '' ,
             cardSecCode:  '' ,
             expmonth: '',
             expyear: '',
             CSRFToken: '',

      };  
      inc.resultTrans= {
        merchantOrderId: '',
        acquirerOrderId: '',
        acquirerTimestamp: '',
        paymentId: ''
      }

      console.log("USAO JE U KONTROLER");
      console.log($stateParams);
        
      console.log($stateParams.idpayment);
      inc.idpayment = $stateParams.idpayment;


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
         //alert("Failed, " + reason);
      };
     
      var onNotify = function(update){
          //alert("Notified: " + update);
      };

        inc.onIncomingParametar= function(vrednostParametra){ 
          console.log("POZIVA NA OSNOVU INDEFINED ID PAYMENTA :"+vrednostParametra);
          console.log("SALJE ID PAYMENTA :"+ vrednostParametra );
          var promise=Payment.send(vrednostParametra) ;
          promise.then(onSuccess, onFailure); 
          console.log("POSLAO JE!@@" );
      };
      
      if(!angular.isUndefined(inc.idpayment)) {
        inc.onIncomingParametar(inc.idpayment);
      }
        
    
       /*inc.transaction = {
             paymentId: 10,
             amount: 10,
             cardholdername: 'aca',
             cardholderlastname: 'ilic',
             pan:  '12341245' ,
             cardSecCode:  '1242' ,
             expmonth: '12',
             expyear: '2011',
             CSRFToken: 'sadas123',

      };  */

      /*inc.sending_transaction = {
           paymentId: 10,
           amount: 10,
           cardHolderName: 'aca ilic',
           pan:  '12341245' ,
           cardSecCode:  '1242' ,
           cardExpDate : new Date(1,12,2011),
           CSRFToken: '312213321',
           acquirerTimeStamp : new Date(1,12,2011),

      }; */
       //  OVDE BI TREBAO LINK NAZAD DO MERCHANTA
      inc.returnUrl= 'http://google.com'; 

     
       var onSuccessTransaction = function(data){
          console.log("ON SUCCESS onSuccessTransaction:");
          console.log(data);
         //uspesna transakcija trebalo bi samo da procitam url i da se redirektujem
          //alert(data.state);
          //inc.showModalPayment(data); 
           inc.resultTrans= {
                    merchantOrderId: data.merchantOrderId,
                    acquirerOrderId: data.acquirerOrderId,
                    acquirerTimestamp: data.acquirerTimestamp,
                    paymentId: data.paymentId
                  };
           $timeout(function() {
            
            inc.showConfirm();
            console.log('update with timeout fired');
        }, 3000);
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

         inc.sending_transaction = {
               paymentId: inc.paymentId,
               amount: inc.amount,
               cardHolderName: inc.cardholdername+' '+inc.cardholderlastname,
               pan:  inc.pan ,
               cardSecCode:  inc.cardSecCode,
               cardExpDate : new Date(1,inc.expmonth,inc.expyear),
               CSRFToken: inc.CSRFToken,
               acquirerTimeStamp : new Date(),
         };

        console.log(inc.sending_transaction );
        var retTransaction=Transaction.generate(inc.sending_transaction);
        retTransaction.then(onSuccessTransaction, onFailureTransaction, onNotifyTransaction); 
      };

      
      inc.showModalPayment = function() {

          $mdDialog.show({
            animate: true,
            controller: ModalController,
            controllerAs: 'mdc',
            templateUrl: 'app/components/modal/success-modal.html',
            parent: angular.element(document.body),
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
      
 
    
     inc.showConfirm = function( ) {
          var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  ;
          $mdDialog.show({
            controller: ModalController,
            templateUrl: 'app/components/modal/success-modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose:true,
            fullscreen: useFullScreen
          })
          .then(function(answer) {
            console.log("redirekcija."+answer);
            alert("redirekcija na merchanta.");
            console.log(inc.resultTrans);
            var retResult=Result.sending(inc.resultTrans);
            $location.path("www.google.com");

            retResult.then(function(data) {
                    // promise fulfilled
                   $location.path(data.redirectUrl);
                }, function(error) {
                    // promise rejected, could log the error with: console.log('error', error);
                    console.log('error', error); 
                });
             
          }, function() {
             console.log('You cancelled the dialog.');
             
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


  
  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputController', InputController);
   

   
  InputController.$inject = ['$mdDialog', '$mdMedia','Transaction', 'Payment' ,'$stateParams','$timeout'  ,'Result',  '$window' ];
 	function InputController( $mdDialog, $mdMedia, Transaction, Payment,  $stateParams, $timeout,  Result,  $window ) {
 		  var inc = this; 
      //inc.$state=$state;
       inc.transaction = {
             paymentId: '',
             amount: '',
             cardholdername: '',
             cardholderlastname: '',
             cardtype:  '' ,
             pan: '',
             cardSecCode:  '' ,
             expmonth: '',
             expyear: '',
             CSRFToken: ''
      };  

      inc.resultTrans= {
        merchantOrderId: '',
        acquirerOrderId: '',
        acquirerTimestamp: '',
        paymentId: ''
      };

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
        
    
       /* inc.transaction = {
             paymentId: 10,
             amount: 10,
             cardholdername: 'aca',
             cardholderlastname: 'ilic',
             pan:  '12345678' ,
             cardSecCode:  '1124' ,
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
      inc.returnUrl= 'http://localhost:8000/insurance'; 

     
       var onSuccessTransaction = function(data){
          console.log("ON SUCCESS onSuccessTransaction:");
          console.log(data);
         //uspesna transakcija trebalo bi samo da procitam url i da se redirektujem
          // alert(data.state);
          //inc.showModalPayment(data); 
          console.log(data.errorMsg);
           inc.resultTrans= {
                    merchantOrderId: data.merchantOrderId,
                    acquirerOrderId: data.acquirerOrderId,
                    acquirerTimestamp: data.acquirerTimeStamp,
                    paymentId: data.paymentId
                  };
           $timeout(function() {
            if (data.state=="SUCCESSFULL")
              inc.showConfirm();
            if (data.state=="UNSUCCESSFULL"){
              inc.showError(data.errorMsg);
               console.log(data.errorMsg);
             }
            console.log('update with timeout fired');
        }, 1500);
      };

      var onFailureTransaction = function(){
        console.log("ON onFailure Transaction:"+ inc.transaction );
        //alert("Failed Transaction, ");
          $timeout(function() {
              $mdDialog.show(
                  $mdDialog.alert()
                    .parent( angular.element(document.body))
                    .clickOutsideToClose(false)
                    .title(' UNSUCCESSFULL TRANSACTION ')
                    .content('<h2>Transaction is not generate.</h2>')
                    .ariaLabel('Alert Dialog Demo')
                    .ok('OK')
                );
            console.log('update with timeout fired');
        }, 1000); 
 
         /* inc.resultTrans= {
                      merchantOrderId: "1",
                      acquirerOrderId: "1",
                      acquirerTimestamp: new Date(),
                      paymentId: '1'
                    };
          $timeout(function() {       
           // inc.showConfirm();  
            console.log('update with timeout fired');
          }, 3000);  
           inc.showError("NISTE IMALI DOVOLJNO PARA.");  */
      };
     
      var onNotifyTransaction = function(update){
          alert("Notified: " + update);
           
      };
       
    
      //inc.transaction = transaction;
        
      inc.generatingTransaction = function(value) {
         console.log("GENERATING TRANSACTIONS:" );

         inc.sending_transaction = {
               paymentId: inc.transaction.paymentId,
               amount: inc.transaction.amount,
               cardHolderName: inc.cardholdername+' '+inc.transaction.cardholderlastname,
               pan:  inc.transaction.pan ,
               cardSecCode:  inc.cardSecCode,
               cardExpDate : new Date(1,inc.transaction.expmonth,inc.transaction.expyear),
               CSRFToken: inc.transaction.CSRFToken,
               acquirerTimeStamp : new Date()
         };

        console.log(inc.sending_transaction );
        var retTransaction=Transaction.generate(inc.sending_transaction);
        retTransaction.then(onSuccessTransaction, onFailureTransaction, onNotifyTransaction); 
      };

      
      /*inc.showModalPayment = function() {

          $mdDialog.show({
            animate: true,
            controller: ModalController,
            controllerAs: 'mdc',
            templateUrl: 'app/components/modal/success-modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose:true,
            fullscreen: $mdMedia('sm') && inc.customFullscreen
          });
          
      };*/

      inc.showModalProgress = function(ev) {

          $mdDialog.show({
            animate: true,
            controller: ModalController,
            controllerAs: 'mdc',
            templateUrl: 'app/components/modal/progress-modal.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false,
            fullscreen: $mdMedia('sm') && inc.customFullscreen
          });
      };
      
 
    
     inc.showConfirm = function( ) {
          var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
          $mdDialog.show({
            
            controller: ModalController,
            templateUrl: 'app/components/modal/success-modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose:false,
            fullscreen: useFullScreen
          })
          .then(function(answer) {
            //$location.path('/about');
            console.log("redirekcija."+answer);
            console.log(inc.resultTrans);
            var retResult=Result.sending(inc.resultTrans);
            retResult.then(function(data) {
                    console.log("URL JE: "+data.url);
                   //$location.path( "www.google.com");
                   $window.location.href=data.url;
                }, function(error) {
                    // promise rejected, could log the error with: console.log('error', error);
                    console.log('error', error); 
                });
             
          }, function() {
             console.log('You cancelled the dialog.');
             
          });
           
       };
   
       

       inc.showError = function(message) {
          var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'));
          console.log("PORUKA PRE SLANJA: "+message);
          $mdDialog.show({
              
          // templateUrl: 'app/components/modal/error-modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose:false,
            fullscreen: useFullScreen,
            controller: ModalController,
            template: '<md-dialog  ng-controller="ModalController as mc">'+
                    ' <md-dialog-content>'+ 
                    '<h2 style="color:red">Fail</h2>'+
                    '<div   layout="column"> Payment failed.<hr><p>'+
                     message+ '</p><hr>'+
                     '<div layout="row" ><img style="margin: auto; max-width: 100%;" alt="error" src="/images/error.png">'
                      + '</div></div><md-dialog-actions layout="row">'+
                   ' <span/><md-button class="md-raised md-primary" ng-click="mc.answer(odg)">'
                    + 'Go to merchant</md-button></md-dialog-actions></md-dialog-content>'
          })  
          .then(function(answer) {
            //$location.path('/about');
           console.log("redirekcija."+answer);
            console.log(inc.resultTrans);
            var retResult=Result.sending(inc.resultTrans);
            retResult.then(function(data) {
                    console.log("URL JE: "+data.url);
                     $window.location.href=data.url;
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
 
  function ModalController($mdDialog ) {


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
 

  
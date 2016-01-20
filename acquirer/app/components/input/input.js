  (function() {
  "use strict";
  
  angular
    .module('acquirer.input' )
    .controller('InputController', InputController);
   

   
   InputController.$inject = ['$mdDialog', '$mdMedia','Transaction'  ];
 	function InputController($mdDialog, $mdMedia, Transaction ) {
 		 
      
    var inc = this; 
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
       inc.transaction.$saveOrUpdate(success);
    };
    
    inc.showModalPayment = function(ev) {

        $mdDialog.show({
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
          controller: ModalController,
          controllerAs: 'mdc',
          templateUrl: 'app/components/modal/progress-modal/inprogress.html',
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


  
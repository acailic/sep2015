(function() {
  "use strict";
  
  angular
    .module('merchant')
    .service('SharedObject', SharedObject);


    SharedObject.$inject = [];

    function SharedObject(){

        var insurance = {};

        var setInsurance = function(sharedInsurance) {
          insurance = sharedInsurance;
        };

        var getInsurance = function(){
          return insurance;
        };

        return {
          setInsurance: setInsurance,
          getInsurance: getInsurance
        };

    }

})();
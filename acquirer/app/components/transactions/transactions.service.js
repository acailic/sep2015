(function() {
	"use strict";

	angular
		.module('acquirer.transactions')
		.factory('Transactions', Transactions);

	Transactions.$inject = ['$q'];
	function Transactions($q) {
	var tableData = [
       {"id": "000", "acqid": "000", "issuerordid":"1", "amount":"888", "state":"success"},
       {"id": "111", "acqid": "111", "issuerordid":"2", "amount":"666",  "state":"success"},
       {"id": "222", "acqid": "222", "issuerordid":"2", "amount":"999",  "state":"success"},
       {"id": "333", "acqid": "333", "issuerordid":"3", "amount":"555",  "state":"success"},
        {"id": "000", "acqid": "000", "issuerordid":"1", "amount":"888", "state":"success"},
       {"id": "111", "acqid": "111", "issuerordid":"2", "amount":"666",  "state":"success"},
       {"id": "222", "acqid": "222", "issuerordid":"2", "amount":"999",  "state":"success"},
       {"id": "333", "acqid": "333", "issuerordid":"3", "amount":"555",  "state":"success"},
        {"id": "000", "acqid": "000", "issuerordid":"1", "amount":"888", "state":"success"},
       {"id": "111", "acqid": "111", "issuerordid":"2", "amount":"666",  "state":"success"},
       {"id": "222", "acqid": "222", "issuerordid":"2", "amount":"999",  "state":"success"},
       {"id": "333", "acqid": "333", "issuerordid":"3", "amount":"555",  "state":"success"},
       {"id": "444", "acqid": "444", "issuerordid":"4", "amount":"444", "state":"success"}
    ];

    return {
      loadAllItems : function() {
        return $q.when(tableData);
      }
    };
	}
})();
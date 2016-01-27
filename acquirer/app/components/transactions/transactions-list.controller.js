(function() {
	"use strict";

	angular
		.module('acquirer.transactions')
		.controller('TransactionsListController', TransactionsListController);
//, 'Transactions'
	TransactionsListController.$inject = ['$scope', '$location' , "NgTableParams" ];
	function TransactionsListController($scope, $location, NgTableParams ) {
		//Objekat koji sadrži podatke vezane za straničenje
		var tlc = this;
		tlc.data={ 
			"transactions":[  
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
			    ]
		};

		//var self = this;
		//var data = [{name: "Moroni", age: 50} /*,*/];
		tlc.tableParams = new NgTableParams({
			 page: 1, // show first page
     		 count: 10 // count per page
		}, { data:  tlc.data});
    /*Transactions
      .loadAllItems()
      .then(function(tableData) {
        tlc.transactions = [].concat(tableData);
      });*/
		//tlc.transactions = Transactions;
		//tlc.places = places;

		/*tlc.pagination = {
			currentPage: 1,
			pageSize: 5,
			pages: new Array(Math.ceil(tlc.transactions.length / 5)),
			changePage: function(page) {
				if(tlc.pagination.currentPage != page && page > 0 && page <= tlc.pagination.pages.length) {
					tlc.pagination.currentPage = page;
				}
			}
		};*/

		$scope.$watch(function() {
			return tlc.filtered; //preporučen način da se posmatra objekat kod controller as
		}, function(newVal, oldVal) {
			if(!angular.equals(newVal, oldVal)) { //koristimo angular.equals zato što nam treba dubinsko poređenje vrednosti objekata
				tlc.pagination.pages = new Array(Math.ceil(tlc.filtered.length / tlc.pagination.pageSize));
			}
		}, true); //postavljamo true jer želimo da pratimo kad se promeni property objekta koji posmatramo, a ne samo njegova referenca

		tlc.tableChanged = function(sortParam) {
			if(tlc.sort === sortParam) {
				tlc.sortDirection = tlc.sortDirection == '+' ? '-' : '+';
			} else {
				tlc.sort = sortParam;
				tlc.sortDirection = '+';
			}
		};

		 
	}
})();
(function() {
	"use strict";

	angular
		.module('acquirer.input')
		.factory('Transaction', Transaction);

	Transaction.$inject = ['$resource'];
	function Transaction($resource) {
        //ovde nema nista, cisto nako za sad
		 return $resource('api/transactions/', {}, {
                'query': {method: 'GET', isArray: true},
                'get': {
                    method: 'GET',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                },
                'save': { method:'POST' },
                'update': { method:'PUT' },
                'delete':{ method:'DELETE'}
            }); 
	}
})();
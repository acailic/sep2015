 (function() {
 	"use strict";
	//ubaciti zavisnost Insurance
 	angular
 		.module('merchant.calculator')
 		.controller('CalculatorController', CalculatorController);

 		CalculatorController.$inject = ['Insurance', 'SharedObject', '$state'];

 		function CalculatorController(Insurance, SharedObject, $state) {
 			var calc= this;
 		// 	var promise_idata = Insurance.data_init();	


			// promise_idata.then(function (data) {
			// 	calc.idata = data;
			// });

			// calc.insurance = {};


			// calc.calculate = function () {
			// 	promise_price= Insurance.calculate(calc.insurance);
			// 	promise_price.then(function (data) {
			// 		calc.price = data;
			// 	});

			// };

			calc.sale = function(){
				//SharedObject.setInsurance(insurance);
				//$state.go("main.sale.wizard1");
			};
		

 		}
 	})();
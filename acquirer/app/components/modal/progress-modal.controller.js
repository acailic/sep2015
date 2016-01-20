(function() {
	"use strict";

	angular
		.module('acquirer.input.progress-modal')
		.controller('ProgressModalController', ProgressModalController);

	ProgressModalController.$inject = ['$modalInstance', 'newPlace'];
	function ProgressModalController($modalInstance, newPlace) {
		var prog = this;
		prog.ok = ok;
		 

		function ok() {
			$modalInstance.close(prog.ok);
		}

		function cancel() {
			$modalInstance.dismiss();
		}
	}
})();
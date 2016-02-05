(function() {
	"use strict";
	
	angular
		.module('acquirer.core')
		 .controller('HeaderController', HeaderController);

	HeaderController.$inject = ['$translate', 'tmhDynamicLocale', 'crTranslations'];
	function HeaderController($translate, tmhDynamicLocale, crTranslations) {
		//console.log("UCITALO JE HeaderController");
		var hc = this;
		hc.currentLanguage = crTranslations[$translate.use()].LANGUAGE;
		hc.setLanguage = setLanguage;
		//$translate.useSanitizeValueStrategy('sanitize');
		function setLanguage(language) {
			$translate.use(language);
			tmhDynamicLocale.set(language);
			hc.currentLanguage = crTranslations[language].LANGUAGE;
		}

		  
	}



})();

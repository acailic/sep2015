(function() {
	"use strict";
	
	angular
		.module('acquirer.core')
		 .controller('HeaderController', HeaderController);

	HeaderController.$inject = ['$translate', 'tmhDynamicLocale', 'crTranslations'];
	function HeaderController($translate, tmhDynamicLocale, crTranslations) {
		console.log("UCITALO JE HeaderController");
		var hc = this;
		hc.currentLanguage = crTranslations[$translate.use()].LANGUAGE;
		hc.setLanguage = setLanguage;

		function setLanguage(language) {
			$translate.use(language);
			tmhDynamicLocale.set(language);
			hc.currentLanguage = crTranslations[language].LANGUAGE;
		}

		  
	}



})();

/*
.config(function($mdIconProvider) {
    	$mdIconProvider
      .iconSet("call", 'images/serbia.svg', 24)
      .iconSet("social", 'images/serbia.svg', 24);
  })
	*/	
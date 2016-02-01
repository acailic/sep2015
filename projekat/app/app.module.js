(function() {
	angular
		.module('merchant', ['ngMaterial', 'ngMessages', 'merchant.core', 'merchant.insurance', 'merchant.modal', 
			'merchant.sale', 'merchant.calculator'])
		.config(function($sceDelegateProvider, $mdThemingProvider, $mdDateLocaleProvider) {




			//Allow CORS requests to these urls:
			$sceDelegateProvider.resourceUrlWhitelist([
   			'self',
   			'http://localhost:8080/**'
			]);
		    
		    $mdThemingProvider.theme('default');
		    var customBlueMap = $mdThemingProvider.extendPalette('light-blue', {
			    'contrastDefaultColor': 'light',
			    'contrastDarkColors': ['50'],
			    '50': 'ffffff'
			});

			$mdThemingProvider.definePalette('customBlue', customBlueMap);

			$mdThemingProvider.theme('default')
			    .primaryPalette('customBlue', {
			    'default': '500',
			    'hue-1': '50'
			    })
			    .accentPalette('pink');

			$mdThemingProvider.theme('input', 'default')
			    .primaryPalette('grey');

			$mdDateLocaleProvider.formatDate = function(date) {
				if(date !== undefined && date !== null)
			    	return date.getDate() +"."+ Number(date.getMonth()+1) +"."+ date.getFullYear()+".";
			    else
			    	return null;
			};
		});
})();
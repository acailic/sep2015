(function() {
	angular
		.module('merchant', ['ngMaterial','merchant.core', 'merchant.insurance', 'merchant.modal', 
			'merchant.sale', 'merchant.calculator'])
		.config(function($mdThemingProvider) {
		    
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
		});
})();
(function() {
	angular
		.module('acquirer', ['acquirer.core','acquirer.input','acquirer.modal', 'acquirer.transactions', 'acquirer.shared'])
		.config(function($mdThemingProvider) {
		    
		    $mdThemingProvider.theme('default');
		    var customBlueMap = $mdThemingProvider.extendPalette('light-blue', {
			    'contrastDefaultColor': 'light',
			    'contrastDarkColors': ['50'],
			    '50': 'ffffff'
			});

			$mdThemingProvider.definePalette('customBlue', customBlueMap);

			$mdThemingProvider.theme('default')
			    .primaryPalette('orange')
			    .accentPalette('pink');

			$mdThemingProvider.theme('input', 'default')
			    .primaryPalette('purple');
		});

		 
		 
})();
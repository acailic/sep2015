(function() {
	angular
		.module('acquirer', ['acquirer.core','acquirer.input',  'acquirer.modal', 'acquirer.transactions','acquirer.users', 'acquirer.shared', 'acquirer.menu'])
		.config(function($sceDelegateProvider, $mdThemingProvider) {
		    
			 
			  //Allow CORS requests to these urls:
				   $sceDelegateProvider.resourceUrlWhitelist([
				      'self',
				      'http://localhost:8080/**',
				      'http://localhost:8081/**',
				      'http://localhost:8082/**',
				      'https://localhost:8444/**'

				   ]);
		    

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
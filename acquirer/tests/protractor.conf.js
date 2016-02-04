exports.config = {
  specs: ['e2e/*.js'],
  baseUrl: 'http://localhost:8000/#/input.html',
   //pokrećemo selenium server putem jara, kako bi imali 1 konzolu, a ne 2
  seleniumServerJar: '../node_modules/selenium-server-standalone-jar/jar/selenium-server-standalone-2.50.1.jar'
  //seleniumAddress: 'http://localhost:4444/wd/hub'

}
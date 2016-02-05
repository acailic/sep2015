// Karma configuration
// Generated on Tue Oct 25 2016 15:20:11 GMT+0200 (Let/zim. vreme u Centr. Evropi)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '../',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    //treba doddati
    files: [
        'assets/js/angular/angular.js',
        'assets/js/angular-animate/angular-animate.js',
        'assets/js/angular-aria/angular-aria.js',
        'assets/js/angular-resource/angular-resource.js',
        'assets/js/angular-ui-router/release/angular-ui-router.js',
        'assets/js/angular-material/angular-material.js',
        'assets/js/angular-messages/angular-messages.js',
        'assets/js/angular-mocks/angular-mocks.js',
        'assets/js/angular-route/angular-route.js',
        'assets/js/angular-translate/angular-translate.js',
        'app/app.module.js',
        'app/components/acquirer/acquirer.module.js',
        'app/components/acquirer/acquirer.route.js',
        'app/components/modal/modal.module.js',
        'app/components/input/input.module.js',
        'app/components/modal/modal.module.js',
        'app/components/shared/shared.module.js',
        'app/components/shared/i18n/i18n.module.js',
        'app/components/shared/i18n/i18n-constants/i18n-constants.module.js',
        'app/components/aboutus/users.js',
        'assets/js/angular-dynamic-locale/dist/tmhDynamicLocale.js',
        'app/**/*.js'
    ],
 

    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false
  })
}

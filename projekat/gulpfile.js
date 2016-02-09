//Na vrhu gulpfile.js datoteke pišemo sve module koje ćemo koristiti
var gulp = require('gulp');

var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var webserver = require('gulp-webserver');
//concat-vendor služi za spajanje stranih biblioteka koje preuzimamo putem bower alata
var concatVendor = require('gulp-concat-vendor');
//sass kompajler i css obrađivači
var sass = require('gulp-sass');
var minifyCss = require('gulp-minify-css');
var concatCss = require('gulp-concat-css');
//jshint
var jshint = require('gulp-jshint');
var protractor = require('gulp-protractor');
var karma = require('karma');
var exit = require('gulp-exit');

var sources = [
  'app/app.module.js',
  'app/app.service.js',
  'app/components/core/core.module.js',
  'app/components/core/core.route.js',
  'app/components/insurance/insurance.module.js',
  'app/components/insurance/insurance.service.js',
  'app/components/insurance/insurance.js',
  'app/components/insurance/insurance.route.js',
  'app/components/sale/sale.module.js',
  'app/components/sale/sale.js',
  'app/components/sale/sale.route.js',
  'app/components/modal/modal.module.js',
  'app/components/modal/modal.js',
  'app/components/calculator/calculator.module.js',
  'app/components/calculator/calculator.js',
  'app/components/calculator/calculator.route.js',
  'assets/js/angular-messages/angular-messages.js'
];

var sourcesAssets = [
  'assets/js/angular/angular.js',
  'assets/js/angular-ui-router/release/angular-ui-router.js',
  'assets/js/angular-material/angular-material.js',
  'assets/js/angular-animate/angular-animate.js',
  'assets/js/angular-aria/angular-aria.js',
  'assets/js/angular-messages/angular-messages.js',
  'assets/js/angular-mocks/angular-mocks.js'
];

gulp.task('vendorScripts', function() {
  return gulp.src('assets/js/*')
    .pipe(concatVendor('vendor.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest('dist'))
});

gulp.task('minCss', ['sass'], function() {
  gulp.src('assets/css/**/*.css')
    .pipe(concatCss("all.min.css"))
        .pipe(minifyCss())
        .pipe(gulp.dest('dist'));
});

gulp.task('scripts', function() {
  return gulp.src(sources)
    .pipe(concat('all.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest('dist'));
});

gulp.task('minify', ['minCss', 'vendorScripts', 'scripts']);

gulp.task('webserver', function() {
  gulp.src('.')
    .pipe(webserver({
      livereload: true,
      open: true,
      port: 8001,
      https: true

    }));
});

gulp.task('sass', function () {
    gulp.src('assets/sass/**/*.scss')
        .pipe(sass())
        .pipe(gulp.dest('assets/css'));
});

gulp.task('sass:watch', function () {
    gulp.watch(['assets/sass/**/*.scss', 'assets/css/**/*.css'], ['minCss']);
});

gulp.task('lint', function() {
    gulp.src('app/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task('watch', function() {
  gulp.watch('app/**/*.js', ['lint', 'scripts']);
});

//gulp.task('default', ['lint', 'vendorScripts', 'scripts', 'minCss', 'watch', 'sass:watch', 'webserver']);
gulp.task('default', ['lint','watch', 'sass:watch', 'webserver']);

gulp.task('test', function(done) {
    karma.server.start({
      //putanja do karma.config.js
        configFile: __dirname + '\\tests\\karma.conf.js'
    }, done);
});

//potrebno je ažurirati webdriver za selenium server 
var webdriverUpdate = require('gulp-protractor').webdriver_update;
gulp.task('webdriverUpdate', webdriverUpdate);

gulp.task('e2e', ['webserver', 'webdriverUpdate'], function(done) {
  gulp.src([])
    .pipe(protractor.protractor({
      //putanja do protractor.config.js
      configFile: __dirname + "\\tests\\protractor.conf.js",
      //menjamo baseUrl pošto webserver koristi port 8000, a http-server 8080
          //args: ['--baseUrl', 'http://localhost:8000/#/input']
    }))
    .on('error', function(error) {
      console.log(error);
    })
  //bez ovog plugina se ne ugase pokrenuti serveri
    .pipe(exit());
});

gulp.task('defaultmin', ['lint', 'minify', 'watch', 'sass:watch', 'webserver']);
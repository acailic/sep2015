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
var karma = require('karma');
var jshint = require('gulp-jshint');
var protractor = require('gulp-protractor');
var exit = require('gulp-exit');

var sources = [
        'app/app.module.js',
        'app/components/acquirer/acquirer.module.js',
      //  'app/components/acquirer/home.js',
    //    'app/components/acquirer/acquirer.route.js',
        'app/components/modal/modal.module.js',
        'app/components/menu/menu.module.js',
        'app/components/input/input.module.js',
        'app/components/transactions/transactions.module.js',
        'app/components/shared/shared.module.js',
       // 'app/components/shared/i18n/i18n.module.js',
        'app/components/shared/i18n/i18n-constants/i18n-constants.module.js',
        'app/components/aboutus/users.js',
       // 'app/components/aboutus/usercontroller.js',
       // 'app/components/aboutus/userservice.js',
       // 'assets/js/angular-dynamic-locale/dist/tmhDynamicLocale.js',
       // 'app/components/transactions/transaction.service.js',
       // 'app/components/input/payment.service.js',
       '!app/components/payment.spec.js',
       '!app/appe2e.module.js',
        'app/**/*.js'
];

gulp.task('vendorScripts', function() {
	return gulp.src('assets/js/*')
		.pipe(concatVendor('vendor.min.js'))
		.pipe(uglify())
		.pipe(gulp.dest('dist'))
});

gulp.task('scripts', function() {
	return gulp.src(sources)
		.pipe(concat('all.min.js'))
		.pipe(uglify())
		.pipe(gulp.dest('dist'));
});

 

gulp.task('webserverhttps', function() {
  gulp.src('.')
    .pipe(webserver({
      livereload: true,
      open: true,
  https: true
    }));
});

gulp.task('webserverhttp', function() {
  gulp.src('.')
    .pipe(webserver({
      livereload: true,
      open: true,
  https: false
    }));
});


gulp.task('sass', function () {
    gulp.src('assets/sass/**/*.scss')
        .pipe(sass())
        .pipe(gulp.dest('assets/css'));
});

gulp.task('minCss', ['sass'], function() {
	gulp.src('assets/css/**/*.css')
		.pipe(concatCss("all.min.css"))
        .pipe(minifyCss())
        .pipe(gulp.dest('dist'));
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

gulp.task('webserver', function() {
   gulp.src('.')
    .pipe(webserver({
      livereload: true,
      open: true,
  https: true
    }));
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

//minifikacija css i js
gulp.task('minCssDist', ['sass'], function() {
  gulp.src('assets/css/**/*.css')
    .pipe(concatCss("all.min.css"))
        .pipe(minifyCss())
        .pipe(gulp.dest('dist'));
});

gulp.task('scriptsDist', function() {
  return gulp.src(sources)
    .pipe(concat('all.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest('dist'));
});

gulp.task('vendorScripts', function() {
  return gulp.src('assets/js/*')
    .pipe(concatVendor('vendor.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest('dist'))
});

gulp.task('minifikuj', [ 'vendorScripts','minCssDist', 'scriptsDist']);

gulp.task('defaultmin', ['lint', 'minifikuj', 'watch', 'sass:watch', 'webserver']);
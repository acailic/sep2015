(function(){

  angular
       .module('acquirer.users')
       .controller('UserController', [
          'userService', '$mdSidenav', '$mdBottomSheet', '$log', '$q',
          UserController
       ]);


  angular
        .module('acquirer.users' )
              .config(function(  $mdIconProvider){

                  $mdIconProvider
                      .defaultIconSet("./images/avatars.svg", 512)
                      .icon("menu"       , "./assets/images/angular-material/icons/menu.svg"        , 128)
                      .icon("share"      , "./assets/images/angular-material/icons/message.svg"       , 128)
                      .icon("google_plus", "./assets/images/angular-material/icons/ic_email_24px.svg" , 512)
                      .icon("hangouts"   , "./assets/images/angular-material/icons/hangout.svg"    , 512)
                      .icon("twitter"    , "./assets/images/angular-material/icons/twitter.svg"     , 512)
                      .icon("phone"      , "./assets/images/angular-material/icons/ic_phone_24px.svg"  , 512);

                     
              });

  /**
   
   * @param $scope
   * @param $mdSidenav
   * @param avatarsService
   * @constructor
   */
  function UserController( userService, $mdSidenav, $mdBottomSheet, $log, $q) {
    var self = this;

    self.selected     = null;
    self.users        = [ ];
    self.selectUser   = selectUser;
    self.toggleList   = toggleUsersList;
    self.showContactOptions  = showContactOptions;

    // Load all registered users

    userService
          .loadAllUsers()
          .then( function( users ) {
            self.users    = [].concat(users);
            self.selected = users[0];
          });

   

    /**
     *   hide the bottomsheet IF visible, then
     * hide or Show the 'left' sideNav area
     */
    function toggleUsersList() {
      var pending = $mdBottomSheet.hide() || $q.when(true);

      pending.then(function(){
        $mdSidenav('left').toggle();
      });
    }

    /**
     * Select the current avatars
     * @param menuId
     */
    function selectUser ( user ) {
      self.selected = angular.isNumber(user) ? $scope.users[user] : user;
      self.toggleList();
    }

    /**
     * Show the bottom sheet
     */
    function showContactOptions($event) {
        var user = self.selected;

        return $mdBottomSheet.show({
          parent: angular.element(document.getElementById('content')),
          templateUrl: './app/components/aboutus/view/contactSheet.html',
          controller: [ '$mdBottomSheet', ContactPanelController],
          controllerAs: "cp",
          bindToController : true,
          targetEvent: $event
        }).then(function(clickedItem) {
        //  $log.debug( clickedItem.name + ' clicked!');
        });

        /**
         * Bottom Sheet controller for the Avatar Actions
         */
        function ContactPanelController( $mdBottomSheet ) { 
          this.user = user;
          this.actions = [
            { name: 'Phone'       , icon: 'phone'       , icon_url: './assets/images/angular-material/icons/ic_phone_24px.svg'},
            { name: 'Twitter'     , icon: 'twitter'     , icon_url: './assets/images/angular-material/icons/twitter.svg'},
            { name: 'Google+'     , icon: 'google_plus' , icon_url: './assets/images/angular-material/icons/ic_email_24px.svg'},
            { name: 'Hangout'     , icon: 'hangouts'    , icon_url: './assets/images/angular-material/icons/hangout.svg'}
          ];
          this.submitContact = function(action) {
            $mdBottomSheet.hide(action);
          };
        }
    }

  }

})();

 

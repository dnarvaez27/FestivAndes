(function (ng) {
  let mod = ng.module('homeModule', ['ui.router']);

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/home');

      $stateProvider
        .state('home', {
          url: '/home',
          views: {
            'bodyView': {
              templateUrl: 'assets/js/home/home.html'
            }
          }
        });
    }]);
})(window.angular);
(function (ng) {
  let app = ng.module('mainApp', [
    'ngSanitize',
    'ui.router',

    'aboutModule',
    'iteracionModule',
    'homeModule'
  ]);

  app.controller('mainController', function ($scope, $state) {
    toggleMenu(0);

    $scope.goHome = function () {
      toggleMenu(0);
      $state.go('home', {});
      scrollTop();
    };
    $scope.goIteracion = function (index) {
      toggleMenu(index);
      $state.go('iteracion', {iteracionNumber: index});
      scrollTop();
    };
    $scope.goAbout = function () {
      toggleMenu(6);
      $state.go('about', {});
      scrollTop();
    };

    function scrollTop () {
      $('html, body').animate({scrollTop: 0}, 'slow');
    }

    function toggleMenu (index) {
      for (let i = 0; i <= 6; i++) {
        $('#menu' + i).toggleClass('footer-button-active', false);
      }
      $('#menu' + index).toggleClass('footer-button-active', true);
    }
  });

  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);

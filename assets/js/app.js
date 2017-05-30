(function (ng) {
  var app = ng.module('mainApp', [
    'ui.router',
    'iteracionModule',
    'homeModule'
  ]);

  app.controller('mainController', function ($scope, $state) {

    $scope.goHome = function () {
      console.log('Im home');
      $state.go('home', {});
    };

    $scope.goIteracion = function (index) {
      console.log('Iteracion ' + index);
      $state.go('iteracion', {iteracionNumber: index});
    };
  });

  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);
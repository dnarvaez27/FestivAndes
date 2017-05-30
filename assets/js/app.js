(function (ng) {
  var app = ng.module('mainApp', [
    'ngSanitize',
    'ui.router',
    'iteracionModule',
    'homeModule'
  ]);

  app.controller('mainController', function ($scope, $state) {

    $scope.goHome = function () {
      $state.go('home', {});
    };
    $scope.goIteracion = function (index) {
      $state.go('iteracion', {iteracionNumber: index});
    };
  });

  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);
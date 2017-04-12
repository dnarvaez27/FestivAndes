(function (ng) {
  var mod = ng.module('espectaculosModule', ['ui.router'])

  mod.constant('espectaculosContext', 'festivales/{festivalId:int}/espectaculos')

  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modules/espectaculo/'

    $stateProvider
      .state('espectaculos', {
        url: '/espectaculos',
        abstract: true,
        parent: 'fetivalesDetail',
        //TODO above
        views: {
          'mainView': {
            templateUrl: basePath + 'espectaculos.html'
          }
        }
      })
      .state('espectaculoDetail', {
        url: '/{espectaculoId:int}',
        parent: 'espectaculos',
        param: {
          espectaculoId: null
        },
        views: {
          'infoView': {
            templateUrl: basePath + 'espectaculos.detail.html',
            controller: ['$scope', '$stateParams', function ($scope, $params) {
              $scope.currentEspectaculo = $scope.espectaculosRecords[$params.espectaculoId - 1]
            }]
          }
        }
      })
  }])
})(window.angular)
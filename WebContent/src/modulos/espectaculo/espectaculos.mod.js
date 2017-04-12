(function (ng) {
  var mod = ng.module('espectaculosModule', ['ui.router'])

  mod.constant('espectaculosContext', 'festivales/{festivalId:int}/espectaculos/')
  mod.constant('festivalesContext', 'festivales/')

  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modulos/espectaculo/'

    $stateProvider
      .state('espectaculos', {
        url: '/espectaculos',
        abstract: true,
        parent: 'fetivalesDetail',
        views: {
          'mainView@': {
            templateUrl: basePath + 'espectaculos.html'
          }
        }
      })
      .state('espectaculoDetail', {
        url: '/{espectaculoId:int}',
        parent: 'espectaculos',
        resolve: {
          espectaculo: ['$http', 'festivalesContext', 'espectaculosContext', '$stateParams', function ($http, festivalesContext, espectaculosContext, $stateParams) {
            return $http.get(festivalesContext + '/' + $stateParams.festivalId + '/espectaculos/' + $stateParams.espectaculoId)
          }]
        },
        param: {
          espectaculoId: null
        },
        views: {
          'infoView': {
            templateUrl: basePath + 'espectaculos.detail.html',
            controller: ['$scope', '$stateParams', 'espectaculo', function ($scope, $stateParams, espectaculo) {
              $scope.currentEspectaculo = espectaculo.data
            }]
          }
        }
      })
  }])
})(window.angular)
(function (ng) {
  var mod = ng.module('festivalesModule', ['ui.router'])

  mod.constant('festivalesContext', 'rest/festivales')

  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    var basePath = 'src/modulos/festival/'
    var basePathEspectaculo = 'src/modulos/espectaculo/'

    $urlRouterProvider.otherwise('/festivales/list')

    $stateProvider
      .state('festivales', {
        url: '/festivales',
        abstract: true,
        resolve: {
          festivales: ['$http', 'festivalesContext', function ($http, festivalesContext) {
            return $http.get(festivalesContext)
          }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'festivales.html',
            controller: ['$scope', 'festivales', function ($scope, festivales) {
              $scope.festivalesRecords = festivales.data
            }]
          }
        }
      })
      .state('festivalesList', {
        url: '/list',
        parent: 'festivales',
        views: {
          'listView': {
            templateUrl: basePath + 'festivales.list.html'
          }
        }
      })
      .state('fetivalesDetail', {
        url: '/{festivalId:int}/espectaculos',
        parent: 'festivales',
        param: {
          festivalId: null
        },
        resolve: {
          espectaculos: ['$http', 'festivalesContext', '$stateParams', function ($http, festivalesContext, $stateParams) {
            return $http.get(festivalesContext + '/' + $stateParams.festivalId + '/espectaculos')
          }]
        },
        views: {
          'listView': {
            templateUrl: basePath + 'festivales.list.html'
          },
          'detailView': {
            templateUrl: basePathEspectaculo + 'espectaculos.list.html',
            controller: ['$scope', 'espectaculos', function ($scope, espectaculos) {
              $scope.espectaculosRecords = espectaculos.data
            }]
          }
        }
      })
  }])
})(window.angular)
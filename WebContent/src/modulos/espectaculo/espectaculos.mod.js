(function (ng) {
  var mod = ng.module('espectaculosModule', ['ui.router']);

  mod.constant('espectaculoContext', '/espectaculos/');
  mod.constant('lugaresContext', '/lugares/');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

      var basePath = 'src/modulos/espectaculo/';
      var basePathGeneros = 'src/modulos/genero/';
      var basePathRequerimientos = 'src/modulos/requerimiento/';
      var basePathCompanias = 'src/modulos/compania/';
      var basePathFunciones = 'src/modulos/funcion/';

      $stateProvider
        .state('espectaculos', {
          url: '',
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
          param: {
            espectaculoId: null
          },
          resolve: {
            espectaculo: ['$http', 'festivalesContext', 'espectaculoContext', '$stateParams',
              function ($http, festivalesContext, espectaculoContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId + espectaculoContext + $stateParams.espectaculoId);
              }],
            generos: ['$http', 'festivalesContext', '$stateParams',
              function ($http, festivalesContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId + '/espectaculos/' + $stateParams.espectaculoId + '/generos');
              }],
            requerimientos: ['$http', 'festivalesContext', '$stateParams',
              function ($http, festivalesContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId + '/espectaculos/' + $stateParams.espectaculoId + '/requerimientos');
              }
            ],
            companias: ['$http', 'festivalesContext', '$stateParams',
              function ($http, festivalesContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId + '/espectaculos/' + $stateParams.espectaculoId + '/companias');
              }],
            funciones: ['$http', 'festivalesContext', '$stateParams',
              function ($http, festivalesContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId + '/espectaculos/' + $stateParams.espectaculoId + '/funciones');
              }]
          },
          views: {
            'headerView': {
              templateUrl: basePath + 'espectaculos.header.html',
              controller: ['$scope', 'espectaculo',
                function ($scope, espectaculo) {
                  $scope.currentEspectaculo = espectaculo.data;
                }]
            },
            'infoView': {
              templateUrl: basePath + 'espectaculos.detail.html',
              controller: ['$scope', 'espectaculo',
                function ($scope, espectaculo) {
                  $scope.currentEspectaculo = espectaculo.data;
                }]
            },
            'generosView': {
              templateUrl: basePathGeneros + 'generos.list.html',
              controller: ['$scope', 'generos',
                function ($scope, generos) {
                  $scope.generosRecords = generos.data;
                }]
            },
            'requerimientosView': {
              templateUrl: basePathRequerimientos + 'requerimientos.list.html',
              controller: ['$scope', 'requerimientos',
                function ($scope, requerimientos) {
                  $scope.requerimientosRecords = requerimientos.data;
                }]
            },
            'companiasView': {
              templateUrl: basePathCompanias + 'companias.list.html',
              controller: ['$scope', 'companias',
                function ($scope, companias) {
                  $scope.companiasRecords = companias.data;
                }]
            },
            'funcionesView': {
              templateUrl: basePathFunciones + 'funciones.list.html',
              controller: ['$scope', 'funciones',
                function ($scope, funciones) {
                  $scope.funcionesRecords = funciones.data;
                }]
            }
          }
        });
    }]);
})(window.angular);
(function (ng) {
  var mod = ng.module('funcionesModule', ['ui.router']);

  mod.constant('funcionContext', '/funciones/');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

      var basePath = 'src/modulos/funcion/';
      var basePathLocalidades = 'src/modulos/localidad/';

      $urlRouterProvider.otherwise('/');

      $stateProvider
        .state('funciones', {
          url: '/funciones',
          abstract: true,
          parent: 'espectaculoDetail',
          views: {
            'mainView@': {
              templateUrl: basePath + 'funciones.html'
            }
          }
        })
        .state('funcionDetail', {
          url: '/{funcionIdLugar: int}/{funcionFecha: string}',
          parent: 'funciones',
          params: {
            funcionIdLugar: null,
            funcionFecha: null
          },
          resolve: {
            funcion: ['$http', 'festivalesContext', 'funcionContext', 'espectaculoContext', '$stateParams',
              function ($http, festivalesContext, funcionContext, espectaculoContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId +
                  espectaculoContext + $stateParams.espectaculoId +
                  funcionContext + $stateParams.funcionIdLugar + '/' + toDate($stateParams.funcionFecha));
              }],
            localidades: ['$http', 'festivalesContext', 'funcionContext', 'espectaculoContext', '$stateParams',
              function ($http, festivalesContext, funcionContext, espectaculoContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId +
                  espectaculoContext + $stateParams.espectaculoId +
                  funcionContext + $stateParams.funcionIdLugar + '/' + toDate($stateParams.funcionFecha) + '/localidades');
              }]
          },
          views: {
            'funcionView': {
              templateUrl: basePath + 'funciones.detail.html',
              controller: ['$scope', 'funcion', 'espectaculo', 'localidades',
                function ($scope, funcion, espectaculo, localidades) {
                  $scope.currentFuncion = funcion.data;
                  $scope.currentFuncion.localidades = localidades.data;
                  $scope.currentEspectaculo = espectaculo.data;//TODO SCOPE
                }]
            }
          }
        })
        .state('funcionLocalidades', {
          url: '/localidad/{localidadId: int}',
          parent: 'funcionDetail',
          param: {
            localidadId: null
          },
          resolve: {
            localidad: ['$http', 'festivalesContext', 'funcionContext', 'espectaculoContext', '$stateParams',
              function ($http, festivalesContext, funcionContext, espectaculoContext, $stateParams) {
                return $http.get(festivalesContext + $stateParams.festivalId +
                  espectaculoContext + $stateParams.espectaculoId +
                  funcionContext + $stateParams.funcionIdLugar + '/' + toDate($stateParams.funcionFecha) + '/localidades/' + $stateParams.localidadId);
              }]
          },
          views: {
            'localidadesView': {
              templateUrl: basePathLocalidades + 'localidades.detail.html',
              controller: ['$scope', 'localidad', 'CompraService',
                function ($scope, localidad, CompraService) {
                  $scope.currentLocalidad = localidad.data;
                  CompraService.setLocalidadActual($scope.currentLocalidad);
                }]
            }
          }
        });
    }]);

  function toDate (timestamp) {
    var d = new Date(Number(timestamp) - 18000000);
    // var s = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + 'T' + d.getHours() + ':' + d.getMinutes()
    return d.toISOString().substr(0, 16);
  }

})(window.angular);
(function (ng) {
  var mod = ng.module('localidadesModule', ['ui.router']);

  mod.constant('localidadContext', '/localidades/');

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/localidad/';
      $urlRouterProvider.otherwise('/');

      $stateProvider
        .state('localidadFuncion', {
          url: 'localidades/{localidadId}',
          parent: 'funcionLocalidades',
          param: {
            localidadId: null
          },
          views: {
            'localidadesView': {
              templateUrl: basePath + 'localidades.list.html',
              controller: ['$scope', '$stateParams', function ($scope, $params) {
                $scope.currentLocalidad = $scope.sRecords[$params.Id - 1];
              }]
            }
          }
        });
    }
  ])
  ;
})
(window.angular);
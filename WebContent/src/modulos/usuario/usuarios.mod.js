(function (ng) {
  var mod = ng.module('usuariosModule', ['ui.router']);

  mod.constant('usuariosContext', 'rest/usuarios');

  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    var basePath = 'src/modulos/usuario/';
    $urlRouterProvider.otherwise('/usuarios/list');

    $stateProvider
      .state('usuarios', {
        abstract: true,
        url: '/usuarios',
        views: {
          'mainView@': {
            templateUrl: basePath + 'usuarios.html'
          }
        }
      })
      .state('usuariosAdministradores', {
        url: '/admins',
        parent: 'usuarios',
        resolve: {
          admins: ['$http', 'usuariosContext',
            function ($http, usuariosContext) {
              return $http.get(usuariosContext + '?rol=Usuario%20Administrador');
            }]
        },
        views: {
          'listView': {
            templateUrl: basePath + 'usuarios.list.html',
            controller: ['$scope', 'admins',
              function ($scope, admins) {
                $scope.usuariosRecords = admins.data;
              }]
          }
        }
      })
      .state('usuariosCompanias', {
        url: '/companias',
        parent: 'usuarios',
        resolve: {
          companias: ['$http', 'usuariosContext',
            function ($http, usuariosContext) {
              return $http.get(usuariosContext + '?rol=Compania%20De%20Teatro');
            }]
        },
        views: {
          'listView': {
            templateUrl: basePath + 'companias.list.html',
            controller: ['$scope', 'companias',
              function ($scope, companias) {
                $scope.usuariosRecords = companias.data;
              }]
          }
        }
      });

  }]);
})(window.angular);
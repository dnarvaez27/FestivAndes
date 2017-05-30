(function (ng) {
  var mod = ng.module('aboutModule', ['ui.router']);

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

      $urlRouterProvider.otherwise('/about');

      $stateProvider
        .state('about', {
          url: '/about',
          views: {
            'bodyView': {
              templateUrl: 'assets/js/about/about.html',
              controller: ['$scope',
                function ($scope) {
                  $scope.bibliography = [
                    {
                      titulo: 'Database Systems- An Application-Oriented Approach',
                      autores: [
                        'Michael Kifer',
                        'Philip Lewis',
                        'Arthur Bernstein'
                      ]
                    },
                    {
                      titulo: 'Database Systems: The complete Book',
                      autores: [
                        'Jennifer Widom',
                        'Jeffrey Ullman',
                        'Hector Garcia-Molina'
                      ]
                    },
                    {
                      titulo: 'Database System Implementation',
                      autores: [
                        'Jennifer Widom',
                        'Jeffrey Ullman',
                        'Hector Garcia-Molina'
                      ]
                    },
                    {
                      titulo: 'Beginning SQL Queries From Novice to Professional',
                      autores: [
                        'Claire Churcher'
                      ]
                    },
                    {
                      titulo: 'A first course in database systems',
                      autores: [
                        'Jennifer Widom',
                        'Jeffrey Ullman'
                      ]
                    }
                  ];
                  $scope.integrantes = [
                    {
                      nombre: 'David Narvaez Guerrero',
                      desempeno: [
                        'Estudiante de Ingenieria de Sistemas y Computacion'
                      ]
                    },
                    {
                      nombre: 'Juan Diego Chaves',
                      desempeno: [
                        'Estudiante de Ingenieria de Sistemas y Computacion',
                        'Estudiante de Matemáticas'
                      ]
                    }
                  ];
                  $scope.periodo = '2017-10';
                  $scope.profesor = 'Claudia Jiménez';
                }]
            }
          }
        });
    }]);
})(window.angular);

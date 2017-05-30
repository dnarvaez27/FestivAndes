(function (ng) {
  var mod = ng.module('iteracionModule', ['ui.router']);

  mod.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
      $urlRouterProvider.otherwise('/iteracion');

      $stateProvider
        .state('iteracion', {
          url: '/iteracion/{iteracionNumber:int}',
          params: {
            iteracionNumber: null
          },
          views: {
            'bodyView': {
              templateUrl: 'assets/js/iteracion/iteracion.html',
              controller: ['$scope', '$stateParams',
                function ($scope, $stateParams) {

                  var iteraciones = [
                    {
                      numero: 1,
                      requerimientos_modificacion: [
                        {
                          codigo: 'RF1',
                          titulo: 'REGISTRAR USUARIO',
                          descripcion: 'Registra la información básica de un usuario (nombre, identificación, correo electrónico y rol) y la información particular según el rol. Debe basarse en la descripción de los siguientes requerimientos para definir de manera completa la información necesaria.'
                        },
                        {
                          codigo: 'RF2',
                          titulo: 'REGISTRAR CLIENTE',
                          descripcion: 'Registra la información básica de un cliente. Esta operación es realizada por un usuario administrador de FestivAndes'
                        },
                        {
                          codigo: 'RF3',
                          titulo: 'REGISTRAR COMPAÑÍA DE TEATRO',
                          descripcion: 'Registra la información básica de la compañía, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.'
                        },
                        {
                          codigo: 'RF4',
                          titulo: 'REGISTRAR ESPECTÁCULO',
                          descripcion: 'Registra la información básica de un espectáculo, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.'
                        },
                        {
                          codigo: 'RF5',
                          titulo: 'REGISTRAR SITIO',
                          descripcion: 'Registra la información básica de un sitio donde se realizan funciones, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.'
                        },
                        {
                          codigo: 'RF6',
                          titulo: 'PROGRAMAR FUNCIÓN',
                          descripcion: 'Registra la información de una función, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.'
                        },
                        {
                          codigo: 'RF7',
                          titulo: 'REGISTRAR PREFERENCIA DE CLIENTE',
                          descripcion: 'Registra la información sobre una preferencia de un cliente, cambiando una preferencia registrada anteriormente, introduciendo una nueva preferencia o quitando alguna previamente registrada. Esta operación es realizada por un usuario cliente de FestivAndes'
                        },
                        {
                          codigo: 'RF8',
                          titulo: 'REGISTRAR COMPRA DE BOLETA',
                          descripcion: 'Registra la compra de una boleta para una función, de acuerdo con el enunciado. El registro es aceptado siempre y cuando se cumplan las condiciones capacidad de la localidad y disponibilidad de la silla deseada. Esta operación es realizada por un usuario público no registrado de FestivAndes. Esta operación también puede ser realizada por un cliente de FestivAndes'
                        },
                        {
                          codigo: 'RF9',
                          titulo: 'REGISTRAR LA REALIZACIÓN DE UNA FUNCIÓN DE UN ESPECTÁCULO',
                          descripcion: 'Registra en el sistema que una función de un espectáculo fue efectivamente realizada. Inicialmente, por simplicidad, se supone que todos los usuarios que compraron boletas asisten a la función. Esta operación es realizada por un usuario operario de FestivAndes.'
                        }
                      ],
                      requerimientos_consulta: [
                        {
                          codigo: 'RFC1',
                          titulo: 'CONSULTAR LAS FUNCIONES DE ESPECTÁCULOS PROGRAMADOS EN FESTIVANDES',
                          descripcion: 'Los usuarios de FestivAndes pueden consultar las funciones de los espectáculos previstos en el festival. Esta consulta puede estar discriminada por rango de fechas, compañía de teatro, categoría de espectáculo, idioma, disponibilidad de traducción, restricciones, entre otros. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.'
                        },
                        {
                          codigo: 'RFC2',
                          titulo: 'CONSULTAR UN SITIO',
                          descripcion: 'El resultado incluye toda la información de las funciones programadas, incluyendo la fecha y hora en que se programan, toda la información relevante sobre el sitio, los espectáculos, las localidades con los precios y la boletería disponible. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.'
                        }
                      ]
                    }
                  ];

                  $scope.currentIteracion = iteraciones[$stateParams.iteracionNumber - 1];

                  $scope.currentReqMod = undefined;
                  $scope.setRequerimientoMod = function (req) {
                    if ($scope.currentReqMod === req) {
                      $scope.currentReqMod = undefined;
                    }
                    else {
                      $scope.currentReqMod = req;
                    }
                  };
                }]
            }
          }
        });
    }]);
})(window.angular);
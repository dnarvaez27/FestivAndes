(function (ng) {
  var app = angular.module('mainApp', [
    // External dependencies
    'ui.router',
    // Internal modules dependencies
    'usuariosModule',
    'localidadesModule',
    'funcionesModule',
    'espectaculosModule',
    'festivalesModule'
  ]);

  // CONTROLLERS
  app.controller('lugaresController', function ($scope, $http) {
    $http.get('rest/lugares')
      .then(function (response) {
        $scope.lugaresRecords = response.data;
      });
  });
  app.controller('lugarCtrl', function ($scope) {
    $scope.nombreLugar = function (id) {
      return $scope.lugaresRecords[id - 1].nombre;
    };
  });
  app.controller('operacionesBoletas', function ($scope, CompraService) {

    // TIPO DE OPERACION
    $scope.showingType = -1;
    $scope.mainMenu = function () {
      CompraService.resetSilla();
      $scope.revalidate();

      $scope.showingType = -1;
      $scope.statSillas(-1);
    };
    $scope.statSillas = function (type) {

      var p = document.getElementById('sillasLocalidad');
      var filas = p.children[0].children;

      type = ($scope.showingType === type) ? 0 : type;
      $scope.showingType = type;
      // default: Both Enabled
      // 1: Compra Unitaria ( Available Enabled )
      // 2: Compra Multiple ( Available Enabled )
      // 3: Eliminar Boleta ( Ocupped Enabled )
      for (var i = 0; i < filas.length; i++) {
        var f = filas[i];
        var fC = f.children;
        for (var j = 0; j < fC.length; j++) {
          var c = fC[j].children[0].children[0];
          switch (type) {
            case 1:
            case 2:
              if (c.children[0].className.match(/(?:^|\s)silla-ocup(?!\S)/)) {
                c.className += ' disabled';
              }
              else {
                c.className = c.className.replace(/(?:^|\s)disabled(?!\S)/g, '');
              }
              break;
            case 3:
              if (c.children[0].className.match(/(?:^|\s)silla-disp(?!\S)/)) {
                c.className += ' disabled';
              }
              else {
                c.className = c.className.replace(/(?:^|\s)disabled(?!\S)/g, '');
              }
              break;
            default:
              c.className = c.className.replace(/(?:^|\s)disabled(?!\S)/g, '');
          }
        }
      }
    };

    // COMPRA BOLETA
    $scope.haySeleccion = false;
    $scope.getSelectedSilla = function () {
      $scope.revalidate();
      return CompraService.getSilla();
    };
    $scope.seleccionarSilla = function (silla) {
      if (silla.disponibilidad === 0) {
        CompraService.selectSilla(silla);
      }
    };

    $scope.revalidate = function () {
      $scope.haySeleccion = CompraService.getSilla().numFila !== -1;
    };

    $scope.getLocalidad = function () {
      $scope.revalidate();
      return CompraService.getLocalidadActual();
    };
    $scope.setLocalidad = function (localidad) {
      CompraService.resetSilla();
      CompraService.setLocalidadActual(localidad);
    };
  });
  app.controller('companiaInfo', function ($scope) {
    $scope.showModal = function (comp) {
      $scope.currentCompania = comp;
      $('#modalCompania').modal('show');
    };
  });

  // DIRECTIVES
  app.directive('sillaStatus', function () {
    return {
      restrict: 'E',
      scope: {
        silla: '='
      },
      templateUrl: 'src/modulos/localidad/silla.html'
    };
  });
  app.directive('funcionOpciones', function () {
    return {
      restrict: 'E',
      templateUrl: 'src/modulos/funcion/opciones.html'
    };
  });
  app.directive('usuario', function () {
    return {
      restrict: 'E',
      scope: {
        user: '=',
        showUsuario: '&?'
      },
      templateUrl: 'src/modulos/usuario/user.html'
    };
  });

  // SERVICES
  app.service('CompraService', function () {
    this.sillaSeleccionada = {numFila: -1, numSilla: -1};
    this.localidad = {};

    this.resetSilla = function () {
      this.sillaSeleccionada = {numFila: -1, numSilla: -1};
    };
    this.selectSilla = function (silla) {
      this.sillaSeleccionada = silla;
    };
    this.getSilla = function () {
      return this.sillaSeleccionada;
    };
    this.getLocalidadActual = function () {
      return this.localidad;
    };
    this.setLocalidadActual = function (localidad) {
      this.sillaSeleccionada = {numFila: -1, numSilla: -1};
      this.localidad = localidad;
    };
  });

  // Resuelve problemas de las promesas
  app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
  }]);
})(window.angular);
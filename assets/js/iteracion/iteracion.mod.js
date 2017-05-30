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

                  $scope.showModal = false;
                  var iteraciones = [
                    {
                      numero: 1,
                      requerimientos_modificacion: [
                        {
                          codigo: 'FEST',
                          titulo: 'Festival',
                          descripcion: 'El festival se programa en un rango de fechas, durante las cuales se ofrecen espectáculos que son promocionados por FestivAndes en lugares previamente acordados.'
                        },
                        {
                          codigo: 'COMP',
                          titulo: 'Compañias de Teatro',
                          descripcion: 'Las compañías de teatro son quienes ofrecen los espectáculos programados en FestivAndes. Una compañía de teatro tiene un nombre con el cual se le reconoce en el medio artístico, un representante que es la persona que maneja los contratos, un país de origen y, opcionalmente, una página Web donde publica sus logros y trayectoria. Una compañía puede ofrecer varios espectáculos durante la realización de FestivAndes. Cada compañía tiene además una fecha prevista de llegada y de salida de la ciudad donde se realiza el festival.'
                        },
                        {
                          codigo: 'ESPE',
                          titulo: 'Espectaculos',
                          descripcion: 'Los espectáculos son el objetivo fundamental del festival. Un espectáculo tiene un nombre que lo identifica ante el público, duración, requerimientos técnicos y posiblemente idioma en el que se presenta y un costo de realización. Puede ser ofrecido por una compañía o por varias que cooperan en su realización. Un espectáculo puede ser diseñado de manera que el público sea espectador de la obra o que participe en ella. Puede requerir de uso de elementos como agua, espumas, vidrios, montajes de escenarios, herramientas, elementos de grandes alturas, efectos visuales o de luces, efectos de sonido o de interacción con el público, movimiento del público durante la obra, movimiento del escenario o plataformas intercambiables durante las escenas. Cuando un espectáculo se presenta en un idioma diferente al local, puede incluir servicio de traducción simultánea mediante subtítulos o mediante audífonos personalizados para los asistentes. El espectáculo presenta en su promoción una descripción corta de lo que el público puede esperar y el público objetivo (adultos, familias, infantil, jóvenes, etc.).'
                        },
                        {
                          codigo: 'GENR',
                          titulo: 'Generos',
                          descripcion: 'Para facilitar la selección de espectáculos al público, estos se clasifican de acuerdo con categorías conocidas: drama,teatro mudo, títeres, comparsas, ópera, zarzuela, musical, circo, etc. Un espectáculo puede ser clasificado en múltiples categorías. No se espera que FestivAndes verifique la coherencia artística de las categorías definidas para un espectáculo'
                        },
                        {
                          codigo: 'FNCN',
                          titulo: 'Funcion',
                          descripcion: 'Una función se programa para presentar un espectáculo. El sitio en el cual se programa debe satisfacer los requerimientos del espectáculo y corresponde a la realización en cierto día y hora. Un espectáculo puede ser programado en muchas funciones, en fechas, horas y lugares diferentes, respetando también su duración; en cualquier caso, deben ser realizadas durante las fechas previstas para la realización del festival y la presencia de la(s) compañía(s) responsable(s) en el festival.'
                        },
                        {
                          codigo: 'LGRS',
                          titulo: 'Lugares',
                          descripcion: 'Los sitios en los cuales se ofrecen los espectáculos pueden ser abiertos o cerrados. Por ejemplo, pueden ser parques, coliseos, teatros, auditorios, etc. De cada sitio se conoce la capacidad de público que puede albergar, si es apto para el ingreso de personas con necesidades especiales (menores de edad, restricciones de movilidad, personas mayores de edad, etc.), el horario de disponibilidad diaria para la realización de espectáculos durante el festival, las condiciones técnicas que ofrece (amplificación de sonido, efectos de luz, fuegos artificiales, efectos 4D, efectos 3D, interacción con el público, efectos en alturas, instalación de escenarios propios, instalaciones interactivas, etc.), tipo de silletería (móvil, fija o removible), protección frente a lluvia o efectos atmosféricos.'
                        },
                        {
                          codigo: 'LOCL',
                          titulo: 'Localidades',
                          descripcion: 'Para una determinada función el sitio en el que se programa puede ofrecer diferentes tipos de localidades, con comodidades o beneficios específicos, las cuales determinan el valor de la boleta. Por ejemplo, puede ofrecer VIP, platea, platea lateral, intermedia, intermedia lateral, general, etc. Una localidad puede ser numerada, en cuyo caso cada sitio disponible cuenta con un número de fila y un número de silla dentro de la fila. También puede ser no numerada, en cuyo caso se tiene la información sobre la capacidad permitida'
                        },
                        {
                          codigo: 'ORGN',
                          titulo: 'Organizadores',
                          descripcion: 'Los organizadores del festival son los responsables de la programación de las funciones, de tal manera que seofrezcan todos los espectáculos previstos en los sitios adecuados. Ellos requieren conocer la disponibilidad de cada uno de los sitios potenciales para la realización de un espectáculo y las fechas de disponibilidad de las compañías para cada una de las funciones. De acuerdo con la demanda del público por un espectáculo pueden programar nuevas funciones o cancelar algunas. Son además los responsables de las finanzas del festival, por lo cual están interesados en conocer el balance de ingresos por venta de boletería y de costos asociados con las funciones programadas.'
                        },
                        {
                          codigo: 'ESPC',
                          titulo: 'Espectadores',
                          descripcion: 'El público interesado en el festival puede consultar toda la información sobre el festival, incluyendo programación de funciones, disponibilidad de boletas, espectáculos previstos para cierta fecha o de cierto tipo. Además, puede comprar boletas para una determinada función, en una localidad indicada. No es necesario estar registrado para hacer estas operaciones. Los pagos se realizan mediante servicio PSE.'
                        },
                        {
                          codigo: 'RGST',
                          titulo: 'Usuarios Registrados',
                          descripcion: 'Para el público que decide registrarse como usuario del sistema (cliente del festival) es factible filtrar las funciones de acuerdo con preferencias de categorías previamente seleccionadas, por edad o por preferencia de sitios.'
                        }
                      ],
                      requerimientos_consulta: [],
                      requerimientos_no_funcionales: [
                        {
                          codigo: 'MBOL',
                          titulo: 'Compra múltiple de boletas',
                          descripcion: 'Comprar al mismo tiempo varias boletas para una determinada función. Todas las boletas que se compran a la vez deben corresponder al mismo tipo de localidad. Si la localidad es numerada, el sistema debe realizar la asignación correspondiente para cada una de ellas, garantizando que todas ellas queden contiguas. En ningún caso se puede superar la capacidad de una localidad no numerada.'
                        },
                        {
                          codigo: 'DBOL',
                          titulo: 'Devolución de Boletas',
                          descripcion: 'Devolver boleta. Esta operación se puede hacer hasta cinco (5) días antes de la función correspondiente. Se genera una nota débito con la cual el cliente puede ir a recuperar su dinero en la entidad financiera de FestivAndes'
                        },
                        {
                          codigo: 'CABO',
                          titulo: 'Compra de Abono',
                          descripcion: 'Comprar un abonamiento, que es personal e intransferible. El usuario indica la lista de funciones deseada, cada una con la localidad a la que quiere asistir. Con la compra del abonamiento, el cliente recibe un descuento del 20% en todas las boletas. La operación es válida cuando se realiza con más tres semanas de anticipación con respecto al inicio del festival y hay boletas disponibles en todos y cada una de las funciones y localidades involucradas. No es responsabilidad de FestivAndes verificar la coherencia de la programación en la lista del usuario'
                        },
                        {
                          codigo: 'DABO',
                          titulo: 'Devolver un Abono',
                          descripcion: 'Devolver un abonamiento. La cancelación del abonamiento sólo puede hacerse antes de tres semanas del comienzo del festival. Las boletas correspondientes quedan otra vez disponibles para la venta. Se genera una nota débito con la cual el cliente puede ir a recuperar su dinero en la entidad financiera de FestivAndes.'
                        }
                      ],
                      aprendido: [
                        'Modelado de Datos',
                        'Modelo Conceptual y Relacional',
                        'Álgebra Relacional',
                        'SQL'
                      ]
                    },
                    {
                      numero: 2,
                      requerimientos_modificacion: [
                        {
                          codigo: 'RF1',
                          titulo: 'Registrar Usuario',
                          descripcion: 'Registra la información básica de un usuario (nombre, identificación, correo electrónico y rol) y la información particular según el rol. Debe basarse en la descripción de los siguientes requerimientos para definir de manera completa la información necesaria.'
                        },
                        {
                          codigo: 'RF2',
                          titulo: 'Registrar Cliente',
                          descripcion: 'Registra la información básica de un cliente. Esta operación es realizada por un usuario administrador de FestivAndes'
                        },
                        {
                          codigo: 'RF3',
                          titulo: 'Registrar Compañia de Teatro',
                          descripcion: 'Registra la información básica de la compañía, de acuerdo con el enunciado. Esta operación es realizada por un <strong>usuario administrador</strong> de FestivAndes.'
                        },
                        {
                          codigo: 'RF4',
                          titulo: 'Registrar Espectáculo',
                          descripcion: 'Registra la información básica de un espectáculo, de acuerdo con el enunciado. Esta operación es realizada por un <strong>usuario administrador</strong> de FestivAndes.'
                        },
                        {
                          codigo: 'RF5',
                          titulo: 'Registrar Sitio',
                          descripcion: 'Registra la información básica de un sitio donde se realizan funciones, de acuerdo con el enunciado. Esta operación es realizada por un <strong>usuario administrador</strong> de FestivAndes.'
                        },
                        {
                          codigo: 'RF6',
                          titulo: 'Programar Función',
                          descripcion: 'Registra la información de una función, de acuerdo con el enunciado. Esta operación es realizada por un <strong>usuario administrador</strong> de FestivAndes.'
                        },
                        {
                          codigo: 'RF7',
                          titulo: 'Registrar Preferencia de Cliente',
                          descripcion: 'Registra la información sobre una preferencia de un cliente, cambiando una preferencia registrada anteriormente, introduciendo una nueva preferencia o quitando alguna previamente registrada. Esta operación es realizada por un <strong>usuario cliente</strong> de FestivAndes'
                        },
                        {
                          codigo: 'RF8',
                          titulo: 'Registrar Compra de Boleta',
                          descripcion: 'Registra la compra de una boleta para una función, de acuerdo con el enunciado. El registro es aceptado siempre y cuando se cumplan las condiciones capacidad de la localidad y disponibilidad de la silla deseada. Esta operación es realizada por un <strong>usuario público no registrado</strong> de FestivAndes. Esta operación también puede ser realizada por un <strong>cliente</strong> de FestivAndes'
                        },
                        {
                          codigo: 'RF9',
                          titulo: 'Registrar la Realización de una Función de un Espectáculo',
                          descripcion: 'Registra en el sistema que una función de un espectáculo fue efectivamente realizada. Inicialmente, por simplicidad, se supone que todos los usuarios que compraron boletas asisten a la función. Esta operación es realizada por un <strong>usuario operario</strong> de FestivAndes.'
                        }
                      ],
                      requerimientos_consulta: [
                        {
                          codigo: 'RFC1',
                          titulo: 'Consultar las Funciones de Espectáculos Programados en Festivandes',
                          descripcion: 'Los usuarios de FestivAndes pueden consultar las funciones de los espectáculos previstos en el festival. Esta consulta puede estar discriminada por rango de fechas, compañía de teatro, categoría de espectáculo, idioma, disponibilidad de traducción, restricciones, entre otros. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.'
                        },
                        {
                          codigo: 'RFC2',
                          titulo: 'Consultar un Sitio',
                          descripcion: 'El resultado incluye toda la información de las funciones programadas, incluyendo la fecha y hora en que se programan, toda la información relevante sobre el sitio, los espectáculos, las localidades con los precios y la boletería disponible. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.'
                        },
                        {
                          codigo: 'RFC3',
                          titulo: 'Generar un Reporte de una Función',
                          descripcion: 'Genera un reporte con la cantidad de boletas vendidas y el producido ($) de una función. Debe discriminar por localidad dentro del sitio y por usuarios no registrados o clientes'
                        },
                        {
                          codigo: 'RFC4',
                          titulo: 'Generar un Reporte de Espectáculo',
                          descripcion: 'Genera un reporte con la cantidad de boletas vendidas y el producido ($) de un espectáculo. Debe discriminar por función, por sitio y por usuarios no registrados o clientes. Para cada sitio, debe incluir información sobre el porcentaje de ocupación en cada función. Este porcentaje se calcula teniendo en cuenta la capacidad total del sitio.'
                        },
                        {
                          codigo: 'RFC5',
                          titulo: 'Consultar Rentabilidad de una Compañia de Teatro',
                          descripcion: 'En un rango de fechas, el sistema debe informar por sitio, por tipo de sitio, por espectáculo y por categoría, el número de boletas vendidas, el número de asistentes a cada función, la proporción de asistencia con respecto a la capacidad y el valor total facturado. El resultado debe estar ordenado de mayor a menor por el total del valor facturado.',
                          nota: 'NOTA: Respetando la privacidad de los usuarios, cuando un usuario compañía hace esta consulta, la respuesta sólo contiene la información de su compañía, mientras que el gerente de FestivAndes puede ver toda la información. Ver <a class="btn btn-primary btn-xs" ng-click="search(\'RNF1\')">RNF1</a>.'
                        },
                        {
                          codigo: 'RFC6',
                          titulo: 'Obtener los Datos de los Espectáculos más Populares',
                          descripcion: 'Esta consulta retorna toda la información de los espectáculos con mayor ocupación (cantidad de asistentes) en un periodo de tiempo, para todas sus funciones.'
                        }
                      ],
                      requerimientos_no_funcionales: [
                        {
                          codigo: 'RNF1',
                          titulo: 'Privacidad',
                          descripcion: 'Los usuarios de FestivAndes solo pueden manipular y consultar la información que les es propia.'
                        },
                        {
                          codigo: 'RNF2',
                          titulo: 'Persistencia',
                          descripcion: 'La información manipulada por la aplicación debe ser persistente. Recuerde que la información que se requiere para resolver un requerimiento funcional no cabe simultáneamente en memoria principal'
                        },
                        {
                          codigo: 'RNF3',
                          titulo: 'Concurrencia',
                          descripcion: 'Los requerimientos pueden ser solicitados de manera concurrente.'
                        },
                        {
                          codigo: 'RNF4',
                          titulo: 'Distribución',
                          descripcion: 'La base de datos de la aplicación está centralizada.'
                        }
                      ],
                      aprendido: [
                        'SQL',
                        'Cálculo & Álgebra Relacional',
                        'Normalización'
                      ]
                    },
                    {
                      numero: 3,
                      requerimientos_modificacion: [
                        {
                          codigo: 'RF10',
                          titulo: 'Registrar Compra Múltiple de Boletas',
                          descripcion: 'Registra la compra de grupos de boletas para una función, de acuerdo con el enunciado. Esta operación puede ser realizada por un <strong>cliente registrado</strong> de FestivAndes. <strong>DEBE</strong> utilizar como subtransacción la implementación del requerimiento <a class="btn btn-primary btn-xs" ng-click="search(\'RF8\')">RF8</a> de la iteración anterior.',
                        },
                        {
                          codigo: 'RF11',
                          titulo: 'Registrar Compra de un Abonamiento',
                          descripcion: 'Registra la compra de un abonamiento, de acuerdo con el enunciado. Esta operación puede ser realizada por un <strong>cliente registrado</strong> de FestivAndes. <strong>DEBE</strong> utilizar como subtransacción la implementación del requerimiento <a class="btn btn-primary btn-xs" ng-click="search(\'RF8\')">RF8</a> de la iteración anterior.',
                        },
                        {
                          codigo: 'RF12',
                          titulo: 'Devolver Boleta',
                          descripcion: 'Registra la devolución de una boleta, de acuerdo con el enunciado. Esta operación puede ser realizada por un <strong>cliente registrado</strong> de FestivAndes.',
                        },
                        {
                          codigo: 'RF13',
                          titulo: 'Devolver Abonamiento',
                          descripcion: 'Registra la devolución de un abonamiento, de acuerdo con el enunciado. Esta operación puede ser realizada por un <strong>cliente registrado</strong> de FestivAndes. <strong>DEBE</strong> utilizar como subtransacción la implementación de los requerimientos <a class="btn btn-primary btn-xs" ng-click="search(\'RF12\')">RF12</a>.',
                        },
                        {
                          codigo: 'RF14',
                          titulo: 'Cancelar una Función',
                          descripcion: 'Cancela la realización de una función programada en FestivAndes. Puede darse, inclusive, si la función ya ha empezado, pero no si ya terminó. Cuando se cancela una función, se debe devolver el dinero de todos los usuarios registrados que tienen boletas adquiridas. Esta operación puede ser realizada por un <strong>usuario administrador</strong> de FestivAndes. <strong>DEBE</strong> utilizar como subtransacción la implementación del requerimiento <a class="btn btn-primary btn-xs" ng-click="search(\'RF12\')">RF12</a>.',
                        }
                      ],
                      requerimientos_consulta: [
                        {
                          codigo: 'RFC7',
                          titulo: 'Consultar Asistencia al Festival de un Cliente Registrado',
                          descripcion: 'Consulta las funciones a las que asiste un cliente registrado de FestivAndes. Debe discriminar las funciones ya realizadas o en curso, las funciones previstas y las boletas devueltas. Esta operación es realizada por los <strong>clientes registrados</strong> y por el <strong>usuario administrador</strong> de FestivAndes.',
                          nota: 'NOTA: Respetando la privacidad de los clientes, cuando un cliente registrado hace esta consulta obtiene la información de sus propia actividad, mientras que el administrador obtiene toda la información de cualquiera de los clientes. Ver <a class="btn btn-primary btn-xs" ng-click="search(\'RNF1\')">RNF1.</a>',
                        },
                        {
                          codigo: 'RFC8',
                          titulo: 'Consultar Compañia',
                          descripcion: 'Muestra la información relevante a una compañía de teatro. Consolida para cada uno de sus espectáculos la asistencia total, la asistencia de clientes registrados, el dinero generado en la taquilla y porcentaje de ocupación de sus funciones por cada sitio de realización. Esta operación es realizada por los <strong>usuarios compañía de teatro</strong> y el administrador de FestivAndes.',
                          nota: 'NOTA: Respetando la privacidad de los clientes, cuando un una compañía hace esta consulta obtiene la información de sus propias funciones, mientras que el administrador obtiene toda la información. Ver <a class="btn btn-primary btn-xs" ng-click="search(\'RNF1\')">RNF1</a>.',
                        }
                      ],
                      requerimientos_no_funcionales: [
                        {
                          codigo: 'RNF5',
                          titulo: 'Transaccionalidad',
                          descripcion: 'Debe tenerse en cuenta que en el funcionamiento diario de FestivAndes puede haber solicitudes simultáneas, que pueden comprometer los planes de los clientes. FestivAndes debe asegurar la transaccionalidad en el proceso de registro de compras y devolución de boletas de los espectáculos. '
                        }
                      ],
                      aprendido: [
                        'Manejo Transaccional Centralizado',
                        'Control de Concurrencia',
                        'Atomicidad - Recuperación ante fallas',
                        'Jerarquía de Memoria - Funcionamiento y Tiempos de Disco',
                        'Ordenamiento Externo'
                      ]
                    },
                    {
                      numero: 4,
                      requerimientos_modificacion: [],
                      requerimientos_consulta: [
                        {
                          codigo: 'RFC9',
                          titulo: 'Consultar Asistencia a Festivandes',
                          descripcion: 'Se quiere conocer la información de los usuarios que asistieron al menos a una función de una determinada compañía en un <strong>rango de fechas</strong>. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.'
                        },
                        {
                          codigo: 'RFC10',
                          titulo: 'Consultar Asistencia a Festivandes - RFC9-v2',
                          descripcion: 'Se quiere conocer la información de los usuarios que <strong>NO</strong> asistieron al menos a una función de una determinada compañía en un rango de fechas. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.'
                        },
                        {
                          codigo: 'RFC11',
                          titulo: 'Consultar Compras de Boletas',
                          descripcion: 'Muestra la información de las boletas compradas por usuarios de acuerdo con las características de las funciones correspondientes. Esta consulta puede ser filtrada por diferentes conceptos (rangos de fecha, elementos del escenario, tipo de localidad, franja horaria y día de la semana) y se espera como resultado: nombre del espectáculo, fecha de la función, sitio de la función, cantidad de boletas vendidas, cantidad de usuarios registrados, entre otros. Esta operación es realizada el <strong>gerente</strong> general de FestivAndes.'
                        },
                        {
                          codigo: 'RFC12',
                          titulo: 'Consultar los Buenos Clientes',
                          descripcion: 'Los buenos clientes son aquellos que compran siempre en localidad <strong>VIP</strong> y también son aquellos que compran al menos <strong>n</strong> boletas durante el festival. Esta consulta retorna toda la información de dichos clientes. Esta operación es realizada únicamente por el <strong>gerente</strong> general de FestivAndes'
                        }
                      ],
                      requerimientos_no_funcionales: [
                        {
                          codigo: 'RNF6',
                          titulo: 'Eficiencia en las Consultas',
                          descripcion: 'Las consultas deben tardar un máximo de <strong>0,8</strong> segundos, independiente del tamaño de la base de datos'
                        },
                        {
                          codigo: 'RNF7',
                          titulo: 'Eficiencia en la Actualización',
                          descripcion: 'La aplicación debe garantizar eficiencia en la ejecución de los requerimientos de modificación solicitados'
                        },
                        {
                          codigo: 'RNF8',
                          titulo: 'Esquema Físico de la Base de Datos',
                          descripcion: 'El diseño físico de la base de datos debe reflejar un balance global de eficiencia de la aplicación'
                        }
                      ],
                      aprendido: [
                        'Afinamiento de Bases de Datos',
                        'Índices: Tipos y Selección',
                        'Joins: Tipos y Selección'
                      ]
                    },
                    {
                      numero: 5,
                      requerimientos_modificacion: [
                        {
                          codigo: 'RF15',
                          titulo: 'Registrar la Compra de Un Abonamiento - v2',
                          descripcion: 'Es el mismo requerimiento <a class="btn btn-primary btn-xs" ng-click="search(\'RF11\')">RF11</a>, sólo que ahora puede involucrar funciones y sitios de dos o tres de las unidades de negocio que participan en la aplicación. La nueva versión del requerimiento permite completar el abonamiento utilizando recursos disponibles en las otras unidades.<ul><li>El requerimiento solo es posible cuando para todas las funciones asociadas al abonamiento hay por lo menos una boleta disponible. En caso contrario se registra la solicitud del servicio con un estado de <code>rechazada</code>. <br><strong>NOTA: Los casos de prueba de prueba deben garantizar que el abonamiento no puede ser satisfecho por una sola de las unidades.</strong></li><li>Si la solicitud puede ser atendida, cada uno de las unidades indica las boletas que puede asumir, con toda la información correspondiente.<ul><li>Cada una de las unidades involucrados al confirmar su participación asegura el cumplimiento del servicio y la garantía de la integridad en el manejo de la información que le corresponde.</li></ul></li><li>El final de la solicitud se registra cuando la último de las unidades realiza de forma correcta el abonamiento solicitado. En ese momento, se debe informar al usuario con un número de confirmación y el estado de la solicitud. El número de confirmación es único en FestivAndes. Si alguna de las unidades no puede confirmar de forma inmediata la disponibilidad de boletas en sus funciones, dicha solicitud debe quedar en estado <code>rechazada</code>. La operación queda en estado <code>realizada</code> cuando puede ser procesada en su totalidad.</li></ol>'
                        },
                        {
                          codigo: 'RF16',
                          titulo: 'Retirar Compañia de Teatro',
                          descripcion: 'Este requerimiento se utiliza cuando una compañía, por razones ajenas a FestivAndes, decide retirarse de los festivales en los que inicialmente había decidido participar. Las funciones programadas deben ser canceladas (Ver <a class="btn btn-primary btn-xs" ng-click="search(\'RF14\')">RF14</a> ) y se debe garantizar que la base de datos quede en estado coherente. Esta operación puede ser realizada por uno de los <strong>administradores</strong> de FestivAndes'
                        }
                      ],
                      requerimientos_consulta: [
                        {
                          codigo: 'RFC13',
                          titulo: 'Consultar las Funciones Disponibles en Festivandes - RFC1 v2',
                          descripcion: 'Es el requerimiento <a class="btn btn-primary btn-xs" ng-click="search(\'RFC1\')">RFC1</a>, teniendo en cuenta que debe mostrar el detalle de los tres proveedores de la alianza.'
                        },
                        {
                          codigo: 'RFC14',
                          titulo: 'Consultar la Rentabilidad de las Compañias - RFC5 v2',
                          descripcion: 'Es el requerimiento <a class="btn btn-primary btn-xs" ng-click="search(\'RFC5\')">RFC5</a>, teniendo en cuenta que la información a reportar incluye el detalle de los valores facturados en cada proveedor y el total facturado en el periodo de consulta.'
                        }
                      ],
                      requerimientos_no_funcionales: [
                        {
                          codigo: 'RNF9',
                          titulo: 'Distribución 2',
                          descripcion: 'La aplicación se puede utilizar desde cualquier sitio y desde la administración de FestivAndes. La base de datos de la aplicación está distribuida en tres puertos que se aliaron.'
                        },
                        {
                          codigo: 'RNF10',
                          titulo: 'Disponibilidad',
                          descripcion: 'FestivAndes debe funcionar hacia sus usuarios, aunque alguno de los sistemas de los puertos tenga fallas puntuales de disponibilidad o pueda estar congestionado y su tiempo de respuesta pueda estar degradado.'
                        }
                      ],
                      aprendido: [
                        'Manejo Transaccional Distribuido',
                        'Atomicidad con Bases de Datos Distribuidas',
                        'Control de Concurrencia con Bases de Datos Distribuidas',
                        'Arquitectura de Sistemas Transaccionales con Bases de Datos Distribuidas',
                        'Two Phase Commit & Colas de Mensajes'
                      ]
                    }
                  ];
                  $scope.stackTrace = [];

                  $scope.currentIteracion = iteraciones[$stateParams.iteracionNumber - 1];

                  $scope.currentReq = undefined;
                  $scope.setRequerimientoMod = function (req) {
                    $scope.currentReq = req;
                    var nn = $('#nextButMod');
                    nn.focus();
                    $scope.showModal = true;
                    nn.focus();
                  };
                  $scope.hideModal = function () {
                    $scope.currentReq = undefined;
                    $scope.showModal = false;
                    $scope.stackTrace = [];
                  };

                  $scope.nextReq = function () {
                    var reqs = $scope.currentIteracion.requerimientos_modificacion
                      .concat($scope.currentIteracion.requerimientos_consulta)
                      .concat($scope.currentIteracion.requerimientos_no_funcionales);
                    var index = reqs.indexOf($scope.currentReq);
                    $scope.currentReq = reqs[( index + 1 ) % ( reqs.length )];
                  };
                  $scope.prevReq = function () {
                    var reqs = $scope.currentIteracion.requerimientos_modificacion
                      .concat($scope.currentIteracion.requerimientos_consulta)
                      .concat($scope.currentIteracion.requerimientos_no_funcionales);
                    var index = reqs.indexOf($scope.currentReq);
                    var i = (index - 1) < 0 ? reqs.length - 1 : (index - 1);
                    $scope.currentReq = reqs[i % ( reqs.length )];
                  };

                  $scope.keyPress = function (event) {
                    if (event.key === 'ArrowRight') {
                      $scope.nextReq();
                    }
                    else if (event.key === 'ArrowLeft') {
                      $scope.prevReq();
                    }
                    else if (event.key === 'Escape') {
                      $scope.hideModal();
                    }
                  };

                  $scope.search = function (codigo) {
                    console.log('searching...' + codigo);
                    iteraciones.forEach(function (item) {

                      var reqs = item.requerimientos_modificacion
                        .concat(item.requerimientos_consulta)
                        .concat(item.requerimientos_no_funcionales);

                      reqs.forEach(function (req) {
                        if (req.codigo === codigo) {

                          $scope.stackTrace.push($scope.currentReq);

                          $scope.currentReq = req;
                          return req;
                        }
                      });
                    });
                  };

                  $scope.goBack = function () {
                    $scope.currentReq = $scope.stackTrace.pop();
                  };

                  $scope.validar = function (string) {
                    if (string !== undefined) {
                      var urlRegEx = /((([A-Za-z]{3,9}:(?:\/\/)?)(?:[\-;:&=\+\$,\w]+@)?[A-Za-z0-9\.\-]+|(?:www\.|[\-;:&=\+\$,\w]+@)[A-Za-z0-9\.\-]+)((?:\/[\+~%\/\.\w\-]*)?\??(?:[\-\+=&;%@\.\w]*)#?(?:[\.\!\/\\\w]*))?)/g;
                      return string.replace(urlRegEx, string);
                    }
                    return '';
                  };
                }]
            }
          }
        });
    }]);

  mod.directive('compile', ['$compile', function ($compile) {
    return function (scope, element, attrs) {
      scope.$watch(
        function (scope) {
          return scope.$eval(attrs.compile);
        },
        function (value) {
          element.html(value);
          $compile(element.contents())(scope);
        }
      );
    };
  }]);

})(window.angular);
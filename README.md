# Iteracion
> Sistemas Transaccionales 2017-1  
> Universidad de Los Andes

## Contexto
Una de las expresiones artísticas más importantes y populares en el quehacer cultural de una sociedad es el teatro. Con el ánimo de difundir la cultura y como medio de entretenimiento, muchas corporaciones y ciudades organizan festivales de teatro, en los cuales participan actores de varios países y diversos géneros y que generalmente copan las instalaciones de espectáculos de las que dispone una ciudad y que atraen una gran cantidad de público. Con este propósito, se quiere crear <b>FestivAndes</b>, una aplicación informática disponible tanto para los organizadores como para el público, que apoya los procesos de organización y ejecución de un festival de teatro, aprovechando la tecnología de comunicaciones existente y teniendo en cuenta toda la legislación cultural y de derechos de autor del país.  
Por último, hay que tener en cuenta que la dinámica cultural y económica del país, obliga a que FestivAndes esté preparado para eventuales ampliaciones o modificaciones, ya sea desde el punto de vista de cobertura geográfica, nuevos actores, fusión, entre otras.  
El objetivo de este trabajo es la construcción de FestivAndes, que ofrece sus servicios de manera confiable y efectiva para la comunidad, de acuerdo con las políticas y expectativas descritas.

## Descripción
FestivAndes es responsable del manejo de la realización del Festival de Teatro Los Andes, de forma que los organizadores, los operadores de logística y público encuentren la mejor experiencia cultural. Los servicios ofrecidos por FestivAndes permiten al público obtener información sobre la programación cultural del Festival, de manera que pueda conocer toda la oferta de funciones que están programadas, las alternativas de pago y las características de logística propias a cada espectáculo programado. De igual manera, los organizadores pueden hacer un seguimiento detallado de la evolución del festival, monitoreando las ventas realizadas, sobre la realización de los espectáculos, de sus respectivas funciones y sobre la demanda y resultados de cada una de ellas. Finalmente, los operadores logísticos pueden tener información oportuna que les permite reaccionar rápidamente para suplir los requerimientos de cada función de acuerdo con el público asistente y los requerimientos del espectáculo.

## Requerimientos
### Parte 1:
#### Requerimientos Funcionales de Modificación:
  * <b>RF1 - REGISTRAR USUARIO:</b>  
  Registra la información básica de un usuario (nombre, identificación, correo electrónico y rol) y la información particular según el rol. Debe basarse en la descripción de los siguientes requerimientos para definir de manera completa la información necesaria.
  * <b>RF2 - REGISTRAR CLIENTE:</b>  
  Registra la información básica de un cliente. Esta operación es realizada por un usuario administrador de FestivAndes.
  * <b>RF3 - REGISTRAR COMPAÑÍA DE TEATRO:</b>  
  Registra la información básica de la compañía, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.
  * <b>RF4 - REGISTRAR ESPECTÁCULO:</b>  
  Registra la información básica de un espectáculo, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.
  * <b>RF5 - REGISTRAR SITIO:</b>  
  Registra la información básica de un sitio donde se realizan funciones, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.
  * <b>RF6 - PROGRAMAR FUNCIÓN:</b>   
  Registra la información de una función, de acuerdo con el enunciado. Esta operación es realizada por un usuario administrador de FestivAndes.
  * <b>RF7 - REGISTRAR PREFERENCIA DE CLIENTE:</b>  
  Registra la información sobre una preferencia de un cliente, cambiando una preferencia registrada anteriormente, introduciendo una nueva preferencia o quitando alguna previamente registrada. Esta operación es realizada por un usuario cliente de FestivAndes.
  * <b>RF8 - REGISTRAR COMPRA DE BOLETA:</b>  
  Registra la compra de una boleta para una función, de acuerdo con el enunciado. El registro es aceptado siempre y cuando se cumplan las condiciones capacidad de la localidad y disponibilidad de la silla deseada. Esta operación es realizada por un usuario público no registrado de FestivAndes. Esta operación también puede ser realizada por un cliente de FestivAndes.
  * <b>RF9 - REGISTRAR LA REALIZACIÓN DE UNA FUNCIÓN DE UN ESPECTÁCULO:</b>  
  Registra en el sistema que una función de un espectáculo fue efectivamente realizada. Inicialmente, por simplicidad, se supone que todos los usuarios que compraron boletas asisten a la función. Esta operación es realizada por un usuario operario de FestivAndes.
#### Requerimientos Funcionales de Consulta:
  * <b>RFC1. CONSULTAR LAS FUNCIONES DE ESPECTÁCULOS PROGRAMADOS EN FESTIVANDES:</b>  
  Los usuarios de FestivAndes pueden consultar las funciones de los espectáculos previstos en el festival. Esta consulta puede estar discriminada por rango de fechas, compañía de teatro, categoría de espectáculo, idioma, disponibilidad de traducción, restricciones, entre otros. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.
  * <b>RFC2. CONSULTAR UN SITIO:</b>  
  El resultado incluye toda la información de las funciones programadas, incluyendo la fecha y hora en que se programan, toda la información relevante sobre el sitio, los espectáculos, las localidades con los precios y la boletería disponible. Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. Debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta.
  * <b>RFC3. GENERAR UN REPORTE DE UNA FUNCIÓN:</b>  
  Genera un reporte con la cantidad de boletas vendidas y el producido ($) de una función. Debe discriminar por localidad dentro del sitio y por usuarios no registrados o clientes.
  * <b>RFC4. GENERAR UN REPORTE DE ESPECTÁCULO:</b>  
  Genera un reporte con la cantidad de boletas vendidas y el producido ($) de un espectáculo. Debe discriminar por función, por sitio y por usuarios no registrados o clientes. Para cada sitio, debe incluir información sobre el porcentaje de ocupación en cada función. Este porcentaje se calcula teniendo en cuenta la capacidad total del sitio.
  * <b>RFC5. CONSULTAR RENTABILIDAD DE UNA COMPAÑÍA:</b>  
  En un rango de fechas, el sistema debe informar por sitio, por tipo de sitio, por espectáculo y por categoría, el número de boletas vendidas, el número de asistentes a cada función, la proporción de asistencia con respecto a la capacidad y el valor total facturado. El resultado debe estar ordenado de mayor a menor por el total del valor facturado.  
  <b>NOTA:</b> Respetando la privacidad de los usuarios, cuando un usuario compañía hace esta consulta, la respuesta sólo contiene la información de su compañía, mientras que el gerente de FestivAndes puede ver toda la información. Ver RNF1.
  * <b>RFC6. OBTENER LOS DATOS DE LOS ESPECTÁCULOS MÁS POPULARES:</b>  
  Esta consulta retorna toda la información de los espectáculos con mayor ocupación (cantidad de asistentes) en un periodo de tiempo, para todas sus funciones.
#### Requerimientos No Funcionales:
  * <b>RNF1 - PRIVACIDAD:</b>  
  Los usuarios de FestivAndes solo pueden manipular y consultar la información que les es propia.
  * <b>RNF2 - PERSISTENCIA:</b>  
  La información manipulada por la aplicación debe ser persistente. Recuerde que la información que se requiere para resolver un requerimiento funcional no cabe simultáneamente en memoria principal.
  * <b>RNF3 - CONCURRENCIA:</b>  
  Los requerimientos pueden ser solicitados de manera concurrente.
  * <b>RNF4 - DISTRIBUCIÓN:</b>  
  La base de datos de la aplicación está centralizada.
Este proyecto hace parte de la estructura curricular del curso Sistemas Transaccionales de la Universidad de Los Andes

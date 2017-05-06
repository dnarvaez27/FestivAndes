package utilities.data;

/**
 * Created by dnarv on 1/05/2017.
 */
public interface DataControl
{
	/**
	 * Maximos número de días de duración de un festival
	 */
	Integer FESTIVAL_MAX_DAY_LENGTH = 365;
	
	//	Integer NUM_FESTIVALES = 32;
	Integer NUM_FESTIVALES = 5;
	
	/**
	 * Numero de Compañias <strong><u>por Festival</strong></u>
	 */
	Integer NUM_COMPANIAS = 100;
	
	/**
	 * Numero total de Espectaculos <strong><u>por Festival</strong></u>
	 */
	Integer NUM_ESPECTACULOS = 50;
	
	Integer MIN_DURATION_ESPECTACULO = 30;
	
	Integer MAX_DURATION_ESPECTACULO = 240;
	
	Float MIN_COSTO_ESPECTACULO = 500000f;
	
	Float MAX_COSTO_ESPECTACULO = 15000000f;
	
	Integer MIN_GENEROS_ESPECTACULO = 2;
	
	Integer MAX_GENEROS_ESPECTACULO = 10;
	
	Integer MIN_REQUERIMIENTOS_ESPECTACULO = 2;
	
	Integer MAX_REQUERIMIENTOS_ESPECTACULO = 10;
	
	Integer MIN_DESCRIPCION_ESPECTACULO = 100;
	
	Integer MAX_DESCRIPCION_ESPECTACULO = 5706;
	
	Integer MIN_COMPANIAS_ESPECTACULO = 2;
	
	Integer MAX_COMPANIAS_ESPECTACULO = 10;
	
	Integer NUM_FUNCIONES = 20;
	
	Integer MAX_CAPACIDAD_LOCALIDAD = 150;
	
	Integer MIN_CANTIDAD_FILAS = 5;
	
	Integer MAX_CANTIDAD_FILAS = 15;
	
	Integer MIN_CANTIDAD_SILLAS_POR_FILA = 5;
	
	Integer MAX_CANTIDAD_SILLAS_POR_FILA = 15;
	
	Float MIN_COSTO_LOCALIDAD = 25000F;
	
	Float MAX_COSTO_LOCALIDAD = 400000F;
	
	Integer NUM_USUARIOS = 100;
	
	Integer MIN_EDAD_ESPECTADORES = 18;
	
	Integer MAX_EDAD_ESPECTADORES = 65;
	
	Integer NUM_ESPECTADORES = 100;
	
	Integer MIN_REQUERIMIENTOS_LUGAR = 5;
	
	Integer MAX_REQUERIMIENTOS_LUGAR = 13;
	
	Integer MIN_ACCESIBIILIDADES_LUGAR = 3;
	
	Integer MAX_ACCESIBILIDADES_LUGAR = 5;
	
	Integer NUM_ABONOS = 100;
	
	Float MIN_DESCUENTO_ABONO = 0.4F;
	
	Float MAX_DESCUENTO_ABONO = 0.8F;
	
	Integer MIN_FUNCIONES_ABONO = 5;
	
	Integer MAX_FUNCION_ABONO = 15;
}
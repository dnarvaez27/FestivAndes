/**
 * -------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package exceptions;

import java.util.List;

/**
 * clase IncompleteReplyException
 *
 * @author Juan
 */
public class IncompleteReplyException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo con las respuestas parciales
	 */
	private List partialResponse;
	
	/**
	 * Método constructor de la clase IncompleteReplyException
	 * <b>post: </b> Crea la  IncompleteReplyException con los valores que entran como parámetro
	 *
	 * @param message         - mensaje de la IncompleteReplyException
	 * @param partialResponse - respuesta parcial a guardar.
	 */
	public IncompleteReplyException( String message, List partialResponse )
	{
		super( message );
		this.partialResponse = partialResponse;
	}
	
	/**
	 * Método que retorna la respuesta parcial
	 *
	 * @return ListaVideos - respuesta parcial
	 */
	public List getPartialResponse( )
	{
		return this.partialResponse;
	}
}
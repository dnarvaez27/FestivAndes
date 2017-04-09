package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dave on 20/03/2017.
 */
public class UsuarioRegistrado extends Usuario
{
	@JsonProperty( value = "edad" )
	private Integer edad;
	
	public UsuarioRegistrado( )
	{
	}
	
	/**
	 * Retrieves the edad of the UsuarioRegistrado
	 *
	 * @return The edad of the UsuarioRegistrado
	 */
	public Integer getEdad( )
	{
		return edad;
	}
	
	/**
	 * Updates the edad of the UsuarioRegistrado by the one given by parameter
	 *
	 * @param edad The new edad of the UsuarioRegistrado
	 */
	public void setEdad( Integer edad )
	{
		this.edad = edad;
	}
}

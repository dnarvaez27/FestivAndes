package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dave on 20/03/2017.
 */
public class UsuarioRegistrado extends Usuario
{
	@JsonProperty( value = "edad" )
	protected Integer edad;
	
	public UsuarioRegistrado( )
	{
	}
	
	public UsuarioRegistrado( Usuario usuario )
	{
		this.nombre = usuario.nombre;
		this.identificacion = usuario.identificacion;
		this.tipoIdentificacion = usuario.getTipoIdentificacion( );
		this.email = usuario.email;
		this.password = usuario.password;
		this.rol = usuario.rol;
		this.idFestival = usuario.idFestival;
		this.imagen = usuario.imagen;
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

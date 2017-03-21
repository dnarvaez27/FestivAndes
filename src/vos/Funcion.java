package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class Funcion
{
	@JsonProperty( value = "fecha" )
	private Date fecha;
	
	@JsonProperty( value = "id_lugar" )
	private Long idLugar;
	
	@JsonProperty( value = "id_espectaculo" )
	private Long idEspectaculo;
	
	public Funcion( )
	{
	}
	
	/**
	 * Retrieves the idEspectaculo of the Funcion
	 *
	 * @return The idEspectaculo of the Funcion
	 */
	public Long getIdEspectaculo( )
	{
		return idEspectaculo;
	}
	
	/**
	 * Updates the idEspectaculo of the Funcion by the one given by parameter
	 *
	 * @param idEspectaculo The new idEspectaculo of the Funcion
	 */
	public void setIdEspectaculo( Long idEspectaculo )
	{
		this.idEspectaculo = idEspectaculo;
	}
	
	/**
	 * Retrieves the fecha of the Funcion
	 *
	 * @return The fecha of the Funcion
	 */
	public Date getFecha( )
	{
		return fecha;
	}
	
	/**
	 * Updates the fecha of the Funcion by the one given by parameter
	 *
	 * @param fecha The new fecha of the Funcion
	 */
	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}
	
	/**
	 * Retrieves the idLugar of the Funcion
	 *
	 * @return The idLugar of the Funcion
	 */
	public Long getIdLugar( )
	{
		return idLugar;
	}
	
	/**
	 * Updates the idLugar of the Funcion by the one given by parameter
	 *
	 * @param idLugar The new idLugar of the Funcion
	 */
	public void setIdLugar( Long idLugar )
	{
		this.idLugar = idLugar;
	}
}
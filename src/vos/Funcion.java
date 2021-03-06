package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class Funcion
{
	@JsonProperty( value = "fecha" )
	private Date fecha;
	
	@JsonProperty( value = "idLugar" )
	private Long idLugar;
	
	@JsonProperty( value = "idEspectaculo" )
	private Long idEspectaculo;
	
	@JsonProperty( value = "seRealiza" )
	private Integer seRealiza;
	
	public Funcion( )
	{
	}
	
	/**
	 * Retrieves the seRealiza of the Funcion
	 *
	 * @return The seRealiza of the Funcion
	 */
	public Integer getSeRealiza( )
	{
		return seRealiza;
	}
	
	/**
	 * Updates the seRealiza of the Funcion by the one given by parameter
	 *
	 * @param seRealiza The new seRealiza of the Funcion
	 */
	public void setSeRealiza( Integer seRealiza )
	{
		this.seRealiza = seRealiza;
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
	
	@Override
	public String toString( )
	{
		return String.format( "Fecha: %s\nLugar:%s\nEspectaculo:%s\nRealizado:%s", fecha, idLugar, idEspectaculo, seRealiza );
	}
}
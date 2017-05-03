package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class Festival
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "fecha_inicio" )
	private Date fechaInicio;
	
	@JsonProperty( value = "fecha_fin" )
	private Date fechaFin;
	
	@JsonProperty( value = "ciudad" )
	private String ciudad;
	
	public Festival( )
	{
	}
	
	/**
	 * Retrieves the id of the Festival
	 *
	 * @return The id of the Festival
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the Festival by the one given by parameter
	 *
	 * @param id The new id of the Festival
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the fechaInicio of the Festival
	 *
	 * @return The fechaInicio of the Festival
	 */
	public Date getFechaInicio( )
	{
		return fechaInicio;
	}
	
	/**
	 * Updates the fechaInicio of the Festival by the one given by parameter
	 *
	 * @param fechaInicio The new fechaInicio of the Festival
	 */
	public void setFechaInicio( Date fechaInicio )
	{
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Retrieves the fechaFin of the Festival
	 *
	 * @return The fechaFin of the Festival
	 */
	public Date getFechaFin( )
	{
		return fechaFin;
	}
	
	/**
	 * Updates the fechaFin of the Festival by the one given by parameter
	 *
	 * @param fechaFin The new fechaFin of the Festival
	 */
	public void setFechaFin( Date fechaFin )
	{
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Retrieves the ciudad of the Festival
	 *
	 * @return The ciudad of the Festival
	 */
	public String getCiudad( )
	{
		return ciudad;
	}
	
	/**
	 * Updates the ciudad of the Festival by the one given by parameter
	 *
	 * @param ciudad The new ciudad of the Festival
	 */
	public void setCiudad( String ciudad )
	{
		this.ciudad = ciudad;
	}
	
	@Override
	public String toString( )
	{
		return String.format( "%d: %s:\n\t%s\n\t%s\n", id, ciudad, fechaInicio, fechaFin );
	}
}
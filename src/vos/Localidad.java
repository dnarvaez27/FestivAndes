package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Localidad
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "esNumerada" )
	private Integer esNumerada;
	
	@JsonProperty( value = "capacidad" )
	private Integer capacidad;
	
	@JsonProperty( value = "costo" )
	private Float costo;
	
	public Localidad( )
	{
	
	}
	
	/**
	 * Retrieves the esNumerada of the Localidad
	 *
	 * @return The esNumerada of the Localidad
	 */
	public Integer getEsNumerada( )
	{
		return esNumerada;
	}
	
	/**
	 * Updates the esNumerada of the Localidad by the one given by parameter
	 *
	 * @param esNumerada The new esNumerada of the Localidad
	 */
	public void setEsNumerado( Integer esNumerada )
	{
		this.esNumerada = esNumerada;
	}
	
	/**
	 * Retrieves the capacidad of the Localidad
	 *
	 * @return The capacidad of the Localidad
	 */
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	/**
	 * Updates the capacidad of the Localidad by the one given by parameter
	 *
	 * @param capacidad The new capacidad of the Localidad
	 */
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
	/**
	 * Retrieves the costo of the Localidad
	 *
	 * @return The costo of the Localidad
	 */
	public Float getCosto( )
	{
		return costo;
	}
	
	/**
	 * Updates the costo of the Localidad by the one given by parameter
	 *
	 * @param costo The new costo of the Localidad
	 */
	public void setCosto( Float costo )
	{
		this.costo = costo;
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public String getNombre( )
	{
		return nombre;
	}
	
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
}
package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Accesibilidad
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	public Accesibilidad( )
	{
	}
	
	/**
	 * Retrieves the id of the Accesibilidad
	 *
	 * @return The id of the Accesibilidad
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the Accesibilidad by the one given by parameter
	 *
	 * @param id The new id of the Accesibilidad
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the Accesibilidad
	 *
	 * @return The nombre of the Accesibilidad
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the Accesibilidad by the one given by parameter
	 *
	 * @param nombre The new nombre of the Accesibilidad
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
}
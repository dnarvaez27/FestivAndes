package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Dave on 20/03/2017.
 */
public class Clasificacion
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	public Clasificacion( )
	{
	}
	
	/**
	 * Retrieves the id of the Clasificacion
	 *
	 * @return The id of the Clasificacion
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the Clasificacion by the one given by parameter
	 *
	 * @param id The new id of the Clasificacion
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the Clasificacion
	 *
	 * @return The nombre of the Clasificacion
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the Clasificacion by the one given by parameter
	 *
	 * @param nombre The new nombre of the Clasificacion
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
}
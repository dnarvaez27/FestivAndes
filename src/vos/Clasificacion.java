package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dave on 20/03/2017.
 */
public class Clasificacion
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "imagen" )
	private String imagen;
	
	public Clasificacion( )
	{
	}
	
	public Clasificacion( Long id, String nombre, String imagen )
	{
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
	}
	
	/**
	 * Retrieves the imagen of the Clasificacion
	 *
	 * @return The imagen of the Clasificacion
	 */
	public String getImagen( )
	{
		return imagen;
	}
	
	/**
	 * Updates the imagen of the Clasificacion by the one given by parameter
	 *
	 * @param imagen The new imagen of the Clasificacion
	 */
	public void setImagen( String imagen )
	{
		this.imagen = imagen;
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
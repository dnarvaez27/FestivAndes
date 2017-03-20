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
	private String nommbre;
	
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
	 * Retrieves the nommbre of the Clasificacion
	 *
	 * @return The nommbre of the Clasificacion
	 */
	public String getNommbre( )
	{
		return nommbre;
	}
	
	/**
	 * Updates the nommbre of the Clasificacion by the one given by parameter
	 *
	 * @param nommbre The new nommbre of the Clasificacion
	 */
	public void setNommbre( String nommbre )
	{
		this.nommbre = nommbre;
	}
}
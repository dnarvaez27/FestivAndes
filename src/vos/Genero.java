package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genero
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "imagen")
	private String imagen;
	
	/**
	 * Retrieves the imagen of the Genero
	 *
	 * @return The imagen of the Genero
	 */
	public String getImagen( )
	{
		return imagen;
	}
	
	/**
	 * Updates the imagen of the Genero by the one given by parameter
	 *
	 * @param imagen The new imagen of the Genero
	 */
	public void setImagen( String imagen )
	{
		this.imagen = imagen;
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
	
	public Genero( )
	{
		
	}
	
	//	public Genero(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre) {
	//		super();
	//		this.id = id;
	//		this.nombre = nombre;
	//	}
}

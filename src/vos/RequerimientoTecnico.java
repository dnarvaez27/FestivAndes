package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequerimientoTecnico
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "imagen" )
	private String imagen;
	
	/**
	 * Retrieves the imagen of the RequerimientoTecnico
	 *
	 * @return The imagen of the RequerimientoTecnico
	 */
	public String getImagen( )
	{
		return imagen;
	}
	
	/**
	 * Updates the imagen of the RequerimientoTecnico by the one given by parameter
	 *
	 * @param imagen The new imagen of the RequerimientoTecnico
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
	
	public RequerimientoTecnico( )
	{
		
	}
	
	//	public RequerimientoTecnico(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre) {
	//		super();
	//		this.id = id;
	//		this.nombre = nombre;
	//	}
	
}

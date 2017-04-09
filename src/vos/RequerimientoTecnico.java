package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequerimientoTecnico
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
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

package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Localidad
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "id" )
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
	
	public Localidad( )
	{
		
	}
	
	//	public Localidad(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre) {
	//		super();
	//		this.id = id;
	//		this.nombre = nombre;
	//	}
	
}

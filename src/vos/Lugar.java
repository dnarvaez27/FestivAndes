package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Lugar
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "disponibilidadInicio" )
	private Date disponibilidadInicio;
	
	@JsonProperty( value = "disponibilidadFin" )
	private Date disponibilidadFin;
	
	@JsonProperty( value = "esAbierto" )
	private Integer esAbierto;
	
	public Lugar( )
	{
		
	}
	
	//	public Lugar(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre,
	//			@JsonProperty(value = "disponibilidadInicio") Date disponibilidadInicio,
	//			@JsonProperty(value = "disponibilidadFin") Date disponibilidadFin,
	//			@JsonProperty(value = "esAbierto") Integer esAbierto) {
	//		super();
	//		this.id = id;
	//		this.nombre = nombre;
	//		this.disponibilidadInicio = disponibilidadInicio;
	//		this.disponibilidadFin = disponibilidadFin;
	//		this.esAbierto = esAbierto;
	//	}
	
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
	
	public Date getDisponibilidadInicio( )
	{
		return disponibilidadInicio;
	}
	
	public void setDisponibilidadInicio( Date disponibilidadInicio )
	{
		this.disponibilidadInicio = disponibilidadInicio;
	}
	
	public Date getDisponibilidadFin( )
	{
		return disponibilidadFin;
	}
	
	public void setDisponibilidadFin( Date disponibilidadFin )
	{
		this.disponibilidadFin = disponibilidadFin;
	}
	
	public Integer getEsAbierto( )
	{
		return esAbierto;
	}
	
	public void setEsAbierto( Integer esAbierto )
	{
		this.esAbierto = esAbierto;
	}
}

package vos.intermediate;

import org.codehaus.jackson.annotate.JsonProperty;

public class LugarLocalidad
{
	@JsonProperty(value = "idLugar")
	private Long idLugar;
	
	@JsonProperty(value = "idLocalidad")
	private Long idLocalidad;
	
	@JsonProperty(value = "esNumerada")
	private Integer esNumerada;
	
	@JsonProperty(value = "capacidad")
	private Integer capacidad;
	
	public LugarLocalidad( Long idLugar, Long idLocalidad, Integer esNumerada, Integer capacidad )
	{
		super( );
		this.idLugar = idLugar;
		this.idLocalidad = idLocalidad;
		this.esNumerada = esNumerada;
		this.capacidad = capacidad;
	}
	
	public LugarLocalidad( )
	{
	
	}
	
	public Long getIdLugar( )
	{
		return idLugar;
	}
	
	public void setIdLugar( Long idLugar )
	{
		this.idLugar = idLugar;
	}
	
	public Long getIdLocalidad( )
	{
		return idLocalidad;
	}
	
	public void setIdLocalidad( Long idLocalidad )
	{
		this.idLocalidad = idLocalidad;
	}
	
	public Integer getEsNumerada( )
	{
		return esNumerada;
	}
	
	public void setEsNumerada( Integer esNumerada )
	{
		this.esNumerada = esNumerada;
	}
	
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
}
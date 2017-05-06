package vos.intermediate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LugarLocalidad
{
	@JsonProperty( value = "idLugar" )
	private Long idLugar;
	
	@JsonProperty( value = "idLocalidad" )
	private Long idLocalidad;
	
	@JsonProperty( value = "esNumerado" )
	private Integer esNumerado;
	
	@JsonProperty( value = "capacidad" )
	private Integer capacidad;
	
	public LugarLocalidad( Long idLugar, Long idLocalidad, Integer esNumerado, Integer capacidad )
	{
		super( );
		this.idLugar = idLugar;
		this.idLocalidad = idLocalidad;
		this.esNumerado = esNumerado;
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
	
	public Integer getEsNumerado( )
	{
		return esNumerado;
	}
	
	public void setEsNumerado( Integer esNumerado )
	{
		this.esNumerado = esNumerado;
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
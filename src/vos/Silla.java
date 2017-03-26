package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Silla
{
	@JsonProperty( value = "numSilla" )
	private Integer numSilla;
	
	@JsonProperty( value = "numFila" )
	private Integer numFila;
	
	@JsonProperty( value = "idLugar" )
	private Long idLugar;
	
	@JsonProperty( value = "idLocalidad" )
	private Long idLocalidad;
	
	public Silla( )
	{
	
	}
	
	public Integer getNumSilla( )
	{
		return numSilla;
	}
	
	public void setNumSilla( Integer numSilla )
	{
		this.numSilla = numSilla;
	}
	
	public Integer getNumFila( )
	{
		return numFila;
	}
	
	public void setNumFila( Integer numFila )
	{
		this.numFila = numFila;
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
}

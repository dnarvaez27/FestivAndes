package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Silla
{
	public static class SillaExtended extends Silla
	{
		@JsonProperty( value = "disponibilidad" )
		private Integer disponibilidad;
		
		public SillaExtended( )
		{
		}
		
		public SillaExtended( Silla silla )
		{
			this.setIdLugar( silla.getIdLugar( ) );
			this.setIdLocalidad( silla.getIdLocalidad( ) );
			this.setNumFila( silla.getNumFila( ) );
			this.setNumSilla( silla.getNumSilla( ) );
		}
		
		/**
		 * Retrieves the disponibilidad of the SillaExtended
		 *
		 * @return The disponibilidad of the SillaExtended
		 */
		public Integer getDisponibilidad( )
		{
			return disponibilidad;
		}
		
		/**
		 * Updates the disponibilidad of the SillaExtended by the one given by parameter
		 *
		 * @param disponibilidad The new disponibilidad of the SillaExtended
		 */
		public void setDisponibilidad( Integer disponibilidad )
		{
			this.disponibilidad = disponibilidad;
		}
	}
	
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

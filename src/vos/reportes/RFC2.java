package vos.reportes;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RFC2
{
	public class Funcion
	{
		@JsonProperty( value = "localidad" )
		private String localidad;
		
		@JsonProperty( value = "capacidad" )
		private Integer capacidad;
		
		@JsonProperty( value = "esNumerado" )
		private Integer esNumerado;
		
		@JsonProperty( value = "fechaFuncion" )
		private Date fechaFuncion;
		
		@JsonProperty( value = "espectaculo" )
		private String espectaculo;
		
		@JsonProperty( value = "costo" )
		private Double costo;
		
		@JsonProperty( value = "boleteriaDisponible" )
		private Integer boleteriaDisponible;
		
		/**
		 * Retrieves the localidad of the Funcion
		 *
		 * @return The localidad of the Funcion
		 */
		public String getLocalidad( )
		{
			return localidad;
		}
		
		/**
		 * Updates the localidad of the Funcion by the one given by parameter
		 *
		 * @param localidad The new localidad of the Funcion
		 */
		public void setLocalidad( String localidad )
		{
			this.localidad = localidad;
		}
		
		/**
		 * Retrieves the capacidad of the Funcion
		 *
		 * @return The capacidad of the Funcion
		 */
		public Integer getCapacidad( )
		{
			return capacidad;
		}
		
		/**
		 * Updates the capacidad of the Funcion by the one given by parameter
		 *
		 * @param capacidad The new capacidad of the Funcion
		 */
		public void setCapacidad( Integer capacidad )
		{
			this.capacidad = capacidad;
		}
		
		/**
		 * Retrieves the esNumerado of the Funcion
		 *
		 * @return The esNumerado of the Funcion
		 */
		public Integer getEsNumerado( )
		{
			return esNumerado;
		}
		
		/**
		 * Updates the esNumerado of the Funcion by the one given by parameter
		 *
		 * @param esNumerado The new esNumerado of the Funcion
		 */
		public void setEsNumerado( Integer esNumerado )
		{
			this.esNumerado = esNumerado;
		}
		
		/**
		 * Retrieves the fechaFuncion of the Funcion
		 *
		 * @return The fechaFuncion of the Funcion
		 */
		public Date getFechaFuncion( )
		{
			return fechaFuncion;
		}
		
		/**
		 * Updates the fechaFuncion of the Funcion by the one given by parameter
		 *
		 * @param fechaFuncion The new fechaFuncion of the Funcion
		 */
		public void setFechaFuncion( Date fechaFuncion )
		{
			this.fechaFuncion = fechaFuncion;
		}
		
		/**
		 * Retrieves the espectaculo of the Funcion
		 *
		 * @return The espectaculo of the Funcion
		 */
		public String getEspectaculo( )
		{
			return espectaculo;
		}
		
		/**
		 * Updates the espectaculo of the Funcion by the one given by parameter
		 *
		 * @param espectaculo The new espectaculo of the Funcion
		 */
		public void setEspectaculo( String espectaculo )
		{
			this.espectaculo = espectaculo;
		}
		
		/**
		 * Retrieves the costo of the Funcion
		 *
		 * @return The costo of the Funcion
		 */
		public Double getCosto( )
		{
			return costo;
		}
		
		/**
		 * Updates the costo of the Funcion by the one given by parameter
		 *
		 * @param costo The new costo of the Funcion
		 */
		public void setCosto( Double costo )
		{
			this.costo = costo;
		}
		
		/**
		 * Retrieves the boleteriaDisponible of the Funcion
		 *
		 * @return The boleteriaDisponible of the Funcion
		 */
		public Integer getBoleteriaDisponible( )
		{
			return boleteriaDisponible;
		}
		
		/**
		 * Updates the boleteriaDisponible of the Funcion by the one given by parameter
		 *
		 * @param boleteriaDisponible The new boleteriaDisponible of the Funcion
		 */
		public void setBoleteriaDisponible( Integer boleteriaDisponible )
		{
			this.boleteriaDisponible = boleteriaDisponible;
		}
	}
	
	@JsonProperty( value = "lugar" )
	private String lugar;
	
	@JsonProperty( value = "tipoLugar" )
	private String tipoLugar;
	
	@JsonProperty( value = "esAbierto" )
	private Integer esAbierto;
	
	@JsonProperty( value = "disponibilidadInicio" )
	private Date disponibilidadInicio;
	
	@JsonProperty( value = "disponibilidadFin" )
	private Date disponibilidadFin;
	
	@JsonProperty( value = "localidades" )
	private List<Funcion> localidades;
	
	public RFC2( )
	{
		localidades = new LinkedList<>( );
	}
	
	/**
	 * Retrieves the lugar of the RFC2
	 *
	 * @return The lugar of the RFC2
	 */
	public String getLugar( )
	{
		return lugar;
	}
	
	/**
	 * Updates the lugar of the RFC2 by the one given by parameter
	 *
	 * @param lugar The new lugar of the RFC2
	 */
	public void setLugar( String lugar )
	{
		this.lugar = lugar;
	}
	
	/**
	 * Retrieves the tipoLugar of the RFC2
	 *
	 * @return The tipoLugar of the RFC2
	 */
	public String getTipoLugar( )
	{
		return tipoLugar;
	}
	
	/**
	 * Updates the tipoLugar of the RFC2 by the one given by parameter
	 *
	 * @param tipoLugar The new tipoLugar of the RFC2
	 */
	public void setTipoLugar( String tipoLugar )
	{
		this.tipoLugar = tipoLugar;
	}
	
	/**
	 * Retrieves the esAbierto of the RFC2
	 *
	 * @return The esAbierto of the RFC2
	 */
	public Integer getEsAbierto( )
	{
		return esAbierto;
	}
	
	/**
	 * Updates the esAbierto of the RFC2 by the one given by parameter
	 *
	 * @param esAbierto The new esAbierto of the RFC2
	 */
	public void setEsAbierto( Integer esAbierto )
	{
		this.esAbierto = esAbierto;
	}
	
	/**
	 * Retrieves the disponibilidadInicio of the RFC2
	 *
	 * @return The disponibilidadInicio of the RFC2
	 */
	public Date getDisponibilidadInicio( )
	{
		return disponibilidadInicio;
	}
	
	/**
	 * Updates the disponibilidadInicio of the RFC2 by the one given by parameter
	 *
	 * @param disponibilidadInicio The new disponibilidadInicio of the RFC2
	 */
	public void setDisponibilidadInicio( Date disponibilidadInicio )
	{
		this.disponibilidadInicio = disponibilidadInicio;
	}
	
	/**
	 * Retrieves the disponibilidadFin of the RFC2
	 *
	 * @return The disponibilidadFin of the RFC2
	 */
	public Date getDisponibilidadFin( )
	{
		return disponibilidadFin;
	}
	
	/**
	 * Updates the disponibilidadFin of the RFC2 by the one given by parameter
	 *
	 * @param disponibilidadFin The new disponibilidadFin of the RFC2
	 */
	public void setDisponibilidadFin( Date disponibilidadFin )
	{
		this.disponibilidadFin = disponibilidadFin;
	}
	
	/**
	 * Retrieves the localidades of the RFC2
	 *
	 * @return The localidades of the RFC2
	 */
	public List<Funcion> getLocalidades( )
	{
		return localidades;
	}
	
	/**
	 * Updates the localidades of the RFC2 by the one given by parameter
	 *
	 * @param localidades The new localidades of the RFC2
	 */
	public void setLocalidades( List<Funcion> localidades )
	{
		this.localidades = localidades;
	}
}
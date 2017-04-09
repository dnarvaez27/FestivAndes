package vos.reportes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
public class RFC4
{
	@JsonProperty( value = "espectaculo" )
	private String espectaculo;
	
	@JsonProperty( value = "producido" )
	private List<P1> producido;
	
	@JsonProperty( value = "ocupacion" )
	private List<P2> ocupacion;
	
	public RFC4( )
	{
		producido = new LinkedList<>( );
		ocupacion = new LinkedList<>( );
	}
	
	/**
	 * Retrieves the espectaculo of the RFC4
	 *
	 * @return The espectaculo of the RFC4
	 */
	public String getEspectaculo( )
	{
		return espectaculo;
	}
	
	/**
	 * Updates the espectaculo of the RFC4 by the one given by parameter
	 *
	 * @param espectaculo The new espectaculo of the RFC4
	 */
	public void setEspectaculo( String espectaculo )
	{
		this.espectaculo = espectaculo;
	}
	
	/**
	 * Retrieves the producido of the RFC4
	 *
	 * @return The producido of the RFC4
	 */
	public List<P1> getProducido( )
	{
		return producido;
	}
	
	/**
	 * Updates the producido of the RFC4 by the one given by parameter
	 *
	 * @param producido The new producido of the RFC4
	 */
	public void setProducido( List<P1> producido )
	{
		this.producido = producido;
	}
	
	/**
	 * Retrieves the ocupacion of the RFC4
	 *
	 * @return The ocupacion of the RFC4
	 */
	public List<P2> getOcupacion( )
	{
		return ocupacion;
	}
	
	/**
	 * Updates the ocupacion of the RFC4 by the one given by parameter
	 *
	 * @param ocupacion The new ocupacion of the RFC4
	 */
	public void setOcupacion( List<P2> ocupacion )
	{
		this.ocupacion = ocupacion;
	}
	
	public class P1
	{
		@JsonProperty( value = "fecha" )
		private Date fecha;
		
		@JsonProperty( value = "lugar" )
		private String lugar;
		
		@JsonProperty( value = "tipoUsuario" )
		private String tipoUsuario;
		
		@JsonProperty( value = "boletasVendidas" )
		private Integer boletasVendidas;
		
		@JsonProperty( value = "total" )
		private Double total;
		
		/**
		 * Retrieves the fecha of the P1
		 *
		 * @return The fecha of the P1
		 */
		public Date getFecha( )
		{
			return fecha;
		}
		
		/**
		 * Updates the fecha of the P1 by the one given by parameter
		 *
		 * @param fecha The new fecha of the P1
		 */
		public void setFecha( Date fecha )
		{
			this.fecha = fecha;
		}
		
		/**
		 * Retrieves the lugar of the P1
		 *
		 * @return The lugar of the P1
		 */
		public String getLugar( )
		{
			return lugar;
		}
		
		/**
		 * Updates the lugar of the P1 by the one given by parameter
		 *
		 * @param lugar The new lugar of the P1
		 */
		public void setLugar( String lugar )
		{
			this.lugar = lugar;
		}
		
		/**
		 * Retrieves the tipoUsuario of the P1
		 *
		 * @return The tipoUsuario of the P1
		 */
		public String getTipoUsuario( )
		{
			return tipoUsuario;
		}
		
		/**
		 * Updates the tipoUsuario of the P1 by the one given by parameter
		 *
		 * @param tipoUsuario The new tipoUsuario of the P1
		 */
		public void setTipoUsuario( String tipoUsuario )
		{
			this.tipoUsuario = tipoUsuario;
		}
		
		/**
		 * Retrieves the boletasVendidas of the P1
		 *
		 * @return The boletasVendidas of the P1
		 */
		public Integer getBoletasVendidas( )
		{
			return boletasVendidas;
		}
		
		/**
		 * Updates the boletasVendidas of the P1 by the one given by parameter
		 *
		 * @param boletasVendidas The new boletasVendidas of the P1
		 */
		public void setBoletasVendidas( Integer boletasVendidas )
		{
			this.boletasVendidas = boletasVendidas;
		}
		
		/**
		 * Retrieves the total of the P1
		 *
		 * @return The total of the P1
		 */
		public Double getTotal( )
		{
			return total;
		}
		
		/**
		 * Updates the total of the P1 by the one given by parameter
		 *
		 * @param total The new total of the P1
		 */
		public void setTotal( Double total )
		{
			this.total = total;
		}
	}
	
	public class P2
	{
		@JsonProperty( value = "lugar" )
		private String lugar;
		
		@JsonProperty( value = "porcentajeOcupacion" )
		private Double porcentajeOcupacion;
		
		/**
		 * Retrieves the lugar of the P2
		 *
		 * @return The lugar of the P2
		 */
		public String getLugar( )
		{
			return lugar;
		}
		
		/**
		 * Updates the lugar of the P2 by the one given by parameter
		 *
		 * @param lugar The new lugar of the P2
		 */
		public void setLugar( String lugar )
		{
			this.lugar = lugar;
		}
		
		/**
		 * Retrieves the porcentajeOcupacion of the P2
		 *
		 * @return The porcentajeOcupacion of the P2
		 */
		public Double getPorcentajeOcupacion( )
		{
			return porcentajeOcupacion;
		}
		
		/**
		 * Updates the porcentajeOcupacion of the P2 by the one given by parameter
		 *
		 * @param porcentajeOcupacion The new porcentajeOcupacion of the P2
		 */
		public void setPorcentajeOcupacion( Double porcentajeOcupacion )
		{
			this.porcentajeOcupacion = porcentajeOcupacion;
		}
	}
}
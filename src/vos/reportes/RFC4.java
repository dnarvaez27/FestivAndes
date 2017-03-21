package vos.reportes;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
public class RFC4
{
	@JsonProperty( value = "producido" )
	private List<P1> p1;
	
	@JsonProperty( value = "ocupacion" )
	private List<P2> p2;
	
	public RFC4( )
	{
		p1 = new LinkedList<>( );
		p2 = new LinkedList<>( );
	}
	
	/**
	 * Retrieves the p1 of the RFC4
	 *
	 * @return The p1 of the RFC4
	 */
	public List<P1> getP1( )
	{
		return p1;
	}
	
	/**
	 * Updates the p1 of the RFC4 by the one given by parameter
	 *
	 * @param p1 The new p1 of the RFC4
	 */
	public void setP1( List<P1> p1 )
	{
		this.p1 = p1;
	}
	
	/**
	 * Retrieves the p2 of the RFC4
	 *
	 * @return The p2 of the RFC4
	 */
	public List<P2> getP2( )
	{
		return p2;
	}
	
	/**
	 * Updates the p2 of the RFC4 by the one given by parameter
	 *
	 * @param p2 The new p2 of the RFC4
	 */
	public void setP2( List<P2> p2 )
	{
		this.p2 = p2;
	}
	
	public class P1
	{
		@JsonProperty( value = "nombre_espectaculo" )
		private String nombreEspectaculo;
		
		@JsonProperty( value = "id_funcion" )
		private Long idFuncion;
		
		@JsonProperty( value = "lugar" )
		private String lugar;
		
		@JsonProperty( value = "nombre_localidad" )
		private String nombreLocalidad;
		
		@JsonProperty( value = "tipo_usuario" )
		private String tipoUsuario;
		
		@JsonProperty( value = "numero_boletas_vendidas" )
		private Integer numBoletasVendidas;
		
		@JsonProperty( value = "total_vendido" )
		private Double totalVendido;
		
		/**
		 * Retrieves the nombreEspectaculo of the RFC4
		 *
		 * @return The nombreEspectaculo of the RFC4
		 */
		public String getNombreEspectaculo( )
		{
			return nombreEspectaculo;
		}
		
		/**
		 * Updates the nombreEspectaculo of the RFC4 by the one given by parameter
		 *
		 * @param nombreEspectaculo The new nombreEspectaculo of the RFC4
		 */
		public void setNombreEspectaculo( String nombreEspectaculo )
		{
			this.nombreEspectaculo = nombreEspectaculo;
		}
		
		/**
		 * Retrieves the idFuncion of the RFC4
		 *
		 * @return The idFuncion of the RFC4
		 */
		public Long getIdFuncion( )
		{
			return idFuncion;
		}
		
		/**
		 * Updates the idFuncion of the RFC4 by the one given by parameter
		 *
		 * @param idFuncion The new idFuncion of the RFC4
		 */
		public void setIdFuncion( Long idFuncion )
		{
			this.idFuncion = idFuncion;
		}
		
		/**
		 * Retrieves the lugar of the RFC4
		 *
		 * @return The lugar of the RFC4
		 */
		public String getLugar( )
		{
			return lugar;
		}
		
		/**
		 * Updates the lugar of the RFC4 by the one given by parameter
		 *
		 * @param lugar The new lugar of the RFC4
		 */
		public void setLugar( String lugar )
		{
			this.lugar = lugar;
		}
		
		/**
		 * Retrieves the nombreLocalidad of the RFC4
		 *
		 * @return The nombreLocalidad of the RFC4
		 */
		public String getNombreLocalidad( )
		{
			return nombreLocalidad;
		}
		
		/**
		 * Updates the nombreLocalidad of the RFC4 by the one given by parameter
		 *
		 * @param nombreLocalidad The new nombreLocalidad of the RFC4
		 */
		public void setNombreLocalidad( String nombreLocalidad )
		{
			this.nombreLocalidad = nombreLocalidad;
		}
		
		/**
		 * Retrieves the tipoUsuario of the RFC4
		 *
		 * @return The tipoUsuario of the RFC4
		 */
		public String getTipoUsuario( )
		{
			return tipoUsuario;
		}
		
		/**
		 * Updates the tipoUsuario of the RFC4 by the one given by parameter
		 *
		 * @param tipoUsuario The new tipoUsuario of the RFC4
		 */
		public void setTipoUsuario( String tipoUsuario )
		{
			this.tipoUsuario = tipoUsuario;
		}
		
		/**
		 * Retrieves the numBoletasVendidas of the RFC4
		 *
		 * @return The numBoletasVendidas of the RFC4
		 */
		public Integer getNumBoletasVendidas( )
		{
			return numBoletasVendidas;
		}
		
		/**
		 * Updates the numBoletasVendidas of the RFC4 by the one given by parameter
		 *
		 * @param numBoletasVendidas The new numBoletasVendidas of the RFC4
		 */
		public void setNumBoletasVendidas( Integer numBoletasVendidas )
		{
			this.numBoletasVendidas = numBoletasVendidas;
		}
		
		/**
		 * Retrieves the totalVendido of the RFC4
		 *
		 * @return The totalVendido of the RFC4
		 */
		public Double getTotalVendido( )
		{
			return totalVendido;
		}
		
		/**
		 * Updates the totalVendido of the RFC4 by the one given by parameter
		 *
		 * @param totalVendido The new totalVendido of the RFC4
		 */
		public void setTotalVendido( Double totalVendido )
		{
			this.totalVendido = totalVendido;
		}
	}
	
	public class P2
	{
		@JsonProperty( value = "id_lugar" )
		private Long idLugar;
		
		@JsonProperty( value = "porcentaje_ocupacion" )
		private Double porcentajeOcupacion;
		
		/**
		 * Retrieves the idLugar of the P2
		 *
		 * @return The idLugar of the P2
		 */
		public Long getIdLugar( )
		{
			return idLugar;
		}
		
		/**
		 * Updates the idLugar of the P2 by the one given by parameter
		 *
		 * @param idLugar The new idLugar of the P2
		 */
		public void setIdLugar( Long idLugar )
		{
			this.idLugar = idLugar;
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
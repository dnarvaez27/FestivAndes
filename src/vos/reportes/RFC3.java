package vos.reportes;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RFC3
{
	public class Localidad
	{
		@JsonProperty( value = "localidad" )
		private String localidad;
		
		@JsonProperty( value = "tipoUsuario" )
		private String tipoUsuario;
		
		@JsonProperty( value = "boletasVendidas" )
		private Integer boletasVendidas;
		
		@JsonProperty( value = "totalProducido" )
		private Double totalProducido;
		
		/**
		 * Retrieves the localidad of the Localidad
		 *
		 * @return The localidad of the Localidad
		 */
		public String getLocalidad( )
		{
			return localidad;
		}
		
		/**
		 * Updates the localidad of the Localidad by the one given by parameter
		 *
		 * @param localidad The new localidad of the Localidad
		 */
		public void setLocalidad( String localidad )
		{
			this.localidad = localidad;
		}
		
		/**
		 * Retrieves the tipoUsuario of the Localidad
		 *
		 * @return The tipoUsuario of the Localidad
		 */
		public String getTipoUsuario( )
		{
			return tipoUsuario;
		}
		
		/**
		 * Updates the tipoUsuario of the Localidad by the one given by parameter
		 *
		 * @param tipoUsuario The new tipoUsuario of the Localidad
		 */
		public void setTipoUsuario( String tipoUsuario )
		{
			this.tipoUsuario = tipoUsuario;
		}
		
		/**
		 * Retrieves the boletasVendidas of the Localidad
		 *
		 * @return The boletasVendidas of the Localidad
		 */
		public Integer getBoletasVendidas( )
		{
			return boletasVendidas;
		}
		
		/**
		 * Updates the boletasVendidas of the Localidad by the one given by parameter
		 *
		 * @param boletasVendidas The new boletasVendidas of the Localidad
		 */
		public void setBoletasVendidas( Integer boletasVendidas )
		{
			this.boletasVendidas = boletasVendidas;
		}
		
		/**
		 * Retrieves the totalProducido of the Localidad
		 *
		 * @return The totalProducido of the Localidad
		 */
		public Double getTotalProducido( )
		{
			return totalProducido;
		}
		
		/**
		 * Updates the totalProducido of the Localidad by the one given by parameter
		 *
		 * @param totalProducido The new totalProducido of the Localidad
		 */
		public void setTotalProducido( Double totalProducido )
		{
			this.totalProducido = totalProducido;
		}
	}
	
	@JsonProperty( value = "fecha" )
	private Date fecha;
	
	@JsonProperty( value = "lugar" )
	private String lugar;
	
	@JsonProperty( value = "localidades" )
	private List<Localidad> localidades;
	
	public RFC3( )
	{
		localidades = new LinkedList<>( );
	}
	
	/**
	 * Retrieves the fecha of the RFC3
	 *
	 * @return The fecha of the RFC3
	 */
	public Date getFecha( )
	{
		return fecha;
	}
	
	/**
	 * Updates the fecha of the RFC3 by the one given by parameter
	 *
	 * @param fecha The new fecha of the RFC3
	 */
	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}
	
	/**
	 * Retrieves the lugar of the RFC3
	 *
	 * @return The lugar of the RFC3
	 */
	public String getLugar( )
	{
		return lugar;
	}
	
	/**
	 * Updates the lugar of the RFC3 by the one given by parameter
	 *
	 * @param lugar The new lugar of the RFC3
	 */
	public void setLugar( String lugar )
	{
		this.lugar = lugar;
	}
	
	/**
	 * Retrieves the localidades of the RFC3
	 *
	 * @return The localidades of the RFC3
	 */
	public List<Localidad> getLocalidades( )
	{
		return localidades;
	}
	
	/**
	 * Updates the localidades of the RFC3 by the one given by parameter
	 *
	 * @param localidades The new localidades of the RFC3
	 */
	public void setLocalidades( List<Localidad> localidades )
	{
		this.localidades = localidades;
	}
}
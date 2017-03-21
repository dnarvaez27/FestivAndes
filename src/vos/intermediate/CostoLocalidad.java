package vos.intermediate;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class CostoLocalidad
{
	public static class Extended extends CostoLocalidad
	{
		
		@JsonProperty( value = "nombre_localidad" )
		private String nombreLocalidad;
		
		public Extended( )
		{
			super( );
		}
		
		/**
		 * Retrieves the nombreLocalidad of the Extended
		 *
		 * @return The nombreLocalidad of the Extended
		 */
		public String getNombreLocalidad( )
		{
			return nombreLocalidad;
		}
		
		/**
		 * Updates the nombreLocalidad of the Extended by the one given by parameter
		 *
		 * @param nombreLocalidad The new nombreLocalidad of the Extended
		 */
		public void setNombreLocalidad( String nombreLocalidad )
		{
			this.nombreLocalidad = nombreLocalidad;
		}
	}
	
	@JsonProperty( value = "fecha" )
	protected Date fecha;
	
	@JsonProperty( value = "id_lugar" )
	protected Long idLugar;
	
	@JsonProperty( value = "id_localidad" )
	protected Long idLocalidad;
	
	@JsonProperty( value = "costo" )
	protected Float costo;
	
	public CostoLocalidad( )
	{
	}
	
	/**
	 * Retrieves the fecha of the CostoLocalidad
	 *
	 * @return The fecha of the CostoLocalidad
	 */
	public Date getFecha( )
	{
		return fecha;
	}
	
	/**
	 * Updates the fecha of the CostoLocalidad by the one given by parameter
	 *
	 * @param fecha The new fecha of the CostoLocalidad
	 */
	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}
	
	/**
	 * Retrieves the idLugar of the CostoLocalidad
	 *
	 * @return The idLugar of the CostoLocalidad
	 */
	public Long getIdLugar( )
	{
		return idLugar;
	}
	
	/**
	 * Updates the idLugar of the CostoLocalidad by the one given by parameter
	 *
	 * @param idLugar The new idLugar of the CostoLocalidad
	 */
	public void setIdLugar( Long idLugar )
	{
		this.idLugar = idLugar;
	}
	
	/**
	 * Retrieves the idLocalidad of the CostoLocalidad
	 *
	 * @return The idLocalidad of the CostoLocalidad
	 */
	public Long getIdLocalidad( )
	{
		return idLocalidad;
	}
	
	/**
	 * Updates the idLocalidad of the CostoLocalidad by the one given by parameter
	 *
	 * @param idLocalidad The new idLocalidad of the CostoLocalidad
	 */
	public void setIdLocalidad( Long idLocalidad )
	{
		this.idLocalidad = idLocalidad;
	}
	
	/**
	 * Retrieves the costo of the CostoLocalidad
	 *
	 * @return The costo of the CostoLocalidad
	 */
	public Float getCosto( )
	{
		return costo;
	}
	
	/**
	 * Updates the costo of the CostoLocalidad by the one given by parameter
	 *
	 * @param costo The new costo of the CostoLocalidad
	 */
	public void setCosto( Float costo )
	{
		this.costo = costo;
	}
}

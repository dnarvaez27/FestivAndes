package vos.reportes;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

//TODO REVISAR
public class RFC3
{
	@JsonProperty( value = "producido" )
	private List<RFC3> req;
	
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
	
	public RFC3( )
	{
		req = new LinkedList<>( );
	}
	
	/**
	 * Retrieves the nombreEspectaculo of the RFC3
	 *
	 * @return The nombreEspectaculo of the RFC3
	 */
	public String getNombreEspectaculo( )
	{
		return nombreEspectaculo;
	}
	
	/**
	 * Updates the nombreEspectaculo of the RFC3 by the one given by parameter
	 *
	 * @param nombreEspectaculo The new nombreEspectaculo of the RFC3
	 */
	public void setNombreEspectaculo( String nombreEspectaculo )
	{
		this.nombreEspectaculo = nombreEspectaculo;
	}
	
	/**
	 * Retrieves the idFuncion of the RFC3
	 *
	 * @return The idFuncion of the RFC3
	 */
	public Long getIdFuncion( )
	{
		return idFuncion;
	}
	
	/**
	 * Updates the idFuncion of the RFC3 by the one given by parameter
	 *
	 * @param idFuncion The new idFuncion of the RFC3
	 */
	public void setIdFuncion( Long idFuncion )
	{
		this.idFuncion = idFuncion;
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
	 * Retrieves the nombreLocalidad of the RFC3
	 *
	 * @return The nombreLocalidad of the RFC3
	 */
	public String getNombreLocalidad( )
	{
		return nombreLocalidad;
	}
	
	/**
	 * Updates the nombreLocalidad of the RFC3 by the one given by parameter
	 *
	 * @param nombreLocalidad The new nombreLocalidad of the RFC3
	 */
	public void setNombreLocalidad( String nombreLocalidad )
	{
		this.nombreLocalidad = nombreLocalidad;
	}
	
	/**
	 * Retrieves the tipoUsuario of the RFC3
	 *
	 * @return The tipoUsuario of the RFC3
	 */
	public String getTipoUsuario( )
	{
		return tipoUsuario;
	}
	
	/**
	 * Updates the tipoUsuario of the RFC3 by the one given by parameter
	 *
	 * @param tipoUsuario The new tipoUsuario of the RFC3
	 */
	public void setTipoUsuario( String tipoUsuario )
	{
		this.tipoUsuario = tipoUsuario;
	}
	
	/**
	 * Retrieves the numBoletasVendidas of the RFC3
	 *
	 * @return The numBoletasVendidas of the RFC3
	 */
	public Integer getNumBoletasVendidas( )
	{
		return numBoletasVendidas;
	}
	
	/**
	 * Updates the numBoletasVendidas of the RFC3 by the one given by parameter
	 *
	 * @param numBoletasVendidas The new numBoletasVendidas of the RFC3
	 */
	public void setNumBoletasVendidas( Integer numBoletasVendidas )
	{
		this.numBoletasVendidas = numBoletasVendidas;
	}
	
	/**
	 * Retrieves the totalVendido of the RFC3
	 *
	 * @return The totalVendido of the RFC3
	 */
	public Double getTotalVendido( )
	{
		return totalVendido;
	}
	
	/**
	 * Updates the totalVendido of the RFC3 by the one given by parameter
	 *
	 * @param totalVendido The new totalVendido of the RFC3
	 */
	public void setTotalVendido( Double totalVendido )
	{
		this.totalVendido = totalVendido;
	}
	
	/**
	 * Retrieves the req of the RFC3
	 *
	 * @return The req of the RFC3
	 */
	public List<RFC3> getReq( )
	{
		return req;
	}
	
	/**
	 * Updates the req of the RFC3 by the one given by parameter
	 *
	 * @param req The new req of the RFC3
	 */
	public void setReq( List<RFC3> req )
	{
		this.req = req;
	}
}
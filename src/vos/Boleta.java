package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class Boleta
{
	@JsonProperty( value = "num_boleta" )
	private Long numBoleta;
	
	@JsonProperty( value = "id_numero_silla" )
	private Long idNumeroSilla;
	
	@JsonProperty( value = "id_numero_fila" )
	private Long idNumeroFila;
	
	@JsonProperty( value = "id_localidad" )
	private Long idLocalidad;
	
	@JsonProperty( value = "id_lugar" )
	private Long idLugar;
	
	@JsonProperty( value = "fecha" )
	private Date fecha;
	
	@JsonProperty( value = "id_usuario" )
	private Long idUsuario;
	
	public Boleta( )
	{
	}
	
	/**
	 * Retrieves the numBoleta of the Boleta
	 *
	 * @return The numBoleta of the Boleta
	 */
	public Long getNumBoleta( )
	{
		return numBoleta;
	}
	
	/**
	 * Updates the numBoleta of the Boleta by the one given by parameter
	 *
	 * @param numBoleta The new numBoleta of the Boleta
	 */
	public void setNumBoleta( Long numBoleta )
	{
		this.numBoleta = numBoleta;
	}
	
	/**
	 * Retrieves the idNumeroSilla of the Boleta
	 *
	 * @return The idNumeroSilla of the Boleta
	 */
	public Long getIdNumeroSilla( )
	{
		return idNumeroSilla;
	}
	
	/**
	 * Updates the idNumeroSilla of the Boleta by the one given by parameter
	 *
	 * @param idNumeroSilla The new idNumeroSilla of the Boleta
	 */
	public void setIdNumeroSilla( Long idNumeroSilla )
	{
		this.idNumeroSilla = idNumeroSilla;
	}
	
	/**
	 * Retrieves the idNumeroFila of the Boleta
	 *
	 * @return The idNumeroFila of the Boleta
	 */
	public Long getIdNumeroFila( )
	{
		return idNumeroFila;
	}
	
	/**
	 * Updates the idNumeroFila of the Boleta by the one given by parameter
	 *
	 * @param idNumeroFila The new idNumeroFila of the Boleta
	 */
	public void setIdNumeroFila( Long idNumeroFila )
	{
		this.idNumeroFila = idNumeroFila;
	}
	
	/**
	 * Retrieves the idLocalidad of the Boleta
	 *
	 * @return The idLocalidad of the Boleta
	 */
	public Long getIdLocalidad( )
	{
		return idLocalidad;
	}
	
	/**
	 * Updates the idLocalidad of the Boleta by the one given by parameter
	 *
	 * @param idLocalidad The new idLocalidad of the Boleta
	 */
	public void setIdLocalidad( Long idLocalidad )
	{
		this.idLocalidad = idLocalidad;
	}
	
	/**
	 * Retrieves the idLugar of the Boleta
	 *
	 * @return The idLugar of the Boleta
	 */
	public Long getIdLugar( )
	{
		return idLugar;
	}
	
	/**
	 * Updates the idLugar of the Boleta by the one given by parameter
	 *
	 * @param idLugar The new idLugar of the Boleta
	 */
	public void setIdLugar( Long idLugar )
	{
		this.idLugar = idLugar;
	}
	
	/**
	 * Retrieves the fecha of the Boleta
	 *
	 * @return The fecha of the Boleta
	 */
	public Date getFecha( )
	{
		return fecha;
	}
	
	/**
	 * Updates the fecha of the Boleta by the one given by parameter
	 *
	 * @param fecha The new fecha of the Boleta
	 */
	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}
	
	/**
	 * Retrieves the idUsuario of the Boleta
	 *
	 * @return The idUsuario of the Boleta
	 */
	public Long getIdUsuario( )
	{
		return idUsuario;
	}
	
	/**
	 * Updates the idUsuario of the Boleta by the one given by parameter
	 *
	 * @param idUsuario The new idUsuario of the Boleta
	 */
	public void setIdUsuario( Long idUsuario )
	{
		this.idUsuario = idUsuario;
	}
}

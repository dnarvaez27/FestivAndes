package vos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class Boleta implements Serializable
{
	@JsonProperty( value = "numBoleta" )
	private Long numBoleta;
	
	@JsonProperty( value = "numeroSilla" )
	private Long numeroSilla;
	
	@JsonProperty( value = "numeroFila" )
	private Long numeroFila;
	
	@JsonProperty( value = "idLocalidad" )
	private Long idLocalidad;
	
	@JsonProperty( value = "idLugar" )
	private Long idLugar;
	
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm" )
	private Date fecha;
	
	@JsonProperty( value = "idUsuario" )
	private Long idUsuario;
	
	@JsonProperty( value = "tipoIdUsuario" )
	private String tipoIdUsuario;
	
	public Boleta( )
	{
	
	}
	
	public Boleta( Boleta boleta )
	{
		this.numBoleta = boleta.numBoleta;
		this.idLugar = boleta.idLugar;
		this.idLocalidad = boleta.idLocalidad;
		this.numeroFila = boleta.numeroFila;
		this.numeroSilla = boleta.numeroSilla;
		this.fecha = boleta.fecha;
		this.idUsuario = boleta.idUsuario;
		this.tipoIdUsuario = boleta.tipoIdUsuario;
	}
	
	/**
	 * Retrieves the tipoIdUsuario of the Boleta
	 *
	 * @return The tipoIdUsuario of the Boleta
	 */
	public String getTipoIdUsuario( )
	{
		return tipoIdUsuario;
	}
	
	/**
	 * Updates the tipoIdUsuario of the Boleta by the one given by parameter
	 *
	 * @param tipoIdUsuario The new tipoIdUsuario of the Boleta
	 */
	public void setTipoIdUsuario( String tipoIdUsuario )
	{
		this.tipoIdUsuario = tipoIdUsuario;
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
	 * Retrieves the numeroSilla of the Boleta
	 *
	 * @return The numeroSilla of the Boleta
	 */
	public Long getNumeroSilla( )
	{
		return numeroSilla;
	}
	
	/**
	 * Updates the numeroSilla of the Boleta by the one given by parameter
	 *
	 * @param numeroSilla The new numeroSilla of the Boleta
	 */
	public void setNumeroSilla( Long numeroSilla )
	{
		this.numeroSilla = numeroSilla;
	}
	
	/**
	 * Retrieves the numeroFila of the Boleta
	 *
	 * @return The numeroFila of the Boleta
	 */
	public Long getNumeroFila( )
	{
		return numeroFila;
	}
	
	/**
	 * Updates the numeroFila of the Boleta by the one given by parameter
	 *
	 * @param numeroFila The new numeroFila of the Boleta
	 */
	public void setNumeroFila( Long numeroFila )
	{
		this.numeroFila = numeroFila;
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
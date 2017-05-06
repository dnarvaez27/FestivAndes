package vos;

import com.fasterxml.jackson.annotation.JsonProperty;
import vos.intermediate.CostoLocalidad;

import java.util.List;

/**
 * Created by Dave on 9/04/2017.
 */
public class Abono
{
	@JsonProperty( value = "idFestival" )
	private Long idFestival;
	
	@JsonProperty( value = "idUsuario" )
	private Long idUsuario;
	
	@JsonProperty( value = "tipoId" )
	private String tipoId;
	
	@JsonProperty( value = "descuento" )
	private Float descuento;
	
	@JsonProperty( value = "funciones" )
	private List<CostoLocalidad> funciones;
	
	public Abono( )
	{

	}
	
	/**
	 * Retrieves the funciones of the Abono
	 *
	 * @return The funciones of the Abono
	 */
	public List<CostoLocalidad> getFunciones( )
	{
		return funciones;
	}
	
	/**
	 * Updates the funciones of the Abono by the one given by parameter
	 *
	 * @param funciones The new funciones of the Abono
	 */
	public void setFunciones( List<CostoLocalidad> funciones )
	{
		this.funciones = funciones;
	}
	
	/**
	 * Retrieves the idFestival of the Abono
	 *
	 * @return The idFestival of the Abono
	 */
	public Long getIdFestival( )
	{
		return idFestival;
	}
	
	/**
	 * Updates the idFestival of the Abono by the one given by parameter
	 *
	 * @param idFestival The new idFestival of the Abono
	 */
	public void setIdFestival( Long idFestival )
	{
		this.idFestival = idFestival;
	}
	
	/**
	 * Retrieves the idUsuario of the Abono
	 *
	 * @return The idUsuario of the Abono
	 */
	public Long getIdUsuario( )
	{
		return idUsuario;
	}
	
	/**
	 * Updates the idUsuario of the Abono by the one given by parameter
	 *
	 * @param idUsuario The new idUsuario of the Abono
	 */
	public void setIdUsuario( Long idUsuario )
	{
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Retrieves the tipoId of the Abono
	 *
	 * @return The tipoId of the Abono
	 */
	public String getTipoId( )
	{
		return tipoId;
	}
	
	/**
	 * Updates the tipoId of the Abono by the one given by parameter
	 *
	 * @param tipoId The new tipoId of the Abono
	 */
	public void setTipoId( String tipoId )
	{
		this.tipoId = tipoId;
	}
	
	/**
	 * Retrieves the descuento of the Abono
	 *
	 * @return The descuento of the Abono
	 */
	public Float getDescuento( )
	{
		return descuento;
	}
	
	/**
	 * Updates the descuento of the Abono by the one given by parameter
	 *
	 * @param descuento The new descuento of the Abono
	 */
	public void setDescuento( Float descuento )
	{
		this.descuento = descuento;
	}
}
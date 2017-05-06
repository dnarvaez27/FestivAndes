package vos.reportes;

import java.util.Date;

/**
 * Created by dnarv on 5/05/2017.
 */
public class RFC11
{
	private String nombreEspectaculo;
	
	private Date fecha;
	
	private String nombreLugar;
	
	private Integer boletasUsuariosRegistrados;
	
	private Integer totalBoletas;
	
	public RFC11( )
	{
	
	}
	
	/**
	 * Retrieves the nombreEspectaculo of the RFC11
	 *
	 * @return The nombreEspectaculo of the RFC11
	 */
	public String getNombreEspectaculo( )
	{
		return nombreEspectaculo;
	}
	
	/**
	 * Updates the nombreEspectaculo of the RFC11 by the one given by parameter
	 *
	 * @param nombreEspectaculo The new nombreEspectaculo of the RFC11
	 */
	public void setNombreEspectaculo( String nombreEspectaculo )
	{
		this.nombreEspectaculo = nombreEspectaculo;
	}
	
	/**
	 * Retrieves the fecha of the RFC11
	 *
	 * @return The fecha of the RFC11
	 */
	public Date getFecha( )
	{
		return fecha;
	}
	
	/**
	 * Updates the fecha of the RFC11 by the one given by parameter
	 *
	 * @param fecha The new fecha of the RFC11
	 */
	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}
	
	/**
	 * Retrieves the nombreLugar of the RFC11
	 *
	 * @return The nombreLugar of the RFC11
	 */
	public String getNombreLugar( )
	{
		return nombreLugar;
	}
	
	/**
	 * Updates the nombreLugar of the RFC11 by the one given by parameter
	 *
	 * @param nombreLugar The new nombreLugar of the RFC11
	 */
	public void setNombreLugar( String nombreLugar )
	{
		this.nombreLugar = nombreLugar;
	}
	
	/**
	 * Retrieves the boletasUsuariosRegistrados of the RFC11
	 *
	 * @return The boletasUsuariosRegistrados of the RFC11
	 */
	public Integer getBoletasUsuariosRegistrados( )
	{
		return boletasUsuariosRegistrados;
	}
	
	/**
	 * Updates the boletasUsuariosRegistrados of the RFC11 by the one given by parameter
	 *
	 * @param boletasUsuariosRegistrados The new boletasUsuariosRegistrados of the RFC11
	 */
	public void setBoletasUsuariosRegistrados( Integer boletasUsuariosRegistrados )
	{
		this.boletasUsuariosRegistrados = boletasUsuariosRegistrados;
	}
	
	/**
	 * Retrieves the totalBoletas of the RFC11
	 *
	 * @return The totalBoletas of the RFC11
	 */
	public Integer getTotalBoletas( )
	{
		return totalBoletas;
	}
	
	/**
	 * Updates the totalBoletas of the RFC11 by the one given by parameter
	 *
	 * @param totalBoletas The new totalBoletas of the RFC11
	 */
	public void setTotalBoletas( Integer totalBoletas )
	{
		this.totalBoletas = totalBoletas;
	}
}
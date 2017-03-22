package vos.reportes;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Dave on 22/03/2017.
 */
public class RFC5
{
	@JsonProperty( value = "lugar" )
	private String lugar;
	
	@JsonProperty( value = "tipo_lugar" )
	private String tipoLugar;
	
	@JsonProperty( value = "espectaculo" )
	private String espectaculo;
	
	@JsonProperty( value = "genero" )
	private String genero;
	
	@JsonProperty( value = "num_boletas_vendidas" )
	private Integer numBoletasVendidas;
	
	@JsonProperty( value = "num_asistentes" )
	private Integer numAsistentes;
	
	@JsonProperty( value = "proporcion_asistencia" )
	private Float proporcionAsistencia;
	
	@JsonProperty( value = "valor_total_facturado" )
	private Float valorTotalFacturado;
	
	public RFC5( )
	{
	}
	
	/**
	 * Retrieves the lugar of the RFC5
	 *
	 * @return The lugar of the RFC5
	 */
	public String getLugar( )
	{
		return lugar;
	}
	
	/**
	 * Updates the lugar of the RFC5 by the one given by parameter
	 *
	 * @param lugar The new lugar of the RFC5
	 */
	public void setLugar( String lugar )
	{
		this.lugar = lugar;
	}
	
	/**
	 * Retrieves the tipoLugar of the RFC5
	 *
	 * @return The tipoLugar of the RFC5
	 */
	public String getTipoLugar( )
	{
		return tipoLugar;
	}
	
	/**
	 * Updates the tipoLugar of the RFC5 by the one given by parameter
	 *
	 * @param tipoLugar The new tipoLugar of the RFC5
	 */
	public void setTipoLugar( String tipoLugar )
	{
		this.tipoLugar = tipoLugar;
	}
	
	/**
	 * Retrieves the espectaculo of the RFC5
	 *
	 * @return The espectaculo of the RFC5
	 */
	public String getEspectaculo( )
	{
		return espectaculo;
	}
	
	/**
	 * Updates the espectaculo of the RFC5 by the one given by parameter
	 *
	 * @param espectaculo The new espectaculo of the RFC5
	 */
	public void setEspectaculo( String espectaculo )
	{
		this.espectaculo = espectaculo;
	}
	
	/**
	 * Retrieves the genero of the RFC5
	 *
	 * @return The genero of the RFC5
	 */
	public String getGenero( )
	{
		return genero;
	}
	
	/**
	 * Updates the genero of the RFC5 by the one given by parameter
	 *
	 * @param genero The new genero of the RFC5
	 */
	public void setGenero( String genero )
	{
		this.genero = genero;
	}
	
	/**
	 * Retrieves the numBoletasVendidas of the RFC5
	 *
	 * @return The numBoletasVendidas of the RFC5
	 */
	public Integer getNumBoletasVendidas( )
	{
		return numBoletasVendidas;
	}
	
	/**
	 * Updates the numBoletasVendidas of the RFC5 by the one given by parameter
	 *
	 * @param numBoletasVendidas The new numBoletasVendidas of the RFC5
	 */
	public void setNumBoletasVendidas( Integer numBoletasVendidas )
	{
		this.numBoletasVendidas = numBoletasVendidas;
	}
	
	/**
	 * Retrieves the numAsistentes of the RFC5
	 *
	 * @return The numAsistentes of the RFC5
	 */
	public Integer getNumAsistentes( )
	{
		return numAsistentes;
	}
	
	/**
	 * Updates the numAsistentes of the RFC5 by the one given by parameter
	 *
	 * @param numAsistentes The new numAsistentes of the RFC5
	 */
	public void setNumAsistentes( Integer numAsistentes )
	{
		this.numAsistentes = numAsistentes;
	}
	
	/**
	 * Retrieves the proporcionAsistencia of the RFC5
	 *
	 * @return The proporcionAsistencia of the RFC5
	 */
	public Float getProporcionAsistencia( )
	{
		return proporcionAsistencia;
	}
	
	/**
	 * Updates the proporcionAsistencia of the RFC5 by the one given by parameter
	 *
	 * @param proporcionAsistencia The new proporcionAsistencia of the RFC5
	 */
	public void setProporcionAsistencia( Float proporcionAsistencia )
	{
		this.proporcionAsistencia = proporcionAsistencia;
	}
	
	/**
	 * Retrieves the valorTotalFacturado of the RFC5
	 *
	 * @return The valorTotalFacturado of the RFC5
	 */
	public Float getValorTotalFacturado( )
	{
		return valorTotalFacturado;
	}
	
	/**
	 * Updates the valorTotalFacturado of the RFC5 by the one given by parameter
	 *
	 * @param valorTotalFacturado The new valorTotalFacturado of the RFC5
	 */
	public void setValorTotalFacturado( Float valorTotalFacturado )
	{
		this.valorTotalFacturado = valorTotalFacturado;
	}
}
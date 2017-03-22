package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by Dave on 20/03/2017.
 */
public class CompaniaDeTeatro extends Usuario
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "nombre_representante" )
	private String nombreRepresentante;
	
	@JsonProperty( value = "pagina_web" )
	private String paginaWeb;
	
	@JsonProperty( value = "pais_origen" )
	private String paisOrigen;
	
	@JsonProperty( value = "fecha_llegada" )
	private Date fechaLlegada;
	
	@JsonProperty( value = "fecha_salida" )
	private Date fechaSalida;
	
	public CompaniaDeTeatro( )
	{
	}
	
	/**
	 * Retrieves the id of the CompaniaDeTeatro
	 *
	 * @return The id of the CompaniaDeTeatro
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param id The new id of the CompaniaDeTeatro
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the CompaniaDeTeatro
	 *
	 * @return The nombre of the CompaniaDeTeatro
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param nombre The new nombre of the CompaniaDeTeatro
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the nombreRepresentante of the CompaniaDeTeatro
	 *
	 * @return The nombreRepresentante of the CompaniaDeTeatro
	 */
	public String getNombreRepresentante( )
	{
		return nombreRepresentante;
	}
	
	/**
	 * Updates the nombreRepresentante of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param nombreRepresentante The new nombreRepresentante of the CompaniaDeTeatro
	 */
	public void setNombreRepresentante( String nombreRepresentante )
	{
		this.nombreRepresentante = nombreRepresentante;
	}
	
	/**
	 * Retrieves the paginaWeb of the CompaniaDeTeatro
	 *
	 * @return The paginaWeb of the CompaniaDeTeatro
	 */
	public String getPaginaWeb( )
	{
		return paginaWeb;
	}
	
	/**
	 * Updates the paginaWeb of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param paginaWeb The new paginaWeb of the CompaniaDeTeatro
	 */
	public void setPaginaWeb( String paginaWeb )
	{
		this.paginaWeb = paginaWeb;
	}
	
	/**
	 * Retrieves the paisOrigen of the CompaniaDeTeatro
	 *
	 * @return The paisOrigen of the CompaniaDeTeatro
	 */
	public String getPaisOrigen( )
	{
		return paisOrigen;
	}
	
	/**
	 * Updates the paisOrigen of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param paisOrigen The new paisOrigen of the CompaniaDeTeatro
	 */
	public void setPaisOrigen( String paisOrigen )
	{
		this.paisOrigen = paisOrigen;
	}
	
	/**
	 * Retrieves the fechaLlegada of the CompaniaDeTeatro
	 *
	 * @return The fechaLlegada of the CompaniaDeTeatro
	 */
	public Date getFechaLlegada( )
	{
		return fechaLlegada;
	}
	
	/**
	 * Updates the fechaLlegada of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param fechaLlegada The new fechaLlegada of the CompaniaDeTeatro
	 */
	public void setFechaLlegada( Date fechaLlegada )
	{
		this.fechaLlegada = fechaLlegada;
	}
	
	/**
	 * Retrieves the fechaSalida of the CompaniaDeTeatro
	 *
	 * @return The fechaSalida of the CompaniaDeTeatro
	 */
	public Date getFechaSalida( )
	{
		return fechaSalida;
	}
	
	/**
	 * Updates the fechaSalida of the CompaniaDeTeatro by the one given by parameter
	 *
	 * @param fechaSalida The new fechaSalida of the CompaniaDeTeatro
	 */
	public void setFechaSalida( Date fechaSalida )
	{
		this.fechaSalida = fechaSalida;
	}
}
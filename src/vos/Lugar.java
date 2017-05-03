package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Lugar
{
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "disponibilidadInicio" )
	private Date disponibilidadInicio;
	
	@JsonProperty( value = "disponibilidadFin" )
	private Date disponibilidadFin;
	
	@JsonProperty( value = "esAbierto" )
	private Integer esAbierto;
	
	@JsonProperty( value = "tipo" )
	private String tipo;
	
	@JsonProperty( value = "requerimientos" )
	private List<RequerimientoTecnico> requerimientosTecnicos;
	
	@JsonProperty( value = "accesibilidades" )
	private List<Accesibilidad> accesibilidades;
	
	public Lugar( )
	{
		requerimientosTecnicos = new LinkedList<>( );
		accesibilidades = new LinkedList<>( );
	}
	
	public Lugar( Long id, String nombre, Date disponibilidadInicio, Date disponibilidadFin, Integer esAbierto, String tipo )
	{
		this.id = id;
		this.nombre = nombre;
		this.disponibilidadInicio = disponibilidadInicio;
		this.disponibilidadFin = disponibilidadFin;
		this.esAbierto = esAbierto;
		this.tipo = tipo;
	}
	
	/**
	 * Retrieves the accesibilidades of the Lugar
	 *
	 * @return The accesibilidades of the Lugar
	 */
	public List<Accesibilidad> getAccesibilidades( )
	{
		return accesibilidades;
	}
	
	/**
	 * Updates the accesibilidades of the Lugar by the one given by parameter
	 *
	 * @param accesibilidades The new accesibilidades of the Lugar
	 */
	public void setAccesibilidades( List<Accesibilidad> accesibilidades )
	{
		this.accesibilidades = accesibilidades;
	}
	
	/**
	 * Retrieves the requerimientosTecnicos of the Lugar
	 *
	 * @return The requerimientosTecnicos of the Lugar
	 */
	public List<RequerimientoTecnico> getRequerimientosTecnicos( )
	{
		return requerimientosTecnicos;
	}
	
	/**
	 * Updates the requerimientosTecnicos of the Lugar by the one given by parameter
	 *
	 * @param requerimientosTecnicos The new requerimientosTecnicos of the Lugar
	 */
	public void setRequerimientosTecnicos( List<RequerimientoTecnico> requerimientosTecnicos )
	{
		this.requerimientosTecnicos = requerimientosTecnicos;
	}
	
	/**
	 * Retrieves the tipo of the Lugar
	 *
	 * @return The tipo of the Lugar
	 */
	public String getTipo( )
	{
		return tipo;
	}
	
	/**
	 * Updates the tipo of the Lugar by the one given by parameter
	 *
	 * @param tipo The new tipo of the Lugar
	 */
	public void setTipo( String tipo )
	{
		this.tipo = tipo;
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public String getNombre( )
	{
		return nombre;
	}
	
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	public Date getDisponibilidadInicio( )
	{
		return disponibilidadInicio;
	}
	
	public void setDisponibilidadInicio( Date disponibilidadInicio )
	{
		this.disponibilidadInicio = disponibilidadInicio;
	}
	
	public Date getDisponibilidadFin( )
	{
		return disponibilidadFin;
	}
	
	public void setDisponibilidadFin( Date disponibilidadFin )
	{
		this.disponibilidadFin = disponibilidadFin;
	}
	
	public Integer getEsAbierto( )
	{
		return esAbierto;
	}
	
	public void setEsAbierto( Integer esAbierto )
	{
		this.esAbierto = esAbierto;
	}
}

package vos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class Espectaculo
{
	public static class EspectaculoExtended extends Espectaculo
	{
		@JsonProperty( value = "numAsistentes" )
		private Integer numAsistentes;
		
		public EspectaculoExtended( Espectaculo e )
		{
			this.setId( e.getId( ) );
			this.setNombre( e.getNombre( ) );
			this.setDuracion( e.getDuracion( ) );
			this.setIdioma( e.getIdioma( ) );
			this.setCostoRealizacion( e.getCostoRealizacion( ) );
			this.setDescripcion( e.getDescripcion( ) );
			this.setIdFestival( e.getIdFestival( ) );
			this.setIdClasificacion( e.getIdClasificacion( ) );
		}
		
		/**
		 * Retrieves the numAsistentes of the EspectaculoExtended
		 *
		 * @return The numAsistentes of the EspectaculoExtended
		 */
		public Integer getNumAsistentes( )
		{
			return numAsistentes;
		}
		
		/**
		 * Updates the numAsistentes of the EspectaculoExtended by the one given by parameter
		 *
		 * @param numAsistentes The new numAsistentes of the EspectaculoExtended
		 */
		public void setNumAsistentes( Integer numAsistentes )
		{
			this.numAsistentes = numAsistentes;
		}
	}
	
	@JsonProperty( value = "id" )
	private Long id;
	
	@JsonProperty( value = "nombre" )
	private String nombre;
	
	@JsonProperty( value = "duracion" )
	private Integer duracion;
	
	@JsonProperty( value = "idioma" )
	private String idioma;
	
	@JsonProperty( value = "costoRealizacion" )
	private Float costoRealizacion;
	
	@JsonProperty( value = "descripcion" )
	private String descripcion;
	
	@JsonProperty( value = "idFestival" )
	private Long idFestival;
	
	@JsonProperty( value = "idClasificacion" )
	private Long idClasificacion;
	
	@JsonProperty( value = "generos" )
	private List<Genero> generos;
	
	@JsonProperty( value = "reqs" )
	private List<RequerimientoTecnico> reqs;
	
	@JsonProperty( value = "companias" )
	private List<CompaniaDeTeatro> companias;
	
	public Espectaculo( )
	{
		companias = new LinkedList<>( );
		reqs = new LinkedList<>( );
		generos = new LinkedList<>( );
	}
	
	/**
	 * Retrieves the companias of the Espectaculo
	 *
	 * @return The companias of the Espectaculo
	 */
	public List<CompaniaDeTeatro> getCompanias( )
	{
		return companias;
	}
	
	/**
	 * Updates the companias of the Espectaculo by the one given by parameter
	 *
	 * @param companias The new companias of the Espectaculo
	 */
	public void setCompanias( List<CompaniaDeTeatro> companias )
	{
		this.companias = companias;
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
	
	public Integer getDuracion( )
	{
		return duracion;
	}
	
	public void setDuracion( Integer duracion )
	{
		this.duracion = duracion;
	}
	
	public String getIdioma( )
	{
		return idioma;
	}
	
	public void setIdioma( String idioma )
	{
		this.idioma = idioma;
	}
	
	public Float getCostoRealizacion( )
	{
		return costoRealizacion;
	}
	
	public void setCostoRealizacion( Float costoRealizacion )
	{
		this.costoRealizacion = costoRealizacion;
	}
	
	public String getDescripcion( )
	{
		return descripcion;
	}
	
	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}
	
	public Long getIdFestival( )
	{
		return idFestival;
	}
	
	public void setIdFestival( Long idFestival )
	{
		this.idFestival = idFestival;
	}
	
	public Long getIdClasificacion( )
	{
		return idClasificacion;
	}
	
	public void setIdClasificacion( Long idClasificacion )
	{
		this.idClasificacion = idClasificacion;
	}
	
	public List<Genero> getGeneros( )
	{
		return generos;
	}
	
	public void setGeneros( List<Genero> generos )
	{
		this.generos = generos;
	}
	
	public List<RequerimientoTecnico> getReqs( )
	{
		return reqs;
	}
	
	public void setReqs( List<RequerimientoTecnico> reqs )
	{
		this.reqs = reqs;
	}
	
	@Override
	public String toString( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		sBuilder.append( String.format( "%s: %s\n", id, nombre ) );
		sBuilder.append( String.format( "Duracion: %s\n", duracion ) );
		sBuilder.append( String.format( "Idioma: %s\n", idioma ) );
		sBuilder.append( String.format( "Costo: %s\n", costoRealizacion ) );
		sBuilder.append( String.format( "Clasificacion: %s\n", idClasificacion ) );
		sBuilder.append( String.format( "Descripcion: %s\n", descripcion ) );
		sBuilder.append( String.format( "Festival: %s\n", idFestival ) );
		sBuilder.append( String.format( "Generos: %s\n", generos ) );
		sBuilder.append( String.format( "Requerimientos: %s\n", reqs ) );
		sBuilder.append( String.format( "Companias: %s\n", companias ) );
		return sBuilder.toString( );
	}
}
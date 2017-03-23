package vos.reportes;

import org.codehaus.jackson.annotate.JsonProperty;
import vos.Espectaculo;
import vos.Lugar;

import java.util.List;

public class RFC2
{
	@JsonProperty( value = "lugar" )
	private Lugar lugar;
	
	@JsonProperty( value = "espectaculos" )
	private List<Espectaculo> espectaculos;
	
	public Lugar getLugar( )
	{
		return lugar;
	}
	
	public void setLugar( Lugar lugar )
	{
		this.lugar = lugar;
	}
	
	public List<Espectaculo> getEspectaculos( )
	{
		return espectaculos;
	}
	
	public void setEspectaculos( List<Espectaculo> espectaculos )
	{
		this.espectaculos = espectaculos;
	}
}
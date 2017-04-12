package vos.reportes;

import java.util.Date;
import java.util.List;

/**
 * Created by juanchaves on 11/04/17.
 */
public class RFC8
{
	private String nombreCompania;
	
	private Long idCompania;
	
	private List<EspectaculoInfo> espectaculos;
	
	public RFC8( )
	{
	
	}
	
	/**
	 * Retrieves the espectaculos of the RFC8
	 *
	 * @return The espectaculos of the RFC8
	 */
	public List<EspectaculoInfo> getEspectaculos( )
	{
		return espectaculos;
	}
	
	/**
	 * Updates the espectaculos of the RFC8 by the one given by parameter
	 *
	 * @param espectaculos The new espectaculos of the RFC8
	 */
	public void setEspectaculos( List<EspectaculoInfo> espectaculos )
	{
		this.espectaculos = espectaculos;
	}
	
	/**
	 * Retrieves the nombreCompania of the RFC8
	 *
	 * @return The nombreCompania of the RFC8
	 */
	public String getNombreCompania( )
	{
		return nombreCompania;
	}
	
	/**
	 * Updates the nombreCompania of the RFC8 by the one given by parameter
	 *
	 * @param nombreCompania The new nombreCompania of the RFC8
	 */
	public void setNombreCompania( String nombreCompania )
	{
		this.nombreCompania = nombreCompania;
	}
	
	/**
	 * Retrieves the idCompania of the RFC8
	 *
	 * @return The idCompania of the RFC8
	 */
	public Long getIdCompania( )
	{
		return idCompania;
	}
	
	/**
	 * Updates the idCompania of the RFC8 by the one given by parameter
	 *
	 * @param idCompania The new idCompania of the RFC8
	 */
	public void setIdCompania( Long idCompania )
	{
		this.idCompania = idCompania;
	}
	
	public class EspectaculoInfo
	{
		private String nombreEspectaculo;
		
		private Integer asistencia;
		
		private Integer asistenciaRegistrados;
		
		private Double dinero;
		
		private List<FuncionOcupacion> ocupaciones;
		
		public EspectaculoInfo( )
		{
		
		}
		
		public String getNombreEspectaculo( )
		{
			return nombreEspectaculo;
		}
		
		public void setNombreespectaculo( String nombreespectaculo )
		{
			this.nombreEspectaculo = nombreespectaculo;
		}
		
		public Integer getAsistencia( )
		{
			return asistencia;
		}
		
		public void setAsistencia( Integer asistencia )
		{
			this.asistencia = asistencia;
		}
		
		public Integer getAsistenciaRegistrados( )
		{
			return asistenciaRegistrados;
		}
		
		public void setAsistenciaRegistrados( Integer asistenciaRegistrados )
		{
			this.asistenciaRegistrados = asistenciaRegistrados;
		}
		
		public Double getDinero( )
		{
			return dinero;
		}
		
		public void setDinero( Double dinero )
		{
			this.dinero = dinero;
		}
		
		public List<FuncionOcupacion> getOcupaciones( )
		{
			return ocupaciones;
		}
		
		public void setOcupaciones( List<FuncionOcupacion> ocupaciones )
		{
			this.ocupaciones = ocupaciones;
		}
	}
	
	public class FuncionOcupacion
	{
		public FuncionOcupacion( )
		{
		
		}
		
		private Long idFuncion;
		
		private Integer asistencia;
		
		private Integer asistencia_registrados;
		
		private Double totalPlata;
		
		private Date fecha;
		
		private Double porcentaje;
		
		public Long getIdFuncion( )
		{
			return idFuncion;
		}
		
		public void setIdFuncion( Long idFuncion )
		{
			this.idFuncion = idFuncion;
		}
		
		public Date getFecha( )
		{
			return fecha;
		}
		
		public void setFecha( Date fecha )
		{
			this.fecha = fecha;
		}
		
		public Double getPorcentaje( )
		{
			return porcentaje;
		}
		
		public void setPorcentaje( Double porcentaje )
		{
			this.porcentaje = porcentaje;
		}
		
		public Integer getAsistencia( )
		{
			return asistencia;
		}
		
		public void setAsistencia( Integer asistencia )
		{
			this.asistencia = asistencia;
		}
		
		public Integer getAsistencia_registrados( )
		{
			return asistencia_registrados;
		}
		
		public void setAsistencia_registrados( Integer asistencia_registrados )
		{
			this.asistencia_registrados = asistencia_registrados;
		}
		
		public Double getTotalPlata( )
		{
			return totalPlata;
		}
		
		public void setTotalPlata( Double totalPlata )
		{
			this.totalPlata = totalPlata;
		}
	}
}
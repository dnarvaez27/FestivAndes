package vos.reportes;

import java.util.Date;
import java.util.List;

/**
 * Created by juanchaves on 11/04/17.
 */
public class RFC8
{
    private String nombreEspectaculo;

    public String getNombreEspectaculo() {
        return nombreEspectaculo;
    }

    public void setNombreespectaculo(String nombreespectaculo) {
        this.nombreEspectaculo = nombreespectaculo;
    }

    private Integer asistencia;
    private Integer asistenciaRegistrados;
    private Double dinero;
    private List<FuncionOcupacion> ocupaciones;

    public RFC8()
    {

    }
    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public Integer getAsistenciaRegistrados() {
        return asistenciaRegistrados;
    }

    public void setAsistenciaRegistrados(Integer asistenciaRegistrados) {
        this.asistenciaRegistrados = asistenciaRegistrados;
    }

    public Double getDinero() {
        return dinero;
    }

    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }

    public List<FuncionOcupacion> getOcupaciones() {
        return ocupaciones;
    }

    public void setOcupaciones(List<FuncionOcupacion> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    public class FuncionOcupacion
    {
        public FuncionOcupacion()
        {

        }

        public Long getIdFuncion() {
            return idFuncion;
        }

        public void setIdFuncion(Long idFuncion) {
            this.idFuncion = idFuncion;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public Double getPorcentaje() {
            return porcentaje;
        }

        public void setPorcentaje(Double porcentaje) {
            this.porcentaje = porcentaje;
        }
        private Long idFuncion;

        private Date fecha;
        private Double porcentaje;
    }
}

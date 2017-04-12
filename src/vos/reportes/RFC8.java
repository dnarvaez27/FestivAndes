package vos.reportes;

import java.util.Date;
import java.util.List;

/**
 * Created by juanchaves on 11/04/17.
 */
public class RFC8
{

    private List<RFC8aux> espectaculos;

    public List<RFC8aux> getEspectaculos()
    {
        return espectaculos;
    }
    public void setEspectaculos(List<RFC8aux> param)
    {
        this.espectaculos = param;
    }
    public RFC8()
    {

    }

    public class RFC8aux {
        public RFC8aux()
        {

        }
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

        public Integer getAsistencia() {
            return asistencia;
        }

        public void setAsistencia(Integer asistencia) {
            this.asistencia = asistencia;
        }

        public Integer getAsistencia_registrados() {
            return asistencia_registrados;
        }

        public void setAsistencia_registrados(Integer asistencia_registrados) {
            this.asistencia_registrados = asistencia_registrados;
        }

        private Integer asistencia;
        private Integer asistencia_registrados;

        public Double getTotalPlata() {
            return totalPlata;
        }

        public void setTotalPlata(Double totalPlata) {
            this.totalPlata = totalPlata;
        }

        private Double totalPlata;
        private Date fecha;
        private Double porcentaje;
    }
}

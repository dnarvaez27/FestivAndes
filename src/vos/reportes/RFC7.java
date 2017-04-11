package vos.reportes;

import com.fasterxml.jackson.annotation.JsonProperty;
import vos.Funcion;

import java.util.List;

/**
 * Created by juanchaves on 11/04/17.
 */
public class RFC7 {
    @JsonProperty(value = "idUsuario")
    private Long idUsuario;
    @JsonProperty(value = "tipo")
    private String tipo;
    @JsonProperty(value = "enCurso")
    private List<FuncionEspectaculo> enCurso;
    @JsonProperty(value = "terminadas")
    private List<FuncionEspectaculo> terminadas;
    @JsonProperty(value = "previstas")
    private List<FuncionEspectaculo> previstas;
    @JsonProperty(value = "canceladas")
    private List<FuncionEspectaculo> canceladas;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<FuncionEspectaculo> getEnCurso() {
        return enCurso;
    }

    public void setEnCurso(List<FuncionEspectaculo> enCurso) {
        this.enCurso = enCurso;
    }

    public List<FuncionEspectaculo> getTerminadas() {
        return terminadas;
    }

    public void setTerminadas(List<FuncionEspectaculo> terminadas) {
        this.terminadas = terminadas;
    }

    public List<FuncionEspectaculo> getPrevistas() {
        return previstas;
    }

    public void setPrevistas(List<FuncionEspectaculo> previstas) {
        this.previstas = previstas;
    }

    public List<FuncionEspectaculo> getCanceladas() {
        return canceladas;
    }

    public void setCanceladas(List<FuncionEspectaculo> canceladas) {
        this.canceladas = canceladas;
    }

    public RFC7() {

    }

    public class FuncionEspectaculo {
        public Funcion getFuncion() {
            return funcion;
        }

        public void setFuncion(Funcion funcion) {
            this.funcion = funcion;
        }

        public String getNombreEspectaculo() {
            return nombreEspectaculo;
        }

        public void setNombreEspectaculo(String nombreEspectaculo) {
            this.nombreEspectaculo = nombreEspectaculo;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        private Funcion funcion;
        private String nombreEspectaculo;
        private Integer cantidad;

        public FuncionEspectaculo()
        {

        }
    }
}

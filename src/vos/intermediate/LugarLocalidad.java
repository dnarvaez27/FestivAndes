package vos.intermediate;

public class LugarLocalidad {

	private Long idLugar;
	private Long idLocalidad;
	private Integer esNumerada;
	private Integer capacidad;
	public Long getIdLugar() {
		return idLugar;
	}
	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}
	public Long getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public Integer getEsNumerada() {
		return esNumerada;
	}
	public void setEsNumerada(Integer esNumerada) {
		this.esNumerada = esNumerada;
	}
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	public LugarLocalidad(Long idLugar, Long idLocalidad, Integer esNumerada, Integer capacidad) {
		super();
		this.idLugar = idLugar;
		this.idLocalidad = idLocalidad;
		this.esNumerada = esNumerada;
		this.capacidad = capacidad;
	}
	public LugarLocalidad()
	{
		
	}
}

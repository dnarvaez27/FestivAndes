package utilities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dao.DAOBoleta;
import dao.DAOEspectaculo;
import dao.DAOFuncion;
import dao.DAOUsuarioRegistrado;
import dao.intermediate.DAOOfrecen;
import vos.Boleta;
import vos.Espectaculo;
import vos.Funcion;
import vos.RequerimientoTecnico;
import vos.UsuarioRegistrado;

public class InstruccionesDeControl {

	public List<UsuarioRegistrado> rfc9(Date inicio, Date fin, Long companiaId) throws SQLException {
		List<UsuarioRegistrado> resp = new ArrayList<>();
		List<UsuarioRegistrado> todosUsuarios = new DAOUsuarioRegistrado().getUsuarioRegistrados();
		List<Funcion> todasFunciones = new DAOFuncion().getFunciones();
		List<Espectaculo> todosEspectaculos = new DAOEspectaculo().getEspectaculos();
		List<Boleta> todasBoletas = new DAOBoleta().getBoletas();
		List<Long[]> todosOfrecen = new DAOOfrecen().getOfrecen();
		for (UsuarioRegistrado u : todosUsuarios) {
			for (Boleta b : todasBoletas) {
				if (b.getIdUsuario().equals(u.getIdentificacion())
						&& b.getTipoIdUsuario().equals(u.getTipoIdentificacion()))
					for (Funcion f : todasFunciones) {
						if (f.getFecha().equals(b.getFecha()) && f.getIdLugar().equals(b.getIdLugar())) {
							if (f.getFecha().before(fin) && f.getFecha().after(inicio)) {
								for (Espectaculo e : todosEspectaculos) {
									if (e.getId().equals(f.getIdEspectaculo())) {
										for (Long[] a : todosOfrecen) {
											if (a[0].equals(companiaId) && a[1].equals(e.getId())) {
												resp.add(u);
											}
										}
									}
								}
							}
						}
					}
			}
		}
		return resp;

	}

	public List<UsuarioRegistrado> rfc10(Date inicio, Date fin, Long compania) throws SQLException {
		List<UsuarioRegistrado> resp = new ArrayList<>();
		List<UsuarioRegistrado> todosUsuarios = new DAOUsuarioRegistrado().getUsuarioRegistrados();
		List<Funcion> todasFunciones = new DAOFuncion().getFunciones();
		List<Espectaculo> todosEspectaculos = new DAOEspectaculo().getEspectaculos();
		List<Boleta> todasBoletas = new DAOBoleta().getBoletas();
		List<Long[]> todosOfrecen = null;
		boolean tiene = false;
		for (UsuarioRegistrado u : todosUsuarios) {
			for (Boleta b : todasBoletas) {
				if (tiene)
					break;
				if (b.getIdUsuario().equals(u.getIdentificacion())
						&& b.getTipoIdUsuario().equals(u.getTipoIdentificacion()))
					for (Funcion f : todasFunciones) {
						if (f.getFecha().equals(b.getFecha()) && f.getIdLugar().equals(b.getIdLugar())) {
							if (f.getFecha().before(fin) && f.getFecha().after(inicio)) {
								for (Espectaculo e : todosEspectaculos) {
									if (e.getId().equals(f.getIdEspectaculo())) {
										for (Long[] a : todosOfrecen) {
											if (a[0].equals(compania) && a[1].equals(e.getId())) {
												tiene = true;
											}
										}
									}
								}
							}
						}
					}
			}
			if (!tiene)
				resp.add(u);
			tiene = false;
		}
		return resp;
	}

	public List<BoletaFuncion> rfc11(Date inicio, Date fin, Long localidadId, Integer dia,
			List<RequerimientoTecnico> reqs, Date inicioHorario, Date finHorario) throws SQLException {

		List<BoletaFuncion> resp = new ArrayList<>();
		List<Funcion> todasFunciones = new DAOFuncion().getFunciones();
		List<Espectaculo> todosEspectaculos = new DAOEspectaculo().getEspectaculos();
		List<Boleta> todasBoletas = new DAOBoleta().getBoletas();
		for (Espectaculo e : todosEspectaculos) {
			boolean cumple = true;
			if (reqs != null) {
				for (RequerimientoTecnico r : reqs) {
					if (!e.getReqs().contains(r)) {
						cumple = false;
						break;
					}
				}
			}
			if (cumple) {
				for (Funcion f : todasFunciones) {
					if (!f.getIdEspectaculo().equals(e.getId()))
						break;
					cumple = true;
					if (inicio != null)
						cumple = (f.getFecha().after(inicio)) ? true : false;
					if (fin != null)
						cumple = (f.getFecha().before(fin)) ? true : false;
					if (inicioHorario != null)
						cumple = (despuesHora(inicioHorario, f.getFecha())) ? true : false;
					if (finHorario != null)
						cumple = (antesHora(finHorario, f.getFecha())) ? true : false;
					if (dia != null) {
						Calendar c = Calendar.getInstance();
						c.setTime(f.getFecha());
						cumple = (dia == c.get(Calendar.DAY_OF_WEEK)) ? true : false;
					}
					if (cumple) {
						for (Boleta b : todasBoletas) {
							if (!b.getFecha().equals(f.getFecha()) && b.getIdLugar().equals(f.getIdLugar()))
								break;
							if (b.getIdLocalidad().equals(localidadId)) {
								resp.add(new BoletaFuncion(b, f, e.getNombre()));
							}
						}
					}
				}
			}
		}
		return resp;
	}

	public List<UsuarioBoleta> rfc12(Integer n) throws SQLException {
		List<UsuarioBoleta> resp = new ArrayList<>();
		List<UsuarioRegistrado> todosUsuarios = new DAOUsuarioRegistrado().getUsuarioRegistrados();
		List<Boleta> todasBoletas = new DAOBoleta().getBoletas();
		for (UsuarioRegistrado u : todosUsuarios) {
			UsuarioBoleta temp = new UsuarioBoleta(u);
			for (Boleta b : todasBoletas) {
				if (b.getIdUsuario().equals(u.getIdentificacion())
						&& u.getTipoIdentificacion().equals(b.getTipoIdUsuario()) && b.getIdLocalidad().equals(1)) {
					temp.agregarBoleta(b);
				}
			}
			resp.add(temp);
		}
		List<UsuarioBoleta> ans = new ArrayList<>();
		for (UsuarioBoleta ub : resp) {
			if (ub.getBoletas().size() >= n)
				ans.add(ub);
		}
		return ans;
	}

	class BoletaFuncion {
		private Boleta b;
		private Funcion f;
		private String nombreEspectaculo;

		public Boleta getB() {
			return b;
		}

		public void setB(Boleta b) {
			this.b = b;
		}

		public Funcion getF() {
			return f;
		}

		public void setF(Funcion f) {
			this.f = f;
		}

		public String getNombreEspectaculo() {
			return nombreEspectaculo;
		}

		public void setNombreEspectaculo(String nombreEspectaculo) {
			this.nombreEspectaculo = nombreEspectaculo;
		}

		public BoletaFuncion(Boleta b, Funcion f, String nombreEspectaculo) {
			super();
			this.b = b;
			this.f = f;
			this.nombreEspectaculo = nombreEspectaculo;
		}

	}

	class UsuarioBoleta {
		private UsuarioRegistrado u;
		private List<Boleta> bs;

		public UsuarioBoleta(UsuarioRegistrado u) {
			this.u = u;
			bs = new LinkedList<>();
		}

		public void agregarBoleta(Boleta boleta) {
			bs.add(boleta);
		}

		public UsuarioRegistrado getUser() {
			return u;
		}

		public List<Boleta> getBoletas() {
			return bs;
		}
	}

	static boolean antesHora(Date fin, Date actual) {
		Calendar c = Calendar.getInstance();
		c.setTime(actual);
		int horaActual = c.get(Calendar.HOUR_OF_DAY);
		int minActual = c.get(Calendar.MINUTE);

		Calendar d = Calendar.getInstance();
		c.setTime(actual);
		int horaFin = d.get(Calendar.HOUR_OF_DAY);
		int minFin = d.get(Calendar.MINUTE);

		if (horaActual < horaFin)
			return true;
		else if (horaActual == horaFin) {
			if (minActual <= minFin)
				return true;
			else
				return false;

		} else
			return false;
	}

	static boolean despuesHora(Date inicio, Date actual) {
		Calendar c = Calendar.getInstance();
		c.setTime(actual);
		int horaActual = c.get(Calendar.HOUR_OF_DAY);
		int minActual = c.get(Calendar.MINUTE);

		Calendar d = Calendar.getInstance();
		c.setTime(inicio);
		int horaInicio = d.get(Calendar.HOUR_OF_DAY);
		int minInicio = d.get(Calendar.MINUTE);

		if (horaActual > horaInicio)
			return true;
		else if (horaActual == horaInicio) {
			if (minActual >= minInicio)
				return true;
			else
				return false;

		} else
			return false;
	}
}

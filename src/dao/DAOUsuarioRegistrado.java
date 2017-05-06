package dao;

import utilities.SQLUtils;
import vos.Usuario;
import vos.UsuarioRegistrado;
import vos.reportes.RFC12;
import vos.reportes.RFC7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DAOUsuarioRegistrado extends DAO {
	public DAOUsuarioRegistrado() {
		super();
	}

	public UsuarioRegistrado createUsuarioRegistrado(UsuarioRegistrado object) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO USUARIOS_REGISTRADOS ");
		sql.append("( id_usuario, tipo_id, edad ) ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append(String.format("%s, ", object.getIdentificacion()));
		sql.append(String.format("'%s', ", object.getTipoIdentificacion()));
		sql.append(String.format("%s ", object.getEdad()));
		sql.append(") ");

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();
		s.close();
		return object;
	}

	public List<UsuarioRegistrado> getUsuarioRegistrados() throws SQLException {
		List<UsuarioRegistrado> list = new LinkedList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USUARIOS_REGISTRADOS UR INNER JOIN USUARIOS U");
		sql.append("                             ON UR.id_usuario = U.identificacion ");
		sql.append("                             AND UR.tipo_id = U.tipo_identificacion ");

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		ResultSet rs = s.executeQuery();
		while (rs.next()) {
			list.add(restultToAccesibildiad(rs));
		}
		rs.close();
		s.close();
		return list;
	}

	public UsuarioRegistrado getUsuarioRegistrado(Long id, String tipoId) throws SQLException {
		UsuarioRegistrado object = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USUARIOS_REGISTRADOS UR INNER JOIN USUARIOS U ");
		sql.append("                             ON UR.id_usuario = U.identificacion ");
		sql.append("                             AND UR.tipo_id = U.tipo_identificacion ");
		sql.append(String.format("WHERE id_usuario = %s ", id));
		sql.append(String.format("AND tipo_id = '%s' ", tipoId));

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		ResultSet rs = s.executeQuery();
		if (rs.next()) {
			object = restultToAccesibildiad(rs);
		}
		rs.close();
		s.close();
		return object;
	}

	public UsuarioRegistrado updateUsuarioRegistrado(Long id, String tipo, UsuarioRegistrado object)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE USUARIOS ");
		sql.append(String.format("SET nombre = '%s' ", object.getNombre()));
		sql.append(String.format("SET email = '%s' ", object.getEmail()));
		sql.append(String.format("SET password = '%s' ", object.getPassword()));
		sql.append(String.format("WHERE identificacion = %s ", id));
		sql.append(String.format("AND tipo_identificacion = '%s' ", tipo));

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();

		sql = new StringBuilder();
		sql.append("UPDATE USUARIOS_REGISTRADOS ");
		sql.append(String.format("SET edad = %s ", object.getEdad()));
		sql.append(String.format("WHERE id_usuario = %s ", id));
		sql.append(String.format("AND tipo_id = '%s' ", tipo));

		s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();

		s.close();
		return object;
	}

	public void deleteUsuarioRegistrado(Long id, String tipoId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM USUARIOS_REGISTRADOS ");
		sql.append(String.format("WHERE id_usuario = %s", id));
		sql.append(String.format("AND tipo_id = '%s'", tipoId));

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();

		sql = new StringBuilder();
		sql.append("DELETE FROM USUARIOS ");
		sql.append(String.format("WHERE  identificacion = %s", id));

		s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();

		s.close();
	}

	public static UsuarioRegistrado restultToAccesibildiad(ResultSet rs) throws SQLException {
		UsuarioRegistrado object = new UsuarioRegistrado();
		object.setEmail(rs.getString("email"));
		object.setPassword(rs.getString("password"));
		object.setNombre(rs.getString("nombre"));
		object.setIdentificacion(rs.getLong("id_usuario"));
		object.setTipoIdentificacion(rs.getString("tipo_id"));
		object.setEdad(rs.getInt("edad"));
		object.setIdFestival(rs.getLong("id_festival"));
		object.setRol(rs.getString("rol"));
		return object;
	}

	public RFC7 asistenciaCliente(Long id, String tipo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  B.FECHA, ");
		sql.append("  B.ID_LUGAR, ");
		sql.append("  E.NOMBRE AS NOMBRE_ESPECTACULO, ");
		sql.append("  COUNT(*) AS TOTAL, ");
		sql.append("  F.SE_REALIZA, ");
		sql.append("  F.ID_ESPECTACULO, ");
		sql.append("  E.DURACION ");
		sql.append("FROM ");
		sql.append("  BOLETAS B ");
		sql.append("  INNER JOIN ");
		sql.append("  FUNCIONES F ");
		sql.append("    ON F.FECHA = B.FECHA ");
		sql.append("       AND F.ID_LUGAR = B.ID_LUGAR ");
		sql.append("  INNER JOIN ");
		sql.append("  ESPECTACULOS E ");
		sql.append("    ON F.ID_ESPECTACULO = E.ID ");
		sql.append("  INNER JOIN ");
		sql.append("  USUARIOS U ");
		sql.append("    ON B.ID_USUARIO = U.IDENTIFICACION ");
		sql.append("       AND B.ID_TIPO = U.TIPO_IDENTIFICACION ");
		sql.append(String.format("WHERE U.ROL = '%s' ", Usuario.USUARIO_REGISTRADO));
		sql.append(String.format("AND U.IDENTIFICACION = %s ", id));
		sql.append(String.format("AND U.TIPO_IDENTIFICACION = '%s' ", tipo));
		sql.append(
				"GROUP BY F.FECHA, F.ID_LUGAR, B.FECHA, B.ID_LUGAR, E.NOMBRE, F.SE_REALIZA, F.ID_ESPECTACULO, E.DURACION ");

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		ResultSet rs = s.executeQuery();
		RFC7 req = new RFC7();
		List<RFC7.FuncionEspectaculo> terminadas = new LinkedList<>();
		List<RFC7.FuncionEspectaculo> enCurso = new LinkedList<>();
		List<RFC7.FuncionEspectaculo> previstas = new LinkedList<>();
		List<RFC7.FuncionEspectaculo> canceladas = new LinkedList<>();
		while (rs.next()) {
			RFC7.FuncionEspectaculo f = req.new FuncionEspectaculo();
			f.setCantidad(rs.getInt("TOTAL"));
			f.setFuncion(DAOFuncion.resultToFuncion(rs));
			f.setNombreEspectaculo(rs.getString("NOMBRE_ESPECTACULO"));
			if (rs.getInt("SE_REALIZA") == 2) {
				canceladas.add(f);
			} else {
				Calendar c = Calendar.getInstance();
				Calendar d = SQLUtils.DateUtils.dateToCalendar(rs.getTimestamp("FECHA"));
				Calendar e = SQLUtils.DateUtils.dateToCalendar(rs.getTimestamp("FECHA"));
				e.add(Calendar.MINUTE, rs.getInt("DURACION"));

				if (c.getTimeInMillis() < d.getTimeInMillis()) {
					previstas.add(f);
				} else {
					if (c.getTimeInMillis() < e.getTimeInMillis()) {
						enCurso.add(f);
					} else {
						terminadas.add(f);
					}
				}
			}
		}
		req.setCanceladas(canceladas);
		req.setEnCurso(enCurso);
		req.setPrevistas(previstas);
		req.setTerminadas(terminadas);
		req.setIdUsuario(id);
		req.setTipo(tipo);
		return req;
	}

	public List<RFC12> clientesCheveres(Integer n) throws SQLException {
		List<RFC12> resp = new LinkedList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  U.*, ");
		sql.append("  UR.*, ");
		sql.append("  NUM_BOLETAS_VIP, ");
		sql.append("  NUM_BOLETAS ");
		sql.append("FROM ( SELECT ");
		sql.append("         ID_USUARIO, ");
		sql.append("         ID_TIPO, ");
		sql.append("         ID_LOCALIDAD, ");
		sql.append("         COUNT( * ) AS NUM_BOLETAS_VIP ");
		sql.append("       FROM BOLETAS B ");
		sql.append("       WHERE ID_LOCALIDAD = 1 ");
		sql.append("       GROUP BY ID_USUARIO, ID_TIPO, ID_LOCALIDAD ) Z ");
		sql.append("  INNER JOIN LOCALIDADES L ");
		sql.append("    ON ID_LOCALIDAD = L.ID ");
		sql.append("  INNER JOIN ( SELECT ");
		sql.append("                 ID_USUARIO, ");
		sql.append("                 ID_TIPO, ");
		sql.append("                 SUM( NUM_BOLETAS ) AS NUM_BOLETAS ");
		sql.append("               FROM ( SELECT ");
		sql.append("                        ID_USUARIO, ");
		sql.append("                        ID_TIPO, ");
		sql.append("                        ID_LOCALIDAD, ");
		sql.append("                        COUNT( * ) AS NUM_BOLETAS ");
		sql.append("                      FROM BOLETAS B ");
		sql.append("                      GROUP BY ID_USUARIO, ID_TIPO, ID_LOCALIDAD ) Z ");
		sql.append("                 INNER JOIN LOCALIDADES L ");
		sql.append("                   ON ID_LOCALIDAD = L.ID ");
		sql.append("               GROUP BY ID_USUARIO, ID_TIPO ) W ");
		sql.append("    ON Z.ID_USUARIO = W.ID_USUARIO ");
		sql.append("       AND Z.ID_TIPO = W.ID_TIPO ");
		sql.append("  INNER JOIN ");
		sql.append("  USUARIOS U ");
		sql.append("    ON U.IDENTIFICACION = Z.ID_USUARIO ");
		sql.append("       AND U.TIPO_IDENTIFICACION = Z.ID_TIPO ");
		sql.append("  INNER JOIN ");
		sql.append("  USUARIOS_REGISTRADOS UR ");
		sql.append("    ON U.IDENTIFICACION = UR.ID_USUARIO AND U.TIPO_IDENTIFICACION = UR.TIPO_ID ");
		sql.append(String.format(" WHERE NUM_BOLETAS_VIP = NUM_BOLETAS AND NUM_BOLETAS >= %s", n));

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		ResultSet rs = s.executeQuery();

		while (rs.next()) {
			RFC12 temp = new RFC12(restultToAccesibildiad(rs));
			temp.setCant(rs.getInt("NUM_BOLETAS"));
			resp.add(temp);
		}
		return resp;
	}

}
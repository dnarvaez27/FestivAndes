package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import vos.Silla;

public class DAOSilla extends DAO {
	public DAOSilla() {
		super();
	}

	public Silla createSilla(Silla silla) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO SILLAS ");
		sql.append("( numero_silla, numero_fila, id_lugar, id_localidad ) ");
		sql.append("VALUES ");
		sql.append("( ");
		sql.append(String.format("%s, ", silla.getNumSilla()));
		sql.append(String.format("%s, ", silla.getNumFila()));
		sql.append(String.format("%s, ", silla.getIdLugar()));
		sql.append(String.format("%s ", silla.getIdLocalidad()));
		sql.append(")");

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();
		s.close();
		return silla;
	}

	public List<Silla> getSillas() throws SQLException {
		List<Silla> list = new LinkedList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM SILLAS");

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		ResultSet rs = s.executeQuery();
		while (rs.next()) {
			list.add(resultToSilla(rs));
		}

		s.close();
		return list;
	}

	public Silla getSilla(Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad) throws SQLException {
		Silla silla = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM SILLAS ");
		sql.append(String.format("WHERE numero_silla = %s AND ", num_silla));
		sql.append(String.format("numero_fila = %s AND ", num_fila));
		sql.append(String.format("id_lugar = %s AND ", id_lugar));
		sql.append(String.format("id_localidad = %s", id_localidad));
		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		ResultSet rs = s.executeQuery();
		if (rs.next()) {
			silla = resultToSilla(rs);
		}
		s.close();
		return silla;
	}

	public Silla updateSilla(Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad, Silla silla)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SILLAS ");
		sql.append(String.format("SET numero_silla = %s, ", silla.getNumSilla()));
		sql.append(String.format("numero_fila = %s, ", silla.getNumFila()));
		sql.append(String.format("id_lugar = %s, ", silla.getIdLugar()));
		sql.append(String.format("id_localidad = %s", silla.getIdLocalidad()));
		sql.append(String.format("WHERE numero_silla = %s AND ", num_silla));
		sql.append(String.format("numero_fila = %s AND ", num_fila));
		sql.append(String.format("id_lugar = %s AND ", id_lugar));
		sql.append(String.format("id_localidad = %s", id_localidad));

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();
		s.clearParameters();
		return silla;
	}

	public void deleteSilla(Integer num_silla, Integer num_fila, Long id_lugar, Long id_localidad) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM SILLAS ");
		sql.append(String.format("WHERE numero_silla = %s AND ", num_silla));
		sql.append(String.format("numero_fila = %s AND ", num_fila));
		sql.append(String.format("id_lugar = %s AND ", id_lugar));
		sql.append(String.format("id_localidad = %s", id_localidad));

		PreparedStatement s = connection.prepareStatement(sql.toString());
		recursos.add(s);
		s.execute();
		s.close();
	}

	private Silla resultToSilla(ResultSet rs) throws SQLException {
		Silla pSilla = new Silla();
		pSilla.setNumSilla(rs.getInt("numero_silla"));
		pSilla.setNumFila(rs.getInt("numero_fila"));
		pSilla.setIdLugar(rs.getLong("id_lugar"));
		pSilla.setIdLocalidad(rs.getLong("id_localidad"));
		return pSilla;
	}
}

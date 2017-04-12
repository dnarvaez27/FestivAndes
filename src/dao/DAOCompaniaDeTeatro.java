package dao;

import vos.CompaniaDeTeatro;
import vos.reportes.RFC5;
import vos.reportes.RFC8;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DAOCompaniaDeTeatro extends DAO {
    public DAOCompaniaDeTeatro() {
        super();
    }

    public CompaniaDeTeatro createCompaniaDeTeatro(CompaniaDeTeatro object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO COMPANIAS_DE_TEATRO ");
        sql.append("( id, tipo_id, nombre, nombre_representante, pais_origen, pagina_web, fecha_llegada, fecha_salida ) ");
        sql.append("VALUES ");
        sql.append("( ");
        sql.append(String.format("%s, ", object.getIdentificacion()));
        sql.append(String.format("'%s', ", object.getTipoIdentificacion()));
        sql.append(String.format("'%s', ", object.getNombre()));
        sql.append(String.format("'%s', ", object.getNombreRepresentante()));
        sql.append(String.format("'%s', ", object.getPaisOrigen()));
        sql.append(String.format("'%s', ", object.getPaginaWeb()));
        sql.append(String.format("%s, ", toDate(object.getFechaLlegada())));
        sql.append(String.format("%s ", toDate(object.getFechaSalida())));
        sql.append(")");

        PreparedStatement s = connection.prepareStatement(sql.toString());
        recursos.add(s);
        s.execute();
        s.close();
        return object;
    }

    public List<CompaniaDeTeatro> getCompaniaDeTeatros() throws SQLException {
        List<CompaniaDeTeatro> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM COMPANIAS_DE_TEATRO CT INNER JOIN USUARIOS U");
        sql.append("                                      ON CT.id = U.identificacion ");
        sql.append("                                      AND CT.tipo_id = U.tipo_identificacion ");

        PreparedStatement s = connection.prepareStatement(sql.toString());
        recursos.add(s);
        ResultSet rs = s.executeQuery();
        while (rs.next()) {
            list.add(resultToCompaniaDeTeatro(rs));
        }

        rs.close();
        s.close();
        return list;
    }

    public CompaniaDeTeatro getCompaniaDeTeatro(Long id) throws SQLException {
        CompaniaDeTeatro object = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM COMPANIAS_DE_TEATRO CT INNER JOIN USUARIOS U ");
        sql.append("                            ON CT.id = U.identificacion ");
        sql.append(String.format("WHERE id = %s ", id));
        sql.append(String.format("AND tipo_id = '%s'", CompaniaDeTeatro.TIPO_ID));

        PreparedStatement s = connection.prepareStatement(sql.toString());
        recursos.add(s);
        ResultSet rs = s.executeQuery();
        if (rs.next()) {
            object = resultToCompaniaDeTeatro(rs);
        }
        rs.close();
        s.close();
        return object;
    }

    public CompaniaDeTeatro updateCompaniaDeTeatro(Long idCompania, CompaniaDeTeatro object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE COMPANIAS_DE_TEATRO ");
        sql.append("SET ");
        sql.append(String.format("nombre = '%s', ", object.getNombre()));
        sql.append(String.format("nombre_representante = '%s', ", object.getNombreRepresentante()));
        sql.append(String.format("pagina_web = '%s', ", object.getPaginaWeb()));
        sql.append(String.format("pais_origen = '%s', ", object.getPaisOrigen()));
        sql.append(String.format("fecha_llegada = %s, ", toDate(object.getFechaLlegada())));
        sql.append(String.format("fecha_salida = %s ", toDate(object.getFechaSalida())));
        sql.append(String.format("WHERE id = %s ", idCompania));
        sql.append(String.format("AND tipo_id = '%s'", CompaniaDeTeatro.TIPO_ID));

        PreparedStatement s = connection.prepareStatement(sql.toString());
        recursos.add(s);
        s.execute();
        s.close();
        return object;
    }

    public void deleteCompaniaDeTeatro(Long id) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM COMPANIAS_DE_TEATRO ");
        sql.append(String.format("WHERE id = %s ", id));
        sql.append(String.format("AND tipo_id = '%s'", CompaniaDeTeatro.TIPO_ID));

        PreparedStatement s = connection.prepareStatement(sql.toString());
        recursos.add(s);
        s.execute();
        s.close();
    }

    public List<RFC5> rentabilidad() {
        List<RFC5> list = new LinkedList<>();
        //TODO RFC5
        return list;
    }

    public static CompaniaDeTeatro resultToBasicCompaniaDeTeatro(ResultSet rs) throws SQLException {
        CompaniaDeTeatro object = new CompaniaDeTeatro();
        object.setIdentificacion(rs.getLong("id"));
        object.setTipoIdentificacion(rs.getString("tipo_id"));
        object.setNombre(rs.getString("nombre"));
        object.setNombreRepresentante(rs.getString("nombre_representante"));
        object.setPaginaWeb(rs.getString("pagina_web"));
        object.setPaisOrigen(rs.getString("pais_origen"));
        object.setFechaLlegada(rs.getDate("fecha_llegada"));
        object.setFechaSalida(rs.getDate("fecha_salida"));
        return object;
    }

    private static CompaniaDeTeatro resultToCompaniaDeTeatro(ResultSet rs) throws SQLException {
        CompaniaDeTeatro object = resultToBasicCompaniaDeTeatro(rs);
        object.setEmail(rs.getString("email"));
        object.setPassword(rs.getString("password"));
        object.setRol(rs.getString("rol"));
        object.setIdFestival(rs.getLong("id_festival"));
        return object;
    }

    public RFC8 informacionCompania(Long idCompania) throws SQLException {
        //StringBuilder sql = new StringBuilder();
        //sql.append("SELECT E.NOMBRE, ");
        //	sql.append("FROM ");
//		sql.append("  BOLETAS B ");
//		sql.append("  INNER JOIN ");
//		sql.append("  COSTO_LOCALIDAD CL ");
//		sql.append("    ON B.FECHA = CL.FECHA ");
//		sql.append("       AND B.ID_LUGAR = CL.ID_LUGAR ");
//		sql.append("       AND B.ID_LOCALIDAD = CL.ID_LOCALIDAD ");
//		sql.append("  INNER JOIN ");
//		sql.append("  LUGARES L ");
//		sql.append("    ON L.ID = B.ID_LUGAR ");
//		sql.append("       AND B.ID_LUGAR = CL.ID_LUGAR ");
//		sql.append("       AND B.ID_LOCALIDAD = CL.ID_LOCALIDAD ");
//		sql.append("  INNER JOIN FUNCIONES F");
//		sql.append("    ON F.FECHA = B.FECHA ");
//		sql.append("       AND F.ID_LUGAR = B.ID_LUGAR ");
//		sql.append("  INNER JOIN ESPECTACULOS E");
//		sql.append("    ON F.ID_ESPECTACULO = E.ID ");
//		sql.append("  INNER JOIN OFRECEN O");
//		sql.append("    ON O.ID_ESPECTACULO = E.ID ");
//		sql.append("  INNER JOIN COMPANIA_TEATRO CT ");
//		sql.append("  	ON   O.ID_COMPANIA_TEATRO = CT.ID ");
//		sql.append(String.format("WHERE CT.ID = %s ", idCompania));


//		PreparedStatement s = connection.prepareStatement(sql.toString());
//		recursos.add(s);
//		ResultSet rs = s.executeQuery();

        return null;

    }

    public List<RFC8> informacionTodasCompanias() {
        return null;
    }
}
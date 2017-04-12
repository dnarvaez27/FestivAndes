package dao;

import vos.CompaniaDeTeatro;
import vos.Usuario;
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
        StringBuilder sql = new StringBuilder();
        sql.append( "SELECT " );
        sql.append( "  ID_ESPECTACULO, " );
        sql.append( "  NOMBRE_ESPECTACULO, " );
        sql.append( "  ID_COMPANIA, " );
        sql.append( "  NOMBRE_COMPANIA, " );
        sql.append( "  FECHA_FUNCION, " );
        sql.append( "  LUGAR_FUNCION, " );
        sql.append( "  ASISTENCIA * 100 / TOTAL_CAPACIDAD AS OCUPACION, " );
        sql.append( "  ASISTENCIA, " );
        sql.append( "  ASISTENCIA_REGISTRADOS, " );
        sql.append( "  TOTAL " );
        sql.append( "FROM (SELECT " );
        sql.append( "        E.ID          AS ID_ESPECTACULO, " );
        sql.append( "        E.NOMBRE      AS NOMBRE_ESPECTACULO, " );
        sql.append( "        CT.ID         AS ID_COMPANIA, " );
        sql.append( "        CT.NOMBRE     AS NOMBRE_COMPANIA, " );
        sql.append( "        F.FECHA       AS FECHA_FUNCION, " );
        sql.append( "        F.ID_LUGAR    AS LUGAR_FUNCION, " );
        sql.append( "        COUNT(*)      AS ASISTENCIA, " );
        sql.append( "        SUM(CL.COSTO) AS TOTAL " );
        sql.append( "      FROM " );
        sql.append( "        BOLETAS B " );
        sql.append( "        INNER JOIN USUARIOS U " );
        sql.append( "          ON U.IDENTIFICACION = B.ID_USUARIO " );
        sql.append( "             AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
        sql.append( "        INNER JOIN " );
        sql.append( "        COSTO_LOCALIDAD CL " );
        sql.append( "          ON B.FECHA = CL.FECHA " );
        sql.append( "             AND B.ID_LUGAR = CL.ID_LUGAR " );
        sql.append( "             AND B.ID_LOCALIDAD = CL.ID_LOCALIDAD " );
        sql.append( "        INNER JOIN " );
        sql.append( "        LUGARES L " );
        sql.append( "          ON L.ID = B.ID_LUGAR " );
        sql.append( "        INNER JOIN FUNCIONES F " );
        sql.append( "          ON F.FECHA = B.FECHA " );
        sql.append( "             AND F.ID_LUGAR = B.ID_LUGAR " );
        sql.append( "        INNER JOIN ESPECTACULOS E " );
        sql.append( "          ON F.ID_ESPECTACULO = E.ID " );
        sql.append( "        INNER JOIN OFRECE O " );
        sql.append( "          ON O.ID_ESPECTACULO = E.ID " );
        sql.append( "        INNER JOIN COMPANIAS_DE_TEATRO CT " );
        sql.append( "          ON O.ID_COMPANIA_DE_TEATRO = CT.ID " );
        sql.append( "      WHERE CT.ID = 0 " );
        sql.append( "      GROUP BY E.ID, E.NOMBRE, CT.ID, CT.NOMBRE, F.FECHA, F.ID_LUGAR " );
        sql.append( "      ORDER BY E.ID) Z " );
        sql.append( "  LEFT JOIN " );
        sql.append( "  (SELECT " );
        sql.append( "     CL.FECHA, " );
        sql.append( "     CL.ID_LUGAR, " );
        sql.append( "     COUNT(*) AS ASISTENCIA_REGISTRADOS " );
        sql.append( "   FROM " );
        sql.append( "     BOLETAS B " );
        sql.append( "     INNER JOIN " );
        sql.append( "     COSTO_LOCALIDAD CL " );
        sql.append( "       ON B.FECHA = CL.FECHA " );
        sql.append( "          AND B.ID_LUGAR = CL.ID_LUGAR " );
        sql.append( "          AND B.ID_LOCALIDAD = CL.ID_LOCALIDAD " );
        sql.append( "     INNER JOIN " );
        sql.append( "     USUARIOS U " );
        sql.append( "       ON B.ID_USUARIO = U.IDENTIFICACION " );
        sql.append( "          AND B.ID_TIPO = U.TIPO_IDENTIFICACION " );
        sql.append(String.format("   WHERE U.ROL = '%s' ", Usuario.USUARIO_REGISTRADO) );
        sql.append( "   GROUP BY CL.FECHA, CL.ID_LUGAR, U.ROL) B " );
        sql.append( "    ON Z.FECHA_FUNCION = B.FECHA " );
        sql.append( "       AND Z.LUGAR_FUNCION = B.ID_LUGAR " );
        sql.append( "  LEFT JOIN " );
        sql.append( "  (SELECT " );
        sql.append( "     L.ID, " );
        sql.append( "     SUM(CAPACIDAD) AS TOTAL_CAPACIDAD " );
        sql.append( "   FROM LUGARES L " );
        sql.append( "     INNER JOIN LUGAR_LOCALIDAD LL " );
        sql.append( "       ON L.ID = LL.ID_LUGAR " );
        sql.append( "   GROUP BY L.ID) C " );
        sql.append( "    ON C.ID = Z.LUGAR_FUNCION " );
        sql.append(String.format("WHERE ID_COMPANIA = %s ",idCompania));
        PreparedStatement s = connection.prepareStatement(sql.toString());
        recursos.add(s);
        ResultSet rs = s.executeQuery();

        RFC8 resp = new RFC8();
        List<RFC8.RFC8aux> list = new LinkedList<>();
        Long idActual = -1l;
        boolean algo = false;

        while (rs.next()) {
            RFC8.RFC8aux aux = resp.new RFC8aux();
            List<RFC8.FuncionOcupacion> temp = new LinkedList<>();
            aux.setNombreespectaculo("NOMBRE_ESPECTACULO");
            idActual = idActual ==-1? rs.getLong("ID_ESPECTACULO"):idActual;
            while (rs.getLong("ID_ESPECTACULO") == (idActual)) {
                RFC8.FuncionOcupacion fo = resp.new FuncionOcupacion();
                fo.setAsistencia(rs.getInt("ASISTENCIA"));
                fo.setAsistencia_registrados(rs.getInt("ASISTENCIA_REGISTRADOS"));
                fo.setFecha(rs.getDate("FECHA_FUNCION"));
                fo.setIdFuncion(rs.getLong("LUGAR_FUNCION"));
                fo.setPorcentaje(rs.getDouble("OCUPACION"));
                fo.setTotalPlata(rs.getDouble("TOTAL"));
                temp.add(fo);
                if(!rs.next())
                {
                    algo = true;
                    break;
                }

            }
            aux.setOcupaciones(temp);
            Integer asistencia = 0;
            Integer asistenciaRegistrados = 0;
            Double plataTotal = 0d;

            for (RFC8.FuncionOcupacion fo : temp) {
                asistencia += fo.getAsistencia();
                asistenciaRegistrados += fo.getAsistencia_registrados();
                plataTotal += fo.getTotalPlata();
            }
            aux.setAsistencia(asistencia);
            aux.setAsistenciaRegistrados(asistenciaRegistrados);
            aux.setDinero(plataTotal);
            list.add(aux);
            if(algo)
                break;
            idActual = rs.getLong("ID_ESPECTACULO");
            rs.previous();
        }
        resp.setEspectaculos(list);
        return resp;
    }
}
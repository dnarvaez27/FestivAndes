package dao.intermediate;

import dao.DAO;
import vos.Abono;
import vos.intermediate.CostoLocalidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dave on 9/04/2017.
 */
public class DAOAbonoFuncion extends DAO
{
	public void createEntryAbonoFuncion( Abono abono, Date fechaFuncion, Long idLugarFuncion, Long idLocalidadFuncion ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO ABONO_FUNCION " );
		sql.append( "(ID_FESTIVAL, ID_USUARIO, TIPO_ID, FECHA, ID_LUGAR, ID_LOCALIDAD) " );
		sql.append( "VALUES ( " );
		sql.append( String.format( "%s, ", abono.getIdFestival( ) ) );
		sql.append( String.format( "%s, ", abono.getIdUsuario( ) ) );
		sql.append( String.format( "'%s', ", abono.getTipoId( ) ) );
		sql.append( String.format( "%s, ", toDateTime( fechaFuncion ) ) );
		sql.append( String.format( "%s, ", idLugarFuncion ) );
		sql.append( String.format( "%s ", idLocalidadFuncion ) );
		sql.append( ") " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
	
	public List<CostoLocalidad> getFuncionesFromAbono( Long idFestival, Long idUsuario, String tipoId ) throws SQLException
	{
		List<CostoLocalidad> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT F.* " );
		sql.append( "FROM " );
		sql.append( "  ABONO A " );
		sql.append( "  INNER JOIN " );
		sql.append( "  ABONO_FUNCION AF " );
		sql.append( "    ON A.ID_FESTIVAL = AF.ID_FESTIVAL " );
		sql.append( "       AND A.ID_USUARIO = AF.ID_USUARIO " );
		sql.append( "       AND A.TIPO_ID = AF.TIPO_ID " );
		sql.append( "  INNER JOIN " );
		sql.append( "  COSTO_LOCALIDAD F " );
		sql.append( "    ON AF.ID_LUGAR = F.ID_LUGAR " );
		sql.append( "       AND AF.FECHA = F.FECHA " );
		sql.append( "       AND AF.ID_LOCALIDAD = F.ID_LOCALIDAD " );
		sql.append( String.format( "WHERE A.ID_FESTIVAL = %s ", idFestival ) );
		sql.append( String.format( "  AND A.ID_USUARIO = %s ", idUsuario ) );
		sql.append( String.format( "  AND A.TIPO_ID = '%s' ", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( DAOCostoLocalidad.resultToCostoLocalidad( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public void deleteEntriesFromAbono( Long idFestival, Long idUsuario, String tipoId ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM ABONO_FUNCION " );
		sql.append( String.format( "WHERE ID_FESTIVAL = %s ", idFestival ) );
		sql.append( String.format( "  AND ID_USUARIO = %s ", idUsuario ) );
		sql.append( String.format( "  AND TIPO_ID = '%s' ", tipoId ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		s.execute( );
		s.close( );
	}
}
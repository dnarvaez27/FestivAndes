package dao;

import vos.Boleta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAOBoleta extends DAO
{
	public DAOBoleta( )
	{
		super( );
	}
	
	public Boleta createBoleta( Boleta boleta ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO BOLETAS " );
		sql.append( "(NUM_BOLETA, NUM_SILLA, NUM_FILA, ID_LOCALIDAD, ID_LUGAR, FECHA, ID_USUARIO, ID_TIPO ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", boleta.getNumBoleta( ) ) );
		sql.append( String.format( "%s, ", boleta.getNumeroSilla( ) ) );
		sql.append( String.format( "%s, ", boleta.getNumeroFila( ) ) );
		sql.append( String.format( "%s, ", boleta.getIdLocalidad( ) ) );
		sql.append( String.format( "%s, ", boleta.getIdLugar( ) ) );
		sql.append( String.format( "%s, ", toDateTime( boleta.getFecha( ) ) ) );
		sql.append( String.format( "%s, ", boleta.getIdUsuario( ) ) );
		sql.append( String.format( "'%s' ", boleta.getTipoIdUsuario( ) ) );
		sql.append( ") " );
		
		System.out.println( sql.toString( ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		
		s.close( );
		return boleta;
	}
	
	public Long getNextBoleta( ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT MAX(NUM_BOLETA) AS bol_actual " );
		sql.append( "FROM BOLETAS " );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		rs.next( );
		return rs.getLong( "bol_actual" ) + 1;
	}
	
	public List<Boleta> getBoletas( ) throws SQLException
	{
		List<Boleta> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM BOLETAS" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToBoleta( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public List<Boleta> getBoletasFrom( Long idUsuario, String tipo ) throws SQLException
	{
		List<Boleta> list = new LinkedList<>( );
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * FROM BOLETAS " );
		sql.append( String.format( "WHERE id_usuario = %s ", idUsuario ) );
		sql.append( String.format( "  AND id_tipo = '%s' ", tipo ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		while( rs.next( ) )
		{
			list.add( resultToBoleta( rs ) );
		}
		
		rs.close( );
		s.close( );
		return list;
	}
	
	public Boleta getBoleta( Long numBoleta ) throws SQLException
	{
		Boleta object = null;
		
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM BOLETAS " );
		sql.append( String.format( "WHERE num_boleta = %s", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		ResultSet rs = s.executeQuery( );
		if( rs.next( ) )
		{
			object = resultToBoleta( rs );
		}
		rs.close( );
		s.close( );
		return object;
	}
	
	public Boleta updateBoleta( Long numBoleta, Boleta object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE BOLETAS " );
		sql.append( "SET" );
		sql.append( String.format( "num_silla = %s, ", object.getNumeroSilla( ) ) );
		sql.append( String.format( "num_fila = %s, ", object.getNumeroFila( ) ) );
		sql.append( String.format( "id_localidad = %s, ", object.getIdLocalidad( ) ) );
		sql.append( String.format( "id_lugar = %s, ", object.getIdLugar( ) ) );
		sql.append( String.format( "fecha = %s, ", toDateTime( object.getFecha( ) ) ) );
		sql.append( String.format( "id_usuario = %s ", object.getIdUsuario( ) ) );
		sql.append( String.format( "WHERE num_boleta = %s ", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
	}
	
	public void deleteBoleta( Long numBoleta ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "DELETE FROM BOLETAS " );
		sql.append( String.format( "WHERE num_boleta = %s", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
	}
	
	private static Boleta resultToBoleta( ResultSet rs ) throws SQLException
	{
		Boleta object = new Boleta( );
		object.setNumBoleta( rs.getLong( "num_boleta" ) );
		object.setNumeroSilla( rs.getLong( "num_silla" ) );
		object.setNumeroFila( rs.getLong( "num_fila" ) );
		object.setIdLocalidad( rs.getLong( "id_localidad" ) );
		object.setIdLugar( rs.getLong( "id_lugar" ) );
		object.setFecha( rs.getTimestamp( "fecha" ) );
		object.setIdUsuario( rs.getLong( "id_usuario" ) );
		object.setTipoIdUsuario( rs.getString( "id_tipo" ) );
		return object;
	}
	
	public boolean verificarCompraBoleta( Boleta boleta ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "SELECT * " );
		sql.append( "FROM BOLETAS " );
		sql.append( String.format( "WHERE NUM_SILLA = %s ", boleta.getNumeroSilla( ) ) );
		sql.append( String.format( "  AND NUM_FILA = %s ", boleta.getNumeroFila( ) ) );
		sql.append( String.format( "  AND ID_LUGAR = %s ", boleta.getIdLugar( ) ) );
		sql.append( String.format( "  AND ID_LOCALIDAD = %s ", boleta.getIdLocalidad( ) ) );
		sql.append( String.format( "  AND fecha = %s ", toDateTime( boleta.getFecha( ) ) ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		ResultSet rs = s.executeQuery( );
		boolean rta = !rs.next( );
		
		rs.close( );
		s.close( );
		return rta;
	}
}
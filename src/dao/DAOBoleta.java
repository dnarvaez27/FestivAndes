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
	
	public Boleta createBoleta( Boleta object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "INSERT INTO BOLETAS " );
		sql.append( "( num_boleta, id_numero_silla, id_numero_fila, id_localidad, id_lugar, fecha, id_usuario ) " );
		sql.append( "VALUES " );
		sql.append( "( " );
		sql.append( String.format( "%s, ", object.getNumBoleta( ) ) );
		sql.append( String.format( "%s, ", object.getIdNumeroSilla( ) ) );
		sql.append( String.format( "%s, ", object.getIdNumeroFila( ) ) );
		sql.append( String.format( "%s, ", object.getIdLocalidad( ) ) );
		sql.append( String.format( "%s, ", object.getIdLugar( ) ) );
		sql.append( String.format( "%s, ", object.getFecha( ) ) );
		sql.append( String.format( "%s, ", object.getIdUsuario( ) ) );
		sql.append( ")" );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.close( );
		return object;
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
		s.close( );
		return object;
	}
	
	public Boleta updateBoleta( Long numBoleta, Boleta object ) throws SQLException
	{
		StringBuilder sql = new StringBuilder( );
		sql.append( "UPDATE BOLETAS " );
		sql.append( "SET" );
		sql.append( String.format( "id_numero_silla = '%s'", object.getIdNumeroSilla( ) ) );
		sql.append( String.format( "id_numero_fila = '%s'", object.getIdNumeroFila( ) ) );
		sql.append( String.format( "id_localidad = '%s'", object.getIdLocalidad( ) ) );
		sql.append( String.format( "id_lugar = '%s'", object.getIdLugar( ) ) );
		sql.append( String.format( "fecha = '%s'", object.getFecha( ) ) );
		sql.append( String.format( "id_usuario = '%s'", object.getIdUsuario( ) ) );
		sql.append( String.format( "WHERE numBoleta = %s", numBoleta ) );
		
		PreparedStatement s = connection.prepareStatement( sql.toString( ) );
		recursos.add( s );
		s.execute( );
		s.clearParameters( );
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
	
	private Boleta resultToBoleta( ResultSet rs ) throws SQLException
	{
		Boleta object = new Boleta( );
		object.setNumBoleta( rs.getLong( "num_boleta" ) );
		object.setIdNumeroSilla( rs.getLong( "id_numero_silla" ) );
		object.setIdNumeroFila( rs.getLong( "id_numero_fila" ) );
		object.setIdLocalidad( rs.getLong( "id_localidad" ) );
		object.setIdLugar( rs.getLong( "id_lugar" ) );
		object.setFecha( rs.getDate( "fecha" ) );
		object.setIdUsuario( rs.getLong( "id_usuario" ) );
		return object;
	}
}
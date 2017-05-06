package dao;

import utilities.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO
{
	protected List<Object> recursos;
	
	protected Connection connection;
	
	public DAO( )
	{
		recursos = new LinkedList<>( );
	}
	
	public void cerrarRecursos( )
	{
		for( Object ob : recursos )
		{
			if( ob instanceof PreparedStatement )
			{
				try
				{
					( ( PreparedStatement ) ob ).close( );
				}
				catch( Exception ex )
				{
					ex.printStackTrace( );
				}
			}
		}
	}
	
	public void setConnection( Connection con )
	{
		this.connection = con;
	}
	
	public String toDate( Date date )
	{
		// Para corregir el dia
		Calendar c = Calendar.getInstance( );
		c.setTime( date );
		c.add( Calendar.DATE, 1 );
		date = c.getTime( );
		return SQLUtils.DateUtils.toDate( date );
	}
	
	public String toDateTime( Date date )
	{
		Calendar c = Calendar.getInstance( );
		c.setTime( date );
		//		c.add( Calendar.DATE, 1 );
		date = c.getTime( );
		return SQLUtils.DateUtils.toDateTime( date );
	}
	
	public String toDate( String date )
	{
		return SQLUtils.DateUtils.toDate( date );
	}
	
	public String toTime( Date date )
	{
		return SQLUtils.DateUtils.getTime( date );
	}
}
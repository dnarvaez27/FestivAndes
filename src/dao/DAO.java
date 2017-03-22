package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO
{
	private static final String dateStringFormat = "YYYY-MM-DD";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat( dateStringFormat );
	
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
		return String.format( "TO_DATE( '%s', '%s' )", dateFormat.format( date ), dateStringFormat );
	}
}
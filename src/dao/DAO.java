package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public abstract class DAO
{
	protected List<Object> recursos;
	
	protected Connection conn;
	
	public DAO( )
	{
		recursos = new LinkedList<Object>( );
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
		this.conn = con;
	}
}
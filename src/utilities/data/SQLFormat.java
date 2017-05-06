package utilities.data;

import vos.Lugar;

import java.lang.reflect.Field;

/**
 * Created by dnarv on 3/05/2017.
 */
public class SQLFormat
{
	
	public static String create( Class<?> c, String name )
	{
		StringBuilder sBuilder = new StringBuilder( );
		sBuilder.append( String.format( "CREATE TABLE %s", name ) );
		sBuilder.append( "( " );
		
		for( Field field : c.getDeclaredFields( ) )
		{
			String type = field.getType( ).getName( ).substring( field.getType( ).getName( ).lastIndexOf( "." ) + 1 );
			sBuilder.append( String.format( "\t%s %s,\n", field.getName( ), type ) );
		}
		sBuilder.append( ") " );
		
		return sBuilder.toString( );
	}
	
	public static void main( String[] args )
	{
		System.out.println( create( Lugar.class, "LUGARES" ) );
	}
}
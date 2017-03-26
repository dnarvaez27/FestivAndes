package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dave on 25/03/2017.
 */
public class DateFormatter
{
	// todo HORAS?
	private static final String dateStringFormat = "yyyy-MM-dd";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat( dateStringFormat );
	
	public static Date format( String date )
	{
		try
		{
			return dateFormat.parse( date );
		}
		catch( ParseException e )
		{
			return null;
		}
	}
	
	public static String toDate( Date date )
	{
		return String.format( "TO_DATE( '%s', '%s' )", dateFormat.format( date ), dateStringFormat );
	}
	
	public static String toDate( String date )
	{
		return String.format( "TO_DATE( '%s', '%s' )", date, dateStringFormat );
	}
}
package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dave on 25/03/2017.
 */
public class DateUtils
{
	private static final String dateTimeStringFormatSQL = "YYYY-MM-DD HH24:MI";
	
	private static final String dateTimeStringFormat = "yyyy-MM-dd HH:mm";
	
	private static final String dateStringFormat = "yyyy-MM-dd";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat( dateStringFormat );
	
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat( dateTimeStringFormat );
	
	public static Date milisToDate( Long milis )
	{
		Calendar c = dateToCalendar( new Date( milis ) );
		c.add( Calendar.DATE, -1 );
		return c.getTime( );
	}
	
	public static Date format( String date )
	{
		try
		{
			Calendar c = dateToCalendar( dateFormat.parse( date ) );
			c.add( Calendar.DATE, -1 );
			return c.getTime( );
		}
		catch( ParseException e )
		{
			e.printStackTrace( );
			return null;
		}
	}
	
	public static String toDate( Date date )
	{
		return String.format( "TO_DATE( '%s', '%s' )", dateFormat.format( date ), dateStringFormat );
	}
	
	public static String toDateTime( Date date )
	{
		return String.format( "TO_DATE( '%s', '%s' )", dateTimeFormat.format( date ), dateTimeStringFormatSQL );
	}
	
	public static String toDate( String date )
	{
		return String.format( "TO_DATE( '%s', '%s' )", date, dateStringFormat );
	}
	
	public static Calendar dateToCalendar( Date date )
	{
		Calendar c = Calendar.getInstance( );
		c.setTime( date );
		return c;
	}
	
	public static boolean isDateBetween( Date date, Date begining, Date end )
	{
		Calendar c = dateToCalendar( date );
		return ( dateToCalendar( begining ).before( c ) && dateToCalendar( end ).after( c ) ) || date.equals( c );
	}
}
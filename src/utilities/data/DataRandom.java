package utilities.data;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by dnarv on 30/04/2017.
 */
public class DataRandom
{
	private static final Random random = new Random( );
	
	public static Date generateRandomDate( )
	{
		Calendar c = Calendar.getInstance( );
		c.set( Calendar.MONTH, random.nextInt( 11 ) );
		c.set( Calendar.DAY_OF_MONTH, random.nextInt( 30 ) );
		c.set( Calendar.YEAR, ( int ) ( ( random.nextDouble( ) * 20 ) + 2000 ) );
		c.set( Calendar.HOUR_OF_DAY, 0 );
		c.set( Calendar.MINUTE, 0 );
		return c.getTime( );
	}
	
	public static Date generateRandomTimestamp( )
	{
		Calendar c = Calendar.getInstance( );
		c.set( Calendar.MONTH, random.nextInt( 11 ) );
		c.set( Calendar.DAY_OF_MONTH, random.nextInt( 30 ) );
		c.set( Calendar.YEAR, ( int ) ( ( random.nextDouble( ) * 20 ) + 2000 ) );
		c.set( Calendar.HOUR_OF_DAY, random.nextInt( 23 ) );
		c.set( Calendar.MINUTE, random.nextInt( 59 ) );
		return c.getTime( );
	}
	
	public static Date generateNextRandomDate( Date date, int maxDays )
	{
		int days = random.nextInt( maxDays );
		days = days == 0 ? maxDays : days;
		Calendar c = Calendar.getInstance( );
		c.setTime( date );
		c.add( Calendar.DAY_OF_YEAR, days );
		return c.getTime( );
	}
	
	public static Date generateNextRandomTimestamp( Date date, int maxDays, int maxMinutes )
	{
		maxDays = maxDays <= 0 ? 1 : maxDays;
		maxMinutes = maxMinutes <= 0 ? 1 : maxMinutes;
		
		int days = random.nextInt( maxDays );
		int mins = random.nextInt( maxMinutes );
		
		Calendar c = Calendar.getInstance( );
		c.setTime( date );
		c.add( Calendar.DAY_OF_YEAR, days );
		c.add( Calendar.MINUTE, mins );
		
		return c.getTime( );
	}
	
	public static Integer nextInt( Integer bound )
	{
		return random.nextInt( bound );
	}
	
	public static Integer nextIntBetween( Integer lowerBound, Integer upperBound )
	{
		Integer number = random.nextInt( upperBound );
		return number < lowerBound ? lowerBound : number;
	}
	
	public static Double nextDoubleBetween( Double lowerBound, Double upperBound )
	{
		Double number = random.nextDouble( ) * upperBound;
		return number < lowerBound ? lowerBound : number;
	}
	
	public static Float nextFloatBetween( Float lowerBound, Float upperBound )
	{
		Float number = random.nextFloat( ) * upperBound;
		return number < lowerBound ? lowerBound : number;
	}
}
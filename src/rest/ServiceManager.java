package rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by dnarv on 24/05/2017.
 */
@WebListener
public class ServiceManager implements ServletContextListener
{
	@Override
	public void contextInitialized( ServletContextEvent servletContextEvent )
	{
		System.out.println( "Its me Bitches" );
		
	}
	
	@Override
	public void contextDestroyed( ServletContextEvent servletContextEvent )
	{
		System.out.println( "Bye Bitches" );
	}
}

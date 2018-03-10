package com.sapo.drakkar.web.listener;

import com.sapo.drakkar.common.dao.DaoLookup;
import com.sapo.drakkar.common.dao.SystemPropDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

/**
 * Created by giampaolo.saporito on 15/12/2016.
 * Startup and shutdown listener
 */
@WebListener
public class DrakkarListener implements ServletContextListener
{
	private static final Logger logger = LogManager.getLogger( DrakkarListener.class  );

	@Override
	public void contextInitialized( ServletContextEvent sce )
	{
		System.out.println("===================================================");
		System.out.println(" Drakkar System started " + ( new Date().toString() ) );
		System.out.println("===================================================");

		SystemPropDao sysDao = DaoLookup.getDao( SystemPropDao.class );
		String enviName = sysDao.getEnvironmentName();
		
		logger.debug("===================================================" );
		logger.debug("Drakkar System Started at: "+ ( new Date().toString() ) +"- environment: " + enviName );
		logger.debug("===================================================" );
	}

	@Override
	public void contextDestroyed( ServletContextEvent sce )
	{
		System.out.println("===================================================");
		System.out.println(" Drakkar System end " + (new Date().toString()) );
		System.out.println("===================================================");
		
		logger.debug("===================================================" );
		logger.debug("Drakkar System stop at: "+ new Date().toString() );
		logger.debug("===================================================" );
	}
}

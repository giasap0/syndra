package com.sapo.drakkar.common.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Objects;

/**
 * Created by giampaolo.saporito on 21/12/2016.
 * classe per il lookup dal web container per il layer di accesso ai dati
 */
public class DaoLookup
{
	private static final Logger logger = LogManager.getLogger( DaoLookup.class );
	
	private DaoLookup() {}
	
	private static String getEarName() { return "drakkar.ear"; }
	private static String getWarName() { return "drakkar-web"; }

	/**
	 * @param claz used for class name
	 * @return instance of the requested Dao
	 */
	public static <T> T getDao(Class<T> claz)
	{
		if( claz == null ) { throw new NullPointerException( DaoLookup.class.getSimpleName()+ " - getDao(Class claz). required input is null"); }
		
		String ejbLookupString = "java:global/" + getEarName() + "/" + getWarName() + "/" + claz.getSimpleName();
		T ret = null;
		try {
			Context context = new InitialContext();
			
			Object obj = context.lookup( ejbLookupString );
			Objects.requireNonNull( obj );
			ret = claz.cast(  obj  );
		} catch( NamingException | ClassCastException e ) {
			logger.fatal("cannot lookup for DAO " + claz.getName() + " With lookupString " + ejbLookupString );
			logger.fatal(  e.getMessage() );
		}

		return ret;
	}

}

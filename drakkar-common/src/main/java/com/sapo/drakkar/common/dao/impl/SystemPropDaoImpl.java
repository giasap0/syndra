package com.sapo.drakkar.common.dao.impl;

import com.sapo.drakkar.common.dao.SystemPropDao;
import com.sapo.drakkar.common.entities.Entity_SystemProp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by giampaolo.saporito on 20/12/2016.
 *
 */
@Stateless(name="SystemPropDao")
@Local(SystemPropDao.class)
public class SystemPropDaoImpl implements SystemPropDao
{
	@PersistenceContext( unitName = "persistence_drakkar_common" )
	private EntityManager em;

	private static final Logger logger = LogManager.getLogger( SystemPropDaoImpl.class );

	
	private EntityManager eManager() {
		if( em == null ){
			logger.fatal("entity manager is null");
			throw new RuntimeException( getClass().getCanonicalName() + "- EntityManager is null" );
		}
		return em;
	}
	
	@Override
	public Entity_SystemProp getSystemPropByCode( String code )
	{
		if(code == null || code.isEmpty() ) { throw new IllegalArgumentException("getSystemPropByCode() - code is null"); }
		
		String queryString = "SELECT s FROM Entity_SystemProp AS s WHERE s.parameter = :param";
		TypedQuery<Entity_SystemProp> query = eManager().createQuery( queryString, Entity_SystemProp.class ).setParameter("param", code );
		Entity_SystemProp dto = query.getSingleResult();
		
		if( dto == null ){
			logger.warn("cannot find system property with code " + code );
		}
		return dto;
	}

	@Override
	public String getEnvironmentName()
	{
		Entity_SystemProp prop =  getSystemPropByCode("environment");
		if( prop == null ){
			return "";
		}
		return prop.getValue();
	}
}

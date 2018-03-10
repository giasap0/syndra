package com.sapo.drakkar.common.dao.impl;

import com.sapo.drakkar.common.dao.UserDao;
import com.sapo.drakkar.common.dto.DTO_User;
import com.sapo.drakkar.common.dto.User;
import com.sapo.drakkar.common.entities.Entity_User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
@Stateless(name="UserDao")
@Local(UserDao.class)
public class UserDaoImpl implements UserDao
{
	@PersistenceContext( unitName = "persistence_drakkar_common" )
	private EntityManager em;

	private static final Logger logger = LogManager.getLogger( UserDaoImpl.class );

	private EntityManager eManager() {
		if( em == null ){
			logger.fatal("entity manager is null");
			throw new RuntimeException( getClass().getCanonicalName() + "- EntityManager is null" );
		}
		return em;
	}

	@Override
	public User getUserByName( String username ) {
		if( username == null || username.trim().isEmpty() ) {
			throw new IllegalArgumentException("getUserByName() - username is null");
		}
		String queryString = "SELECT u FROM Entity_User AS u WHERE u.username = :param";
		TypedQuery<Entity_User> query = eManager().createQuery( queryString, Entity_User.class ).setParameter( "param", username );
		Entity_User  entity = query.getSingleResult();
		User usr = null;
		if (entity != null ) {
			usr = new DTO_User( entity );
		}
		return usr;
	}

	@Override
	public String getUserServiceKey( String username ) {
		if( username == null || username.trim().isEmpty() ) {
			return null;
		}
		String queryString = "SELECT u.code FROM Entity_User AS u WHERE u.username = :param";
		TypedQuery<String> query = eManager().createQuery( queryString, String.class ).setParameter( "param", username );
		String  serviceKey = query.getSingleResult();
		if( serviceKey != null && serviceKey.isEmpty() ) {
			serviceKey = null;
		}
		return serviceKey;
	}

}

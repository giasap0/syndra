package com.sapo.drakkar.common.autentication;

import com.sapo.drakkar.common.dao.UserDao;
import com.sapo.drakkar.common.dto.User;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
@Stateful(name="AuthenticationProvider")
@Local(AuthenticationProvider.class)
@ApplicationScoped
public class AuthenticationProviderImpl implements AuthenticationProvider, Serializable
{
	//inject dei DAO che mi servono
	@Inject
	private UserDao usersDao;

	/** serviceKey, user container */
	private Map<String, AuthenticatedUser> authenticated = new HashMap<>();

	@Override
	public AuthenticatedUser authenticateUser( String serviceKey, String authToken ) throws AuthException {
		if( serviceKey == null || authToken == null || serviceKey.isEmpty() || authToken.isEmpty() ) {
			throw new AuthException( "invalid access" );
		}
		AuthenticatedUser user = authenticated.get( serviceKey );
		if( user != null ) {
			if( authToken.equals(  user.getAuthToken() ) ) {
				return user;
			}
		}
		throw new AuthException( "invalid access" );
	}

	@Override
	public AuthenticatedUser loginUser( String username, String password ) throws LoginException
	{
		if( username == null || username.trim().isEmpty() || password.isEmpty() ) {
			throw new LoginException( "username doesn't exists" );
		}

		//log in
		User user = usersDao.getUserByName( username );
		if( user == null ) {
			throw new LoginException( "username doesn't exists" );
		}
		if( !user.getPassword().equals(  password ) ) {
			throw new LoginException( "user can't login" );
		}

		AuthenticatedUser container = null;
		//check if already logged in
		if( authenticated.containsKey(  user.getCode() )) {
			container = authenticated.get(  user.getCode() );
		} else {
			container = createUserContainer( user );
			authenticated.put( container.getServiceKey(), container );
		}

		return container;
	}

	@Override
	public void logoutUser( String serviceKey ) {
		authenticated.remove( serviceKey );
	}

	private AuthenticatedUser createUserContainer( User user ) {
		AuthenticatedUser container = new AuthenticatedUser();
		container.setUser(  user );
		container.setAuthToken( UserCodeGenerator.generateSessionCode() );
		container.setLoginDate( new Date() );
		return container;
	}
}

package com.sapo.drakkar.common.autentication;

import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
public interface AuthenticationProvider
{
	AuthenticatedUser authenticateUser( String serviceKey, String AuthToken ) throws AuthException;
	AuthenticatedUser loginUser( String username, String password ) throws LoginException;
	void logoutUser( String serviceKey );
}

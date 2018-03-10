package com.sapo.drakkar.web.filter;

import com.sapo.drakkar.common.autentication.AuthHeaders;
import com.sapo.drakkar.common.autentication.AuthenticatedUser;
import com.sapo.drakkar.common.autentication.AuthenticationProvider;

import javax.annotation.Priority;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter
{
	private AuthenticationProvider authProvider;

	public AuthenticationFilter(AuthenticationProvider authenticationProvider) {
		this.authProvider = authenticationProvider;
	}

	@Override
	public void filter( ContainerRequestContext requestContext ) throws IOException
	{
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		String serviceKey = headers.getFirst( AuthHeaders.SERVICE_KEY );
		String authToken = headers.getFirst( AuthHeaders.AUTH_TOKEN );
		AuthenticatedUser user = null;
		try {
			user = this.authProvider.authenticateUser( serviceKey, authToken );
			user.setLastActionTime( new Date() );
		} catch( LoginException e ) {
			requestContext.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
		}
		if( user == null ) {
			requestContext.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
		}
	}
}

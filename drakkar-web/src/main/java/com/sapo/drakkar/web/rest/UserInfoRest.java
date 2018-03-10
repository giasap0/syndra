package com.sapo.drakkar.web.rest;

import com.sapo.drakkar.common.autentication.AuthHeaders;
import com.sapo.drakkar.common.autentication.AuthenticatedUser;
import com.sapo.drakkar.common.autentication.AuthenticationProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.auth.message.AuthException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by giampaolo.saporito
 * on 04.01.2018 - giovedì
 */
@Stateless
@Path("userInfo")
public class UserInfoRest
{
	@Inject
	private AuthenticationProvider authProvider;

	@GET
	@Path("username")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsername( @Context HttpHeaders httpHeaders ) {
		try {
			AuthenticatedUser authenticatedUser = getAuthenticatedUser( httpHeaders );
			JsonObject json = Json.createObjectBuilder()
					.add( "username", authenticatedUser.getUser().getUsername() )
					.build();
			return Response.status( Response.Status.OK ).entity( json.toString() ).build();
		} catch( AuthException e ) {
			return Response.status( Response.Status.UNAUTHORIZED ).build();
		}
	}

	@GET
	@Path("logoff")
	@Produces({MediaType.APPLICATION_JSON})
	public Response logout( @Context HttpHeaders httpHeaders )
	{
		authProvider.logoutUser( getServiceKey( httpHeaders ) );
		JsonObject json = Json.createObjectBuilder()
				.add( "msg", "logout success" )
				.build();
		return Response.status( Response.Status.OK ).entity( json.toString() ).build();
	}

	private AuthenticatedUser getAuthenticatedUser( HttpHeaders httpHeaders ) throws AuthException {
		String serviceKey = getServiceKey( httpHeaders );
		String authToken = httpHeaders.getHeaderString( AuthHeaders.AUTH_TOKEN );
		return authProvider.authenticateUser( serviceKey, authToken );
	}

	private String getServiceKey( HttpHeaders httpHeaders ) {
		return httpHeaders.getHeaderString( AuthHeaders.SERVICE_KEY );
	}
}

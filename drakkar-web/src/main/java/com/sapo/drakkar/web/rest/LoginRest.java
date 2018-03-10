package com.sapo.drakkar.web.rest;

import com.sapo.drakkar.common.autentication.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Created by giampaolo.saporito
 * on 28.12.2017 - giovedì
 */
@Stateless
@Path("login")
public class LoginRest {

    //inject dei DAO che mi servono
    @Inject
	private AuthenticationProvider authProvider;

	@POST
	@AuthenticationNotRequired
	@Produces({MediaType.APPLICATION_JSON})
	public Response login(  @Context HttpHeaders httpHeaders,
							  @FormParam( "username" ) String username,
							  @FormParam( "password" ) String password )
	{
		password = handlePasswordInput( password );
		username = handleUsernameInput( username );

		try {
			AuthenticatedUser user = authProvider.loginUser( username, password );
			JsonObject jsonAuth = Json.createObjectBuilder()
					.add( AuthHeaders.SERVICE_KEY, user.getServiceKey() )
					.add( AuthHeaders.AUTH_TOKEN, user.getAuthToken() )
					.build();
			return getNoCacheResponseBuilder( Response.Status.OK ).entity( jsonAuth.toString() ).build();

		} catch( LoginException e ) {
			JsonObject jsonObj = Json.createObjectBuilder()
								.add( "message", "User input doesn't match" )
								.build();

			return getNoCacheResponseBuilder( Response.Status.UNAUTHORIZED ).entity( jsonObj.toString() ).build();
		}
	}

	private String handleUsernameInput( @FormParam("username") String username )
	{
		if( username == null ) {
			username = "";
		}
		username = username.trim();
		return username;
	}

	private String handlePasswordInput( @FormParam("password") String password )
	{
		if( password == null ) {
			password = "";
		}
		password = password.trim();
		return password;
	}

	private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
		CacheControl cc = new CacheControl();
		cc.setNoCache( true );
		cc.setMaxAge( -1 );
		cc.setMustRevalidate( true );

		return Response.status( status ).cacheControl( cc );
	}

}

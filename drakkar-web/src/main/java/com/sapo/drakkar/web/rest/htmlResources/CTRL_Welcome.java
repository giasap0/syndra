package com.sapo.drakkar.web.rest.htmlResources;

import com.sapo.drakkar.common.autentication.AuthenticationNotRequired;

import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by giampaolo.saporito
 * on 30.12.2017 - sabato
 */
@Stateless
@Path("welcome")
public class CTRL_Welcome
{

	@GET
	@AuthenticationNotRequired
	@Produces({MediaType.TEXT_HTML})
	public Response gotoLoginPage( @Context ServletContext context ) {
		String resourceName = "/WEB-INF/html/login.html";
		return ResourceReader.execute( context, resourceName );
	}

}

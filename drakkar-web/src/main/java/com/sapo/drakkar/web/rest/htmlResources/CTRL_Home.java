package com.sapo.drakkar.web.rest.htmlResources;

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
 * on 29.12.2017 - venerdì
 */
@Stateless
@Path("home")
public class CTRL_Home
{

	@GET
	@Produces({MediaType.TEXT_HTML})
	public Response gotoHome( @Context ServletContext context  ) {
		String resourceName = "/WEB-INF/html/home.html";
		return ResourceReader.execute( context, resourceName );
	}

}

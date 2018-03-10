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
 * on 02.01.2018 - martedì
 */
@Stateless
@Path("tabWarnProducer")
public class CTRL_TabWarnProducer
{
	@GET
	@Produces({MediaType.TEXT_HTML})
	public Response getHtml( @Context ServletContext context  ) {
		String resourceName = "/WEB-INF/html/tabWarningProducer.html";
		return ResourceReader.execute( context, resourceName );
	}
}

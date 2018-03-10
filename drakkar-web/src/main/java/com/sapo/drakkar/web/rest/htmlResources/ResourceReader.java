package com.sapo.drakkar.web.rest.htmlResources;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by giampaolo.saporito
 * on 02.01.2018 - martedì
 * read files as resources
 */
final class ResourceReader
{
	private ResourceReader() {}

	private static String get( String resourceName , ServletContext context ) throws Exception {
		InputStream stream = context.getResourceAsStream( resourceName );
		String html = IOUtils.toString( stream , StandardCharsets.UTF_8);
		stream.close();
		return html;
	}

	public static Response execute( ServletContext context, String resourceName )
	{
		String html = null;
		try {
			html = get( resourceName, context);
		} catch( Exception e ) {
			return Response.status( Response.Status.SERVICE_UNAVAILABLE ).entity( "something went wrong" ).build();
		}
		return Response.status( Response.Status.OK ).entity( html ).build();
	}

}

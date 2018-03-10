package com.sapo.drakkar.web;

import com.sapo.drakkar.common.autentication.AuthenticationNotRequired;
import com.sapo.drakkar.common.autentication.AuthenticationProvider;
import com.sapo.drakkar.web.filter.AuthenticationFilter;

import javax.inject.Inject;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
@Provider
public class AuthenticationFeature implements DynamicFeature
{
	@Inject
	AuthenticationProvider authenticationProvider;

	@Override
	public void configure( ResourceInfo resourceInfo, FeatureContext context )
	{
		Method method = resourceInfo.getResourceMethod();
		if (! method.isAnnotationPresent(AuthenticationNotRequired.class)) {
			//if authentication is required - add filter in chain
			AuthenticationFilter authenticationFilter =	new AuthenticationFilter(authenticationProvider);
			context.register( authenticationFilter );
		}
	}
}

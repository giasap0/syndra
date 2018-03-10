package com.sapo.drakkar.common.autentication;

import com.sapo.drakkar.common.dto.User;

import java.util.Date;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
public class AuthenticatedUser
{
	private User user;
	private String authToken;
	private Date loginDate;
	private Date lastActionTime;

	public String getServiceKey() {
		return getUser().getCode();
	}

	public User getUser()
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
	}

	public String getAuthToken()
	{
		return authToken;
	}

	public void setAuthToken( String authToken )
	{
		this.authToken = authToken;
	}

	public Date getLoginDate()
	{
		return loginDate;
	}

	public void setLoginDate( Date loginDate )
	{
		this.loginDate = loginDate;
	}

	public Date getLastActionTime() {
		return lastActionTime;
	}

	public void setLastActionTime( Date lastActionTime ) {
		this.lastActionTime = lastActionTime;
	}
}

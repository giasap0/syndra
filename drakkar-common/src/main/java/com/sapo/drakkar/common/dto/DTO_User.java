package com.sapo.drakkar.common.dto;

import javax.json.Json;
import javax.json.JsonObject;
import java.sql.Timestamp;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
public class DTO_User implements User
{
	private Long id;
	private String code;
	private String username;
	private String password;
	private Timestamp tInsert;
	private Boolean enabled;

	public DTO_User() {}

	public DTO_User( User cpy ) {
		this.id = cpy.getId();
		this.code = cpy.getCode();
		this.username = cpy.getUsername();
		this.password = cpy.getPassword();
		this.tInsert = new Timestamp( cpy.gettInsert().getTime() );
		this.enabled = cpy.isEnabled();
	}

	///////////////////////////////////////////////////////////////////////////////
	// getters and setters
	///////////////////////////////////////////////////////////////////////////////

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode( String code ) {
		this.code = code;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername( String username ) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword( String password ) {
		this.password = password;
	}

	@Override
	public Timestamp gettInsert() {
		return tInsert;
	}

	@Override
	public void settInsert( Timestamp tInsert ) {
		this.tInsert = tInsert;
	}

	@Override
	public Boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled( Boolean enabled ) {
		this.enabled = enabled;
	}

	@Override
	public String toJSon() {
		return jsonUser( this ).toString();
	}

	@Override
	public JsonObject getJson() {
		return jsonUser( this );
	}

	public static JsonObject jsonUser( User usr ) {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add( "code", usr.getCode() )
				.add( "username", usr.getUsername() )
				.add( "password", usr.getPassword() )
				.add( "tInsert", usr.gettInsert().getTime() )
				.add( "enabled", usr.isEnabled() ).build()
				;
		return jsonObject;
	}
}

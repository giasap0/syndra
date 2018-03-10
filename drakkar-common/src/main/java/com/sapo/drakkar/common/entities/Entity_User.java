package com.sapo.drakkar.common.entities;

import com.sapo.drakkar.common.dto.DTO_User;
import com.sapo.drakkar.common.dto.User;

import javax.json.JsonObject;
import javax.persistence.*;
import java.sql.Timestamp;

/*
CREATE TABLE TUSER (
	ID_USER bigint(20) NOT NULL AUTO_INCREMENT,
	USER_CODE varchar(50) not null,
	NAME varchar(25) not null,
	PASSWORD varchar(30) not null,
	TINSERT timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	IS_ENABLED int(1) not null default 1,
	constraint pk_user PRIMARY KEY (ID_USER),
	UNIQUE KEY U_USR (USER_CODE),
	UNIQUE KEY U_USR_NAME (NAME)
);
 */

/**
 * Created by giampaolo.saporito
 * on 28.12.2017 - giovedì
 */
@Entity
@Table(name ="TUSER")
public class Entity_User implements User
{
	@Id
	@Column(name = "ID_USER" )
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true, name="USER_CODE" )
	private String code;

	@Column(unique=true, name="NAME" )
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "TINSERT")
	private Timestamp tInsert;

	@Column(name = "IS_ENABLED")
	private Boolean enabled;

	@Override
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
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
		return DTO_User.jsonUser( this ).toString();
	}

	@Override
	public JsonObject getJson() {
		return DTO_User.jsonUser( this );
	}
}

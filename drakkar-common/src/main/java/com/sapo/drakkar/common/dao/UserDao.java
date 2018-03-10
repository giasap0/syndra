package com.sapo.drakkar.common.dao;

import com.sapo.drakkar.common.dto.User;

import java.io.Serializable;

/**
 * Created by giampaolo.saporito
 * on 28.12.2017 - giovedì
 */
public interface UserDao extends DrakkarCommonDao, Serializable
{
	User getUserByName( String username );
	String getUserServiceKey( String username );
}

package com.sapo.drakkar.common.dao;

import com.sapo.drakkar.common.entities.Entity_SystemProp;

public interface SystemPropDao extends DrakkarCommonDao
{
	Entity_SystemProp getSystemPropByCode( String code );
	String getEnvironmentName();
}

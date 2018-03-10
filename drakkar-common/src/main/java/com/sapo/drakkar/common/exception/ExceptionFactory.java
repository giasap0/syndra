package com.sapo.drakkar.common.exception;

/**
 * Created by giampaolo.saporito on 20/12/2016.
 *
 * Only static methods
 */
public final class ExceptionFactory
{
	private ExceptionFactory() {}

	public static DrakkarException onEntityNotFound(Class<?> daoClass) {
		return new DrakkarException("Entity not found", daoClass );
	}
	
}

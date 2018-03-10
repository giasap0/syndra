package com.sapo.drakkar.common.dto;

import com.sapo.drakkar.common.JsonProducer;

/**
 * Created by giampaolo.saporito
 * on 03.01.2018 - mercoledì
 */
public interface CodiceDescrizione extends JsonProducer
{
	String getCodice();
	String getDescrizione();

	void setCodice( String cod );
	void setDescrizione( String cod );
}

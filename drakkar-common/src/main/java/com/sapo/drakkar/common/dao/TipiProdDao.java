package com.sapo.drakkar.common.dao;

import com.sapo.drakkar.common.dto.CodiceDescrizione;

import java.util.List;

/**
 * Created by giampaolo.saporito
 * on 03.01.2018 - mercoledì
 */
public interface TipiProdDao extends DrakkarCommonDao
{
	List<CodiceDescrizione> getAllTipiProdotto();
	CodiceDescrizione getTipoProdottoByCodice( String codice );
}

package com.sapo.drakkar.common.dao.impl;

import com.sapo.drakkar.common.dto.CodiceDescrizione;
import com.sapo.drakkar.common.dao.TipiProdDao;
import com.sapo.drakkar.common.entities.Entity_TipoProd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giampaolo.saporito
 * on 03.01.2018 - mercoledì
 */
@Stateless(name="TipiProdDao")
@Local(TipiProdDao.class)
public class TipiProdDaoImpl implements TipiProdDao
{
	@PersistenceContext( unitName = "persistence_drakkar_common" )
	private EntityManager em;

	private static final Logger logger = LogManager.getLogger( TipiProdDaoImpl.class );

	@Override
	public List<CodiceDescrizione> getAllTipiProdotto()
	{
		List<CodiceDescrizione> tipiProd = new ArrayList<>();

		String queryString = "SELECT s FROM Entity_TipoProd AS s";
		TypedQuery<Entity_TipoProd> typedQuery = em.createQuery(queryString , Entity_TipoProd.class);
		List<Entity_TipoProd> results = typedQuery.getResultList();
		if( results != null && results.size() > 0 ) {
			tipiProd.addAll(  results );
		}
		return tipiProd;
	}

	@Override
	public CodiceDescrizione getTipoProdottoByCodice( String code )
	{
		if(code == null || code.isEmpty() ) { throw new IllegalArgumentException("getTipoProdottoByCodice() - code is null"); }

		String queryString = "SELECT s FROM Entity_TipoProd AS s WHERE s.codice = :param";
		TypedQuery<Entity_TipoProd> query = em.createQuery( queryString, Entity_TipoProd.class ).setParameter("param", code );
		Entity_TipoProd dto = query.getSingleResult();

		if( dto == null ){
			logger.warn("cannot find system property with code " + code );
		}
		return dto;
	}
}

/*
	String query = "SELECT NEW org.company.directory.CustomObject(i.firstProperty, i.secondProperty) FROM ObjectName i WHERE i.id=10";
	TypedQuery<CustomObject> typedQuery = em.createQuery(query , CustomObject.class);
	List<CustomObject> results = typedQuery.getResultList();
*/

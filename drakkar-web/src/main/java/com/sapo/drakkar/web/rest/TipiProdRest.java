package com.sapo.drakkar.web.rest;

import com.sapo.drakkar.common.dto.CodiceDescrizione;
import com.sapo.drakkar.common.dao.TipiProdDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by giampaolo.saporito
 * on 04.01.2018 - giovedì
 * get json tipi prodotto
 */
@Stateless
@Path("tipiProd")
public class TipiProdRest
{
	//inject dei DAO che mi servono
	@Inject
	private TipiProdDao daoTipiprod;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipiProdotto() {
		List<CodiceDescrizione> tipiProdotto = daoTipiprod.getAllTipiProdotto();
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for( CodiceDescrizione prodotto : tipiProdotto ) {
			builder.add( prodotto.getJson() );
		}
		return Response.status( Response.Status.OK ).entity( builder.build().toString() ).build();
	}
}

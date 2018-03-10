package com.sapo.drakkar.common.entities;

import com.sapo.drakkar.common.dto.CodiceDescrizione;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by giampaolo.saporito
 * on 03.01.2018 - mercoledì
 */
@Entity
@Table(name ="TTIPIPRODOTTO")
public class Entity_TipoProd implements Serializable, CodiceDescrizione
{
	@Id
	@Column(name = "ID_TIPO_PROD" )
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true, name = "CODICE")
	private String codice;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Override
	public String getCodice() {
		return codice;
	}

	@Override
	public void setCodice( String codice ) {
		this.codice = codice;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione( String descrizione ) {
		this.descrizione = descrizione;
	}

	@Override
	public boolean equals( Object o )
	{
		if( this == o ) return true;
		if( o == null || getClass() != o.getClass() ) return false;
		Entity_TipoProd that = ( Entity_TipoProd ) o;
		return Objects.equals( id, that.id ) &&
				Objects.equals( codice, that.codice );
	}

	@Override
	public int hashCode() {
		return Objects.hash( id, codice );
	}

	@Override
	public String toString() {
		return "Entity_TipoProd{" +
				"id=" + id +
				", codice='" + codice + '\'' +
				", descrizione='" + descrizione + '\'' +
				'}';
	}

	@Override
	public String toJSon() {
		return getJson().toString();
	}

	@Override
	public JsonObject getJson() {
		return Json.createObjectBuilder().add( "codice", getCodice() )
				.add( "descrizione", getDescrizione() )
				.build();
	}

}

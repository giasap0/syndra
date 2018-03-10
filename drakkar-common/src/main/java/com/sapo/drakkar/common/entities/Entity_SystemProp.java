package com.sapo.drakkar.common.entities;

import com.sapo.drakkar.common.JsonProducer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;

/*
CREATE TABLE `TSYSTEMPROP` (
  `ID_SYSPROP` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM` varchar(30) NOT NULL,
  `VAL` varchar(60) DEFAULT NULL,
  `TINSERT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TSTART` timestamp NULL DEFAULT NULL,
  `TEND` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_SYSPROP`),
  UNIQUE KEY `U_SYSPROP` (`PARAM`)
);
 */

/**
 * Created by giampaolo.saporito on 20/12/2016.
 * DTO for TSYSTEMPROP
 */
@Entity
@Table(name ="TSYSTEMPROP")
public class Entity_SystemProp implements Serializable, JsonProducer
{
	@Id
	@Column(name = "ID_SYSPROP" )
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true, name="PARAM" )
	private String parameter;

	@Column(name = "VAL")
	private String value;
	
	public Long getId() {
		return id;
	}
	
	public void setId( Long id ) {
		this.id = id;
	}
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Entity_SystemProp )) return false;

		Entity_SystemProp that = (Entity_SystemProp ) o;
		
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (parameter != null ? !parameter.equals(that.parameter) : that.parameter != null) return false;
		if (value != null ? !value.equals(that.value) : that.value != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = parameter != null ? parameter.hashCode() : 0;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result += 100 * (id != null ? id.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		String idStr = id!= null ? id.toString() : "null";
		return "Entity_SystemProp{" +
				"id='" + idStr + '\'' +
				"parameter='" + parameter + '\'' +
				", value='" + value + '\'' +
				'}';
	}

	@Override
	public String toJSon() {
		return getJson().toString();
	}

	@Override
	public JsonObject getJson() {
		return Json.createObjectBuilder().add( "parameter", parameter )
				.add( "value", value ).build();
	}
}

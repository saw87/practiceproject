
package com.maventree.entities.business_logic;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = -489637051819304520L;

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Version
	private Integer version;
	
	private String value;

	public Integer getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}

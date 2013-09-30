package com.maventree.entities.business_logic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.maventree.entities.business_logic.AbstractEntity;
import com.maventree.entities.business_logic.ResourceEntity;

@Entity
@Table(name="PRIVILEGES")
public class Privilege extends AbstractEntity {

	@OneToOne
	private ResourceEntity resourceEntity;
	
	@Override
	public String toString() {
		return getValue();
	}
}

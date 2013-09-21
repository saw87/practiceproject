package com.maventree.entities.business_logic.user_access;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.maventree.entities.business_logic.AbstractEntity;

@Entity
@Table(name="PRIVILEGES")
public class Privilege extends AbstractEntity {

	@Override
	public String toString() {
		return getValue();
	}
}

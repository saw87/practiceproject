package com.maventree.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DualListModel;

@ManagedBean
public class PickListBean {

	private DualListModel<String> cities;

	public PickListBean() {

		// Cities
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();

		citiesSource.add("Istanbul");
		citiesSource.add("Ankara");
		citiesSource.add("Izmir");
		citiesSource.add("Antalya");
		citiesSource.add("Bursa");

		cities = new DualListModel<String>(citiesSource, citiesTarget);
	}

	public DualListModel<String> getCities() {
		return cities;
	}

	public void setCities(DualListModel<String> cities) {
		this.cities = cities;
	}

}

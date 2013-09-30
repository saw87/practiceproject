package com.maventree.controllers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.maventree.global.Registry;

@ManagedBean(eager = true)
@ApplicationScoped
public class AppBean {

	@PostConstruct
	public void init() {
		
	}

	@PreDestroy
	public void destroy() {
		
	}

}
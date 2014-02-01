package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.WoredaForm;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class WoredaFormController implements Button.ClickListener,ValueChangeListener{

	private WoredaForm woredaForm;
    private WoredaDAO woredaDAO;
    private ZoneDAO zoneDAO;
	private ConnManager connManager;
	public WoredaFormController(WoredaForm _woredaForm) {
		connManager=new ConnManager();
		woredaForm=_woredaForm;
		woredaDAO=new WoredaDAO(connManager);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String debug="CBZone:"+(Integer)woredaForm.CBZone.getValue();
		System.out.println(debug);
		Zone zone=zoneDAO.getZoneById((Integer)woredaForm.CBZone.getValue()).get(0);
		Woreda woreda=new Woreda(zone, woredaForm.TFWoreda.getValue(),Integer.valueOf(woredaForm.TFWoredaCode.getValue()),0);
		//woredaDAO.persist(woreda);
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		woredaForm.fillZone();

	}

}

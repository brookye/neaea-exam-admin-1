package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.view.WoredaAllowanceForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.WoredaAllowanceDAO;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.WoredaAllowance;

@SuppressWarnings("serial")
public class WoredAllowanceFormController implements ClickListener,
		ValueChangeListener {
	private WoredaAllowanceForm waf;
	private ConnManager connManager;
	private WoredaDAO woredaDAO;
	private ZoneDAO zoneDAO;
	private RegionDAO regionDAO;
	private WoredaAllowanceDAO woredaAllowanceDAO;

	public WoredAllowanceFormController(WoredaAllowanceForm _waf) {
		waf = _waf;
		connManager = new ConnManager();
		woredaDAO = new WoredaDAO(connManager);
		zoneDAO = new ZoneDAO(connManager);
		regionDAO = new RegionDAO(connManager);
		woredaAllowanceDAO = new WoredaAllowanceDAO(connManager);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		waf.fillZone();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Woreda woreda = woredaDAO.getById(
				(Integer) waf.CBWoredaAllowanceWoreda.getValue()).get(0);
		WoredaAllowance woredaAllowance = new WoredaAllowance(
				Float.valueOf(waf.TFWoredaAllowanceLow.getValue()),
				Float.valueOf(waf.TFWoredaAllowanceMedium.getValue()),
				Float.valueOf(waf.TFWoredaAllowanceHigh.getValue()),
				Float.valueOf(waf.TFWoredaAllowanceDesert.getValue()), woreda,
				waf.TFWoredaAllowanceCode.getValue());
		woredaAllowanceDAO.persist(woredaAllowance);

	}

}

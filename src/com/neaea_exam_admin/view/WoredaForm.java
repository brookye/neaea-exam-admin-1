package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.controller.WoredaFormController;
import com.neaea_exam_admin.controller.ZoneController;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class WoredaForm extends CustomComponent {
	public TextField TFWoreda;
	public TextField TFWoredaCode;
	public ComboBox CBRegion;
	public ComboBox CBZone;
	public Button BTAddWoreda;
	public FormLayout fl;
	private WoredaFormController wfc;

	public WoredaForm() {
		wfc = new WoredaFormController(this);
		init();
		fillRegion();
		setCompositionRoot(fl);
	}

	private void init() {
		TFWoreda = new TextField("Name");
		TFWoredaCode = new TextField("woreda code");
		BTAddWoreda = new Button("Add");
		BTAddWoreda.addClickListener(wfc);
		CBRegion = new ComboBox("Region");
		CBRegion.addValueChangeListener(wfc);
		CBZone = new ComboBox("Zone");
		CBZone.setWidth(160, Unit.POINTS);
		CBRegion.setWidth(160, Unit.POINTS);
		TFWoreda.setWidth(160, Unit.POINTS);
		TFWoredaCode.setWidth(160, Unit.POINTS);
		fl = new FormLayout();
		fl.addComponent(CBRegion);
		fl.addComponent(CBZone);
		fl.addComponent(TFWoreda);
		fl.addComponent(TFWoredaCode);
		fl.addComponent(BTAddWoreda);
	}

	private void fillRegion() {
		RegionDAO regionDAO = new RegionDAO(new ConnManager());
		List<Region> regions = regionDAO.getAll();
		for (Region reg : regions) {
			CBRegion.addItem(reg.getRegionCode());
			CBRegion.setItemCaption(reg.getRegionCode(), reg.getRegionName());
		}
	}
	public void fillZone() {
		CBZone.removeAllItems();
		ZoneDAO zoneDAO = new ZoneDAO(new ConnManager());
		List<Zone> zones = zoneDAO.getByRegionId((Integer)CBRegion.getValue());
		for (Zone zn : zones) {
			CBZone.addItem(zn.getZoneCode());
			CBZone.setItemCaption(zn.getZoneCode(), zn.getZoneName());
		}
	}
}

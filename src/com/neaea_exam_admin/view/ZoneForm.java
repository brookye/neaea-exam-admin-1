package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.controller.ZoneController;
import com.neaea_exam_admin.entity.Category;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ZoneForm extends CustomComponent {
	public TextField TFZone;
	public TextField TFZoneCode;
	public ComboBox CBRegion;
	public Button BTAddZone;
	public FormLayout fl;
	private ZoneController zc;

	public ZoneForm() {
		zc = new ZoneController(this);
		init();		
		fillRegion();
		setCompositionRoot(fl);
	}

	private void init() {
		TFZone = new TextField("Name");
		TFZoneCode = new TextField("Zone code");
		BTAddZone = new Button("Add");
		BTAddZone.addClickListener(zc);
		CBRegion = new ComboBox("Region");
		CBRegion.setWidth(160, Unit.POINTS);
		CBRegion.setNullSelectionAllowed(false);
		CBRegion.setNewItemsAllowed(false);
		TFZone.setWidth(160, Unit.POINTS);
		TFZoneCode.setWidth(160, Unit.POINTS);		
		fl = new FormLayout();
		fl.addComponent(CBRegion);
		fl.addComponent(TFZone);
		fl.addComponent(TFZoneCode);
		fl.addComponent(BTAddZone);
	}
	private void fillRegion(){
		RegionDAO regionDAO=new RegionDAO(new ConnManager());
		List<Region> regions = regionDAO.getAll();
		for (Region reg : regions) {
			CBRegion.addItem(reg.getRegionCode());
			CBRegion.setItemCaption(reg.getRegionCode(), reg.getRegionName());
		}
	}
}

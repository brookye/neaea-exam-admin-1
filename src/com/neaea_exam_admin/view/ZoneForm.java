package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.controller.ZoneController;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.data.Validator.InvalidValueException;
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
		TFZone.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
		TFZoneCode = new TextField("Zone code");
		TFZoneCode.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
		BTAddZone = new Button("Add");
		BTAddZone.addClickListener(zc);
		CBRegion = new ComboBox("Region");
		CBRegion.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
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
	public void validate() throws InvalidValueException{
		TFZone.validate();
		TFZoneCode.validate();
		CBRegion.validate();
	}
	public void setFormValidator(boolean isOn){
		TFZone.setValidationVisible(isOn);
		TFZoneCode.setValidationVisible(isOn);
		CBRegion.setValidationVisible(isOn);

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

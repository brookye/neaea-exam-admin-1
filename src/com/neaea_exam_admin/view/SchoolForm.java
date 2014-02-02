package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.controller.SchoolFormController;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SchoolForm  extends CustomComponent{
	
	public ComboBox CBWoreda;
	public ComboBox CBZone;
	public ComboBox CBRegion;
    public TextField TFSchoolCode;
    public TextField TFSchoolName;
    public Button BTAdd;
    public FormLayout fl;
    public SchoolFormController sfc=new SchoolFormController(this);
    public SchoolForm(){
    	init();
    	setCompositionRoot(fl);
    	fillRegion();
    }
	private void init() {
		CBWoreda=new ComboBox("Woreda");
		CBWoreda.setNullSelectionAllowed(false);
		CBWoreda.setNewItemsAllowed(false);
		CBWoreda.setWidth(160, Unit.POINTS);
		CBZone=new ComboBox("Zone");
		CBZone.setNullSelectionAllowed(false);
		CBZone.setNewItemsAllowed(false);
		CBZone.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				fillWoreda();
				
			}
		});
		CBZone.setWidth(160, Unit.POINTS);
		CBRegion=new ComboBox("Region");
		CBRegion.addValueChangeListener(sfc);
		CBRegion.setWidth(160, Unit.POINTS);
		CBRegion.setNullSelectionAllowed(false);
		CBRegion.setNewItemsAllowed(false);
		TFSchoolCode=new TextField("School Code");
		TFSchoolCode.setWidth(160, Unit.POINTS);
		TFSchoolName=new TextField("School Name");
		TFSchoolName.setWidth(160, Unit.POINTS);
		BTAdd=new Button("Add");
		BTAdd.addClickListener(sfc);
		fl=new FormLayout();
		fl.addComponent(TFSchoolCode);
		fl.addComponent(TFSchoolName);
		fl.addComponent(CBRegion);
		fl.addComponent(CBZone);
		fl.addComponent(CBWoreda);
		fl.addComponent(BTAdd);
	}
	private void fillRegion() {
		RegionDAO regionDAO = new RegionDAO(new ConnManager());
		List<Region> regions = regionDAO.getAll();
		for (Region reg : regions) {
			CBRegion.addItem(reg.getRegionCode());
			CBRegion.setItemCaption(reg.getRegionCode(),
					reg.getRegionName());
		}
	}

	public void fillZone() {
		CBZone.removeAllItems();
		ZoneDAO zoneDAO = new ZoneDAO(new ConnManager());
		System.out.println("INFO:CBRegion value:"
				+ (Integer) CBRegion.getValue());
		List<Zone> zones = zoneDAO
				.getByRegionId((Integer) CBRegion.getValue());
		for (Zone zn : zones) {
			CBZone.addItem(zn.getZoneId());
			CBZone.setItemCaption(zn.getZoneId(),
					zn.getZoneName());
		}
	}

	public void fillWoreda() {
		CBWoreda.removeAllItems();
		WoredaDAO woredaDAO = new WoredaDAO(new ConnManager());
		System.out.println("INFO"+(Integer) CBZone.getValue());
		List<Woreda> woredas = woredaDAO
				.getByZoneId((Integer) CBZone.getValue());
		for (Woreda w : woredas) {

			CBWoreda.addItem(w.getWoredaId());
			CBWoreda.setItemCaption(w.getWoredaId(),
					w.getWoredaName());
		}
	}
}

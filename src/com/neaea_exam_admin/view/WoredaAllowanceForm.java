package com.neaea_exam_admin.view;

import java.awt.image.TileObserver;
import java.util.List;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.controller.WoredAllowanceFormController;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.controller.WoredAllowanceFormController;

@SuppressWarnings({ "serial", "unused" })
public class WoredaAllowanceForm extends CustomComponent {

	public TextField TFWoredaAllowanceCode;
	public ComboBox CBWoredaAllowanceRegion;
	public ComboBox CBWoredaAllowanceWoreda;
	public ComboBox CBWoredaAllowanceZone;
	public TextField TFWoredaAllowanceLow;
	public TextField TFWoredaAllowanceMedium;
	public TextField TFWoredaAllowanceHigh;
	public TextField TFWoredaAllowanceDesert;
	public Button BTworedaAllowanceAdd;
	public Button BTworedaAllowanceCancel;
	public Button BTworedaAllowanceUpdate;
	public FormLayout WoredaAllowanceFormLayout;
	WoredAllowanceFormController wafc = new WoredAllowanceFormController(this);

	public WoredaAllowanceForm() {
		init();
		setCompositionRoot(WoredaAllowanceFormLayout);
	}

	public void init() {
		WoredaAllowanceFormLayout = new FormLayout();

		Label titleLB = new Label(" <h1>Woreda Allowance</h1>",
				ContentMode.HTML);
		TFWoredaAllowanceCode = new TextField("WoredaAllowance Code");
		CBWoredaAllowanceRegion = new ComboBox("Region");
		CBWoredaAllowanceRegion.setNullSelectionAllowed(false);
		CBWoredaAllowanceRegion.setNewItemsAllowed(false);
		CBWoredaAllowanceRegion.setImmediate(true);
		CBWoredaAllowanceRegion.addValueChangeListener(wafc);
		CBWoredaAllowanceWoreda = new ComboBox("Woreda");
		CBWoredaAllowanceWoreda.setNullSelectionAllowed(false);
		CBWoredaAllowanceWoreda.setNewItemsAllowed(false);
		CBWoredaAllowanceZone = new ComboBox("Zone");
		CBWoredaAllowanceZone.setNullSelectionAllowed(false);
		CBWoredaAllowanceZone.setNewItemsAllowed(false);
		//for the time being
		CBWoredaAllowanceZone.addValueChangeListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				fillWoreda();				
			}
		});
		TFWoredaAllowanceLow = new TextField("Low Scale");
		TFWoredaAllowanceMedium = new TextField("Medium Scale");
		TFWoredaAllowanceHigh = new TextField("High Scale");
		TFWoredaAllowanceDesert = new TextField("Desert Allowance");

		BTworedaAllowanceAdd = new Button(" Add");
		BTworedaAllowanceAdd.addClickListener(wafc);
		BTworedaAllowanceCancel = new Button("Cancel");
		BTworedaAllowanceUpdate = new Button(" Update");
		fillRegion();
		titleLB.setSizeFull();
		titleLB.setWidth(250, Sizeable.Unit.POINTS);
		// WoredaAllowanceNameTF.setWidth(160, Sizeable.Unit.POINTS);
		TFWoredaAllowanceCode.setWidth(160, Sizeable.Unit.POINTS);
		CBWoredaAllowanceWoreda.setWidth(160, Sizeable.Unit.POINTS);
		CBWoredaAllowanceZone.setWidth(160, Sizeable.Unit.POINTS);
		CBWoredaAllowanceRegion.setWidth(160, Sizeable.Unit.POINTS);
		TFWoredaAllowanceLow.setWidth(160, Sizeable.Unit.POINTS);
		TFWoredaAllowanceMedium.setWidth(160, Sizeable.Unit.POINTS);
		TFWoredaAllowanceHigh.setWidth(160, Sizeable.Unit.POINTS);
		TFWoredaAllowanceDesert.setWidth(160, Sizeable.Unit.POINTS);

		WoredaAllowanceFormLayout.addComponent(titleLB);
		WoredaAllowanceFormLayout.addComponent(CBWoredaAllowanceRegion);
		WoredaAllowanceFormLayout.addComponent(CBWoredaAllowanceZone);
		WoredaAllowanceFormLayout.addComponent(CBWoredaAllowanceWoreda);
		WoredaAllowanceFormLayout.addComponent(TFWoredaAllowanceCode);
		WoredaAllowanceFormLayout.addComponent(TFWoredaAllowanceLow);
		WoredaAllowanceFormLayout.addComponent(TFWoredaAllowanceMedium);
		WoredaAllowanceFormLayout.addComponent(TFWoredaAllowanceHigh);
		WoredaAllowanceFormLayout.addComponent(TFWoredaAllowanceDesert);
		WoredaAllowanceFormLayout.addComponent(BTworedaAllowanceAdd);

	}

	private void fillRegion() {
		RegionDAO regionDAO = new RegionDAO(new ConnManager());
		List<Region> regions = regionDAO.getAll();
		for (Region reg : regions) {
			CBWoredaAllowanceRegion.addItem(reg.getRegionCode());
			CBWoredaAllowanceRegion.setItemCaption(reg.getRegionCode(),
					reg.getRegionName());
		}
	}

	public void fillZone() {
		CBWoredaAllowanceZone.removeAllItems();
		ZoneDAO zoneDAO = new ZoneDAO(new ConnManager());
		System.out.println("INFO:CBRegion value:"
				+ (Integer) CBWoredaAllowanceRegion.getValue());
		List<Zone> zones = zoneDAO
				.getByRegionId((Integer) CBWoredaAllowanceRegion.getValue());
		for (Zone zn : zones) {
			CBWoredaAllowanceZone.addItem(zn.getZoneId());
			CBWoredaAllowanceZone.setItemCaption(zn.getZoneId(),
					zn.getZoneName());
		}
	}

	public void fillWoreda() {
		CBWoredaAllowanceWoreda.removeAllItems();
		WoredaDAO woredaDAO = new WoredaDAO(new ConnManager());
		System.out.println("INFO"+(Integer) CBWoredaAllowanceZone.getValue());
		List<Woreda> woredas = woredaDAO
				.getByZoneId((Integer) CBWoredaAllowanceZone.getValue());
		for (Woreda w : woredas) {

			CBWoredaAllowanceWoreda.addItem(w.getWoredaId());
			CBWoredaAllowanceWoreda.setItemCaption(w.getWoredaId(),
					w.getWoredaName());
		}
	}
}

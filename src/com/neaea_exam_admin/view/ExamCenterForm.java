/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import java.util.List;

import com.google.gwt.validation.client.constraints.NullValidator;
import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.controller.ExamCenterController;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

/**
 * 
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class ExamCenterForm extends CustomComponent {

	public ComboBox CBWoreda;
	public ComboBox CBZone;
	public ComboBox CBRegion;
	public ComboBox CBSchoolName;
	public TextField TFGroupNo;
	public TextField TFDistance;
	public TextField TFNoOfClassRoom;
	public TextField TFSearchExamCenter;
	public Button BTAdd;
	public Button BTEdit;
	public FormLayout fl;
	public Table TBExamCenter;
	public static boolean isEdit = false;
	private ExamCenterController ecc = new ExamCenterController(this);

	public ExamCenterForm() {

		init();
		setCompositionRoot(fl);
		fillRegion();
	}

	private void init() {
		CBWoreda = new ComboBox("Woreda");
		CBWoreda.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
		CBWoreda.setNullSelectionAllowed(false);
		CBWoreda.setNewItemsAllowed(false);
		CBWoreda.setWidth(160, Unit.POINTS);
		CBZone = new ComboBox("Zone");
		CBZone.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
		CBWoreda.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillSchool();

			}
		});
		CBZone.setNullSelectionAllowed(false);
		CBZone.setNewItemsAllowed(false);
		CBZone.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillWoreda();

			}
		});
		CBZone.setWidth(160, Unit.POINTS);
		CBRegion = new ComboBox("Region");
		CBRegion.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
		CBRegion.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillZone();

			}
		});
		CBRegion.addValueChangeListener(ecc);
		CBRegion.setWidth(160, Unit.POINTS);
		CBRegion.setNullSelectionAllowed(false);
		CBRegion.setNewItemsAllowed(false);
		CBSchoolName = new ComboBox("School name");
		CBSchoolName.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty", false));
		CBSchoolName.setWidth(160, Unit.POINTS);
		CBSchoolName.setNullSelectionAllowed(false);
		CBSchoolName.setNewItemsAllowed(false);
		TFGroupNo = new TextField("Group No");
		TFGroupNo.setWidth(160, Unit.POINTS);
		TFGroupNo.addValidator(new RegexpValidator("", true,
				"Only a number is allowed"));
		TFGroupNo.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty",true));
		TFGroupNo.addValidator(new RegexpValidator("^[0-9]*$", true,
				"Only an integer number is allowed"));
		TFDistance = new TextField("Distance");
		TFDistance.addValidator(new RegexpValidator("^[0-9\\.]*$", true,
				"Only a number is allowed"));
		TFDistance.addValidator(new com.vaadin.data.validator.NullValidator(
				"Can't be empty",true));
		TFDistance.setWidth(160, Unit.POINTS);
		TFNoOfClassRoom = new TextField("No of classroom");
		TFNoOfClassRoom.addValidator(new RegexpValidator("^[0-9]*$", true,
				"Only an integer number is allowed"));
		TFNoOfClassRoom
				.addValidator(new com.vaadin.data.validator.NullValidator(
						"Can't be empty", true));
		TFNoOfClassRoom.setWidth(160, Unit.POINTS);
		TFSearchExamCenter = new TextField("Search");
		TFSearchExamCenter.addTextChangeListener(ecc);
		TFSearchExamCenter.setWidth(160, Unit.POINTS);
		TFNoOfClassRoom = new TextField("No of classroom");
		TFNoOfClassRoom.setWidth(160, Unit.POINTS);
		BTAdd = new Button("Add");
		BTAdd.addClickListener(ecc);
		BTEdit = new Button("Edit");
		BTEdit.addClickListener(ecc);
		TBExamCenter = new Table();
		TBExamCenter.setSizeFull();
		TBExamCenter.addContainerProperty("Exam center", String.class, null);
		TBExamCenter.addContainerProperty("Group No", String.class, null);
		TBExamCenter.addContainerProperty("Woreda", String.class, null);
		TBExamCenter.addContainerProperty("Zone", String.class, null);
		TBExamCenter.addContainerProperty("Region", String.class, null);
		fl = new FormLayout();
		fillForm();
		setFormValidators(false);
	}

	public void setFormValidators(boolean isOn) {
		CBWoreda.setValidationVisible(isOn);
		CBZone.setValidationVisible(isOn);
		CBRegion.setValidationVisible(isOn);
		CBSchoolName.setValidationVisible(isOn);
		TFGroupNo.setValidationVisible(isOn);
		TFDistance.setValidationVisible(isOn);
		TFNoOfClassRoom.setValidationVisible(isOn);
	}

	public void validate() throws InvalidValueException{
		CBWoreda.validate();
		CBZone.validate();
		CBRegion.validate();
		CBSchoolName.validate();
		TFGroupNo.validate();
		TFDistance.validate();
		TFNoOfClassRoom.validate();
	}

	public void fillForm() {
		// make sure fl was empty
		fl.removeAllComponents();

		fl.addComponent(CBRegion);
		fl.addComponent(CBZone);
		fl.addComponent(CBWoreda);
		fl.addComponent(CBSchoolName);
		fl.addComponent(TFGroupNo);
		fl.addComponent(TFNoOfClassRoom);
		fl.addComponent(TFDistance);
		if (!isEdit) {
			fl.addComponent(BTAdd);
		} else {
			fl.addComponent(BTEdit);
			fl.addComponent(TFSearchExamCenter);
			fl.addComponent(TBExamCenter);
		}
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
		System.out.println("INFO:CBRegion value:"
				+ (Integer) CBRegion.getValue());
		List<Zone> zones = zoneDAO.getByRegionId((Integer) CBRegion.getValue());
		for (Zone zn : zones) {
			CBZone.addItem(zn.getZoneId());
			CBZone.setItemCaption(zn.getZoneId(), zn.getZoneName());
		}
	}

	public void fillWoreda() {
		CBWoreda.removeAllItems();
		WoredaDAO woredaDAO = new WoredaDAO(new ConnManager());
		System.out.println("INFO" + (Integer) CBZone.getValue());
		List<Woreda> woredas = woredaDAO.getByZoneId((Integer) CBZone
				.getValue());
		for (Woreda w : woredas) {

			CBWoreda.addItem(w.getWoredaId());
			CBWoreda.setItemCaption(w.getWoredaId(), w.getWoredaName());
		}
	}

	public void fillSchool() {
		CBSchoolName.removeAllItems();
		SchoolDAO schoolDAO = new SchoolDAO(new ConnManager());
		List<School> schools = schoolDAO.getByWoredaId((Integer) CBWoreda
				.getValue());
		for (School s : schools) {

			CBSchoolName.addItem(s.getCode());
			CBSchoolName.setItemCaption(s.getCode(), s.getSchoolName());
		}
	}
}

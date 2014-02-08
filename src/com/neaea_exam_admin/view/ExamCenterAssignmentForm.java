package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.ExamCenterDAO;
import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.controller.ExamCenterAssignmentFormController;
import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
<<<<<<< HEAD
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.NullValidator;
=======
>>>>>>> 802585f8dc16a331acb25b38f7bf6efabef3f729
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;

@SuppressWarnings("serial")
public class ExamCenterAssignmentForm extends CustomComponent {
	public ComboBox CBWoreda;
	public ComboBox CBZone;
	public ComboBox CBRegion;
	public ComboBox CBSchoolName;
	public ComboBox CBExamCenter;
	SchoolDAO schoolDAO = new SchoolDAO(new ConnManager());
	public Button BTAssign;
	public FormLayout fl;
	
	private ExamCenterAssignmentFormController ecafc = new ExamCenterAssignmentFormController(
			this);

	public ExamCenterAssignmentForm() {

		init();
		setCompositionRoot(fl);
		fillRegion();
	}

	
	private void init() {
		CBWoreda = new ComboBox("Woreda");
		CBWoreda.setNullSelectionAllowed(false);
		CBWoreda.setNewItemsAllowed(false);
		CBWoreda.setWidth(160, Unit.POINTS);
		CBZone = new ComboBox("Zone");
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
		CBRegion.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillZone();

			}
		});
		CBRegion.addValueChangeListener(ecafc);
		CBRegion.setWidth(160, Unit.POINTS);
		
		CBRegion.setNullSelectionAllowed(false);
		CBRegion.setNewItemsAllowed(false);
		CBSchoolName = new ComboBox("School name");		
		CBSchoolName.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillExamCenter();

			}
		});
		CBSchoolName.setWidth(160, Unit.POINTS);
		CBSchoolName.setNullSelectionAllowed(false);
		CBSchoolName.setNewItemsAllowed(false);
		CBExamCenter = new ComboBox("Exam center");
		CBExamCenter.setWidth(160, Unit.POINTS);
		CBExamCenter.setNullSelectionAllowed(false);
		CBExamCenter.setNewItemsAllowed(false);
		BTAssign = new Button("Assign");
		BTAssign.addClickListener(ecafc);
		fl = new FormLayout();
		
		fl.addComponent(CBRegion);
		fl.addComponent(CBZone);
		fl.addComponent(CBWoreda);
		fl.addComponent(CBSchoolName);
		fl.addComponent(CBExamCenter);		
		fl.addComponent(BTAssign);
		CBRegion.addValidator(new NullValidator("Cannot be empty", false));
		CBZone.addValidator(new NullValidator("Cannot be empty", false));
		CBWoreda.addValidator(new NullValidator("Cannot be empty", false));		
		CBExamCenter.addValidator(new NullValidator("Cannot be empty", false));
		CBSchoolName.addValidator(new NullValidator("Cannot be empty", false));
		formValidatorLabelsOn(false);
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
		
		List<School> schools = schoolDAO.getByWoredaId((Integer)CBWoreda.getValue());
		for (School s : schools) {

			CBSchoolName.addItem(s.getCode());
			CBSchoolName.setItemCaption(s.getCode(), s.getSchoolName());
		}
	}
public void formValidatorLabelsOn(boolean isOn) {
		
		CBRegion.setValidationVisible(isOn);
		CBZone.setValidationVisible(isOn);
		CBWoreda.setValidationVisible(isOn);
		CBSchoolName.setValidationVisible(isOn);
		CBExamCenter.setValidationVisible(isOn);

	}

	public void Validate() throws InvalidValueException {
		
		CBRegion.validate();
		CBZone.validate();
		CBWoreda.validate();
		CBSchoolName.validate();
		CBExamCenter.validate();

	}
	public void fillExamCenter() {
		CBExamCenter.removeAllItems();
		ExamCenterDAO examCenterDAO=new ExamCenterDAO(new ConnManager());		
		List<ExamCenter> examCenters=examCenterDAO.getByWoreda((Integer)CBWoreda.getValue());
		for (ExamCenter  e : examCenters) {

			CBExamCenter.addItem(e.getGroupNo());
			CBExamCenter.setItemCaption(e.getGroupNo(),e.getSchool().getSchoolName());
		}
	}
}

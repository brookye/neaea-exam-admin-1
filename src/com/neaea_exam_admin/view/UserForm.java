package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.RoleDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.controller.UserFormController;
import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.Role;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class UserForm extends CustomComponent {
	public ComboBox CBUserType;
	public TextField TFFName;
	public TextField TFLName;
	public TextField TFEmail;
	public PasswordField PFPassword;
	public PasswordField PFConfirmPassword;
	public TextField TFUName;
	public TextField TFTelephone;
	public Button BTAddUser;
	public FormLayout fl;
	public UserFormController ufc = new UserFormController(this);
	public ComboBox CBZone;
	public ComboBox CBRegion;
	public ComboBox CBWoreda;
	public ComboBox CBSchoolName;

	public UserForm() {
		init();
		setCompositionRoot(fl);
	}

	private void init() {
		fl = new FormLayout();
		CBUserType = new ComboBox("User type");
		CBUserType.addValidator(new NullValidator("Empty selection", false));
		CBUserType.setImmediate(true);
		CBUserType.addValueChangeListener(ufc);
		CBUserType.setNewItemsAllowed(false);
		CBUserType.setNullSelectionAllowed(false);
		CBUserType.setWidth(160, Sizeable.Unit.POINTS);
		CBRegion = new ComboBox("Region");
		CBRegion.addValidator(new NullValidator("Empty selection", false));
		CBRegion.setNewItemsAllowed(false);
		CBRegion.setNullSelectionAllowed(false);
		CBRegion.setWidth(160, Sizeable.Unit.POINTS);
		CBZone = new ComboBox("Zone");
		CBZone.addValidator(new NullValidator("Empty selection", false));
		CBWoreda = new ComboBox("Woreda");

		CBWoreda.addValidator(new NullValidator("Empty selection", false));

		CBSchoolName = new ComboBox("School");
		CBRegion.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillZone();

			}
		});
		CBZone.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillWoreda();

			}
		});
		CBWoreda.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				fillSchool();

			}
		});

		CBZone.setNewItemsAllowed(false);
		CBZone.setNullSelectionAllowed(false);
		CBZone.setWidth(160, Sizeable.Unit.POINTS);

		CBWoreda.setNewItemsAllowed(false);
		CBWoreda.setNullSelectionAllowed(false);
		CBWoreda.setWidth(160, Sizeable.Unit.POINTS);

		CBSchoolName.setNewItemsAllowed(false);
		CBSchoolName.setNullSelectionAllowed(false);
		CBSchoolName.setWidth(160, Sizeable.Unit.POINTS);
		fillUserType();
		TFEmail = new TextField("Email");
		TFEmail.addValidator(new EmailValidator("Invalid email"));

		TFEmail.addValidator(new StringLengthValidator("Not a valid email", 3,
				40, false));
		TFEmail.setWidth(160, Sizeable.Unit.POINTS);
		TFFName = new TextField("First name");
		TFFName.addValidator(new StringLengthValidator("Not a valid name", 2,
				40, false));

		TFFName.addValidator(new RegexpValidator("[a-zA-z]+", true,
				"only alphabet"));

		TFFName.setWidth(160, Sizeable.Unit.POINTS);
		TFLName = new TextField("Last name");

		TFFName.addValidator(new StringLengthValidator("Not a valid name", 2,
				40, false));
		TFLName.addValidator(new RegexpValidator("[a-zA-z]+", true,
				"Only alphabet is allowed"));
		TFLName.setWidth(160, Sizeable.Unit.POINTS);
		TFUName = new TextField("User name");

		TFUName.addValidator(new StringLengthValidator(
				"Not a valid user name atlease five alphabetic charactes", 5,
				40, false));

		TFUName.addValidator(new RegexpValidator("[a-zA-z]+", true,
				"Only alphabet is allowed"));
		TFUName.setWidth(160, Sizeable.Unit.POINTS);
		PFPassword = new PasswordField("Password");
		PFPassword
				.addValidator(new StringLengthValidator(
						"Incorrect password format atleast string sould be between 8 and 15",
						8, 15, false));
		/*
		 * PFPassword .addValidator(new RegexpValidator( "[a-zA-Z0-9]+", false,
		 * "password should consist special characters,numbers and letters.No other character is allowed."
		 * ));
		 */
		PFPassword.setWidth(160, Sizeable.Unit.POINTS);
		PFConfirmPassword = new PasswordField("Confirm password");
		/*
		 * PFConfirmPassword .addValidator(new RegexpValidator( "[a-zA-Z0-9]+",
		 * false,
		 * "password should consist special characters,numbers and letters.No other character is allowed."
		 * ));
		 */
		PFConfirmPassword
				.addValidator(new StringLengthValidator(
						"Incorrect password format atleast string should be between 8 and 15",
						8, 15, false));

		PFConfirmPassword.setWidth(160, Sizeable.Unit.POINTS);
		TFTelephone = new TextField("Telephone");
		TFTelephone.setWidth(160, Sizeable.Unit.POINTS);
		TFTelephone
				.addValidator(new NullValidator("null is not allowed", false));
		TFTelephone.addValidator(new RegexpValidator("[0-9]+", true,
				"invalid telephone"));

		TFTelephone.addValidator(new StringLengthValidator(
				"Invalid phone number", 10, 10, true));
		BTAddUser = new Button("Add");
		BTAddUser.addClickListener(ufc);
		fl.setImmediate(true);
		resetFormLayout(false);
		formValidatorsOn(false);

	}

	public void schoolRepFormPartsValidated(boolean isOn) {
		CBZone.setValidationVisible(isOn);
		CBRegion.setValidationVisible(isOn);
		CBWoreda.setValidationVisible(isOn);
		CBSchoolName.setValidationVisible(isOn);
	}

	public void schoolRepFormPartValidate() {
		CBZone.validate();
		CBRegion.validate();
		CBWoreda.validate();
		CBSchoolName.validate();
	}

	public void formValidatorsOn(boolean isOn) {
		CBUserType.setValidationVisible(isOn);
		TFFName.setValidationVisible(isOn);
		TFLName.setValidationVisible(isOn);
		TFEmail.setValidationVisible(isOn);
		PFPassword.setValidationVisible(isOn);
		PFConfirmPassword.setValidationVisible(isOn);
		TFUName.setValidationVisible(isOn);
		TFTelephone.setValidationVisible(isOn);

		CBZone.setValidationVisible(isOn);
		CBRegion.setValidationVisible(isOn);
		CBWoreda.setValidationVisible(isOn);
		CBSchoolName.setValidationVisible(isOn);

	}

	public void Validate() throws InvalidValueException {
		CBUserType.validate();
		TFFName.validate();
		TFLName.validate();
		TFEmail.validate();
		PFPassword.validate();
		PFConfirmPassword.validate();
		TFUName.validate();
		TFTelephone.validate();

	}

	public void resetFormLayout(boolean schoolMasterIsSelected) {
		fl.removeAllComponents();
		fl.addComponent(CBUserType);
		if (schoolMasterIsSelected) {
			fl.addComponent(CBRegion);
			fl.addComponent(CBZone);
			fl.addComponent(CBWoreda);
			fl.addComponent(CBSchoolName);
			fillRegion();
		}
		fl.addComponent(TFFName);
		fl.addComponent(TFLName);
		fl.addComponent(TFEmail);
		fl.addComponent(TFTelephone);
		fl.addComponent(TFUName);
		fl.addComponent(PFPassword);
		fl.addComponent(PFConfirmPassword);
		fl.addComponent(BTAddUser);
	}

	public void fillUserType() {
		RoleDAO roleDAO = new RoleDAO(new ConnManager());
		List<Role> roles = roleDAO.getAll();
		for (Role r : roles) {
			CBUserType.addItem(r.getRoleId());
			CBUserType.setItemCaption(r.getRoleId(), r.getRoleName());
		}
	}

	public void fillRegion() {
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

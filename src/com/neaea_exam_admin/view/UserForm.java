package com.neaea_exam_admin.view;

import java.util.List;

import com.neaea_exam_admin.DAO.RoleDAO;
import com.neaea_exam_admin.DAO.UserDAO;
import com.neaea_exam_admin.controller.UserFormController;
import com.neaea_exam_admin.entity.Role;
import com.neaea_exam_admin.utilities.ConnManager;
import com.sun.javafx.print.Units;
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

	public UserForm() {
		init();
		setCompositionRoot(fl);
	}

	private void init() {
		fl = new FormLayout();
		CBUserType = new ComboBox("User type");
		CBUserType.setNewItemsAllowed(false);
		CBUserType.setNullSelectionAllowed(false);
		CBUserType.setWidth(160, Sizeable.Unit.POINTS);
		fillUserType();
		TFEmail = new TextField("Email");
		TFEmail.setWidth(160, Sizeable.Unit.POINTS);
		TFFName = new TextField("First name");
		TFFName.setWidth(160, Sizeable.Unit.POINTS);
		TFLName = new TextField("Last name");
		TFLName.setWidth(160, Sizeable.Unit.POINTS);
		TFUName = new TextField("User name");
		TFUName.setWidth(160, Sizeable.Unit.POINTS);
		PFPassword = new PasswordField("Password");
		PFPassword.setWidth(160, Sizeable.Unit.POINTS);
		PFConfirmPassword = new PasswordField("Confirm password");
		PFConfirmPassword.setWidth(160, Sizeable.Unit.POINTS);
		TFTelephone = new TextField("Telephone");
		TFTelephone.setWidth(160, Sizeable.Unit.POINTS);
		BTAddUser = new Button("Add");
		BTAddUser.addClickListener(ufc);
		fl.addComponent(CBUserType);
		fl.addComponent(TFFName);
		fl.addComponent(TFLName);
		fl.addComponent(TFEmail);
		fl.addComponent(TFTelephone);
		fl.addComponent(TFUName);
		fl.addComponent(PFPassword);
		fl.addComponent(PFConfirmPassword);
		fl.addComponent(BTAddUser);
	}
	public void fillUserType(){
		RoleDAO roleDAO=new RoleDAO(new ConnManager());
		List<Role> roles=roleDAO.getAll();
		for (Role r:roles){
			CBUserType.addItem(r.getRoleId());
			CBUserType.setItemCaption(r.getRoleId(), r.getRoleName());
		}
	}
}

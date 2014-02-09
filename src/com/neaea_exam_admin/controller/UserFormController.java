package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.DAO.RoleDAO;
import com.neaea_exam_admin.DAO.UserDAO;
import com.neaea_exam_admin.entity.Role;
import com.neaea_exam_admin.entity.User;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.UserForm;
import com.vaadin.ui.Notification;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class UserFormController implements ClickListener, ValueChangeListener {
	private UserForm uf;
	private ConnManager connManager;
	private UserDAO userDAO;
	private RoleDAO roleDAO;

	public UserFormController(UserForm _uf) {
		uf = _uf;
		connManager = new ConnManager();
		userDAO = new UserDAO(connManager);
		roleDAO = new RoleDAO(connManager);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try{
	    uf.formValidatorsOn(false);
	    uf.Validate();
		Role role = roleDAO.getById((Integer) uf.CBUserType.getValue()).get(0);
		User user = null;
		if(!uf.PFPassword.getValue().equals(uf.PFConfirmPassword.getValue())){
			Notification.show("Password don't much",Notification.TYPE_ERROR_MESSAGE);
			return;
		}
		if (role.getRoleName().equals("SCHOOL_MASTER")) {
			uf.schoolRepFormPartValidate();
			user = new User(role, uf.PFPassword.getValue(),
					uf.TFTelephone.getValue(), uf.TFEmail.getValue(),
					uf.TFFName.getValue(), uf.TFLName.getValue(),
					uf.TFUName.getValue(), uf.CBSchoolName.getValue()
							.toString());
		} else {
			user = new User(role, uf.PFPassword.getValue(),
					uf.TFTelephone.getValue(), uf.TFEmail.getValue(),
					uf.TFFName.getValue(), uf.TFLName.getValue(),
					uf.TFUName.getValue(), "NULL");
		}
		userDAO.persist(user);
		Notification.show("User has been added",Notification.TYPE_HUMANIZED_MESSAGE);
		}
		catch(InvalidValueException e){
			uf.formValidatorsOn(true);
			Notification.show("Error in form",Notification.TYPE_ERROR_MESSAGE);
			
		}

	}

	

	@Override
	public void valueChange(ValueChangeEvent event) {
		System.out.println("INFO:ValueChangeListe");
		Role role = roleDAO.getById((Integer) uf.CBUserType.getValue()).get(0);
		boolean schoolMasterIsSelected = false;
		if (role.getRoleName().equals("SCHOOL_MASTER")) {
			uf.resetFormLayout(!schoolMasterIsSelected);
		} else {
			uf.resetFormLayout(schoolMasterIsSelected);
		}

	}
}

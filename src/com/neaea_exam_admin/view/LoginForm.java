/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.neaea_exam_admin.controller.LoginFormController;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * 
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class LoginForm extends CustomComponent {
	public Button BTLogin;
	public TextField TFUname;
	public PasswordField PFPassword;
	public FormLayout loginFormLayout;
	public Label LBError;	
	private LoginFormController lfc = new LoginFormController(this);

	public LoginForm() {		
		init();  	
		setCompositionRoot(loginFormLayout);
	}

	public void init() {
		loginFormLayout = new FormLayout();
		LBError = new Label();
		PFPassword = new PasswordField("Password");
		BTLogin = new Button("Login");
		BTLogin.addClickListener(lfc);
		TFUname = new TextField("User Name");
		loginFormLayout.addComponent(LBError);
		loginFormLayout.addComponent(TFUname);
		loginFormLayout.addComponent(PFPassword);
		loginFormLayout.addComponent(BTLogin);
		TFUname.setWidth(160, Sizeable.Unit.POINTS);
		PFPassword.setWidth(160, Sizeable.Unit.POINTS);
		BTLogin.setWidth(80, Sizeable.Unit.POINTS);
		LBError.setVisible(false);
	}

	

}

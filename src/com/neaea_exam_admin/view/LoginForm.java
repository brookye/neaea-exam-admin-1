/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

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
public class LoginForm extends CustomComponent{
    Button loginBtn=new Button("Login");
    TextField userNameTF=new TextField("User Name");
    PasswordField  paswdPwd=new PasswordField("Password");
    Label errorLbl=new Label();
    public LoginForm(){
      init();  
        FormLayout loginFormLayout=new FormLayout();
        loginFormLayout.addComponent(errorLbl);
        loginFormLayout.addComponent(userNameTF);
        loginFormLayout.addComponent(paswdPwd); 
        loginFormLayout.addComponent(loginBtn);
        setCompositionRoot(loginFormLayout);
    }
    public void init(){
        userNameTF.setWidth(160, Sizeable.Unit.POINTS);
        paswdPwd.setWidth(160, Sizeable.Unit.POINTS);
        loginBtn.setWidth(80, Sizeable.Unit.POINTS);
        errorLbl.setVisible(false);
    }

   
}

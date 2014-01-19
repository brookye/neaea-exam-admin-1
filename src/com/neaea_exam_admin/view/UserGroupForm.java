/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class UserGroupForm extends CustomComponent {
    TextField name=new TextField("Name");
    Button create=new Button("Create");
    private CheckBox financeRoleChk;
    private CheckBox schoolRepRoleChk;
    private CheckBox codeBookRoleChk;
    private CheckBox sysAdminRoleChk;
    public UserGroupForm(){
        init();
        VerticalLayout userGLayout=new VerticalLayout();
        FormLayout userGFormLayout=new FormLayout();
        userGFormLayout.addComponent(new Label("<h1>Create user group</h>", ContentMode.HTML));
        userGFormLayout.addComponent(name);
        userGFormLayout.addComponent(financeRoleChk);
        userGFormLayout.addComponent(schoolRepRoleChk);
        userGFormLayout.addComponent(codeBookRoleChk);
        userGFormLayout.addComponent(sysAdminRoleChk);
        userGFormLayout.addComponent(create);
        userGLayout.addComponent(userGFormLayout);
        setCompositionRoot(userGLayout);
    }
    public void init(){
         financeRoleChk=new CheckBox("Finance");
        schoolRepRoleChk=new CheckBox("SchoolRep");
         codeBookRoleChk=new CheckBox("Code book");
        sysAdminRoleChk=new CheckBox("System Admin");
        
    }
}

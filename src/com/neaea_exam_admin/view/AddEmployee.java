/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class AddEmployee extends CustomComponent {
    TextField empName = new TextField("Emp Name");
    TextField depName = new TextField("Department");
    ComboBox contractType = new ComboBox("Contract type");
    DateField recruitment = new DateField("Last payment date");
    public  AddEmployee(){
        FormLayout fl=new FormLayout();
        fl.addComponent(empName);
        fl.addComponent(depName);
        fl.addComponent(contractType);
        fl.addComponent(recruitment);
    }
}

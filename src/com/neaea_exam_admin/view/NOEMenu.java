/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class NOEMenu extends CustomComponent {

    MenuBar mb = new MenuBar();

    public NOEMenu() {
        init();
        HorizontalLayout hl=new HorizontalLayout();
        hl.addComponent(mb);
        hl.setSizeFull();
        setCompositionRoot(hl);
    }

    public void init() {
        mb.setSizeFull();
        MenuBar.MenuItem registrar = mb.addItem("Registration", null, null);
        MenuBar.MenuItem userAdmin = mb.addItem("Users", null, null);
        MenuBar.MenuItem finance = mb.addItem("Finance", null, null);
        MenuBar.MenuItem reports = mb.addItem("Reports", null);
        MenuBar.MenuItem view = mb.addItem("View", null);
        registrar.addItem("Students", null, null);
        registrar.addItem("School Representative", null, null);
        userAdmin.addItem("New User", null, null);
        userAdmin.addItem("All User", null, null);
        userAdmin.addItem("Alter User", null, null);
        finance.addItem("Human Resource", null, null);
        finance.addItem("Budget", null, null);
    }
}

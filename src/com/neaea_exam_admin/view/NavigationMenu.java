/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * 
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class NavigationMenu extends CustomComponent {
	public MenuBar menubar;
	public MenuItem zone;
	public MenuItem registerExaminee;

	public NavigationMenu() {
		init();
	}

	private void init() {
		HorizontalLayout vl = new HorizontalLayout();
		menubar = new MenuBar();
		MenuItem userManagement = menubar.addItem("User management", null);
		MenuItem createUser = userManagement.addItem("Create user", null);
		MenuItem updateUser = userManagement.addItem("Update user", null);
		MenuItem school = menubar.addItem("School", null);
		MenuItem createSchool = school.addItem("Create school", null);
		MenuItem updateSchool = school.addItem("Update school", null);
		MenuItem assingExamCenter = school.addItem("Assign exam center", null);
		MenuItem schoolLocation = school.addItem("School location", null);
		zone = schoolLocation.addItem("Zone", null);
		MenuItem woreda = schoolLocation.addItem("Region", null);
		MenuItem examCenter = menubar.addItem("Exam Center", null);
		MenuItem createExamCenter = examCenter.addItem("School location", null);
		MenuItem updateExamCenter = examCenter.addItem("School location", null);
		MenuItem examinee = menubar.addItem("Examinee", null);
		registerExaminee = examinee.addItem("Register examinee", null);
		MenuItem updateExaminee = examinee.addItem("Update examinee", null);
		MenuItem assignRegNo = examinee.addItem("Assign regNo", null);

		MenuItem examAdmin = menubar.addItem("Exam admin", null);
		MenuItem report = menubar.addItem("Report", null);
		menubar.addItem("Help", null);
		menubar.addItem("Logout", null);
		menubar.setSizeFull();
		vl.addComponent(menubar);
		vl.setSizeFull();
		setCompositionRoot(vl);
	}

}

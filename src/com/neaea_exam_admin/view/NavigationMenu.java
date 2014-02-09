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
@SuppressWarnings("serial")
public class NavigationMenu extends CustomComponent {
	public MenuBar menubar;
	public MenuItem zone;
	public MenuItem registerExaminee;
	public MenuItem newUser;
	public MenuItem updateUser;
	public MenuItem newSchool;
	public MenuItem editSchool;
	public MenuItem assingExamCenter;
	public MenuItem woreda;
	public MenuItem newExamCenter;
	public MenuItem updateExamCenter;
	public MenuItem editExaminee;
	public MenuItem assignRegNo;
	public MenuItem finance;
	public MenuItem report;
	public MenuItem woredaAllowance;
	public MenuItem user;
	public MenuItem logout;
	public MenuItem examCenter;
	public MenuItem userManagement;
	public MenuItem school;
	public MenuItem examinee;

	public NavigationMenu() {
		init();
	}

	private void init() {
		HorizontalLayout vl = new HorizontalLayout();
		menubar = new MenuBar();
		userManagement = menubar.addItem("User management", null);
		newUser = userManagement.addItem("New user", null);
		updateUser = userManagement.addItem("Edit user", null);
		school = menubar.addItem("School", null);
		newSchool = school.addItem("New school", null);
		editSchool = school.addItem("Edit school", null);
		assingExamCenter = school.addItem("Assign exam center", null);
		MenuItem schoolLocation = school.addItem("Location setup", null);
		zone = schoolLocation.addItem("New zone", null);
		woreda = schoolLocation.addItem("New worda", null);
		examCenter = menubar.addItem("Exam Center", null);
		newExamCenter = examCenter.addItem("New exam center", null);
		updateExamCenter = examCenter.addItem("Edit exam center", null);
		examinee = menubar.addItem("Examinee", null);
		registerExaminee = examinee.addItem("New examinee", null);
		editExaminee = examinee.addItem("Edit examinee", null);
		assignRegNo = examinee.addItem("Assign regNo", null);
		finance = menubar.addItem("Finance", null);
		woredaAllowance = finance.addItem("Woreda Allowance", null);
		report = menubar.addItem("Report", null);
		menubar.addItem("Help", null);
		user = menubar.addItem("User", null);
		logout = user.addItem("Logout", null);
		menubar.setSizeFull();
		vl.addComponent(menubar);
		vl.setSizeFull();
		setCompositionRoot(vl);
	}

}

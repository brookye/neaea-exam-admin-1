package com.neaea_exam_admin.view;

import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

;
@SuppressWarnings("serial")
@Theme("neaea_exam_admin")
public class MainForm extends UI implements Command, CloseListener {
	public static HashMap<String, String> userData = new HashMap<String, String>();
	public NavigationMenu nm = new NavigationMenu();
	public ExamineeCreateForm ecrf = new ExamineeCreateForm();
	public WoredaAllowanceForm wa = new WoredaAllowanceForm();
	public WoredaForm wf = new WoredaForm();
	public ZoneForm zf = new ZoneForm();
	public SchoolForm sf = new SchoolForm();
	public ExamCenterForm ecf = new ExamCenterForm();
	public VerticalLayout vlayout = new VerticalLayout();
	ExamCenterAssignmentForm ecaf = new ExamCenterAssignmentForm();
	UserForm uf = new UserForm();
	LoginForm lf = new LoginForm();
	static Window loginWindow;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainForm.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		setContent(vlayout);
		showLogin();

	}

	// only to be called by the LoginFormController after authentication
	public static void showMain() {
		loginWindow.close();
	}

	public void showLogin() {
		lf.TFUname.setValue("");
		lf.PFPassword.setValue("");
		loginWindow = new Window();
		loginWindow.addCloseListener(this);
		loginWindow.setContent(lf);
		loginWindow.setClosable(false);
		loginWindow.center();
		loginWindow.setWidth("30em");
		loginWindow.setResizable(false);
		loginWindow.setModal(true);
		loginWindow.setCaption("NEAEA EXAM ADMINISTRATION");
		addWindow(loginWindow);
	}

	public void AssignMenuItemEventHandler(NavigationMenu nm) {
		nm.zone.setCommand(this);
		nm.registerExaminee.setCommand(this);
		nm.newUser.setCommand(this);
		nm.woreda.setCommand(this);
		nm.newSchool.setCommand(this);
		nm.assingExamCenter.setCommand(this);
		nm.woredaAllowance.setCommand(this);
		nm.newExamCenter.setCommand(this);
		nm.updateExamCenter.setCommand(this);
		nm.logout.setCommand(this);
	}

	public void openZoneForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(zf);
	}

	public void openExamineeCreateForm() {
		ecrf.schoolCode.setValue(userData.get("SCHOOL_CODE"));
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(ecrf);
	}

	public void openUserForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(uf);
	}

	public void openExamCenterForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		ecf.fillForm(); //just to refill with edit or add conditions
		vlayout.addComponent(ecf);
	}

	public void openSchoolForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(sf);
	}

	public void openWoredaForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(wf);
	}

	public void openExamCenterAssignmentForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(ecaf);
	}

	public void openWoredaAllowanceForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(wa);
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		int formId = selectedItem.getId();
		if (formId == nm.zone.getId()) {
			openZoneForm();
		} else if (formId == nm.registerExaminee.getId()) {
			openExamineeCreateForm();
		} else if (formId == nm.newUser.getId()) {
			openUserForm();
		} else if (formId == nm.woreda.getId()) {
			openWoredaForm();
		} else if (formId == nm.newExamCenter.getId()) {
			ExamCenterForm.isEdit=false;			
			openExamCenterForm();
		}
		else if (formId == nm.updateExamCenter.getId()) {
			ExamCenterForm.isEdit=true;
			openExamCenterForm();
		}
		else if (formId == nm.newSchool.getId()) {
			openSchoolForm();
		} else if (formId == nm.assingExamCenter.getId()) {
			openExamCenterAssignmentForm();
		} else if (formId == nm.woredaAllowance.getId()) {
			openWoredaAllowanceForm();
		}
		else if (formId == nm.logout.getId()) {
			userData.clear();
			nm.user.setText("User");
			showLogin();
		}
		

	}
    public void setActiveLinks(){
    	String user=userData.get("USER_TYPE");    	
    	if(user.equals("SCHOOL_MASTER")){    		
    		nm.examCenter.setEnabled(false);
    		nm.finance.setEnabled(false);
    		nm.userManagement.setEnabled(false);
    		nm.school.setEnabled(false);
    		nm.report.setEnabled(false);
    	}
    	else if(user.equals("ADMIN")){
    		
    	}
        else if (user.equals("EXAM_PACKING")) {

		}
        else if (user.equals("FINANCE")) {

		}   	
    	
    }
	@Override
	public void windowClose(CloseEvent e) {
		vlayout.addComponent(nm);
		AssignMenuItemEventHandler(nm);
		// please welcome the hero
		nm.user.setText(userData.get("USER"));
		Notification.show("Welcome " + userData.get("USER") + "! ",
				userData.get("USER_TYPE"), Notification.TYPE_HUMANIZED_MESSAGE);
	   setActiveLinks();
	}
}
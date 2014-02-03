package com.neaea_exam_admin.view;

import java.util.Spliterator;
import java.util.function.Consumer;

import javax.servlet.annotation.WebServlet;

import com.neaea_exam_admin.view.LoginForm;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.Command;

;
@SuppressWarnings("serial")
@Theme("neaea_exam_admin")
public class MainForm extends UI implements Command {
	public NavigationMenu nm = new NavigationMenu();
	public ExamineeCreateForm ecrf = new ExamineeCreateForm();
	public WoredaAllowanceForm wa = new WoredaAllowanceForm();
	public WoredaForm wf = new WoredaForm();
	public ZoneForm zf = new ZoneForm();
	public SchoolForm sf = new SchoolForm();
	public ExamCenterForm ecf = new ExamCenterForm();
	public final VerticalLayout vlayout = new VerticalLayout();
	ExamCenterAssignmentForm ecaf = new ExamCenterAssignmentForm();
	UserForm uf = new UserForm();
	LoginForm lf = new LoginForm();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainForm.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		setContent(vlayout);
		vlayout.addComponent(nm);
		AssignMenuItemEventHandler(nm);
		
	}
    public void AssignMenuItemEventHandler(NavigationMenu nm){
    	nm.zone.setCommand(this);
		nm.registerExaminee.setCommand(this);
		nm.newUser.setCommand(this);
		nm.woreda.setCommand(this);
		nm.newSchool.setCommand(this);
		nm.assingExamCenter.setCommand(this);
		nm.woredaAllowance.setCommand(this);
		nm.newExamCenter.setCommand(this);
    }
	public void openZoneForm() {
		vlayout.removeAllComponents();
		vlayout.addComponent(nm);
		vlayout.addComponent(zf);
	}

	public void openExamineeCreateForm() {
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
	public void forEach(Consumer<? super Component> action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Spliterator<Component> spliterator() {
		// TODO Auto-generated method stub
		return null;
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
			openExamCenterForm();
		} else if (formId == nm.newSchool.getId()) {
			openSchoolForm();
		} else if (formId == nm.assingExamCenter.getId()) {
			openExamCenterAssignmentForm();
		} else if (formId == nm.woredaAllowance.getId()) {
			openWoredaAllowanceForm();
		}

	}
}
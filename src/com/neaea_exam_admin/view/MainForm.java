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
	LoginForm lf = new LoginForm();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainForm.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		setContent(vlayout);
		vlayout.addComponent(nm);
		vlayout.addComponent(ecaf);
        nm.zone.setCommand(this);
        nm.registerExaminee.setCommand(this);
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
		int formId=selectedItem.getId();
		if(formId==nm.zone.getId()){
			openZoneForm();
		}
		else if(formId==nm.registerExaminee.getId()){
			openExamineeCreateForm();
		}
	}
}
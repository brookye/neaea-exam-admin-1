package com.neaea_exam_admin.view;

import javax.servlet.annotation.WebServlet;

import com.neaea_exam_admin.view.LoginForm;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("neaea_exam_admin")
public class Main extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Main.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		//LoginForm lf = new LoginForm();
		NavigationMenu nm=new NavigationMenu();
		ExamineeCreateForm ecf=new ExamineeCreateForm();
		WoredaAllowanceForm wa=new WoredaAllowanceForm();
		WoredaForm wf= new WoredaForm();
		ZoneForm zf = new ZoneForm();
		final VerticalLayout vlayout = new VerticalLayout();
		setContent(vlayout);
		vlayout.addComponent(nm);
		vlayout.addComponent(wa);
		
		

	}

}
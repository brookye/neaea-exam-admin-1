package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.DAO.ExamCenterDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.view.ExamCenterForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;

public class ExamCenterController implements ClickListener, ValueChangeListener {

	private ExamCenterForm ecf;
	ExamCenterDAO examCenterDAO;
	SchoolDAO schoolDAO;
	private ConnManager connManager;

	public ExamCenterController(ExamCenterForm _examCenterForm) {
		ecf = _examCenterForm;
		connManager = new ConnManager();
		examCenterDAO = new ExamCenterDAO(connManager);
		schoolDAO = new SchoolDAO(connManager);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		School school = schoolDAO.getByCode(
				ecf.CBSchoolName.getValue().toString()).get(0);
		ExamCenter examCenter = new ExamCenter(school,
				Integer.valueOf(ecf.TFGroupNo.getValue()),
				Float.valueOf(ecf.TFDistance.getValue()),
				Integer.valueOf(ecf.TFNoOfClassRoom.getValue()));
		examCenterDAO.persist(examCenter);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

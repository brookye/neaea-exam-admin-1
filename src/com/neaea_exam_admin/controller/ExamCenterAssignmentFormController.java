package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.view.ExamCenterAssignmentForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.neaea_exam_admin.DAO.ExamCenterDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;

@SuppressWarnings("serial")
public class ExamCenterAssignmentFormController implements ClickListener,
		ValueChangeListener {

	private ExamCenterAssignmentForm ecaf;
	private ExamCenterDAO examCenterDAO;
	private SchoolDAO schoolDAO;
	private ConnManager connManager;

	public ExamCenterAssignmentFormController(
			ExamCenterAssignmentForm examCenterAssignmentForm) {
		ecaf = examCenterAssignmentForm;
		connManager = new ConnManager();
		examCenterDAO = new ExamCenterDAO(connManager);
		schoolDAO = new SchoolDAO(connManager);
	}

	@Override
	public void buttonClick(ClickEvent event) {
       ExamCenter examCenter=examCenterDAO.getByGroupNo((Integer)ecaf.CBExamCenter.getValue()).get(0);
	   School school=schoolDAO.getByCode((String)ecaf.CBSchoolName.getValue()).get(0);
	   school.setExamCenter(examCenter);
	   schoolDAO.update(school);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

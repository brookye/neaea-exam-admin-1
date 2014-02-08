package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.view.ExamCenterAssignmentForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
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
		try{
			ecaf.formValidatorLabelsOn(false);	
			ecaf.Validate();
			ExamCenter examCenter=examCenterDAO.getByGroupNo((Integer)ecaf.CBExamCenter.getValue()).get(0);
		   School school=schoolDAO.getByCode((String)ecaf.CBSchoolName.getValue()).get(0);
		   school.setExamCenter(examCenter);
		   schoolDAO.update(school);
		   Notification.show("Exam Center has been assigned", Notification.TYPE_HUMANIZED_MESSAGE);
			}
			catch(InvalidValueException e){
				ecaf.formValidatorLabelsOn(true);
				Notification.show("Invalid values", Notification.TYPE_ERROR_MESSAGE);
				}
		catch(Exception e){
			Notification.show(e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}
		}
	

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.DAO.ExamCenterDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.view.ExamCenterForm;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;

@SuppressWarnings("serial")
public class ExamCenterController implements ClickListener,
		ValueChangeListener, ValueChangeEvent, TextChangeListener {

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
		try {
			ecf.setFormValidators(false);
			ecf.validate();			
			School school = schoolDAO.getByCode(
					ecf.CBSchoolName.getValue().toString()).get(0);
			ExamCenter examCenter = new ExamCenter(school,
					Integer.valueOf(ecf.TFGroupNo.getValue()),
					Float.valueOf(ecf.TFDistance.getValue()),
					Integer.valueOf(ecf.TFNoOfClassRoom.getValue()));
			examCenterDAO.persist(examCenter);
		} catch (InvalidValueException e) {
			ecf.setFormValidators(true);
			Notification
					.show("Invalid form",
							"One more values you entered are incorrect please check the red highlighted fields",
							Notification.TYPE_ERROR_MESSAGE);
		}
	}

	@Override
	public Property getProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void textChange(TextChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

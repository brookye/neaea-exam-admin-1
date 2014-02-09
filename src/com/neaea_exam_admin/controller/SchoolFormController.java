package com.neaea_exam_admin.controller;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.SchoolForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.neaea_exam_admin.entity.Woreda;
@SuppressWarnings("serial")
public class SchoolFormController  implements ClickListener,
ValueChangeListener {
 private SchoolForm sf;
private ConnManager connManager;
private SchoolDAO schoolDAO;
private WoredaDAO woredaDAO;
public SchoolFormController(SchoolForm _sf){
	 sf=_sf;
	 connManager=new ConnManager();
	 schoolDAO=new SchoolDAO(connManager);
	 woredaDAO=new WoredaDAO(connManager);
 }

@Override
public void valueChange(ValueChangeEvent event) {
	sf.fillZone();
	
}

@SuppressWarnings("deprecation")
@Override
public void buttonClick(ClickEvent event) {
	if(event.getButton().getConnectorId()== sf.BTAdd.getConnectorId()){
		try{
		sf.formValidatorLabelsOn(false);	
		sf.Validate();
		Woreda woreda=woredaDAO.getById((Integer)sf.CBWoreda.getValue()).get(0);
	    School school=new School(sf.TFSchoolCode.getValue(),null,sf.TFSchoolName.getValue(),woreda);
	    schoolDAO.persist(school);
	    Notification.show("School has been added",Notification.TYPE_HUMANIZED_MESSAGE);
		}
		catch(InvalidValueException e){
			sf.formValidatorLabelsOn(true);
			Notification.show("Invalid values", Notification.TYPE_ERROR_MESSAGE);
		}

		catch(Exception e){
			Notification.show(e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}

	}
	
}
}

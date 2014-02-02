package com.neaea_exam_admin.controller;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.SchoolForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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

@Override
public void buttonClick(ClickEvent event) {
	if(event.getButton().getConnectorId()== sf.BTAdd.getConnectorId()){
		Woreda woreda=woredaDAO.getById((Integer)sf.CBWoreda.getValue()).get(0);
	    School school=new School(sf.TFSchoolCode.getValue(),null,sf.TFSchoolName.getValue(),woreda);
	    schoolDAO.persist(school);
	}
	
}
}

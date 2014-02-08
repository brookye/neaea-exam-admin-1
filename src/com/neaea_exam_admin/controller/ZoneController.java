package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.DAO.RegionDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.ZoneForm;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ZoneController implements Button.ClickListener {
	private ZoneForm zf;
	private ZoneDAO zoneDAO;
	private RegionDAO regionDAO;
	private ConnManager connManager;

	public ZoneController(ZoneForm _zf) {
		zf = _zf;
		connManager = new ConnManager();
		zoneDAO = new ZoneDAO(connManager);
		regionDAO = new RegionDAO(connManager);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
        try{
        zf.setFormValidator(false);
        zf.validate();
		Zone zone = new Zone(regionDAO
				.getById((Integer) zf.CBRegion.getValue()).get(0),
				zf.TFZone.getValue(),
				Integer.valueOf(zf.TFZoneCode.getValue()), 0);
		
		String values="RegionId:"+(Integer) zf.CBRegion.getValue()+"Zone Name"+zf.TFZone.getValue()+"Zone Code"+
				Integer.valueOf(zf.TFZoneCode.getValue());
		Notification.show("ZONE",values,Notification.TYPE_HUMANIZED_MESSAGE);
		zoneDAO.persist(zone);
        }
        catch(InvalidValueException e){
        	zf.setFormValidator(true);
        	Notification.show("Error","One or more values were incorrect please check the red lighted fields",Notification.TYPE_ERROR_MESSAGE);
        }
	}
}

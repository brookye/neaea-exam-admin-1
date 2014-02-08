package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.DAO.WoredaDAO;
import com.neaea_exam_admin.DAO.ZoneDAO;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.WoredaForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class WoredaFormController implements ClickListener, ValueChangeListener {

	private WoredaForm woredaForm;
	private ZoneDAO zoneDAO;
	private WoredaDAO woredaDAO;
	private ConnManager connManager;

	public WoredaFormController(WoredaForm _woredaForm) {
		connManager = new ConnManager();
		woredaForm = _woredaForm;
		woredaDAO = new WoredaDAO(connManager);
		zoneDAO = new ZoneDAO(connManager);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {
			woredaForm.setFormValidator(false);
			woredaForm.validate();

			if (event.getButton().getConnectorId() == woredaForm.BTAddWoreda
					.getConnectorId()) {
				int zoneId = (Integer) woredaForm.CBZone.getValue();
				String debug = "CBZone:" + zoneId;
				System.out.println(debug);
				Zone zone = zoneDAO.getZoneById(zoneId).get(0);
				System.out.println("INFO:Zone is" + zone.getZoneName());
				Woreda woreda = new Woreda(zone,
						woredaForm.TFWoreda.getValue(),
						Integer.valueOf(woredaForm.TFWoredaCode.getValue()), 0);
				woredaDAO.persist(woreda);				
				Notification.show("Woreda has been added", Notification.TYPE_HUMANIZED_MESSAGE);
				
			}
		} catch (InvalidValueException e) {
			woredaForm.setFormValidator(true);
			Notification
					.show("Invalid values", Notification.TYPE_ERROR_MESSAGE);
		}
		catch(Exception e){
			Notification.show(e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}
	}
	
	

	@Override
	public void valueChange(ValueChangeEvent event) {
		System.out.println("value change event fired...CBRegion value="
				+ woredaForm.CBRegion.getValue());
		woredaForm.fillZone();

	}

}

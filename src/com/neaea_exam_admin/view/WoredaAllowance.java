package com.neaea_exam_admin.view;
import java.awt.image.TileObserver;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;



public class WoredaAllowance extends CustomComponent{
	 Label titleLB=new Label(" <h1>Woreda Allowance</h1>",ContentMode.HTML);
	 // TextField WoredaAllowanceNameTF=new TextField("WoredaAllowance Name");
	  TextField WoredaAllowanceCodeTF=new TextField("WoredaAllowance Code");  
	  ComboBox WoredaAllowanceWoredaTF=new ComboBox("Woreda");
	  TextField WoredaAllowanceLowTF=new TextField("Low Scale");
	  TextField WoredaAllowanceMediumTF=new TextField("Medium Scale");
	  TextField WoredaAllowanceHighTF=new TextField("High Scale");
	  TextField WoredaAllowanceDesertTF=new TextField("Desert Allowance");
	  Button woredaAllFwanceAddTF=new Button(" Add");
	  Button woredaAllFwanceCancelTF=new Button("Cancel");
	  Button woredaAllFwanceUpdate=new Button(" Update");
	  
	  
	  
	  
	;
	    Label errorLbl=new Label();
	    public WoredaAllowance(){
	      init();  
	        FormLayout WoredaAllowanceFormLayout=new FormLayout();
	        WoredaAllowanceFormLayout.addComponent(titleLB);
	        WoredaAllowanceFormLayout.addComponent(errorLbl);
	        WoredaAllowanceFormLayout.addComponent(WoredaAllowanceCodeTF);
	           
	        WoredaAllowanceFormLayout.addComponent(WoredaAllowanceWoredaTF);
	        WoredaAllowanceFormLayout.addComponent(WoredaAllowanceLowTF);
	        WoredaAllowanceFormLayout.addComponent(WoredaAllowanceMediumTF);
	        WoredaAllowanceFormLayout.addComponent(WoredaAllowanceHighTF);
	        WoredaAllowanceFormLayout.addComponent(WoredaAllowanceDesertTF);
	        
	        WoredaAllowanceFormLayout.addComponent(woredaAllFwanceAddTF);
	        //vl.addComponent(woredaAllFwanceCancelTF);
	        //vl.addComponent(woredaAllFwanceUpdate);
	        //vl.setSizeFull();
	        setCompositionRoot(WoredaAllowanceFormLayout);
	    }
	    public void init(){
	    	titleLB.setSizeFull();
	    	titleLB.setWidth(250, Sizeable.Unit.POINTS);
	       // WoredaAllowanceNameTF.setWidth(160, Sizeable.Unit.POINTS);
	        WoredaAllowanceCodeTF.setWidth(160, Sizeable.Unit.POINTS);
	        WoredaAllowanceWoredaTF.setWidth(160, Sizeable.Unit.POINTS);
	        WoredaAllowanceLowTF.setWidth(160, Sizeable.Unit.POINTS);
	        WoredaAllowanceMediumTF.setWidth(160, Sizeable.Unit.POINTS);
	        WoredaAllowanceHighTF.setWidth(160, Sizeable.Unit.POINTS);
	        WoredaAllowanceDesertTF.setWidth(160, Sizeable.Unit.POINTS);
	        
	        errorLbl.setVisible(false);
	    }
}




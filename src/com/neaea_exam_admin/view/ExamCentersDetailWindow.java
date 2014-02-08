/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class ExamCentersDetailWindow extends CustomComponent {

    Table examCenterDetailTbl = new Table();

    public ExamCentersDetailWindow() {
        init();
        VerticalLayout examCenterLayout=new VerticalLayout();
        examCenterLayout.addComponent(new Label("<h1>Exam ceneter detal</h1>", ContentMode.HTML));
        examCenterLayout.addComponent(examCenterDetailTbl);
        setCompositionRoot(examCenterLayout);
        for(int i=0;i<5;i++){
            String eCenter="school"+i;
            String zone="zone-woreda"+i;
            CheckBox cb10E=new CheckBox();
            CheckBox cb10N=new CheckBox();
            CheckBox cb12E=new CheckBox();
            CheckBox cb12N=new CheckBox();
            Integer rooms=10*i;
            Integer distance=120;           
            examCenterDetailTbl.addItem(new Object[]{eCenter,zone,cb10E,cb10N,cb12E,cb12N,rooms,zone,distance,zone}, null);
            
        }
        examCenterDetailTbl.setPageLength(5);
    }

    public void init() {
        examCenterDetailTbl.addContainerProperty("Exam center", String.class, null);
        examCenterDetailTbl.addContainerProperty("Zone/Woreda", String.class, null);
        examCenterDetailTbl.addContainerProperty("Existing(10th)", CheckBox.class, null);
        examCenterDetailTbl.addContainerProperty("New(10th)", CheckBox.class, null);
        examCenterDetailTbl.addContainerProperty("Existing(12th)", CheckBox.class, null);
        examCenterDetailTbl.addContainerProperty("New(12th)", CheckBox.class, null);
        examCenterDetailTbl.addContainerProperty("Rooms", Integer.class, null);
        examCenterDetailTbl.addContainerProperty("Exam Delivery location", String.class, null);
        examCenterDetailTbl.addContainerProperty("Distance", Integer.class, null);
        examCenterDetailTbl.addContainerProperty("Appropriate location", String.class, null);
        
        

    }
}

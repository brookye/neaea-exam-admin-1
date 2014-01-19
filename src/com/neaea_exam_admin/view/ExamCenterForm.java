/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class ExamCenterForm extends CustomComponent {

    VerticalLayout centerDetail = new VerticalLayout();
    VerticalLayout centerForm = new VerticalLayout();
    VerticalLayout examSeries = new VerticalLayout();
    HorizontalLayout main = new HorizontalLayout();
    private Label info;
    private Label summary;
    private Label candidates;
    private Label tRegistered;
    private Label tcandidates;
    private Label tcandidatesp;
    private Label tRegisteredp;
    private TextField centerNo;
    private TextField centerName;
    private Button saveBtn;
    private Button cancelBtn;
    private Button delBtn;
    

    public ExamCenterForm() {
        init();
        main.addComponent(centerDetail);
        main.addComponent(centerForm);
        main.addComponent(examSeries);
        setCompositionRoot(main);
    }

    public void init() {
        info = new Label("<h1>Center Details</h1>", ContentMode.HTML);
        summary = new Label("Summary", ContentMode.HTML);
        candidates = new Label("Candidates", ContentMode.HTML);
        tcandidates = new Label("Total Candidates Registered", ContentMode.HTML);
        tRegistered = new Label("13", ContentMode.HTML);
        tcandidatesp = new Label("Total Candidates with photo", ContentMode.HTML);
        tRegisteredp = new Label("13", ContentMode.HTML);
        centerDetail.addComponent(info);
        centerDetail.addComponent(summary);
        centerDetail.addComponent(candidates);
        centerDetail.addComponent(tcandidates);
        centerDetail.addComponent(tRegistered);
        centerDetail.addComponent(tcandidatesp);
        centerDetail.addComponent(tRegisteredp);
        centerDetail.setMargin(true);
        centerNo=new TextField("Center Number");
        centerName=new TextField("Center Name");
        HorizontalLayout hl=new HorizontalLayout();
        hl.setMargin(true);
        saveBtn=new Button("Save");
        cancelBtn=new Button("Cancel");
        delBtn=new Button("Delete");
        hl.addComponent(saveBtn);
        hl.addComponent(cancelBtn);
        hl.addComponent(delBtn);
        centerForm.addComponent(centerNo);
        centerForm.addComponent(centerName);
        centerForm.addComponent(hl);
        centerForm.setMargin(true);
        Label examCenterInfo=new Label("<h1>Exam Series</h1>", ContentMode.HTML);
        CheckBox grade10=new CheckBox("Grade10");
        CheckBox grade12=new CheckBox("Grade12");
        examSeries.addComponent(examCenterInfo);
        examSeries.addComponent(grade10);
        examSeries.addComponent(grade12);
        examSeries.setMargin(true);
    }
}

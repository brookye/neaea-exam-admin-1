/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import java.util.HashMap;
import java.util.List;

import com.neaea_exam_admin.DAO.CategoryDAO;
import com.neaea_exam_admin.DAO.ExamDAO;
import com.neaea_exam_admin.controller.ExamineeCreateController;
import com.neaea_exam_admin.entity.Category;
import com.neaea_exam_admin.entity.Exam;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class ExamineeCreateForm extends CustomComponent {
	public TextField fName;
	public TextField mName;
	public TextField gfName;
	public TextField schoolCode;
	public TextField age;
	public ComboBox sex;
	public ComboBox nationality;
	public ComboBox sight;
	public ComboBox category;
	public HashMap<Integer, CheckBox> subjectsToRegister;
	public Button regButton;
	public Panel subjectsPanel;
	public Upload studImageUploader;
	public Image studImage;
	public String studImageTmpPath;
	public VerticalLayout studLayout;
	public Table studentTable;
	public TextField studentSearch;
	public ExamineeCreateController ecfc;
    public VerticalLayout vl ;
    ConnManager connManager = new ConnManager();
	@SuppressWarnings("deprecation")
	public ExamineeCreateForm() {
		ecfc = new ExamineeCreateController(this); // add the controller
													// class for this form
		init();
		FormLayout fl = new FormLayout();
		fl.addComponent(fName);
		fl.addComponent(mName);
		fl.addComponent(gfName);
		fl.addComponent(schoolCode);
		fl.addComponent(age);
		fl.addComponent(sex);
		fl.addComponent(sight);
		fl.addComponent(category);
		fl.addComponent(nationality);
		fl.addComponent(regButton);		
		subjectsPanel.setContent(vl);
		fl.setMargin(true);
		// fl.addComponent(subjectsPanel);
		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(fl);
		hl.addComponent(subjectsPanel);
		hl.addComponent(studLayout);
		VerticalLayout vlmain = new VerticalLayout();
		Label header = new Label("<h1>Examinee Registration</h1>",
				ContentMode.HTML);
		vlmain.addComponent(header);
		vlmain.addComponent(hl);
		vlmain.addComponent(studentSearch);
		vlmain.addComponent(studentTable);
		// instantitate the upload event listener
		// bind the listener to the uploader
		studImageUploader.setReceiver(ecfc);
		studImageUploader.addListener(ecfc);
		// bind regButton to ClickListener
		regButton.addClickListener(ecfc);
		// set the root component
		setCompositionRoot(vlmain);
	}

	public void init() {
		
		subjectsPanel = new Panel("Subjects to Register for");
		new Panel("student photo");
		studentTable = new Table();
		studentTable.setSizeFull();
		// add columns
		studentTable.addContainerProperty("Name", String.class, null);
		studentTable.addContainerProperty("FName", String.class, null);
		studentTable.addContainerProperty("GFName", String.class, null);
		studentTable
				.addContainerProperty("Registration No", String.class, null);
		// Allow selecting items from the table.
		studentTable.setSelectable(true);
		// Send changes in selection immediately to server.
		studentTable.setImmediate(true);
		// add value changelistener
		studentTable.addValueChangeListener(ecfc);
		// Shows feedback from selection.
		studImageUploader = new Upload("Upload", null);
		studImage = new Image();	
		studImage.setVisible(false);
		studLayout = new VerticalLayout();
		studLayout.addComponent(studImage);
		studLayout.addComponent(studImageUploader);
		// studentImagePanel.setContent(studLayout);
		studLayout.setMargin(true);
		subjectsPanel.setSizeUndefined();
		regButton = new Button("Update");
		regButton.setWidth(80, Unit.POINTS);
		fName = new TextField("Name");
		fName.setWidth(160, Unit.POINTS);
		mName = new TextField("Father's name");
		mName.setWidth(160, Unit.POINTS);
		gfName = new TextField("Grandfather's name");
		gfName.setWidth(160, Unit.POINTS);
		schoolCode = new TextField("School Code");
		//schoolCode.setValue(MainForm.userData.get("SCHOOL_CODE"));
		schoolCode.setEnabled(false);
		schoolCode.setWidth(160, Unit.POINTS);
		age = new TextField("Age");
		age.setWidth(160, Unit.POINTS);
		studentSearch = new TextField("Search student");
		studentSearch.addTextChangeListener(ecfc);
		studentSearch.setWidth(160, Unit.POINTS);
		sex = new ComboBox("Sex");
		sex.addItem("M");
		sex.addItem("F");
		sex.setWidth(160, Unit.POINTS);
		category = new ComboBox("Category");
		CategoryDAO categoryDAO = new CategoryDAO(connManager);
		List<Category> categories = categoryDAO.getAll();
		for (Category cat : categories) {
			category.addItem(cat.getId());
			category.setItemCaption(cat.getId(), cat.getName());
		}
		category.setImmediate(true);
		category.setWidth(160, Unit.POINTS);
		nationality = new ComboBox("Nationality");
		nationality.addItem("Ethiopian");
		nationality.addItem("Non Ethiopian");
		nationality.setValue("Ethiopian");
		nationality.setWidth(160, Unit.POINTS);
		sight = new ComboBox("Sight");
		sight.addItem("Non Blind");
		sight.addItem("Blind");
		sight.setValue("Non Blind");
		sight.setWidth(160, Unit.POINTS);
		subjectsToRegister = new HashMap<Integer, CheckBox>();
		// fill exams from the DB
		vl= new VerticalLayout();
		vl.setMargin(true);
		fillExam();
		

	}
	/**
	 * Fills all corresponding exams for a new registration
	 */
	public void fillExam(){
		ExamDAO examDAO = new ExamDAO(connManager);
		List<Exam> exams = examDAO.getAll();
		subjectsToRegister.clear();
		for (Exam exam : exams) {
			subjectsToRegister.put(exam.getId(), new CheckBox(exam.getName()));
		}
		vl.removeAllComponents();
		for (Integer i : subjectsToRegister.keySet()) {
			vl.addComponent(subjectsToRegister.get(i));
		}
	}

}

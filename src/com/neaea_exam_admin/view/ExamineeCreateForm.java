/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.neaea_exam_admin.DAO.CategoryDAO;
import com.neaea_exam_admin.DAO.ExamDAO;
import com.neaea_exam_admin.DAO.ExamExamineeDAO;
import com.neaea_exam_admin.DAO.ExamineeDAO;
import com.neaea_exam_admin.DAO.SchoolCodeBookDAO;
import com.neaea_exam_admin.entity.Category;
import com.neaea_exam_admin.entity.Exam;
import com.neaea_exam_admin.entity.ExamExaminee;
import com.neaea_exam_admin.entity.Examinee;
import com.neaea_exam_admin.entity.SchoolCode;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.utilities.ImageUtil;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
@SuppressWarnings("serial")
public class ExamineeCreateForm extends CustomComponent {
	private TextField fName;
	private TextField mName;
	private TextField gfName;
	private TextField schoolCode;
	private TextField age;
	private Select sex;
	private Select nationality;
	private Select sight;
	private Select category;
	private HashMap<Integer, CheckBox> subjectsToRegister;
	private Button regButton;
	private Panel subjectsPanel;
	private Upload studImageUploader;
	private Image studImage;
	private String studImageTmpPath;
	private VerticalLayout studLayout;
	private Table studentTable;
	private TextField studentSearch;

	@SuppressWarnings("deprecation")
	public ExamineeCreateForm() {
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
		VerticalLayout vl = new VerticalLayout();
		for (Integer i : subjectsToRegister.keySet()) {
			vl.addComponent(subjectsToRegister.get(i));
		}
		vl.setMargin(true);
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
		ImageUploader ip = new ImageUploader(studImage);
		// bind the listener to the uploader
		studImageUploader.setReceiver(ip);
		studImageUploader.addListener(ip);
		// bind regButton to ClickListener
		regButton.addClickListener(new Controller(this));
		// set the root component
		setCompositionRoot(vlmain);
	}

	public void init() {
		ConnManager connManager = new ConnManager();
		subjectsPanel = new Panel("Subjects to Register for");
		new Panel("student photo");
		studentTable = new Table();
		studentTable.setSizeFull();
		studentTable.addContainerProperty("Name", String.class, null);
		studentTable.addContainerProperty("FName", String.class, null);
		studentTable.addContainerProperty("GFName", String.class, null);
		studentTable
				.addContainerProperty("Registration No", String.class, null);
		studImageUploader = new Upload("Upload", null);
		studImage = new Image();
		studImage.setVisible(false);
		studLayout = new VerticalLayout();
		studLayout.addComponent(studImage);
		studLayout.addComponent(studImageUploader);
		// studentImagePanel.setContent(studLayout);
		studLayout.setMargin(true);
		subjectsPanel.setSizeUndefined();
		regButton = new Button("Register");
		regButton.setWidth(80, Unit.POINTS);
		fName = new TextField("Name");
		fName.setWidth(160, Unit.POINTS);
		mName = new TextField("Father's name");
		mName.setWidth(160, Unit.POINTS);
		gfName = new TextField("Grandfather's name");
		gfName.setWidth(160, Unit.POINTS);
		schoolCode = new TextField("School Code");
		schoolCode.setValue("083432");
		schoolCode.setEnabled(false);
		schoolCode.setWidth(160, Unit.POINTS);
		age = new TextField("Age");
		age.setWidth(160, Unit.POINTS);
		studentSearch = new TextField("Search student");
		studentSearch.setWidth(160, Unit.POINTS);
		sex = new Select("Sex");
		sex.addItem("M");
		sex.addItem("F");
		sex.setWidth(160, Unit.POINTS);
		category = new Select("Category");
		CategoryDAO categoryDAO = new CategoryDAO(connManager);
		List<Category> categories = categoryDAO.getAll();
		for (Category cat : categories) {
			category.addItem(cat.getName());
			category.setItemCaption(cat.getId(), cat.getName());
		}
		category.setImmediate(true);
		category.setWidth(160, Unit.POINTS);
		nationality = new Select("Nationality");
		nationality.addItem("Ethiopian");
		nationality.addItem("Non Ethiopian");

		nationality.setWidth(160, Unit.POINTS);
		sight = new Select("Sight");
		sight.addItem("Non Blind");
		sight.addItem("Blind");
		sight.setWidth(160, Unit.POINTS);
		subjectsToRegister = new HashMap<Integer, CheckBox>();
		// fill from the DB
		ExamDAO examDAO = new ExamDAO(connManager);
		List<Exam> allExams = examDAO.getAll();
		for (Exam exam : allExams) {
			subjectsToRegister.put(exam.getId(), new CheckBox(exam.getName()));
		}

	}

	class ImageUploader implements Upload.Receiver, SucceededListener {
		private File file;
		private Image image;

		public ImageUploader(Image _image) {
			image = _image;
		}

		@SuppressWarnings("deprecation")
		public OutputStream receiveUpload(String filename, String mimeType) {
			// Create upload stream
			FileOutputStream fos = null; // Stream to write to
			try {
				// Open the file for writing.
				studImageTmpPath = "/home/misgana/tmp/" + filename;
				file = new File(studImageTmpPath);
				fos = new FileOutputStream(file);
			} catch (final java.io.FileNotFoundException e) {
				Notification.show("Could not open file<br/>", e.getMessage(),
						Notification.TYPE_ERROR_MESSAGE);
				return null;
			}
			return fos; // Return the output stream to write to
		}

		public void uploadSucceeded(SucceededEvent event) {
			// Show the uploaded file in the image viewer
			image.setWidth("200px");
			image.setHeight("200px");
			image.setVisible(true);
			image.setSource(new FileResource(file));
		}

	}

	class Controller implements Button.ClickListener {

		private ExamineeCreateForm ecf;
		private ConnManager connManager = new ConnManager();

		public Controller(ExamineeCreateForm _ecf) {
			ecf = _ecf;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			// check the id button which fired the event
			if (event.getButton().getConnectorId() == regButton
					.getConnectorId()) {
				Notification.show("it works", Notification.TYPE_TRAY_NOTIFICATION);
				byte[] studPhotoBytes = null;

				studPhotoBytes = ImageUtil.extractBytes(studImageTmpPath);

				// registration confirmation no
				String regNo = "45454" + (int) Math.random();

				// get the schoolcodeBook
				SchoolCodeBookDAO schoolCodeBookDAO = new SchoolCodeBookDAO(
						connManager);
				SchoolCode schoolCodeBook = schoolCodeBookDAO.getByCode(
						schoolCode.getValue()).get(0);

				// persist examinee
				ExamineeDAO examineeDAO = new ExamineeDAO(connManager);
				Examinee examinee = new Examinee(fName.getValue(),
						mName.getValue(), gfName.getValue(), schoolCodeBook,
						/*Integer.getInteger((String)age.getValue())*/22,
						(String) sex.getValue(), (String) sight.getValue(),
						(String) nationality.getValue(),
						1, studPhotoBytes, regNo, 0);
                examineeDAO.persist(examinee);
                Notification.show("Examinee saved", Notification.TYPE_TRAY_NOTIFICATION);
				// persist ExamExaminee
				/*ExamExamineeDAO examExamineDAO = new ExamExamineeDAO(
						connManager);
				ExamDAO examDAO = new ExamDAO(connManager);
				List<Exam> exams = new ArrayList<Exam>(); // all exam to be
															// taken by the
															// examinee
				for (Integer i : subjectsToRegister.keySet()) {
					if (subjectsToRegister.get(i).getValue()) {
						exams.add(examDAO.getExamById(i).get(0));
					}
				}
				examinee = examineeDAO.getByRegConfNo(regNo).get(0);
				for (Exam exam : exams) {
					ExamExaminee examExaminee = new ExamExaminee(examinee,
							exam, 0);
					
				}*/

			}

		}
	}
}

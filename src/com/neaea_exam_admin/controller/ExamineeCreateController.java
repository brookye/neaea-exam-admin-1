package com.neaea_exam_admin.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.neaea_exam_admin.DAO.CategoryDAO;
import com.neaea_exam_admin.DAO.ExamDAO;
import com.neaea_exam_admin.DAO.ExamExamineeDAO;
import com.neaea_exam_admin.DAO.ExamineeDAO;
import com.neaea_exam_admin.DAO.SchoolDAO;
import com.neaea_exam_admin.entity.Exam;
import com.neaea_exam_admin.entity.ExamExaminee;
import com.neaea_exam_admin.entity.Examinee;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.utilities.Environment;
import com.neaea_exam_admin.utilities.ImageUtil;
import com.neaea_exam_admin.view.ExamineeCreateForm;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

/**
 * 
 * @author Misgana Bayetta <misgana.bayetta@gmail.com> 2014
 * 
 */
@SuppressWarnings("serial")
public class ExamineeCreateController implements ClickListener,
		Upload.Receiver, SucceededListener, TextChangeListener,
		ValueChangeListener {
	private ExamineeCreateForm ecf;
	private ConnManager connManager = new ConnManager();
	private File file;
	// all involving DAOs
	ExamineeDAO examineeDAO = new ExamineeDAO(connManager);
	ExamExamineeDAO examExamineDAO = new ExamExamineeDAO(connManager);
	ExamDAO examDAO = new ExamDAO(connManager);
	SchoolDAO schoolDAO = new SchoolDAO(connManager);
	CategoryDAO categoryDAO = new CategoryDAO(connManager);

	public ExamineeCreateController(ExamineeCreateForm _ecf) {
		ecf = _ecf;
	}

	public OutputStream receiveUpload(String filename, String mimeType) {
		// Create upload stream
		FileOutputStream fos = null; // Stream to write to
		try {
			// Open the file for writing.
			ecf.studImageTmpPath = Environment.createDir("tmp") + filename;
			file = new File(ecf.studImageTmpPath);
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {

			return null;
		}
		return fos; // Return the output stream to write to
	}

	public void uploadSucceeded(SucceededEvent event) {
		// Show the uploaded file in the image viewer
		ecf.studImage.setWidth("200px");
		ecf.studImage.setHeight("200px");
		ecf.studImage.setVisible(true);
		ecf.studImage.setSource(new FileResource(file));
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// check the id button which fired the event
		if (event.getButton().getConnectorId() == ecf.regButton
				.getConnectorId()) {
			byte[] studPhotoBytes = null;

			studPhotoBytes = ImageUtil.extractBytes(ecf.studImageTmpPath);

			// registration confirmation no
			String regNo = "45454" + "21";

			// get the schoolcodeBook

			School school = schoolDAO.getByCode(
					ecf.schoolCode.getValue()).get(0);

			// persist examinee

			Examinee examinee = new Examinee(ecf.fName.getValue(),
					ecf.mName.getValue(), ecf.gfName.getValue(),
					school, Integer.valueOf(ecf.age.getValue()),
					(String) ecf.sex.getValue(), (String) ecf.sight.getValue(),
					(String) ecf.nationality.getValue(), categoryDAO.getById(
							(Integer) ecf.category.getValue()).get(0),
					studPhotoBytes, regNo, 0);
			examineeDAO.persist(examinee);
			System.out.println("INFO:Examinee created successfully");
			// persist ExamExamine
			List<Exam> exams = new ArrayList<Exam>(); // all exam to be taken by
			// the examinee
			for (Integer i : ecf.subjectsToRegister.keySet()) {
				if (ecf.subjectsToRegister.get(i).getValue()) {
					exams.add(examDAO.getExamById(i).get(0));
				}
			}
			String DEBUG_STRING = "INFO:EXAMINEE regNo:" + regNo;
			System.out.println(DEBUG_STRING);
			examinee = examineeDAO.getByRegConfNo(regNo).get(0);
			DEBUG_STRING = "EXAMINEE:" + examinee.getName() + "-"
					+ examinee.getExamineeId() + "\nEXAMS:";
			for (Exam exam : exams) {
				ExamExaminee examExaminee = new ExamExaminee(examinee, exam, 0);
				DEBUG_STRING += exam.getName() + ":" + exam.getId();
				examExamineDAO.persist(examExaminee);
			}
			System.out.println(DEBUG_STRING);
		}

	}

	@Override
	public void textChange(TextChangeEvent event) {
		if (event.getComponent().getConnectorId() == ecf.studentSearch
				.getConnectorId()) {
			if (event.getText() != null) {
				List<Examinee> matchingExaminees = examineeDAO.getByName(event
						.getText().trim());
				for (Examinee examinee : matchingExaminees) {
					ecf.studentTable.addItem(
							new Object[] { examinee.getName(),
									examinee.getFatherName(),
									examinee.getGrandFatherName(),
									examinee.getRegistrationConfirmationNo() },
							examinee.getExamineeId());
				}
			}
		}

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO set condition for target table since this call back handles any
		// table who has registerd to this class
		if (ecf.studentTable.getValue() != null) {
			Integer examineeId = (Integer) ecf.studentTable.getValue();
			// System.out.println("INFO:examinee selected has an Id:"+examineeId);
			// set the form field
			Examinee selectedExaminee = examineeDAO.getById(examineeId).get(0);
			// set everything
			ecf.age.setValue(Integer.toString(selectedExaminee.getAge()));
			ecf.fName.setValue(selectedExaminee.getName());
			ecf.mName.setValue(selectedExaminee.getFatherName());
			ecf.gfName.setValue(selectedExaminee.getGrandFatherName());
			ecf.category.setValue(selectedExaminee.getCatagory().getId());
			ecf.sex.setValue(selectedExaminee.getSex());
			ecf.sight.setValue(selectedExaminee.getSight());
			ecf.nationality.setValue(selectedExaminee.getNationality());
			// get exam registered by the examinee
			List<ExamExaminee> examExaminees = examExamineDAO
					.getExamExamineeByExaminee(selectedExaminee);
			List<Exam> exams = new ArrayList<Exam>();
			for (ExamExaminee examExaminee : examExaminees) {
				exams.add(examExaminee.getExamId());
			}
			// set registered subject of the examinee
			// TODO there will be a better centralized mechanism to do this but
			// now,wer'e in a hurry
			ecf.fillExam(); // cleare all checked once first
			for (Exam exam : exams) {
				for (int i : ecf.subjectsToRegister.keySet()) {
					if (i == exam.getId()) {
						ecf.subjectsToRegister.get(i).setValue(true);
					}
				}
			}
			//upload existing examinee phot
			try {
				String studImageTmpPath=Environment.createDir("tmp")+"/"+selectedExaminee.getRegistrationConfirmationNo()+".jpg";
				System.out.println("INFO:stud image paht-"+studImageTmpPath);
				BufferedImage bi=ImageIO.read(new ByteArrayInputStream(selectedExaminee.getPhoto()));
				File file=new File(studImageTmpPath);
				ImageIO.write(bi, "jpg",file);
				ecf.studImage.setSource(new FileResource(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			
			// System.out.println("INFO:"+selectedExaminee.getName()+"'s category is "+ecf.category.getValue()+"of id"+ecf.category.getValue());
		}

	}

}

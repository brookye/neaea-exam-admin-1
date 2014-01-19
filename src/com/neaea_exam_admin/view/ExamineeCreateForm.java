/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class ExamineeCreateForm extends CustomComponent {
    private TextField fName;
    private TextField mName;
    private TextField gfName;
    private TextField schoolCode;
    private TextField age;
    private ComboBox sex;
    private ComboBox nationality;
    private ComboBox sight;
    private ArrayList<CheckBox> subjectsToRegister;
    private Button regButton;
    private Panel subjectsPanel;
    private Panel studentImagePanel;
    private Upload studImageUploader;
    private Image studImage;
    private VerticalLayout studLayout;
    
    public ExamineeCreateForm(){
        init();
        FormLayout fl=new FormLayout();
        fl.addComponent(fName);
        fl.addComponent(mName);
        fl.addComponent(gfName);
        fl.addComponent(schoolCode);
        fl.addComponent(age);
        fl.addComponent(sex);
        fl.addComponent(nationality);
        fl.addComponent(regButton);
        VerticalLayout vl=new VerticalLayout();
        for (CheckBox cb : subjectsToRegister){
            vl.addComponent(cb);
        }
        vl.setMargin(true);
        subjectsPanel.setContent(vl);       
        fl.setMargin(true);
       // fl.addComponent(subjectsPanel);
        HorizontalLayout hl=new HorizontalLayout();
        hl.addComponent(fl);
        hl.addComponent(subjectsPanel);
        hl.addComponent(studLayout);
        VerticalLayout vlmain=new VerticalLayout();
        Label header=new Label("<h1>Examinee Registration</h1>",ContentMode.HTML);      
        vlmain.addComponent(header);
        vlmain.addComponent(hl);
        ImageUploader ip=new ImageUploader(studImage);
        studImageUploader.setReceiver(ip);
        studImageUploader.addListener(ip);        
        setCompositionRoot(vlmain);
    }

    public void init() {
        subjectsPanel= new Panel("Subjects to Register for");
        studentImagePanel=new Panel("student photo");
        studImageUploader=new Upload("Upload", null);
        studImage= new Image();
        studImage.setVisible(false);
        studLayout=new VerticalLayout();
        studLayout.addComponent(studImage);
        studLayout.addComponent(studImageUploader);
        //studentImagePanel.setContent(studLayout);
        studLayout.setMargin(true);
        subjectsPanel.setSizeUndefined();
        regButton=new Button("Register");
        regButton.setWidth(80, Unit.POINTS);
        fName = new TextField("NAME");
        fName.setWidth(160, Unit.POINTS);
        mName = new TextField("FATHER'S NAME");
        mName.setWidth(160, Unit.POINTS);
        gfName = new TextField("GRANDFATHER'S NAME");
        gfName.setWidth(160, Unit.POINTS);
        schoolCode = new TextField("SCHOOL CODE");
        schoolCode.setWidth(160, Unit.POINTS);
        age = new TextField("AGE");
        age.setWidth(160, Unit.POINTS);
        sex = new ComboBox("SEX");
        sex.setWidth(160, Unit.POINTS);
        nationality = new ComboBox("NATIONALITY");
        nationality.setWidth(160, Unit.POINTS);
        sight = new ComboBox();
        sight.setWidth(160, Unit.POINTS);
        subjectsToRegister = new ArrayList<CheckBox>();
        subjectsToRegister.add(new CheckBox("English"));
        subjectsToRegister.add(new CheckBox("Maths(Natural)"));
        subjectsToRegister.add(new CheckBox("Scholastic Aptitude Test"));
        subjectsToRegister.add(new CheckBox("Physics"));
        subjectsToRegister.add(new CheckBox("Chemistry"));
        subjectsToRegister.add(new CheckBox("Biology"));
        subjectsToRegister.add(new CheckBox("Geography"));
        subjectsToRegister.add(new CheckBox("History"));
        subjectsToRegister.add(new CheckBox("Economics"));
        subjectsToRegister.add(new CheckBox("Maths(Social)"));
        subjectsToRegister.add(new CheckBox("Civics and Ethical Education"));
    }
    class ImageUploader implements Upload.Receiver,SucceededListener{
        private File file;
        private Image image;
        public ImageUploader(Image _image){
            image=_image;
        }
        public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
            // Open the file for writing.
            file = new File("/home/misgana/tmp/" + filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            Notification.show(
                    "Could not open file<br/>", e.getMessage(),
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
}

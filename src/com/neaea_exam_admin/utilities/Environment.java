package com.neaea_exam_admin.utilities;

import java.io.File;

import com.vaadin.server.VaadinService;

public class Environment {
    public final static String BASE_DIR=VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    
	public static String createDir(String dirName) {
		// TODO Auto-generated method stub
	    File f=new File(BASE_DIR+"/"+dirName);
	    if(f.exists()){
	    	return f.getAbsolutePath();
	    }
	    else{
	    	f.mkdir();
	    }
	   return f.getAbsolutePath();
	}
}

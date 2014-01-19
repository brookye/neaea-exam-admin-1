/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class LoginWindow  extends UI{
    
    public LoginWindow(){     
       
        
    }
    

    @Override
    protected void init(VaadinRequest request) {
       LoginForm lf=new LoginForm();
       final VerticalLayout vlayout = new VerticalLayout();
       vlayout.addComponent(lf);
    }
}

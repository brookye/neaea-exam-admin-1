/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class NavigationMenu extends CustomComponent{
    private MenuBar menubar;
    public NavigationMenu(){
      init();
    }
    private void init(){
        HorizontalLayout vl=new HorizontalLayout();
        menubar=new MenuBar();
        menubar.addItem("Registration", null);
        menubar.addItem("Finance", null);
        menubar.addItem("Reports", null);
        menubar.addItem("Help", null);
        menubar.addItem("Logout", null);
        menubar.setSizeFull();
        vl.addComponent(menubar);
        vl.setSizeFull();
        setCompositionRoot(vl);
    }
}

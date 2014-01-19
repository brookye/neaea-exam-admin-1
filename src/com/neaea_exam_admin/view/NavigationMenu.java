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
        menubar.addItem("menu-1", null);
        menubar.addItem("menu-2", null);
        menubar.addItem("menu-3", null);
        menubar.addItem("menu-4", null);
        menubar.setSizeFull();
        vl.addComponent(menubar);
        vl.setSizeFull();
        setCompositionRoot(vl);
    }
}

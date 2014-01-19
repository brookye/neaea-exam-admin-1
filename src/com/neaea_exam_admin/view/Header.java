/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neaea_exam_admin.view;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

/**
 *
 * @author Misgana Bayetta <misgana.bayetta@gmail.com>
 */
public class Header extends CustomComponent {

    public Header() {
        init();
    }

    private void init() {
        Resource res = new ThemeResource("img/initial.png");
// Display the image without caption
        Image image = new Image(null, res);
        image.setWidth("1600px");
        image.setHeight("100px");
        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setHeight(300, Unit.POINTS);
        headerLayout.setSizeFull();
        setCompositionRoot(headerLayout);
        //headerLayout.setStyleName("v-header");
        headerLayout.addComponent(image);

    }
}

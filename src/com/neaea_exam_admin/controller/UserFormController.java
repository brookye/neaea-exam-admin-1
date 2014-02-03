package com.neaea_exam_admin.controller;

import com.neaea_exam_admin.view.UserForm;
import com.neaea_exam_admin.DAO.RoleDAO;
import com.neaea_exam_admin.DAO.UserDAO;
import com.neaea_exam_admin.entity.Role;
import com.neaea_exam_admin.entity.User;
import com.neaea_exam_admin.utilities.ConnManager;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class UserFormController implements ClickListener {
	private UserForm uf;
	private ConnManager connManager;
	private UserDAO userDAO;
	private RoleDAO roleDAO;

	public UserFormController(UserForm _uf) {
		uf = _uf;
		connManager = new ConnManager();
		userDAO = new UserDAO(connManager);
		roleDAO = new RoleDAO(connManager);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Role role = roleDAO.getById((Integer) uf.CBUserType.getValue()).get(0);
		User user = new User(role, uf.PFPassword.getValue(),
				uf.TFTelephone.getValue(), uf.TFEmail.getValue(),
				uf.TFFName.getValue(), uf.TFLName.getValue(),
				uf.TFUName.getValue());
		userDAO.persist(user);

	}
}

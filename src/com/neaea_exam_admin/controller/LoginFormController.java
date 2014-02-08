package com.neaea_exam_admin.controller;

import java.util.List;

import com.neaea_exam_admin.DAO.UserDAO;
import com.neaea_exam_admin.entity.User;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.view.LoginForm;
import com.neaea_exam_admin.view.MainForm;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class LoginFormController implements ClickListener {

	private LoginForm lf;
	private UserDAO userDAO;

	public LoginFormController(LoginForm lf) {
		this.lf = lf;
		userDAO = new UserDAO(new ConnManager());
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// validate user
		List<User> user = userDAO.getByUnameAndPassword(lf.TFUname.getValue(),
				lf.PFPassword.getValue());

		if (user.isEmpty()) {
			lf.LBError.setValue("Invalid user name or password");
			lf.LBError.setVisible(true);
		} else {
			lf.LBError.setVisible(false);
			User authenticUser = user.get(0);

			// set session attributes

			MainForm.userData.put("USER", authenticUser.getUserName());
			MainForm.userData.put("USER_TYPE", authenticUser.getRole()
					.getRoleName());
			if (authenticUser.getSchoolCode() != null) {
				MainForm.userData.put("SCHOOL_CODE",
						authenticUser.getSchoolCode());
			} else {
				MainForm.userData.put("SCHOOL_CODE", null);
			}
			System.out.println("INFO:User is" + MainForm.userData.get("USER"));
			MainForm.showMain();

		}
	}

}

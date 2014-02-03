package com.neaea_exam_admin.DAO;

import com.neaea_exam_admin.entity.User;
import com.neaea_exam_admin.utilities.ConnManager;

public class UserDAO {
	private ConnManager connManager;

	public UserDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(User user) {
		String query = "INSERT INTO user VALUES('" + user.getUserName() + "','"
				+ user.getFirstName() + "','" + user.getLastName() + "','"
				+ user.getEmail() + "','" + user.getTelephone() + "','"
				+ user.getPassword() + "',"+user.getRole().getRoleId()+")";
		connManager.executeCUD(query);
	}

	public void updatePassword(User user) {
		String query = "UPDATE user SET password='" + user.getPassword()
				+ "' WHERE user_name='" + user.getUserName() + "'";
		connManager.executeCUD(query);
	}
}

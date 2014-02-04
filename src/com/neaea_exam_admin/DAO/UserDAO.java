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
				+ user.getPassword() + "'," + user.getRole().getRoleId() + ",'"
				+ user.getSchoolCode() + "')";
		connManager.executeCUD(query);
	}

	public void updatePassword(User user) {
		String query = "UPDATE user SET password='" + user.getPassword()
				+ "' WHERE user_name='" + user.getUserName() + "'";
		connManager.executeCUD(query);
	}

	public void updateSchoolCode(User user) {
		String query = "UPDATE user SET password='" + user.getPassword()
				+ "' WHERE user_name='" + user.getUserName() + "'";
		connManager.executeCUD(query);
	}
	/*
	 * public List<User> getByUname(String uname){ String
	 * query="SELECT * FROM user where user_name='" + uname+"'"; return
	 * getUsers(query); } public List<User> getUsers(String query){ List<User>
	 * users=new ArrayList<User>(); ResultSet rs=
	 * connManager.executeRead(query);
	 * 
	 * }
	 */
}

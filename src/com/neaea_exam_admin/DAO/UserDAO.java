package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Role;
import com.neaea_exam_admin.entity.User;
import com.neaea_exam_admin.utilities.ConnManager;

public class UserDAO {
	private ConnManager connManager;
	private RoleDAO roleDAO;

	public UserDAO(ConnManager _connManager) {
		connManager = _connManager;
		roleDAO = new RoleDAO(connManager);
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

	public List<User> getByUnameAndPassword(String uname, String password) {
		String query = "SELECT * FROM user where user_name='" + uname
				+ "' AND password='" + password + "'";
		System.out.println(query);
		return getUsers(query);
	}

	public List<User> getUsers(String query) {
		List<User> users = new ArrayList<User>();
		ResultSet rs = connManager.executeRead(query);
		try {
			while (rs.next()) {
				Role role = roleDAO.getById(rs.getInt("role")).get(0);
				User user = new User(role, rs.getString("password"),
						rs.getString("telephone"), rs.getString("email"),
						rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("user_name"), rs.getString("school_code"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

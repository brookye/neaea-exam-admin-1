package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.entity.Role;

public class RoleDAO {
	private ConnManager connManager;

	public RoleDAO(ConnManager _connManager) {
		connManager = _connManager;

	}

	public List<Role> getAll() {
		String query = "SELECT * FROM role";
		return get(query);
	}

	public List<Role> getById(int roleId) {
		String query = "SELECT * FROM role WHERE id=" + roleId;
		return get(query);
	}

	private List<Role> get(String query) {
		List<Role> roles = new ArrayList<Role>();
		ResultSet rs = connManager.executeRead(query);
		try {
			while (rs.next()) {
				Role role = new Role(rs.getInt("id"), rs.getString("name"));
				roles.add(role);
			}
			return roles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

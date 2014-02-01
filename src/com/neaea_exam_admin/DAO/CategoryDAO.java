package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Category;
import com.neaea_exam_admin.entity.Exam;
import com.neaea_exam_admin.utilities.ConnManager;

public class CategoryDAO {
	private ConnManager connManager;

	public CategoryDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(Category category) {

	}

	public void delete(Category category) {

	}

	public void update(Category category) {

	}

	public List<Category> getCategory(String getQuery) {
		ResultSet rs = connManager.executeRead(getQuery);
		List<Category> categories = new ArrayList<Category>();
		try {
			while (rs.next()) {
				Category category = new Category(rs.getInt("id"),
						rs.getString("name"));
				categories.add(category);
			}
			return categories;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public List<Category> getAll() {
		String query = "SELECT * FROM category";
		return getCategory(query);
	}

	public List<Category> getById(int id) {
		String query = "SELECT * FROM category WHERE id=" + id;
		return getCategory(query);
	}
}

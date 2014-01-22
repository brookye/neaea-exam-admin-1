package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.entity.SchoolCodeBook;

public class SchoolCodeBookDAO {
	private ConnManager connManager;

	public SchoolCodeBookDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(SchoolCodeBook schoolCodeBook) {
		String persistQuery = "INSERT INTO schoolcode VALUES('"
				+ schoolCodeBook.getCode() + "'," + schoolCodeBook.getGroupNo()
				+ ",NULL)";
		connManager.executeCUD(persistQuery);
	}

	public void delete(SchoolCodeBook schoolCodeBook) {
		String delQuery = "DELETE FROM schoolcode WHERE schoolCodeId='"
				+ schoolCodeBook.getSchoolCodeId() + "' AND code='"
				+ schoolCodeBook.getCode() + "'";
		connManager.executeCUD(delQuery);
	}

	public void update(SchoolCodeBook schoolCodeBook) {
		String updateQuery = "UPDATE schoolcode SET groupNo="
				+ schoolCodeBook.getGroupNo() + "WHERE schoolCodeId='"
				+ schoolCodeBook.getSchoolCodeId() + "' " + "AND code='"
				+ schoolCodeBook.getCode() + "'";
		connManager.executeCUD(updateQuery);
	}

	public List<SchoolCodeBook> getByCode(String code) {
		String getByCodeQuery = "SELECT * FROM schoolcode WHERE code='" + code
				+ "'";
		return getSchoolCodeBooks(getByCodeQuery);

	}

	public List<SchoolCodeBook> getBySchoolCodeId(int schoolCodeId) {
		String getByCodeQuery = "SELECT * FROM schoolcode WHERE code='"
				+ schoolCodeId + "'";
		return getSchoolCodeBooks(getByCodeQuery);
	}

	private List<SchoolCodeBook> getSchoolCodeBooks(String getQuery) {
		ResultSet rs = connManager.executeRead(getQuery);
		List<SchoolCodeBook> schoolCodeBookList = new ArrayList<SchoolCodeBook>();
		try {
			while (rs.next()) {
				SchoolCodeBook schoolCodeBook = new SchoolCodeBook(
						rs.getString("code"), rs.getInt("groupNo"),
						rs.getInt("schoolCodeId"));
				schoolCodeBookList.add(schoolCodeBook);
			}
			return schoolCodeBookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
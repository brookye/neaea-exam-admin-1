package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.utilities.ConnManager;

public class SchoolDAO {
	private ConnManager connManager;

	public SchoolDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(School school) {
		String persistQuery = "INSERT INTO school VALUES('" + school.getCode()
				+ "',NULL" + ",'" + school.getSchoolName() + "',"
				+ school.getWoreda().getWoredaId() + ")";
		System.out.println(persistQuery);
		connManager.executeCUD(persistQuery);
	}
    
	public void delete(School school) {
		String delQuery = "DELETE FROM school WHERE code='" + school.getCode()
				+ "'";
		connManager.executeCUD(delQuery);
	}

	public void update(School school) {
		String updateQuery = "UPDATE school SET groupNo="
				+ school.getExamCenter().getGroupNo() + " WHERE code='"
				+ school.getCode() + "'";
		System.out.println("INFO:"+updateQuery);
		connManager.executeCUD(updateQuery);
	}

	public List<School> getByCode(String code) {
		String getByCodeQuery = "SELECT * FROM school WHERE code='" + code
				+ "'";
		return getSchool(getByCodeQuery);

	}

	public List<School> getByWoredaId(int woredaId) {
		String getByCodeQuery = "SELECT * FROM school WHERE woreda_id="
				+ woredaId;
		return getSchool(getByCodeQuery);

	}

	public List<School> getAll() {
		String query = "SELECT * FROM school";
		return getSchool(query);
	}

	public List<School> getBySchoolCodeId(int code) {
		String getByCodeQuery = "SELECT * FROM school WHERE code=" + code;
		return getSchool(getByCodeQuery);
	}

	private List<School> getSchool(String getQuery) {
		WoredaDAO woredaDAO = new WoredaDAO(connManager);
		ExamCenterDAO examCenterDAO = new ExamCenterDAO(connManager);
		ResultSet rs = connManager.executeRead(getQuery);
		List<School> schools = new ArrayList<School>();
		try {
			while (rs.next()) {
				Woreda woreda = woredaDAO.getById(rs.getInt("woreda_id"))
						.get(0);
				ExamCenter examCenter;
				rs.getInt("groupNo");
				if (!rs.wasNull()) {
					examCenter = examCenterDAO.getByGroupNo(
							rs.getInt("groupNo")).get(0);
				} else {
					examCenter = null;
				}
				School school = new School(rs.getString("code"), examCenter,
						rs.getString("school_name"), woreda);
				schools.add(school);
			}
			return schools;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
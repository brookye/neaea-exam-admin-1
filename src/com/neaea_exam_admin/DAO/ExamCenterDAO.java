package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;

public class ExamCenterDAO {
	private ConnManager connManager;

	public ExamCenterDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(ExamCenter examCenter) {
		String query = "INSERT INTO examcenter VALUES("
				+ examCenter.getGroupNo() + ",'"
				+ examCenter.getSchool().getCode() + "',"
				+ examCenter.getNoOfClassRooms() + ","
				+ examCenter.getDistance() + ")";
		System.out.println("INFO:" + query);
		connManager.executeCUD(query);
	}
     
	public List<ExamCenter> getByGroupNo(int groupNo) {
		String query = "SELECT * FROM examcenter WHERE groupNo=" + groupNo;
		return getExamCenter(query);
	}

	public List<ExamCenter> getAll() {
		String query = "SELECT * FROM examcenter";
		return getExamCenter(query);
	}

	public List<ExamCenter> getByWoreda(int woredaId) {
		List<ExamCenter> allExamCenters = getAll();
		List<ExamCenter> filteredExamCenter = new ArrayList<ExamCenter>();

		for (ExamCenter e : allExamCenters) {
			if (e.getSchool().getWoreda().getWoredaId() == woredaId) {
				filteredExamCenter.add(e);
			}
		}

		return filteredExamCenter;
	}

	private List<ExamCenter> getExamCenter(String query) {
		SchoolDAO schoolDAO = new SchoolDAO(connManager);
		List<ExamCenter> examCenters = new ArrayList<ExamCenter>();
		ResultSet rs = connManager.executeRead(query);
		try {
			while (rs.next()) {
				School school = schoolDAO
						.getByCode(rs.getString("school_code")).get(0);
				ExamCenter examCenter = new ExamCenter(school,
						rs.getInt("groupNo"), rs.getFloat("distance"),
						rs.getInt("no_of_classroom"));
				examCenters.add(examCenter);
			}
			return examCenters;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

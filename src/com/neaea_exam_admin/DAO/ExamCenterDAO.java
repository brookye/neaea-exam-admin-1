package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.ExamCenter;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.entity.Woreda;

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

	// this method was created to avoid the stackoverflow exception due to
	// cycling calls
	// between SchoolDAO.getByCode() and getExamCenter.Since ExamCenter exists
	// after school
	// according to the business rule,we can use this explicit knowledge
	private School getMySchool(String schoolCode) {
		String query = "SELECT * FROM school WHERE code='" + schoolCode + "'";
		ResultSet rs = connManager.executeRead(query);
		WoredaDAO woredaDAO = new WoredaDAO(connManager);
		School school = null;
		try {
			while (rs.next()) {
				Woreda woreda = woredaDAO.getById(rs.getInt("woreda_id"))
						.get(0);
				school = new School(rs.getString("code"), null,
						rs.getString("school_name"), woreda);
			}
			return school;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<ExamCenter> getExamCenter(String query) {
		List<ExamCenter> examCenters = new ArrayList<ExamCenter>();
		ResultSet rs = connManager.executeRead(query);
		try {
			while (rs.next()) {
				// This was where the problem was let to start
				// School school = schoolDAO
				// .getByCode(rs.getString("school_code")).get(0);
				School school = getMySchool(rs.getString("school_code"));
				ExamCenter examCenter = new ExamCenter(school,
						rs.getInt("groupNo"), rs.getFloat("distance"),
						rs.getInt("no_of_classroom"));
				// this is where the last magic of the fix happens as the
				// remedy of
				// the infinite in b/n recursive calling
				examCenter.getSchool().setExamCenter(examCenter);
				// just as always now
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

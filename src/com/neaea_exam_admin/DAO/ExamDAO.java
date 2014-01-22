package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Exam;
import com.neaea_exam_admin.utilities.ConnManager;

public class ExamDAO {
	private ConnManager connManager;

	public ExamDAO(ConnManager _connmanager) {
		connManager = _connmanager;
	}

	public void persist(Exam exam) {
		String persistQuery = "INSERT INTO exam VALUES(name='" + exam.getName()
				+ "',id=NULL";
		connManager.executeCUD(persistQuery);
	}

	public void delete(Exam exam) {
		String deleteQuery = "DELETE FROM exam WHERE id=" + exam.getId();
		connManager.executeCUD(deleteQuery);
	}

	public void update(Exam exam) {
		String updateQuery = "UPDATE exam SET name='" + exam.getName()
				+ "' WHERE id=" + exam.getId();
		connManager.executeCUD(updateQuery);
	}
    public List<Exam> getExamById(int id){
    	String getQuery="SELECT FORM exam WHERE id='"+id+"'";
    	return getExam(getQuery);
    }
	public List<Exam> getExam(String getQuery) {
		ResultSet rs = connManager.executeRead(getQuery);
		List<Exam> exams = new ArrayList<Exam>();
		try {
			while (rs.next()) {
				Exam exam = new Exam(rs.getInt("id"), rs.getString("name"));
				exams.add(exam);
			}
			return exams;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
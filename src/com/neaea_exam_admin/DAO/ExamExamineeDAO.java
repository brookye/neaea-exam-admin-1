package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Exam;
import com.neaea_exam_admin.entity.ExamExaminee;
import com.neaea_exam_admin.entity.Examinee;
import com.neaea_exam_admin.utilities.ConnManager;

public class ExamExamineeDAO {
	private ConnManager connManager;

	public ExamExamineeDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(ExamExaminee examExaminee) {
		String persistQuery = "INSERT INTO neaeaexamadmin.exam_examinee VALUES(NULL,"
				+ examExaminee.getExamId().getId()
				+ ","
				+ examExaminee.getExaminee().getExamineeId() + ")";
		System.out.println("Executing-" + persistQuery);
		connManager.executeCUD(persistQuery);
	}

	public void delete(ExamExaminee examExaminee) {
		String delQuery = "DELETE FROM exam_examinee WHERE id='"
				+ examExaminee.getId() + "'";
		connManager.executeCUD(delQuery);
	}

	public void update(ExamExaminee examExaminee) {
		String updateQuery = "UPDATE exam_examinee SET examineeId='"
				+ examExaminee.getExaminee().getExamineeId() + "',examId='"
				+ examExaminee.getExamId().getId() + "'";
		connManager.executeCUD(updateQuery);
	}

	public List<ExamExaminee> getExamExamineeById(int id) {
		String getQuery = "SELECT FROM exam_examinee WHERE id='" + id;
		return getExamExaminee(getQuery);
	}
public List<ExamExaminee> getExamExamineeByExaminee(Examinee examinee){
	String getQuery="SELECT * FROM exam_examinee WHERE examineeId="+examinee.getExamineeId();
	return getExamExaminee(getQuery);
}
	private List<ExamExaminee> getExamExaminee(String getQuery) {
		List<ExamExaminee> examExaminees = new ArrayList<ExamExaminee>();
		ResultSet rs = connManager.executeRead(getQuery);
		ExamDAO examDAO = new ExamDAO(connManager);
		ExamineeDAO examineeDAO = new ExamineeDAO(connManager);

		try {
			while (rs.next()) {
				int examId = rs.getInt("examId");
				// since examId is a PK, the list will always have only one Exam
				// object
				Exam exam = examDAO.getExamById(examId).get(0);
				int examineeId = rs.getInt("examineeId");
				// since examineeId is a PK, the list will always have only one
				// Examinee object
				Examinee examinee = examineeDAO.getById(examineeId).get(0);
				ExamExaminee examExaminee = new ExamExaminee(examinee, exam,
						rs.getInt("id"));
				examExaminees.add(examExaminee);
			}
			return examExaminees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

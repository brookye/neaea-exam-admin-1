package com.neaea_exam_admin.DAO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Examinee;
import com.neaea_exam_admin.entity.School;
import com.neaea_exam_admin.utilities.ConnManager;

public class ExamineeDAO {
	private ConnManager connManager;

	public ExamineeDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(Examinee examinee) {
		// TODO It will be better to prepare wrapper class for
		// the PreparedStatement so that closing is automatically handled
		String persistQuery = "INSERT INTO examinee (registrationConfirmationNo,photo,category,"
				+ "nationality,Sight,sex,age,schoolCodeId,grandFatherName,fatherName,name"
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = connManager.getPreparedStatement(persistQuery);
		InputStream inp = new ByteArrayInputStream(examinee.getPhoto());
		try {
			pst.setString(1, examinee.getRegistrationConfirmationNo());
			pst.setBinaryStream(2, inp, examinee.getPhoto().length);
			pst.setInt(3, examinee.getCatagory().getId());
			pst.setString(4, examinee.getNationality());
			pst.setString(5, examinee.getSight());
			pst.setString(6, examinee.getSex());
			pst.setInt(7, examinee.getAge());
			//pst.setInt(8, examinee.getSchoolCode().getSchoolCodeId());
			pst.setString(9, examinee.getGrandFatherName());
			pst.setString(10, examinee.getFatherName());
			pst.setString(11, examinee.getName());
			pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Examinee examinee) {
		String delQuery = "DELETE FROM examinee WHERE examineeId="
				+ examinee.getExamineeId();
		connManager.executeCUD(delQuery);
	}

	public void update(Examinee examinee) {
		String updateQuery = "UPDATE examinee SET examineeId= registrationConfirmationNo= photo= catagory="
				+ "nationality= Sight= sex= age= schoolCodeId= grandFatherName= fatherName= name=";
		connManager.executeCUD(updateQuery);
	}

	public List<Examinee> getByRegConfNo(String regConfNo) {
		String getQuery = "SELECT * FROM examinee WHERE registrationConfirmationNo='"
				+ regConfNo + "'";
		return getExaminees(getQuery);
	}

	/**
	 * returns all results with names similar using the like query
	 * 
	 * @param name
	 * @return List of similar examinees
	 */
	public List<Examinee> getByName(String name) {
		String getQuery = "SELECT * FROM examinee WHERE name LIKE '" + name
				+ "%'";
		return getExaminees(getQuery);
	}

	public List<Examinee> getById(int examineeId) {
		String getQuery = "SELECT * FROM examinee WHERE examineeId="
				+ examineeId;
		return getExaminees(getQuery);
	}

	private List<Examinee> getExaminees(String getQuery) {

		List<Examinee> examinees = new ArrayList<Examinee>();
		ResultSet rs = connManager.executeRead(getQuery);
		try {
			while (rs.next()) {
				School school = new SchoolDAO(connManager)
						.getBySchoolCodeId(rs.getInt("schoolCodeId")).get(0);
				CategoryDAO categoryDAO = new CategoryDAO(connManager);

				/*Examinee examinee = new Examinee(rs.getString("name"),
						rs.getString("fatherName"),
						rs.getString("grandFatherName"), school,
						rs.getInt("age"), rs.getString("sex"),
						rs.getString("Sight"), rs.getString("nationality"),
						categoryDAO.getById(rs.getInt("category")).get(0), rs
								.getBlob("photo").getBytes(1,
										(int) rs.getBlob("photo").length()),
						rs.getString("registrationConfirmationNo"),
						rs.getInt("examineeId"));
				examinees.add(examinee);*/
			}

			return examinees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

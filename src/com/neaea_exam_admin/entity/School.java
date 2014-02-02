package com.neaea_exam_admin.entity;

public class School {

	private String code; // PK
	private ExamCenter examCenter;
	private String schoolName; // PK
	private Woreda woreda;

	public School(String _code, ExamCenter _examCenter, String _schoolName, Woreda _woreda) {
		code = _code;
		examCenter = _examCenter;
		schoolName = _schoolName;
		woreda = _woreda;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public ExamCenter getExamCenter() {
		return examCenter;
	}

	public void setExamCenter(ExamCenter examCenter) {
		this.examCenter = examCenter;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Woreda getWoreda() {
		return woreda;
	}

	public void setWoreda(Woreda woreda) {
		this.woreda = woreda;
	}

}

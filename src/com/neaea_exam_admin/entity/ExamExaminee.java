package com.neaea_exam_admin.entity;

public class ExamExaminee {
	private Examinee examineeId;
	private Exam examId;
	private int id;

	public ExamExaminee(Examinee _examineeId, Exam _examId, int _id) {
		examineeId = _examineeId;
		examId = _examId;
		id = _id;
	}

	public Examinee getExaminee() {
		return examineeId;
	}

	public void setExamineeId(Examinee examineeId) {
		this.examineeId = examineeId;
	}

	public Exam getExamId() {
		return examId;
	}

	public void setExamId(Exam examId) {
		this.examId = examId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

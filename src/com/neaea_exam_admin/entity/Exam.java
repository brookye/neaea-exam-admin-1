package com.neaea_exam_admin.entity;

public class Exam {
	private int id;
	private String name;

	public Exam(int _id,String _name) {
		id=_id;
		name = _name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

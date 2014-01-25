package com.neaea_exam_admin.entity;

public class Category {
    private int id;
	private String name;

	public Category(int _id,String _name){
    	id=_id;
    	name=_name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}

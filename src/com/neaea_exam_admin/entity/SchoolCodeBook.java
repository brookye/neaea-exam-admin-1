package com.neaea_exam_admin.entity;

public class SchoolCodeBook {
	

	private String code; // PK
	private int groupNo;
	private String schoolCodeId; // PK	
    
	public SchoolCodeBook(String _code, int _groupNo,String _schoolCodeId) {
		code = _code;
		groupNo = _groupNo;
		schoolCodeId=_schoolCodeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getSchoolCodeId() {
		return schoolCodeId;
	}

	public void setSchoolCodeId(String schoolCodeId) {
		this.schoolCodeId = schoolCodeId;
	}
	
	
}

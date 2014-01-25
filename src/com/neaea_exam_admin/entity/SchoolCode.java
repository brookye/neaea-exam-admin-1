package com.neaea_exam_admin.entity;

public class SchoolCode {
	

	private String code; // PK
	private int groupNo;
	private int schoolCodeId; // PK	
    
	public SchoolCode(String _code, int _groupNo,int _schoolCodeId) {
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

	public int getSchoolCodeId() {
		return schoolCodeId;
	}

	public void setSchoolCodeId(int schoolCodeId) {
		this.schoolCodeId = schoolCodeId;
	}
	
	
}

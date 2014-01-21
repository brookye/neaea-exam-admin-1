package com.neaea_exam_admin.entity;

public class Examinee {
	private String name;
	private String fatherName;
	private String grandFatherName;
	private SchoolCodeBook schoolCode; // FK
	private int age;
	private String sex;
	private String sight;
	private String nationality;
	private byte[] photo;
	private String registrationConfirmationNo; // PK	
	private String catagory;

	public Examinee(String _name, String _fatherName, String _grandFatherName,
			SchoolCodeBook _schoolCode, int _age, String _sex, String _sight,
			String _nationality, String _catagory, byte[] _photo,
			String _registrationConfiramtinNo) {
            name=_name;
            fatherName=_fatherName;
            grandFatherName=_grandFatherName;
            schoolCode=_schoolCode;
            age=_age;
            sex=_sex;
            sight=_sight;
            nationality=_nationality;
            photo=_photo;
            registrationConfirmationNo=_registrationConfiramtinNo;
            catagory=_catagory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGrandFatherName() {
		return grandFatherName;
	}

	public void setGrandFatherName(String grandFatherName) {
		this.grandFatherName = grandFatherName;
	}

	public SchoolCodeBook getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(SchoolCodeBook schoolCode) {
		this.schoolCode = schoolCode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSight() {
		return sight;
	}

	public void setSight(String sight) {
		this.sight = sight;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCategory() {
		return catagory;
	}

	public void setCategory(String category) {
		this.catagory = category;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getRegistrationConfirmationNo() {
		return registrationConfirmationNo;
	}

	public void setRegistrationConfirmationNo(String registrationConfirmationNo) {
		this.registrationConfirmationNo = registrationConfirmationNo;
	}

}

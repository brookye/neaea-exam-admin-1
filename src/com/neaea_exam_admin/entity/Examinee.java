package com.neaea_exam_admin.entity;

public class Examinee {
	private String name;
	private String fatherName;
	private String grandFatherName;
	private School school; // FK
	private int age;
	private String sex;
	private String sight;
	private String nationality;
	private byte[] photo;
	private String registrationConfirmationNo;
	private Category category;
    private int examineeId;
	public Examinee(String _name, String _fatherName, String _grandFatherName,
			School _schoolCode, int _age, String _sex, String _sight,
			String _nationality, Category _catagory, byte[] _photo,
			String _registrationConfiramtinNo,int _examineeId) {
            name=_name;
            fatherName=_fatherName;
            grandFatherName=_grandFatherName;
            school=_schoolCode;
            age=_age;
            sex=_sex;
            sight=_sight;
            nationality=_nationality;
            photo=_photo;
            registrationConfirmationNo=_registrationConfiramtinNo;
            category=_catagory;
            examineeId=_examineeId;
	}

	public String getName() {
		return name;
	}

	public Category getCatagory() {
		return category;
	}

	public void setCatagory(Category category) {
		this.category = category;
	}

	public int getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(int examineeId) {
		this.examineeId = examineeId;
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

	

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

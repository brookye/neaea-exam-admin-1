package com.neaea_exam_admin.entity;

public class ExamCenter {
   private float distance;
   private int groupNo;
   private School school;
   private int noOfClassRooms;
   public ExamCenter(School _school,int _groupNo,float _distance,int _noOfClassRoom){
	   school=_school;
	   groupNo=_groupNo;
	   distance=_distance;
	   noOfClassRooms=_noOfClassRoom;
   }
public float getDistance() {
	return distance;
}
public void setDistance(float distance) {
	this.distance = distance;
}
public int getGroupNo() {
	return groupNo;
}
public void setGroupNo(int groupNo) {
	this.groupNo = groupNo;
}
public School getSchool() {
	return school;
}
public void setSchool(School school) {
	this.school = school;
}
public int getNoOfClassRooms() {
	return noOfClassRooms;
}
public void setNoOfClassRooms(int noOfClassRooms) {
	this.noOfClassRooms = noOfClassRooms;
}
}

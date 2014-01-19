package com.neaea_exam_admin.entity;

public class Examinee {
   public String name;
   public String fatherName;
   public String grandFatherName;
   public SchoolCodeBook schoolCode; //FK
   public int age;
   public String sex;
   public String sight;
   public String nationality;
   public String category;
   public byte [] photo;
   public String registrationConfirmationNumber; //PK 
}

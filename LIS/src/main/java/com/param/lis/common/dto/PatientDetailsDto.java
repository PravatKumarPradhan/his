package com.param.lis.common.dto;

import java.util.Date;

public class PatientDetailsDto {
  
  private Integer doctorId;
  private String firstName;
  private String lastName;
  private Date dob;
  private Integer age;
  private String gender;
  private String prefix;
  private String speciality;
  
  public Integer getDoctorId() {
    return doctorId;
  }
  public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public Date getDob() {
    return dob;
  }
  public void setDob(Date dob) {
    this.dob = dob;
  }
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getPrefix() {
    return prefix;
  }
  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
  public String getSpeciality() {
    return speciality;
  }
  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }
  
  
  

}

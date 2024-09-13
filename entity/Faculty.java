package com.universityproject.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Faculty {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String department;
private String email;
private String password;
private String facultyname;

public List<Student> getAssignedStudents() {
	return assignedStudents;
}
public void setAssignedStudents(List<Student> assignedStudents) {
	this.assignedStudents = assignedStudents;
}
@OneToMany(mappedBy = "faculty")
private List<Student> assignedStudents;

public String getFacultyname() {
	return facultyname;
}
public void setFacultyname(String facultyname) {
	this.facultyname = facultyname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


}
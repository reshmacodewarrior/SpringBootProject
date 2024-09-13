package com.universityproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
    private String email;
    private String assignedFacultyname;

    public String getAssignedFacultyname() {
		return assignedFacultyname;
	}
	public void setAssignedFacultyname(String assignedFacultyname) {
		this.assignedFacultyname = assignedFacultyname;
	}
	public Long getStudentid() {
		return studentid;
	}
	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}
	private String password;
    private String project;
    private String facultyname;
    private String projectDescription;
    private Long studentid;
   
    

	@Lob
	  @Column(columnDefinition="LONGBLOB")
     private byte[] projectPdf;
        
     @ManyToOne
     private Faculty faculty;

        public String getProjectDescription() {
			return projectDescription;
		}
		public void setProjectDescription(String projectDescription) {
			this.projectDescription = projectDescription;
		}
		public byte[] getProjectPdf() {
			return projectPdf;
		}
		public void setProjectPdf(byte[] projectPdf) {
			this.projectPdf = projectPdf;
		}
		public Faculty getFaculty() {
			return faculty;
		}
		public void setFaculty(Faculty faculty) {
			this.faculty = faculty;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		private String feedback;

        // Getters and Setters
    
   
	public String getFacultyname() {
		return facultyname;
	}
	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}

    
   
}





package com.universityproject.repository;


import com.universityproject.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	

	    Student findByEmail(String email); // Email-based lookup
	    List<Student> findAll();
	    List<Student> findByFacultyname(String facultyname);
	   




	    




}



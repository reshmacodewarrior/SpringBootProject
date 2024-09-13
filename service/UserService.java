package com.universityproject.service;

import com.universityproject.entity.Admin;
import com.universityproject.entity.Faculty;
import com.universityproject.entity.Student;
import com.universityproject.repository.AdminRepository;
import com.universityproject.repository.FacultyRepository;
import com.universityproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}


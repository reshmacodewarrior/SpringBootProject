package com.universityproject.service;

import com.universityproject.entity.Student;
import com.universityproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
 
@Service 
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        return optional.orElseThrow(() -> new RuntimeException("Student not found for id :: " + id));
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email); // Assuming your repository has this method
    }
   // Email-based lookup
    
   
 
    
    public void assignFacultyToStudent(Long studentId, String facultyname) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setFacultyname(facultyname);
          
            studentRepository.save(student);
        } else {
            // Handle student not found case
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
    }

 

       

    }


        
    
    

    

   

       
   


    

    

 



package com.universityproject.service;

import com.universityproject.entity.Faculty;
import com.universityproject.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }
  
  
    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Faculty getFacultyByEmail(String email) {
        return facultyRepository.findByEmail(email); // Assuming your repository has this method
    }
    
    }


    
    

    

 




package com.universityproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universityproject.entity.Feedback;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Add custom query methods if needed
    List<Feedback> findByFacultyId(Long facultyId); // Custom method
    Feedback findByStudentId(Long StudentId);
 
} 


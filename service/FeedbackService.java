package com.universityproject.service;

import java.util.List;

import com.universityproject.entity.Feedback;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacks();
    Feedback getFeedbackById(Long id);
    void deleteFeedbackById(Long id);
    List<Feedback> getFeedbacksByFacultyId(Long facultyId);
    Feedback getFeedbacksByStudentId(Long studentId);
    
}

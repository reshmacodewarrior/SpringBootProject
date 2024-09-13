package com.universityproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universityproject.entity.Feedback;
import com.universityproject.repository.FeedbackRepository;
 
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFeedbackById(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<Feedback> getFeedbacksByFacultyId(Long facultyId) {
        return feedbackRepository.findAll().stream()
                .filter(f -> f.getFacultyId().equals(facultyId))
                .toList();
        

    }
    @Override
    public Feedback getFeedbacksByStudentId(Long studentId) {
        return feedbackRepository.findByStudentId(studentId);
    }
    
}

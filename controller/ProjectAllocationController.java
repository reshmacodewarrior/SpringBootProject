package com.universityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.universityproject.service.StudentService;


    

	@Controller
	public class ProjectAllocationController {

	   

	        @Autowired
	        private StudentService studentService;

	        @GetMapping("/project-allocation") 
	        public String showProjectAllocationPage() {
	            return "project-allocation";
	        }

	        @PostMapping("/project-allocation/assign")
	        public String assignFaculty(@RequestParam("studentid") Long studentId,
	                                    @RequestParam("facultyname") String facultyname){
	            studentService.assignFacultyToStudent(studentId, facultyname);
	            return "redirect:/faculty";
	        }
	    }
	
    



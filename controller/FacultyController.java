package com.universityproject.controller;


import com.universityproject.entity.Faculty;
import com.universityproject.entity.Student;
import com.universityproject.service.FacultyService;
import com.universityproject.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listFaculty(Model model) {
        model.addAttribute("facultyList", facultyService.getAllFaculty());
        return "faculty";
    }

    @GetMapping("/add")
    public String addFacultyForm(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "add-faculty"; // Create add-faculty.html for this form
    }
 
    @PostMapping("/save")
    public String saveFaculty(@ModelAttribute("faculty") Faculty faculty) {
        facultyService.saveFaculty(faculty);
        return "redirect:/faculty";
    }

    @GetMapping("/edit/{id}")
    public String editFacultyForm(@PathVariable Long id, Model model) {
        Faculty faculty = facultyService.getFacultyById(id);
        model.addAttribute("faculty", faculty);
        return "edit-faculty"; // Create edit-faculty.html for this form
    }

    @PostMapping("/update/{id}")
    public String updateFaculty(@PathVariable Long id, @ModelAttribute("faculty") Faculty faculty) {
        Faculty existingFaculty = facultyService.getFacultyById(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setEmail(faculty.getEmail());
        existingFaculty.setPassword(faculty.getPassword());
        existingFaculty.setDepartment(faculty.getDepartment());
        facultyService.saveFaculty(existingFaculty);
        return "redirect:/faculty"; 
    }

    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty";
    }

    @GetMapping("/faculty-dashboard")
        public String showFacultyDashboard(@RequestParam("id") String id, @RequestParam("role") String role, Model model) {
            if (role.equals("faculty")) {
                Faculty faculty = facultyService.getFacultyByEmail(id);
                if (faculty != null) {
                    model.addAttribute("faculty", faculty);
                   List<Student> list=studentService.getAllStudents();
                   List<Student> student=new ArrayList();
                   System.out.println(faculty.getName());
                   System.out.println(id);
                   for(int i=0;i<list.size();i++) {
                	   if(faculty.getName().equals(list.get(i).getFacultyname())) {
                		   student.add(list.get(i));
                	   }
                   }
                   model.addAttribute("submissions",student);
                    return "faculty-dashboard"; // Render the faculty dashboard with faculty-specific data
                }
            }
            return "redirect:/login"; // Redirect to login if validation fails
        }

   

}

       

   



   
    
    

  

        
    
        
    
    


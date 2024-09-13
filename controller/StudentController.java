package com.universityproject.controller;

import com.universityproject.entity.Student;
import com.universityproject.service.StudentService;


//import java.awt.List;
//import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.lang.Long;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String viewStudentPage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // Returns the students.html page
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add-students"; // Returns the add-students.html page
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students"; // Redirect to the student list page after saving
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentService.getStudentById(id); // Corrected method name
        model.addAttribute("student", student);
        return "edit-students"; // Returns the edit-students.html page
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable(value = "id") Long id, @ModelAttribute("student") Student student) {
        Student existingStudent = studentService.getStudentById(id); // Corrected method name
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setProject(student.getProject());

        studentService.saveStudent(existingStudent);
        return "redirect:/students"; // Redirect to the student list page after updating
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students"; // Redirect to the student list page after deletion
    }
    @GetMapping("/student-dashboard")
    public String showStudentDashboard(@RequestParam("id") String id, @RequestParam("role") String role, Model model) {
        if (role.equals("student")) {
            Student student = studentService.getStudentByEmail(id);
            if (student != null) {
                model.addAttribute("student", student);
                return "student-dashboard"; // Render the student dashboard with faculty-specific data
            }
        }
        return "redirect:/login"; // Redirect to login if validation fails
    }
 // Logic when assigning a faculty to a student
   
    //@Autowired
   // private FeedbackService feedbackService;

    // Method to display the student dashboard
   /* @GetMapping("/student-dashboard")
    public String showStudentDashboard(Model model, Principal principal) {
        String studentEmail = principal.getName();
        Student student = studentService.getStudentByEmail(studentEmail);

        if (student != null) {
            model.addAttribute("student", student);

            // Fetch feedback for the student
            List<Feedback> feedbackList = feedbackService.getFeedbacksByStudentId(student.getId());
            model.addAttribute("feedbackList", feedbackList);
        }

        return "student-dashboard";
    }
*/
    // Method to handle project submission by the student
    @PostMapping("/project-insert")
    public String insertProjectDetails(@RequestParam("studentid")Long id,@RequestParam("facultyname")String name,@RequestParam("projectDescription")String description,@RequestParam("projectPdf")MultipartFile project,RedirectAttributes redirectAttributes,Model model) {
   	System.out.println(id);
   	 java.util.List<Student> list=studentService.getAllStudents();
   	 for(int i=0;i<list.size();i++) {
   		 System.out.println(list.get(i).getId());
   		 if(id.equals(list.get(i).getId())) {
   			
//   				System.out.println("sssssss");
   			 try {
   				 Student std=studentService.getStudentByEmail(list.get(i).getEmail());
       			 std.setProjectDescription(description);
				std.setProjectPdf(project.getBytes());
					 studentService.saveStudent(std);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   		
   		 }
   		 
   	 }
   	 return "/student-dashboard";
    }
}

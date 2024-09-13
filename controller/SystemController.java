package com.universityproject.controller;

import com.universityproject.entity.Faculty;
import com.universityproject.entity.Feedback;
import com.universityproject.entity.Student;
import com.universityproject.repository.UserId;
import com.universityproject.service.FacultyService;
import com.universityproject.service.FeedbackService;
import com.universityproject.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SystemController {

    @Autowired
    private FacultyService facultyService;

    @Autowired 
    private StudentService studentService;
    
    @Autowired 
    private FeedbackService feedbackService;

    @PostMapping("/login")
    public String login(@RequestParam("role") String role,
                        @RequestParam("id") String id,  // The ID field will now hold the email for faculty and student.
                        @RequestParam("password") String password,
                        Model model) {

        boolean isValid = false;

        switch (role) {
            case "admin":
                // Admin login checks email and password
                if ("admin@project.com".equals(id) && "admin123".equals(password)) {
                    isValid = true;
                    
                    return "redirect:/dashboard"; // Redirect to dashboard page after successful login
            
                }
                else {
                    model.addAttribute("error", "Invalid Email or Password");
                    return "login"; // Return to the login page if validation fails
                }
 
               
                 
            case "faculty":
                Faculty faculty = facultyService.getFacultyByEmail(id);
                if (faculty != null && faculty.getPassword().equals(password)) {
                    isValid = true;
                    UserId user=UserId.getInstance();
                    user.setMail(id);
                    return "redirect:/faculty/faculty-dashboard?id=" + id + "&role=faculty";
                } else {
                    model.addAttribute("error", "Invalid Email or Password for Faculty");
                    return "login";
                }



            case "student":
                Student student = studentService.getStudentByEmail(id);
                if (student != null && student.getPassword().equals(password)) {
                    isValid = true;
                    System.out.println(id);
                    UserId user=UserId.getInstance();
                    user.setMail(id);
                    
                    System.out.println(user.getMail());
                    return "redirect:/student-dashboard?id=" + id + "&role=student";
                } else {
                    model.addAttribute("error", "Invalid Email or Password for Student");
                    return "login";
                }

        }

        if (isValid) {
            return "redirect:/dashboard"; // Redirect to dashboard page after successful login
        } else {
            model.addAttribute("error", "Invalid Email or Password");
            return "login"; // Return to the login page if validation fails
        }
    }
    

//    @PostMapping("/project-insert")
//    public String insert() {
//    	System.out.println("1111");
//    	return "student-deashboard";
//    }

    @PostMapping("/project-insert")
    public String insertProjectDetails
           (@RequestParam("studentid")Long id,
    		@RequestParam("facultyname")String name,
    		@RequestParam("projectDescription")String description,
    		@RequestParam("projectPdf")MultipartFile project,
    		RedirectAttributes redirectAttributes,Model model) {
   	System.out.println(id);
   	 java.util.List<Student> list=studentService.getAllStudents();
   	 for(int i=0;i<list.size();) {
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
   		  break;
   		 }
   	   	 return "/student-dashboard";

   	 }
   	 return "/student-dashboard";
    }
    
    @GetMapping("/download-pdf/{studentId}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable("studentId") Long studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            byte[] pdfContent = student.getProjectPdf();

            if (pdfContent != null) {
                return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"project.pdf\"")
                    .body(pdfContent);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/feedback/{studentId}/{facultyId}")
    public String showFeedbackForm(@PathVariable("studentId") Long studentId,
                                   @PathVariable("facultyId") Long facultyId,
                                   Model model) {
        // Fetch student and faculty details (you may want to display names)
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("studentId",student.getId());
        model.addAttribute("studentname", student.getName());
        model.addAttribute("facultyId", facultyId);
        //Feedback feedBack=feedbackService.getFeedbackById(student.getId());
        //model.addAttribute("feedback",feedBack.getFeedback());

        return "feedback"; // feedback  fetch form  faculty page
    }
     
    @PostMapping("/submit-feedback")
    public String submitFeedback(@RequestParam("studentId") Long studentId,
                                 @RequestParam("facultyId") Long facultyId,
                                 @RequestParam("feedback") String feedback,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Fetch the student and faculty entities
            //Student student = studentService.getStudentById(studentId);
            //Faculty faculty = facultyService.getFacultyById(facultyId);

            // Create and save the feedback entity
            Feedback feedbackEntity = new Feedback();
            feedbackEntity.setStudentId(studentId);
            feedbackEntity.setFacultyId(facultyId);
            feedbackEntity.setFeedback(feedback);
            Student std=new Student();
            List<Student> student=studentService.getAllStudents();
            for(int i=0;i<student.size();i++) {
            	if(studentId.equals(student.get(i).getId())) {
            		std=student.get(i);
            		break;
            	}
            }
             std.setFeedback(feedback);
            studentService.saveStudent(std);
            feedbackService.saveFeedback(feedbackEntity);

            redirectAttributes.addFlashAttribute("successMessage", "Feedback submitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to submit feedback. Please try again.");
        }

        return "redirect:/feedback/" + studentId+ "/" + facultyId;   
    }
    
  @GetMapping("/student-dashboard")
  public String studentDashboard(Model model) {
      // Fetch feedback for the student
   
      List<Student> list=studentService.getAllStudents();
      Student student =new Student();
      UserId user=UserId.getInstance();
      Long id=(long) 0;
      System.out.println(user.getMail());
      String mail=user.getMail();    
      for(int i=0;i<list.size();i++) {
    	  if(mail.equals(list.get(i).getEmail())) {
    		  student=list.get(i);
    		  break;
    	  }
      }
      List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
      List<Feedback> feedback=new ArrayList<>();
      for(int i=0;i<feedbackList.size();i++) {
    	  if(feedbackList.get(i).getStudentId().equals(student.getId())) {
    		  feedback.add(feedbackList.get(i));
    	  }
      }
      // Add feedback to model
      System.out.println("Hiiii");
//      System.out.println(studentId);
//      System.out.println(feedbackList.getFeedback());
//      System.out.println( feedbackList.getStudentId());
      
      model.addAttribute("feedbackList", feedback);
      
      return "student-dashboard";
  }

//    @GetMapping("/student-dashboard/{id}")
//    public String studentDashboard(@PathVariable("id") Long studentId, Model model) {
//        // Fetch feedback for the student
//        Feedback feedbackList = feedbackService.getFeedbacksByStudentId(studentId);
//  
//  
//        // Add feedback to model
//        System.out.println("Hiiii");
//        System.out.println(studentId);
//        System.out.println(feedbackList.getFeedback());
//        System.out.println( feedbackList.getStudentId());
//        
//        model.addAttribute("feedbackList", feedbackList);
//        
//        return "student-dashboard";
//    }
    
    

}
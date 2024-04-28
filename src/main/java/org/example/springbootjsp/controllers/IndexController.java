package org.example.springbootjsp.controllers;

import jakarta.validation.Valid;
import org.example.springbootjsp.entity.Student;
import org.example.springbootjsp.repository.StudentRepository;
import org.example.springbootjsp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private StudentService service;


    private StudentRepository studentRepository;
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //    @GetMapping("/hien")
//    public String index(Model model) {
//        List<Student> listStudents = service.getAll();
//        model.addAttribute("students", listStudents );
//        return "index";
//    }
@GetMapping("/hien")
public String index(Model model , @RequestParam(defaultValue = "0") int page , @RequestParam(required = false) String search) {
    Page<Student> studentPage;
    if(search != null){
        studentPage = studentRepository.findStudentByNameContaining(search , PageRequest.of(page, 1));
        model.addAttribute("searchQuery", search);
    }else {
        studentPage = studentRepository.findAll(PageRequest.of(page, 1));
    }
    model.addAttribute("students", studentPage.getContent() );
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", studentPage.getTotalPages() );
    return "index";
}

    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@Valid Student student , BindingResult bindingResult , Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", getErrorMessages(bindingResult));
            return "add";
        }

        service.save(student);
        return "redirect:/hien";
    }

    @GetMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable Integer studentId) {
        Student student = service.findStudentById(studentId);
        service.delete(student);
        return "redirect:/hien";
    }


    @GetMapping("/updateStudent/{studentId}")
    public String editStudent(@PathVariable Integer studentId, Model model) {
        Student student = service.findStudentById(studentId);
        model.addAttribute("student", student);
        return "update";
    }

    @PostMapping("/editStudent/{studentId}")
    public String updateStudent(@PathVariable Integer studentId, @Valid Student studentUpdate , BindingResult bindingResult , Model model) {
        Student student = service.findStudentById(studentId);
        if (student != null) {
            if(bindingResult.hasErrors()){
                model.addAttribute("errors", getErrorMessages(bindingResult));
                return "update";
            }
            student.setName(studentUpdate.getName());
            student.setAge(studentUpdate.getAge());
            service.updateStudent(student);
        }
        return "redirect:/hien";
    }



    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

}

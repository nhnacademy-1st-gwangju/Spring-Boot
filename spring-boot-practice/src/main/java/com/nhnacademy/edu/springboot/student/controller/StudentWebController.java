package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.StudentService;
import com.nhnacademy.edu.springboot.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class StudentWebController {

    private final StudentService studentService;

    @GetMapping("/web/students/{id}")
    public String getStudent(@PathVariable Long id,
                             Model model){
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student";
    }
}

package com.nhnacademy.edu.springboot.student.domain;

import com.nhnacademy.edu.springboot.student.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NhnStudentServiceTest {

    @Autowired
    NhnStudentService nhnStudentService;

    @Test
    void getStudents() {
        // when
        List<Student> students = nhnStudentService.getStudents();

        // then
        assertThat(students).hasSize(2);
    }
}
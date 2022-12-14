package com.nhnacademy.edu.springboot.student.domain;

import com.nhnacademy.edu.springboot.student.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void testStudentRepository() throws Exception {
        //given
        Student ramos = new Student(10000L, "ramos", 100);
        studentRepository.save(ramos);

        //when
        Optional<Student> actual = studentRepository.findById(10000L);

        //then
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(ramos);
    }
}
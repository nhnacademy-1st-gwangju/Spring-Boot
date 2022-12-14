package com.nhnacademy.edu.springboot.student.domain;

import com.nhnacademy.edu.springboot.student.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositorySliceTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void test() throws Exception {
        Student ramos = new Student(10L, "Ramos", 100);
        entityManager.merge(ramos);

        Student student = studentRepository.findById(10L).orElse(null);
        assertThat(student).isEqualTo(ramos);
    }
}
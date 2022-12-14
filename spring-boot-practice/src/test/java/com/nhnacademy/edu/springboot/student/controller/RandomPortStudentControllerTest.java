package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.model.Student;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RandomPortStudentControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void testGetStudents() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Student> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Student>> exchange = testRestTemplate.exchange(
                "/students",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Student>>() {
                });

        assertThat(exchange.getBody())
                .contains(new Student(1L, "ramos", 100));
    }

    @Test
    @Order(2)
    void testGetStudent() {
        ResponseEntity<Student> result = testRestTemplate.getForEntity(
                "/students/{id}",
                Student.class,
                1L);

        assertThat(result.getBody())
                .isEqualTo(new Student(1L, "ramos", 100));
    }

    @Test
    @Order(3)
    void testCreateStudent() {
        Student student = new Student(100L, "sergio", 100);
        ResponseEntity<Student> result = testRestTemplate.postForEntity(
                "/students",
                student,
                Student.class);

        assertThat(result.getBody())
                .isEqualTo(student);
    }

    @Test
    @Order(4)
    void testDeleteStudent() {
        testRestTemplate.delete(
                "/students/{id}",
                100L);
    }
}
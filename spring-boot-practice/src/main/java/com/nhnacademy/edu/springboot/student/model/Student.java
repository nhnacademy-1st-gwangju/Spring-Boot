package com.nhnacademy.edu.springboot.student.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class Student {

    @Id
    private Long id;
    private String name;
    private Integer score;
}

package com.nhnacademy.openapi.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account {
    private Long id;
    private String number;
    private Integer balance;
}

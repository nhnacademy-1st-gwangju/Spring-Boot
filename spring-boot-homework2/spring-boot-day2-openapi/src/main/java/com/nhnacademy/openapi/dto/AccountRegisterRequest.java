package com.nhnacademy.openapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AccountRegisterRequest {
    private Long id;
    private String number;
    private Integer balance;
}

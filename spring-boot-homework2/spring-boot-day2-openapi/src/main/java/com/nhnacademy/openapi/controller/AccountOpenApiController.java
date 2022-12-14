package com.nhnacademy.openapi.controller;

import com.nhnacademy.openapi.domain.Account;
import com.nhnacademy.openapi.dto.AccountRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountOpenApiController {

    private final RestTemplate restTemplate;

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Value("${scheme}")
    private String scheme;

    // GET /accounts : 전체 조회
    @GetMapping
    public ApiResponse<List<Account>> findAllAccounts() {
        URI uri = UriComponentsBuilder.fromPath("/accounts")
                .scheme(scheme).host(host).port(port)
                .build(false).encode()
                .toUri();

        Account[] accounts = restTemplate.getForObject(uri, Account[].class);
        return ApiResponse.of(Arrays.asList(accounts));
    }

    // GET /accounts/{id} : 단건 조회
    @GetMapping("/{id}")
    public ApiResponse<Account> findById(@PathVariable Long id) {
        URI uri = UriComponentsBuilder.fromPath("/accounts/{id}")
                .scheme(scheme).host(host).port(port)
                .build(false).expand(id).encode()
                .toUri();

        Account account = restTemplate.getForObject(uri, Account.class);
        return ApiResponse.of(account);
    }

    // POST /accounts : 등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> registerAccount(@RequestBody AccountRegisterRequest request) {
        URI uri = UriComponentsBuilder.fromPath("/accounts")
                .scheme(scheme).host(host).port(port)
                .build(false).encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountRegisterRequest> httpEntity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Account.class);
    }

    // DELETE /accounts/{id} : 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        URI uri = UriComponentsBuilder.fromPath("/accounts/{id}")
                .scheme(scheme).host(host).port(port)
                .build(false).expand(id).encode()
                .toUri();

        restTemplate.delete(uri);

        return ResponseEntity.ok("delete success - account id : " + id);
    }
}

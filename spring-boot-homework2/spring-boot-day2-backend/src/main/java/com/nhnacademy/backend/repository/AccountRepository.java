package com.nhnacademy.backend.repository;

import com.nhnacademy.backend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

package com.wku.menumoa.repository;

import com.wku.menumoa.domain.EmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerifyRepository extends JpaRepository<EmailVerify,Long> {
    Optional<EmailVerify> findByUuid(String uuid);
}

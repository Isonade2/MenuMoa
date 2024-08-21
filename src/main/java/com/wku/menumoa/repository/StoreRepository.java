package com.wku.menumoa.repository;

import com.wku.menumoa.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByUserId(Long userId);
    List<Store> findByIsPostedTrue();
}

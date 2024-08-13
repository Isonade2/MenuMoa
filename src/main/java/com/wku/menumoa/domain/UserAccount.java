package com.wku.menumoa.domain;

import java.util.List;

public class UserAccount extends User{
    private Long userId;

    public UserAccount(Long id, String email, String password, String nickname, String role, String category, List<Store> stores, Long userId) {
        super(id, email, password, nickname, role, category, stores);
        this.userId = userId;
    }

    public UserAccount(Long userId) {
        this.userId = userId;
    }
}

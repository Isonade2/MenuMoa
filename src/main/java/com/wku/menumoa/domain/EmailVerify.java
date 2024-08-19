package com.wku.menumoa.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EmailVerify extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String uuid;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}

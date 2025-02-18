package com.portfolio.entity;

import com.portfolio.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role memberStatus;

    @Column(name = "member_id", length = 10)
    private String memberId;

    @Column(name = "member_password", length = 100)
    private String password;
}

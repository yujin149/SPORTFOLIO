package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry")
@Getter
@Setter
@ToString
public class Inquiry extends BaseEntity{
    @Id
    @Column(name="inquiry_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "inquiry_company", nullable = false, length = 25)
    private String company; // 회사명

    @Column(name = "inquiry_name", nullable = false, length = 25)
    private String name; // 담당자명

    @Column(name = "inquiry_email", nullable = false, length = 50)
    private String email; // 메일

    @Column(name = "inquiry_phone", nullable = false)
    private String phone; // 연락처

    @Column(name = "inquiry_title", nullable = false, length = 100)
    private String title; // 제목

    @Column(name = "inquiry_content", nullable = false)
    private String content; // 문의 내용



}



package com.portfolio.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryDto {
    private Long id;
    @NotBlank(message = "회사명을 입력해주세요.")
    private String company; //회사명

    @NotBlank(message = "담당자명을 입력해주세요.")
    private String name; // 담당자명

    @NotBlank(message = "연락 받으실 이메일을 입력해주세요.")
    private String email; //메일

    @NotBlank(message = "연락 받을실 번호를 입력해주세요.")
    private String phone; //연락처

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title; //제목

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content; //문의 내용
    private LocalDateTime regTime; // 작성일

    // 이메일 조합 메서드
    public void combineEmail(String email01, String email02) {
        this.email = email01 + "@" + email02;
    }

    // 전화번호 조합 메서드
    public void combinePhone(String phone1, String phone2, String phone3) {
        this.phone = phone1 + "-" + phone2 + "-" + phone3;
    }
}
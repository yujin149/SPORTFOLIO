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
    private String company; // 회사명

    @NotBlank(message = "담당자명을 입력해주세요.")
    private String name; // 담당자명

    @NotBlank(message = "연락 받으실 이메일을 입력해주세요.")
    private String email; // 이메일

    @NotBlank(message = "연락 받으실 번호를 입력해주세요.")
    private String phone; // 연락처

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title; // 제목

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content; // 문의 내용

    private LocalDateTime regTime; // 작성일

    // hiddenEmailDomain 필드 추가
    private String hiddenEmailDomain;  // 이메일 도메인을 hidden 필드에서 가져옴

    // 이메일을 합치는 메서드
    public void combineEmail(String email01, String email02) {
        if (email01 == null || email01.trim().isEmpty()) {
            this.email = null;
            return;
        }

        String domain;
        // email02가 있으면 그것을 사용
        if (email02 != null && !email02.trim().isEmpty()) {
            domain = email02;
        }
        // email02가 없고 hiddenEmailDomain이 있으면 그것을 사용
        else if (this.hiddenEmailDomain != null && !this.hiddenEmailDomain.trim().isEmpty()
                && !"직접입력".equals(this.hiddenEmailDomain)) {
            domain = this.hiddenEmailDomain;
        }
        // 둘 다 없으면 null 처리
        else {
            this.email = null;
            return;
        }

        this.email = email01.trim() + "@" + domain.trim();
    }

    // 전화번호를 합치는 메서드
    public void combinePhone(String phone1, String phone2, String phone3) {
        if (phone1 == null || phone2 == null || phone3 == null ||
            phone1.trim().isEmpty() || phone2.trim().isEmpty() || phone3.trim().isEmpty()) {
            this.phone = null;
            return;
        }

        this.phone = phone1.trim() + "-" + phone2.trim() + "-" + phone3.trim();
    }

}

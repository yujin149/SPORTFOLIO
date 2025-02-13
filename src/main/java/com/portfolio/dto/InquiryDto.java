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
        System.out.println("email01: " + email01);
        System.out.println("email02: " + email02);

        if (email01 != null && !email01.isEmpty()) {
            if (email02 != null && !email02.isEmpty()) {
                // email02가 입력된 경우
                this.email = email01 + "@" + email02;
            } else {
                // email02가 비어 있으면 hiddenEmailDomain에서 가져오기
                if (this.hiddenEmailDomain != null && !this.hiddenEmailDomain.isEmpty()) {
                    this.email = email01 + "@" + this.hiddenEmailDomain;  // emailDomain이 있다면 결합
                } else {
                    this.email = email01;  // emailDomain이 없으면 email01만 사용
                }
            }
            System.out.println("이메일 합치기 : " + this.email);  // 이메일 결합 후 값 확인
        }
    }



    // 전화번호를 합치는 메서드
    public void combinePhone(String phone1, String phone2, String phone3) {
        System.out.println("phone1: " + phone1);  // phone1 값 확인
        System.out.println("phone2: " + phone2);  // phone2 값 확인
        System.out.println("phone3: " + phone3);  // phone3 값 확인
        if (phone1 != null && !phone1.isEmpty() && phone2 != null && !phone2.isEmpty() && phone3 != null && !phone3.isEmpty()) {
            this.phone = phone1 + "-" + phone2 + "-" + phone3;
        } else {
            this.phone = "";  // 빈 값 처리
        }
        System.out.println("연락처 합치기 : " + this.phone);  // 전화번호 결합 후 값 확인
    }

 
}

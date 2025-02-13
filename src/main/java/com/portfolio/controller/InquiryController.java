package com.portfolio.controller;

import com.portfolio.dto.InquiryDto;
import com.portfolio.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping(value = "/inquiry")
    public String inquiry() {
        return "inquiry";
    }

    @PostMapping(value = "/inquiry")
    //html에서 name값을 받아옴
    public String inquirySubmit(@RequestParam("company") String company,
                               @RequestParam("name") String name,
                               @RequestParam("email01") String email01,
                               @RequestParam("email02") String email02,
                               @RequestParam("phone1") String phone1,
                               @RequestParam("phone2") String phone2,
                               @RequestParam("phone3") String phone3,
                               @RequestParam("title") String title,
                               @RequestParam("content") String content) {
        
        // InquiryDto 객체 생성 및 설정
        InquiryDto inquiryDto = new InquiryDto();
        inquiryDto.setCompany(company);
        inquiryDto.setName(name);
        inquiryDto.combineEmail(email01, email02);  // DTO에서 직접 이메일 조합
        inquiryDto.combinePhone(phone1, phone2, phone3);  // DTO에서 직접 전화번호 조합
        inquiryDto.setTitle(title);
        inquiryDto.setContent(content);

        // 문의사항 저장
        inquiryService.saveInquiry(inquiryDto);

        return "redirect:/"; // 메인 페이지로 리다이렉트
    }
}

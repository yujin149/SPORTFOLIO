package com.portfolio.controller;

import com.portfolio.dto.InquiryDto;
import com.portfolio.service.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping(value = "/inquiry")
    public String inquiry(Model model) {
        model.addAttribute("inquiryDto", new InquiryDto());
        return "inquiry";
    }

    @PostMapping(value = "/inquiry")
    public String inquirySubmit(@Valid InquiryDto inquiryDto, BindingResult bindingResult,
                                String email01, String email02,
                                String phone1, String phone2, String phone3) {

        // 이메일과 전화번호 결합
        inquiryDto.combineEmail(email01, email02);
        inquiryDto.combinePhone(phone1, phone2, phone3);

        // 유효성 검사에 실패하면 폼으로 다시 이동
        if (bindingResult.hasErrors()) {
            return "inquiry";
        }

        // 문의사항 저장
        inquiryService.saveInquiry(inquiryDto);

        return "redirect:/"; // 저장 후 홈으로 리디렉션
    }

}

package com.portfolio.controller;

import com.portfolio.dto.InquiryDto;
import com.portfolio.entity.Inquiry;
import com.portfolio.service.InquiryService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

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
    public String inquirySubmit(InquiryDto inquiryDto, BindingResult bindingResult,
                                String email01, String email02,
                                String phone1, String phone2, String phone3, String emailDomain) {

        log.info("Form submission - Email: {}, {}, {}", email01, email02, emailDomain);
        log.info("Form submission - Phone: {}, {}, {}", phone1, phone2, phone3);

        // 이메일과 전화번호 결합
        inquiryDto.setHiddenEmailDomain(emailDomain);
        inquiryDto.combineEmail(email01, email02);
        inquiryDto.combinePhone(phone1, phone2, phone3);

        log.info("Combined email: {}", inquiryDto.getEmail());
        log.info("Combined phone: {}", inquiryDto.getPhone());

        // 결합된 데이터에 대한 유효성 검사 수행
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<InquiryDto>> violations = validator.validate(inquiryDto);

        if (!violations.isEmpty()) {
            violations.forEach(violation -> {
                String propertyPath = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                bindingResult.rejectValue(propertyPath, "", message);
                log.error("Validation error - {}: {}", propertyPath, message);
            });
            return "inquiry";
        }

        // 문의사항 저장
        inquiryService.saveInquiry(inquiryDto);

        return "redirect:/"; // 저장 후 홈으로 리디렉션
    }


    /*문의리스트 - 관리자*/
    @GetMapping(value = "admin/inquiryList")
    public String inquiryList(Model model) {
        List<Inquiry> inquiries = inquiryService.getInquiry();
        model.addAttribute("inquiries", inquiries);
        return "inquiryList";
    }



}

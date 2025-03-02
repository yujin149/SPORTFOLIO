package com.portfolio.controller;

import com.portfolio.dto.InquiryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController {
    @GetMapping(value = "/about")
    public String about() {
        return "/about";
    }
}

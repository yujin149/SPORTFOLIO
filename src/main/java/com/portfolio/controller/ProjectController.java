package com.portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    @GetMapping(value = "/project")
    public String project() {
        return "/projects/list";
    }
    @GetMapping(value = "/project/write")
    public String projectWrite() {
        return "/projects/write";
    }
}

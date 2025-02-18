package com.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.portfolio.service.ProjectService;
import com.portfolio.entity.Project;

@Controller
public class MainController {
    
    private final ProjectService projectService;

    @Autowired
    public MainController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String main(Model model) {
        List<Project> projects = projectService.getProjects();  // 모든 프로젝트 리스트를 가져옴
        List<Project> noticeProjects = projectService.getNoticeProjects(); // NOTICE 상태의 프로젝트만 가져옴
        
        model.addAttribute("projects", projects);  // 모든 프로젝트
        model.addAttribute("noticeProjects", noticeProjects);  // NOTICE 프로젝트
        return "main";
    }
}

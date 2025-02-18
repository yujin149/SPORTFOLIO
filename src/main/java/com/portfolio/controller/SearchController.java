package com.portfolio.controller;


import com.portfolio.constant.ProjectCategoryStatus;
import com.portfolio.entity.Project;
import com.portfolio.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class SearchController {
    private final ProjectService projectService;

    public SearchController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/search")
    public String search(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false, defaultValue = "ALL") String category,
        Model model
    ) {
        // ProjectCategoryStatus를 enum으로 변환
        ProjectCategoryStatus categoryStatus = ProjectCategoryStatus.valueOf(category.toUpperCase());

        // 프로젝트 검색 실행
        List<Project> searchResults = projectService.searchProjects(keyword, categoryStatus);

        // 검색 결과를 모델에 추가
        model.addAttribute("projects", searchResults);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("totalResults", searchResults.size());

        return "search"; // search.html로 결과 반환
    }


}

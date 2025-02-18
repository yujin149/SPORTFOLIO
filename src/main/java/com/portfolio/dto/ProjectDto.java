package com.portfolio.dto;


import com.portfolio.constant.ProjectCategoryStatus;
import com.portfolio.dto.ProjectImgDto;
import lombok.Getter;
import lombok.Setter;
import com.portfolio.constant.ProjectStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String title;
    private String client;
    private String type;
    private String tool;
    private String concept;
    private String part;
    private String detail;
    private String url;
    private List<ProjectCategoryStatus> categories; //카테고리 목록
    private List<ProjectImgDto> projectImgList; //이미지 목록 (ProjectImgDto 형태로 변환)
    private Integer viewCount;
    private ProjectStatus projectStatus;


    public ProjectDto() {
        this.categories = new ArrayList<>();
        this.projectImgList = new ArrayList<>();
        this.projectStatus = ProjectStatus.GENERAL;
    }

    //카테고리 목록을 DisplayName으로 변환
    public String getCategoriesDisplayNames() {
        return categories.stream()
                .map(ProjectCategoryStatus::getDisplayName)
                .collect(Collectors.joining(", "));
    }
}

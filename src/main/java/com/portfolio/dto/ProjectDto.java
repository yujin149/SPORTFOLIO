package com.portfolio.dto;

import com.portfolio.entity.Project;

public class ProjectDto {
    private Long id;  // 프로젝트 ID
    private String title; // 프로젝트명
    private String category; // 프로젝트 카테고리
    private String client; // 클라이언트명
    private String type; // 타입
    private String tool; // 툴
    private String concept; // 컨셉
    private String part; // 참여도
    private String detail; // 설명
    private String url; //사이트 주소

    // 생성자: Project 엔티티에서 필요한 데이터만 가져오기
    public ProjectDto(Project project) {
        this.id=project.getId();
        this.title=project.getTitle();
        this.category=project.getCategory();
        this.client=project.getClient();
        this.type=project.getType();
        this.tool=project.getTool();
        this.concept=project.getConcept();
        this.part=project.getPart();
        this.detail=project.getDetail();
        this.url=project.getUrl();

    }
}

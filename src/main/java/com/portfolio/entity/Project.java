package com.portfolio.entity;

import com.portfolio.constant.ProjectStatus;
import com.portfolio.constant.ProjectCategoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
public class Project extends BaseEntity {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus; // 프로젝트 상태 설정

    @Column(name = "project_title", length = 50)
    private String title; // 프로젝트명

    @Column(name = "project_client", length = 50)
    private String client; // 클라이언트명

    @Column(name = "project_type", length = 50)
    private String type; // 타입

    @Column(name = "project_tool", length = 100)
    private String tool; // 툴

    @Column(name = "project_concept", length = 999)
    private String concept; // 컨셉

    @Column(name = "project_part")
    private String part; // 참여도

    @Column(name = "project_detail", length = 999)
    private String detail; // 설명

    @Column(name = "project_url")
    private String url; //사이트 주소

    @Column(name = "project_github")
    private String github; //깃 주소

    @Column(name = "project_notion")
    private String notion; //노션주소

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectImg> projectImgList = new ArrayList<>(); // 프로젝트에 관련된 이미지들

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "project_categories",
        joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "category")
    private List<ProjectCategoryStatus> categories = new ArrayList<>(); // 프로젝트 카테고리 목록

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int viewCount;

    public void incrementViewCount() {
        this.viewCount++;
    }

    //카테고리 목록을 DisplayName으로 변환
    public String getCategoriesDisplayNames() {
        return categories.stream()
            .map(ProjectCategoryStatus::getDisplayName)
            .collect(Collectors.joining(", "));
    }
}

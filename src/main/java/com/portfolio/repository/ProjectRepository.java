package com.portfolio.repository;

import com.portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.constant.ProjectStatus;
import com.portfolio.constant.ProjectCategoryStatus;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    //등록일 순으로 내림차순
    List<Project> findAllByOrderByRegTimeDesc();

    //공지인 게시글
    List<Project> findByProjectStatusOrderByRegTimeDesc(ProjectStatus projectStatus);

    //카테고리별 내림차순
    List<Project> findByCategoriesContainingOrderByRegTimeDesc(ProjectCategoryStatus category);

    //제목별 내림차순
    List<Project> findByTitleContainingOrderByRegTimeDesc(String title);

    //카테고리 및 제목
    List<Project> findByCategoriesContainingAndTitleContainingOrderByRegTimeDesc(
        ProjectCategoryStatus category, 
        String title
    );
}
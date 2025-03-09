package com.portfolio.repository;

import com.portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.portfolio.constant.ProjectStatus;
import com.portfolio.constant.ProjectCategoryStatus;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    //수정일 순으로 내림차순, 없으면 등록일 순으로 내림차순
    List<Project> findAllByOrderByUpdateTimeDescRegTimeDesc();

    //공지인 게시글
    List<Project> findByProjectStatusOrderByUpdateTimeDescRegTimeDesc(ProjectStatus projectStatus);

    //카테고리별 내림차순
    List<Project> findByCategoriesContainingOrderByUpdateTimeDescRegTimeDesc(ProjectCategoryStatus category);

    //제목별 내림차순
    List<Project> findByTitleContainingOrderByUpdateTimeDescRegTimeDesc(String title);

    //카테고리 및 제목
    List<Project> findByCategoriesContainingAndTitleContainingOrderByUpdateTimeDescRegTimeDesc(
        ProjectCategoryStatus category,
        String title
    );

    // 조회수 증가 (updateTime 변경 없이)
    @Modifying
    @Transactional
    @Query("UPDATE Project p SET p.viewCount = p.viewCount + 1 WHERE p.id = :projectId")
    void incrementViewCount(@Param("projectId") Long projectId);
}
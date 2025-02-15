package com.portfolio.repository;

import com.portfolio.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // 제목과 카테고리가 일치하는 프로젝트를 찾는 메서드
    List<Project> findByTitleAndCategory(String title, String category);

    // 제목으로만 검색하는 메서드 (카테고리가 "전체"일 때 사용)
    List<Project> findByTitle(String title);

    //등록일 기준 내림차순으로 페이징된 데이터 조회
    Page<Project> findAllByOrderByRegTimeDesc(Pageable pageable);
}

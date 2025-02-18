package com.portfolio.repository;

import com.portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.constant.ProjectStatus;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    //등록일 순으로 내림차순
    List<Project> findAllByOrderByRegTimeDesc();

    List<Project> findByProjectStatusOrderByRegTimeDesc(ProjectStatus projectStatus);
}
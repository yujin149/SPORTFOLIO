package com.portfolio.service;

import com.portfolio.constant.ProjectCategoryStatus;
import com.portfolio.dto.ProjectDto;
import com.portfolio.dto.ProjectImgDto;
import com.portfolio.entity.Project;
import com.portfolio.entity.ProjectImg;
import com.portfolio.repository.ProjectImgRepository;
import com.portfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import com.portfolio.constant.ProjectStatus;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectImgRepository projectImgRepository;
    private final FileService fileService;

    public ProjectService(ProjectRepository projectRepository, ProjectImgRepository projectImgRepository, FileService fileService) {
        this.projectRepository = projectRepository;
        this.projectImgRepository = projectImgRepository;
        this.fileService = fileService;
    }

    // 프로젝트 등록
    public Project writeProject(ProjectDto projectDto) {
        // ProjectDto를 Project 엔티티로 변환
        Project project = new Project();
        project.setProjectStatus(projectDto.getProjectStatus());
        project.setTitle(projectDto.getTitle());
        project.setClient(projectDto.getClient());
        project.setType(projectDto.getType());
        project.setTool(projectDto.getTool());
        project.setConcept(processContent(projectDto.getConcept()));
        project.setPart(processContent(projectDto.getPart()));
        project.setDetail(processContent(projectDto.getDetail()));
        project.setUrl(projectDto.getUrl());
        project.setCategories(projectDto.getCategories());
        project.setGithub(projectDto.getGithub());
        project.setNotion(projectDto.getNotion());

        // 프로젝트 등록
        Project savedProject = projectRepository.save(project);

        // 프로젝트 이미지 등록 (ProjectImgDto에서 ProjectImg로 변환하여 저장)
        for (ProjectImgDto projectImgDto : projectDto.getProjectImgList()) {
            ProjectImg projectImg = new ProjectImg();
            projectImg.setProject(savedProject);
            projectImg.setImgName(projectImgDto.getImgName());
            projectImg.setOriImgName(projectImgDto.getOriImgName());
            projectImg.setImgUrl(projectImgDto.getImgUrl());
            projectImg.setImageType(projectImgDto.getImageType());

            // 이미지를 정적 리소스 경로로 복사
            try {
                fileService.copyToStaticResource(projectImgDto.getImgName());
            } catch (IOException e) {
                throw new RuntimeException("Failed to copy image to static resource location", e);
            }

            projectImgRepository.save(projectImg);
        }

        return savedProject; // 등록된 프로젝트 반환
    }

    // HTML 컨텐츠를 처리하는 메소드
    public String processContent(String content) {
        if (content == null) {
            return "";
        }
        return content.replaceAll("(?i)<(?!/?span)[^>]*>", ""); // span 태그만 허용
    }

    //리스트 가져오기
    public List<Project> getProjects() {
        return projectRepository.findAllByOrderByUpdateTimeDescRegTimeDesc();
    }

    public List<Project> getNoticeProjects() {
        return projectRepository.findByProjectStatusOrderByUpdateTimeDescRegTimeDesc(ProjectStatus.NOTICE);
    }

    public Project getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        // 조회수 증가를 별도의 쿼리로 처리
        incrementViewCount(projectId);

        return project;
    }

    // 조회수 증가를 위한 별도 메서드
    private void incrementViewCount(Long projectId) {
        projectRepository.incrementViewCount(projectId);
    }

    public Project updateProject(Long projectId, ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        // 기본 정보 업데이트
        project.setProjectStatus(projectDto.getProjectStatus());
        project.setTitle(projectDto.getTitle());
        project.setClient(projectDto.getClient());
        project.setType(projectDto.getType());
        project.setTool(projectDto.getTool());
        project.setConcept(processContent(projectDto.getConcept()));
        project.setPart(processContent(projectDto.getPart()));
        project.setDetail(processContent(projectDto.getDetail()));
        project.setUrl(projectDto.getUrl());
        project.setCategories(projectDto.getCategories());
        project.setGithub(projectDto.getGithub());
        project.setNotion(projectDto.getNotion());

        // 이미지 업데이트
        if (projectDto.getProjectImgList() != null && !projectDto.getProjectImgList().isEmpty()) {
            // 기존 이미지 삭제
            for (ProjectImg oldImg : project.getProjectImgList()) {
                fileService.deleteFile(oldImg.getImgName());
            }
            project.getProjectImgList().clear();

            // 새 이미지 추가
            for (ProjectImgDto imgDto : projectDto.getProjectImgList()) {
                ProjectImg projectImg = new ProjectImg();
                projectImg.setProject(project);
                projectImg.setImgName(imgDto.getImgName());
                projectImg.setOriImgName(imgDto.getOriImgName());
                projectImg.setImgUrl(imgDto.getImgUrl());
                projectImg.setImageType(imgDto.getImageType());

                try {
                    fileService.copyToStaticResource(imgDto.getImgName());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to copy image to static resource location", e);
                }

                project.getProjectImgList().add(projectImg);
            }
        }

        return projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        // 프로젝트 이미지 파일들 삭제
        for (ProjectImg img : project.getProjectImgList()) {
            fileService.deleteFile(img.getImgName());
        }

        projectRepository.delete(project);
    }

    // 제목과 카테고리로 검색
    public List<Project> searchProjects(String keyword, ProjectCategoryStatus category) {
        if (category != null && !category.equals(ProjectCategoryStatus.ALL)) {
            if (keyword != null && !keyword.isEmpty()) {
                return projectRepository.findByCategoriesContainingAndTitleContainingOrderByUpdateTimeDescRegTimeDesc(category, keyword);
            } else {
                return projectRepository.findByCategoriesContainingOrderByUpdateTimeDescRegTimeDesc(category);
            }
        } else if (keyword != null && !keyword.isEmpty()) {
            return projectRepository.findByTitleContainingOrderByUpdateTimeDescRegTimeDesc(keyword);
        }
        // 카테고리나 제목이 비어있으면 전체 검색
        return projectRepository.findAllByOrderByUpdateTimeDescRegTimeDesc();
    }



}
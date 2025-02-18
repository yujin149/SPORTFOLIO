package com.portfolio.service;

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
        // XSS 방지를 위한 기본적인 검증이 필요할 수 있습니다.
        // 여기서는 간단히 허용된 태그만 통과시키는 예시를 들겠습니다.
        return content.replaceAll("(?i)<(?!/?span)[^>]*>", ""); // span 태그만 허용
    }

    //리스트 가져오기
    public List<Project> getProjects() {
        return projectRepository.findAllByOrderByRegTimeDesc();
    }

    public List<Project> getNoticeProjects() {
        return projectRepository.findByProjectStatusOrderByRegTimeDesc(ProjectStatus.NOTICE);
    }

}
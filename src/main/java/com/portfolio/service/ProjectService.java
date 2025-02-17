package com.portfolio.service;

import com.portfolio.dto.ProjectDto;
import com.portfolio.dto.ProjectImgDto;
import com.portfolio.entity.Project;
import com.portfolio.entity.ProjectImg;
import com.portfolio.repository.ProjectImgRepository;
import com.portfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectImgRepository projectImgRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectImgRepository projectImgRepository) {
        this.projectRepository = projectRepository;
        this.projectImgRepository = projectImgRepository;
    }

    // 프로젝트 등록
    public Project writeProject(ProjectDto projectDto) {
        // ProjectDto를 Project 엔티티로 변환
        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setClient(projectDto.getClient());
        project.setType(projectDto.getType());
        project.setTool(projectDto.getTool());
        project.setConcept(projectDto.getConcept());
        project.setPart(projectDto.getPart());
        project.setDetail(projectDto.getDetail());
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
            projectImgRepository.save(projectImg);
        }

        return savedProject; // 등록된 프로젝트 반환
    }

}

package com.portfolio.controller;

import com.portfolio.dto.ProjectDto;
import com.portfolio.entity.Project;
import com.portfolio.service.FileService;
import com.portfolio.service.ProjectService;
import com.portfolio.dto.ProjectImgDto;
import com.portfolio.constant.ProjectImgStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final FileService fileService;
    @Value("${projectImgLocation}")
    private String uploadPath;

    @Autowired
    public ProjectController(ProjectService projectService, FileService fileService) {
        this.projectService = projectService;
        this.fileService = fileService;
    }

    @GetMapping("project")
    public String list() {
        return "/projects/list";
    }

    @GetMapping("project/write")
    public String write() {
        return "/projects/write";
    }
    @PostMapping("/write")
    public String writeProject(
        @ModelAttribute ProjectDto projectDto,
        @RequestParam("projectImgFile") List<MultipartFile> projectImgFiles) throws IOException, Exception {
        
        // 이미지 처리 및 ProjectImgDto 생성
        List<ProjectImgDto> projectImgDtoList = new ArrayList<>();
        
        for (int i = 0; i < projectImgFiles.size(); i++) {
            MultipartFile file = projectImgFiles.get(i);
            if (!file.isEmpty()) {
                ProjectImgDto imgDto = new ProjectImgDto();
                
                // 파일 저장 및 URL 생성
                String originalFilename = file.getOriginalFilename();
                String savedFileName = fileService.uploadFile(uploadPath, originalFilename, file.getBytes());
                String imageUrl = "/images/project/" + savedFileName;
                
                imgDto.setImgName(savedFileName);
                imgDto.setOriImgName(originalFilename);
                imgDto.setImgUrl(imageUrl);
                
                // 이미지 타입 설정
                ProjectImgStatus imageType = switch (i) {
                    case 0 -> ProjectImgStatus.MAINBANNER;
                    case 1 -> ProjectImgStatus.PREVIEW;
                    case 2 -> ProjectImgStatus.MAINPAGE;
                    case 3 -> ProjectImgStatus.SUBPAGE1;
                    case 4 -> ProjectImgStatus.SUBPAGE2;
                    case 5 -> ProjectImgStatus.DETAIL1;
                    case 6 -> ProjectImgStatus.DETAIL2;
                    default -> ProjectImgStatus.ETC;
                };
                imgDto.setImageType(imageType);
                
                projectImgDtoList.add(imgDto);
            }
        }
        
        projectDto.setProjectImgList(projectImgDtoList);
        
        // 프로젝트 저장
        Project savedProject = projectService.writeProject(projectDto);
        
        return "redirect:/project";
    }
}

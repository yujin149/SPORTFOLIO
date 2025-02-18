package com.portfolio.controller;

import com.portfolio.dto.ProjectDto;
import com.portfolio.entity.Project;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.service.FileService;
import com.portfolio.service.ProjectService;
import com.portfolio.dto.ProjectImgDto;
import com.portfolio.constant.ProjectImgStatus;
import com.portfolio.constant.ProjectCategoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.portfolio.entity.ProjectImg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final FileService fileService;
    private final ProjectRepository projectRepository;
    @Value("${projectImgLocation}")
    private String uploadPath;

    @Autowired
    public ProjectController(ProjectService projectService, FileService fileService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.fileService = fileService;
        this.projectRepository = projectRepository;
    }

    @GetMapping("project")
    public String list(Model model) {
        List<Project> projects = projectService.getProjects();  //프로젝트 리스트를 최신순으로 가져오기
        model.addAttribute("projects", projects); //projects를 모델에 추가
        return "/projects/list";
    }

    @GetMapping("project/write")
    public String write(Model model) {
        // HTML 예시 텍스트 설정
        String htmlExample = "<span class=\"bold\">굵은 텍스트</span> 일반 텍스트 <span class=\"color\">색상 텍스트</span>";

        // 각 텍스트 영역에 대한 예시 설정
        model.addAttribute("htmlConcept", htmlExample);
        model.addAttribute("htmlPart", htmlExample);
        model.addAttribute("htmlDetail", htmlExample);

        return "/projects/write";
    }
    @PostMapping("/write")
    public String writeProject(
        @ModelAttribute ProjectDto projectDto,
        @RequestParam("projectImgFile") List<MultipartFile> projectImgFiles) throws IOException {

        // 이미지 처리 및 ProjectImgDto 생성
        List<ProjectImgDto> projectImgDtoList = new ArrayList<>();

        for (int i = 0; i < projectImgFiles.size(); i++) {
            MultipartFile file = projectImgFiles.get(i);
            if (!file.isEmpty()) {
                ProjectImgDto imgDto = new ProjectImgDto();

                // 파일 저장 및 URL 생성
                try {
                    String originalFilename = file.getOriginalFilename();
                    String savedFileName = fileService.uploadFile(uploadPath, originalFilename, file.getBytes());
                    String imageUrl = "/images/projectImg/" + savedFileName;

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
                        case 7 -> ProjectImgStatus.DETAIL3;
                        default -> ProjectImgStatus.ETC;
                    };
                    imgDto.setImageType(imageType);

                    projectImgDtoList.add(imgDto);
                } catch (IOException e) {
                    throw e; // Re-throw IOException which is already declared
                }
            }
        }

        projectDto.setProjectImgList(projectImgDtoList);

        // 프로젝트 저장
        projectService.writeProject(projectDto);
        return "redirect:/project";
    }


    //뷰페이지
    @GetMapping(value = "/project/detail/{id}")
    public String detail(@PathVariable("id") Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        boolean isWebType = !(project.getCategories().contains(ProjectCategoryStatus.SNS)
            || project.getCategories().contains(ProjectCategoryStatus.DETAIL));

        // 각 이미지 타입별로 URL 찾기
        String mainBannerUrl = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.MAINBANNER)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String previewUrl = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.PREVIEW)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String mainPageUrl = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.MAINPAGE)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String subPage1Url = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.SUBPAGE1)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String subPage2Url = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.SUBPAGE2)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String detail1Url = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.DETAIL1)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String detail2Url = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.DETAIL2)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        String detail3Url = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.DETAIL3)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");
        String etcUrl = project.getProjectImgList().stream()
            .filter(img -> img.getImageType() == ProjectImgStatus.ETC)
            .map(ProjectImg::getImgUrl)
            .findFirst()
            .orElse("");

        model.addAttribute("project", project);
        model.addAttribute("isWebType", isWebType);
        model.addAttribute("mainBannerUrl", mainBannerUrl);
        model.addAttribute("previewUrl", previewUrl);
        model.addAttribute("mainPageUrl", mainPageUrl);
        model.addAttribute("subPage1Url", subPage1Url);
        model.addAttribute("subPage2Url", subPage2Url);
        model.addAttribute("detail1Url", detail1Url);
        model.addAttribute("detail2Url", detail2Url);
        model.addAttribute("detail3Url", detail3Url);
        model.addAttribute("etcUrl", etcUrl);

        return "/projects/detail";
    }

    @GetMapping("/project/edit/{id}")
    public String edit(@PathVariable("id") Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        
        // HTML 예시 텍스트 설정
        String htmlExample = "<span class=\"bold\">굵은 텍스트</span> 일반 텍스트 <span class=\"color\">색상 텍스트</span>";
        model.addAttribute("htmlConcept", htmlExample);
        model.addAttribute("htmlPart", htmlExample);
        model.addAttribute("htmlDetail", htmlExample);
        
        // 프로젝트 정보를 모델에 추가
        model.addAttribute("project", project);
        model.addAttribute("isEdit", true);
        
        return "/projects/write";
    }

    @PostMapping("/project/edit/{id}")
    public String updateProject(
        @PathVariable("id") Long projectId,
        @ModelAttribute ProjectDto projectDto,
        @RequestParam("projectImgFile") List<MultipartFile> projectImgFiles,
        @RequestParam(value = "existingImages", required = false) Map<String, String> existingImages) throws IOException {
        
        // 이미지 처리 및 ProjectImgDto 생성
        List<ProjectImgDto> projectImgDtoList = new ArrayList<>();
        
        // 기존 이미지 처리
        if (existingImages != null) {
            for (Map.Entry<String, String> entry : existingImages.entrySet()) {
                ProjectImgDto imgDto = new ProjectImgDto();
                imgDto.setImgName(entry.getValue());
                imgDto.setImageType(ProjectImgStatus.valueOf(entry.getKey()));
                projectImgDtoList.add(imgDto);
            }
        }
        
        // 새로운 이미지 처리
        for (int i = 0; i < projectImgFiles.size(); i++) {
            MultipartFile file = projectImgFiles.get(i);
            if (!file.isEmpty()) {
                ProjectImgDto imgDto = new ProjectImgDto();
                
                // 파일 저장 및 URL 생성
                try {
                    String originalFilename = file.getOriginalFilename();
                    String savedFileName = fileService.uploadFile(uploadPath, originalFilename, file.getBytes());
                    String imageUrl = "/images/projectImg/" + savedFileName;
                    
                    imgDto.setImgName(savedFileName);
                    imgDto.setOriImgName(originalFilename);
                    imgDto.setImgUrl(imageUrl);
                    imgDto.setImageType(ProjectImgStatus.values()[i]);
                    
                    projectImgDtoList.add(imgDto);
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        
        projectDto.setProjectImgList(projectImgDtoList);
        
        // 프로젝트 업데이트
        Project updatedProject = projectService.updateProject(projectId, projectDto);
        
        return "redirect:/project/detail/" + updatedProject.getId();
    }

}
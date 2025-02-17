package com.portfolio.dto;

import com.portfolio.constant.ProjectImgStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectImgDto {
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private ProjectImgStatus imageType;

    // 역직렬화를 위한 기본생성자 추가
    public ProjectImgDto() {
    }


    public ProjectImgDto(Long id, String imgName, String oriImgName, String imgUrl, ProjectImgStatus imageType) {
        this.id = id;
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.imageType = imageType;
    }


}


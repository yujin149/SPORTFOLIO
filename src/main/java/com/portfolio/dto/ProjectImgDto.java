package com.portfolio.dto;

import com.portfolio.entity.ProjectImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectImgDto {
    private String imgName; // 이미지 파일명
    private String oriImgName; // 원본 이미지 파일명
    private String imgUrl; // 이미지 조회 경로
    private String imageTypeDescription; // 이미지 타입에 대한 설명

    // 생성자: ProjectImg 엔티티에서 필요한 데이터만 가져오기
    public ProjectImgDto(ProjectImg projectImg) {
        this.imgName = projectImg.getImgName();
        this.oriImgName = projectImg.getOriImgName();
        this.imgUrl = projectImg.getImgUrl();
        this.imageTypeDescription = projectImg.getImageType().getDescription(); // ProjectImgStatus에서 description 가져오기
    }

}

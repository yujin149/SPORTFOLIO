package com.portfolio.entity;

import com.portfolio.constant.ProjectImgStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project_img")
@Getter
@Setter
public class ProjectImg extends BaseEntity {
    @Id
    @Column(name="projectimg_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="projectimg_name")
    private String imgName; // 이미지 파일명

    @Column(name="projectimg_ori_name")
    private String oriImgName; // 원본 이미지 파일명

    @Column(name="projectimg_url")
    private String imgUrl; // 이미지 조회 경로

    @Column(name="projectimg_type")
    @Enumerated(EnumType.STRING)
    private ProjectImgStatus imageType; // 이미지 타입 (BANNER, PREVIEW, DETAIL 등)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project; // 프로젝트와 연관


}

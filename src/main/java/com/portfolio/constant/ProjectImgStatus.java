package com.portfolio.constant;

public enum ProjectImgStatus {
    MAINBANNER("메인 배너"),
    PREVIEW("미리보기"),
    MAINPAGE("MAIN PAGE"),
    SUBPAGE1("SUB PAGE"),
    SUBPAGE2("SUB PAGE"),
    DETAIL1("DETAIL PAGE"),
    DETAIL2("DETAIL PAGE"),
    DETAIL3("DETAIL PAGE"),
    ETC("ETC"),
    VIDEO("VIDEO");

    private final String description;

    // 생성자
    ProjectImgStatus(String description) {
        this.description = description; //각 항목에 대한 description을 저장
    }

    // description을 반환하는 메서드
    public String getDescription() {
        return description;
    }
}

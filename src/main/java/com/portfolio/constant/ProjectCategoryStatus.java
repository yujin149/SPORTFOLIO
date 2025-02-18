package com.portfolio.constant;

public enum ProjectCategoryStatus {
    ALL("전체"),
    DEVELOPMENT("웹 개발"),
    WEB("웹 & 모바일"),
    REACTION("반응형 웹"),
    SNS("SNS 컨텐츠"),
    DETAIL("상세페이지 & 이벤트페이지");

    private final String displayName;

    ProjectCategoryStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

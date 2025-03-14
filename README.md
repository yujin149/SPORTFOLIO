# 🎨 SPORTFOLIO
**SPORTFOLIO**는 개인 포트폴리오를 관리하고 전시하는 웹 플랫폼으로, 프로젝트 관리와 문의하기 기능을 제공하는 포트폴리오 웹사이트입니다.
이를 통해 더 많은 사람들과 제 작업을 공유하고, 앞으로의 여정에서 더 나은 결과물을 만들어 나가고자 합니다.

## 🚀 주요 기능
### 프로젝트 관리
- **프로젝트 등록/수정/삭제**: 포트폴리오 작품 CRUD 기능 제공
- **카테고리 관리**: 웹 개발, 반응형 웹, SNS 콘텐츠 등 다양한 카테고리 분류 가능
- **이미지 관리**: 메인 배너, 미리보기, 상세 페이지 등 체계적인 이미지 관리
- **조회수 트래킹**: 프로젝트별 조회수 관리

### 문의하기 시스템
- **문의 등록**: 이메일, 연락처 등을 통한 문의 사항 접수
- **관리자 대시보드**: 문의 내역 관리 및 조회
- **페이징 처리**: 효율적인 문의 목록 관리

### 보안 기능
- **Spring Security**: 안전한 인증 및 권한 관리
- **관리자 권한**: 특정 기능에 대한 관리자 전용 접근 제어
- **CSRF 보호**: 보안 취약점 방지

## 🛠 사용 기술
### Backend
- **Java / Spring Boot**: 주요 비즈니스 로직 처리
- **Spring Security**: 인증 및 권한 관리
- **JPA / Hibernate**: 데이터베이스 연동
- **Spring MVC**: 웹 계층 구조 관리

### Frontend
- **Thymeleaf**: 서버사이드 템플릿 엔진
- **HTML / CSS / JavaScript**: 사용자 인터페이스 구현
- **jQuery**: 동적 기능 구현

### 기타
- **File Upload**: 이미지 파일 업로드 및 관리
- **Responsive Design**: 다양한 디바이스 지원

## 📦 프로젝트 구조
<details>
  <summary>📂 src/ 디렉토리 구조</summary>
  <pre>
📂src
 ├──📂main
    ├──📂java
    │   └──📂com/portfolio/
    │       ├──📂config/         # 설정 파일
    │       ├──📂constant/       # 상수 정의
    │       ├──📂controller/     # 컨트롤러
    │       ├──📂dto/           # 데이터 전송 객체
    │       ├──📂entity/        # 엔티티 클래스
    │       ├──📂repository/    # 데이터 접근 계층
    │       └──📂service/       # 비즈니스 로직
    └──📂resources/
        ├──📂static/           # 정적 리소스
        │   ├──📂css/
        │   ├──📂js/
        │   └──📂images/
        └──📂templates/        # 뷰 템플릿
  </pre>
</details>

## 💾 기술 선정 이유
- **Spring Boot**: 빠른 개발과 설정의 용이성을 위해 선택하였으며, 안정적이고 확장 가능한 웹 애플리케이션 개발을 지원함
- **Spring Security**: 안전한 인증 및 권한 관리를 위해 도입하였으며, 다양한 보안 기능을 제공
- **JPA**: 객체지향적인 데이터 접근을 위해 채택하여, 데이터 모델링 및 쿼리 처리의 효율성을 높임
- **Thymeleaf**: 서버사이드 렌더링의 장점을 활용하여, UI와 데이터를 통합적으로 관리하고 빠르게 개발할 수 있도록 함

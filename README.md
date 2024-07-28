# Convention

# Git branch 전략

- `main`
    - 실제 배포 CI/CD용 branch
- `develop`
    - 개발 CI/CD용 branch
- `feature`
    - 기능 구현용 branch
    - 반드시 `develop`에서 뻗어나와 develop으로 **`merge`** 되어야한다.
    

---

# 개발 전 작업

1. **Issue 발행**
    1. 리뷰어 설정
    2. branch 생성 : `issue_name/issue_number`
        
        ex) `feature/12`  `refactor/35` 
        
    
2. **`git fetch` && `git pull`** 

---

# Issue 종류

- `chore` : gradle 의존성 주입 및 yml 설정 등 **프로젝트 기본 세팅**
- `feature` : 기능 구현
- `fix` : 버그 수정
- `refactor` : 코드 리팩토링
- `reconstruct` : 프로젝트 구성 변경
- `test` : 테스트 코드

---

# Git Commit Message Convention

```
[Issue_종류] 구현_내용 #이슈_번호
```

- Pull Request만 날리고, Approve는 reviewer가 한다.

---

# Package Convention

- `config` : security, aws 등 설정 정보

- `domain` : entity
    - `enums`
    - `common`
    - `mapping`  다대다

- `converter` : entity ↔ dto

- `payload` : 응답에 실을 내용

- `repository`

- `service`

- `validation` : 유효성 검증 annotation 및 validator

- `web`
    - `dto`
    - `controller`

---

# etc

- **annotation은 한 줄에 하나만**
- **당연히 camelCasing**
- `xxxDto`
- request, response 패키지로 분리하고, 관련 DtoClass안에 static class를 두자
    - `dto` (package)
        - `request` (package)
            - `xxxRequestDto` (class)
                - `yyyDto` (static class)
        - `response` (package)
            - `xxxResponseDto`
                - `zzzDto` (static class)

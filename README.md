# Instagram Clone Coding

## 목표
* Spring Security를 통한 일반 로그인 & 소셜 로그인 구현하기
* MultiPartFile과 Text 데이터 함께 처리하기
* 로그인한 사용자에 따라 팔로워, 팔로잉 개수 카운트하기
* 팔로우 & 좋아요 작성 및 취소 처리하기

## 핵심 기능
* 일반 로그인 및 Kakao 로그인 기능
* 사용자 마이 페이지 관리 기능
* 로그인한 사용자의 이미지를 다른 사용자가 좋아요 누른 경우 알림 기능
* 로그인한 사용자에 따라 팔로우 한 사용자인지, 좋아요 누른 이미지인지 구분하는 로직 구현

## 실행 결과

|시작 페이지|마이페이지|
|---|---|
|<img width="1501" alt="스크린샷 2024-02-27 오후 4 34 43" src="https://github.com/kylo-dev/Insta-clone/assets/103489352/7776e2bf-76ef-4be1-b436-126b7d76f28b">|<img width="1457" alt="스크린샷 2024-02-27 오후 4 35 30" src="https://github.com/kylo-dev/Insta-clone/assets/103489352/ccae0e83-040d-4416-87d6-e5b17aa18635">

|팔로워 페이지|게시글 작성 페이지|
|---|---|
|<img width="1501" alt="스크린샷 2024-02-27 오후 4 36 28" src="https://github.com/kylo-dev/Insta-clone/assets/103489352/cdd495f2-b9b4-49da-83ac-e4e2b3c5bb6a">|<img width="1457" alt="스크린샷 2024-02-27 오후 4 37 01" src="https://github.com/kylo-dev/Insta-clone/assets/103489352/dca2e9dc-0465-4f30-b283-976e4c2523a7">


## 의존성
* Spring Web
* Spring Security
* Spring Data JPA
* Lombok
* Spring Oauth
* Mysql Driver

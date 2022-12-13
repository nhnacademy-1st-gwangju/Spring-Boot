# 2일차 과제
실습에 사용했던 Account 시스템을 참고하여 이를 Back End 서버와 Open Api 서버로 2개의 프로젝트를 생성하여 개발합니다.  
간단한 Microservice를 구현하는 과제입니다.

## 필수사항
- Open Api 서버와 BackEnd 서버로 분리합니다. (spring-boot 프로젝트는 전체 2개 입니다.)
- Open Api 서버 : 외부에 제공할 Rest Api 를 제공합니다.
- BackEnd 서버 : Account 시스템 기능을 RestController 로 구현하여 JSON 형식으로 반환 합니다.

## OPEN API 가 제공해야 할 API
- GET /accounts
- GET /accounts/{id}
- POST /accounts
- DELETE /accounts/{id}

## 구성
![image](https://user-images.githubusercontent.com/60968342/207319774-f0ec0132-1fd2-4c6c-9cbe-447b2d8af70d.png)

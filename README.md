# Welcome to co_bye ‼️

[cobye](https://github.com/seulda/cobye)(코로나19 정보 전달 웹사이트) 수정 & 배포 버전 <br>
> Cobye 코로나 종식을 희망하며 코로나 감염자 수에 대한 정보를 제공하는 서비스입니다. <br>
> 코로나로 인해 더이상 피해입는 사람들이 발생하지 않고 하루빨리 코로나가 종식되길 희망합니다.

<br>

## ✅ Update 
* 일별/누적 확진자 Page - 지역별 확진자 보기 추가 (2021.08.03)
* 선별진료소 Page - 지도 오류 수정 (2021.08.13)
* Info Page - API 응답 에러에 대비하여 (Redirection) 기본 정보 페이지 추가 (2021.08.15)
* 지역/성별/연령별 확진자 Page - view 일부 수정, controller refactoring (2021.08.17-19)
* Page 일부 - 현재시각 view 추가 (2021.08.19)

<br>

## 👤 Author
**Daseul Kim**
* Github: [@seulda](https://github.com/seulda)

<br>

## ▶️ Show Demo
#### [✨Demo✨](https://cobye.site)

<br><br><br>

---

<br>

## 🎬 Cobye 프로젝트
Cobye 코로나 종식을 희망하며 코로나 감염자 수에 대한 정보를 제공하는 서비스입니다. <br>
코로나로 인해 더이상 피해입는 사람들이 발생하지 않고 하루빨리 코로나가 종식되길 희망합니다.

<br>

## 📆   작업 기간
#### 2021.7.21 ~ 2021.7.28 (8일간)

<br>

## 👩‍💻   팀원 구성
#### [박현성](https://github.com/lusida0131), [김다슬](https://github.com/seulda), [고정현](https://github.com/lsc3421), [유승준](https://github.com/wns1908)

<br>

## 🎯 기술 스택
### Front-end 
<div>
  <img alt="HTML5" src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white"/>
  <img alt="JavaScript" src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E"/>
  <img alt="Bootstrap" src="https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white"/>
  <img alt="jQuery" src="https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white"/>
</div>

### Back-end 
<div>
  <img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/>
  <img alt="Spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
</div>
 
### Communication Tools
<div>
  <img alt="GitHub" src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"/>   
  <img alt="Discord" src="https://img.shields.io/badge/Discord-%237289DA.svg?style=for-the-badge&logo=discord&logoColor=white"/>
  <img alt="Notion" src="https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white"/>
</div>

<br>

## 📜 API 사용내역
  #### 1. 공공데이터활용지원센터_보건복지부 코로나19 감염 현황 API
  #### 2. 공공데이터활용지원센터_보건복지부 코로나19 시·도발생 현황 API
  #### 3. 공공데이터활용지원센터_보건복지부 코로나19 연령별·성별감염 현황 API
  #### 4. 행정안전부_재난문자방송 발령현황 API
  #### 5. Kakao 지도 Javscript API
 
<br>

## 📄 기능 구현 사항
### 1. 일별/누적 확진자 페이지 (Main Page)
  * (1) 전날 확진자, 누적 확진자, 누적 사망자 수
  * (2) 전일대비 확진자 비교 - (원형 그래프)
  * (3) 최근 일별 코로나 확진 현황 - (막대 그래프, 표)
  * (4) 지역별 확진자 보기 - (대한민국 지도맵) ( ✅ 2021.08.03 추가 )
  <img width="1500" alt="스크린샷 2021-08-19 20 33 38" src="https://user-images.githubusercontent.com/57322002/130064203-c98502b5-8540-4d4a-861c-bd848ecbae13.png">


### 2. 지역/성별/연령별 확진자 페이지
  * (1) 지역별 확진자 현황 - (막대 그래프, 표)
  * (2) 연령별 확진자 현황 - (막대 그래프, 표)
  * (3) 성별 확진자 현황 - (표)
  <img width="1000" alt="스크린샷 2021-08-19 20 33 10" src="https://user-images.githubusercontent.com/57322002/130064335-08ec25cf-831c-4c72-be73-e632ff2c10ba.png">


### 3. 선별진료소 페이지   
  * (1) 지도 (현위치, 선별진료소, 임시 선별진료소 정보)
  * (2) 길찾기
  <img width="500" alt="스크린샷 2021-08-19 20 32 50" src="https://user-images.githubusercontent.com/57322002/130061987-aabbabbb-5a19-4dc5-a6f6-9dd944e028c2.png">


### 4. 예방접종 의료기관 페이지
  * (1) 행정구역 검색
  * (2) 행정구역 의료기관 정보
  * (3) 병원 위치 정보가 마킹된 지도
  <img width="1500" alt="스크린샷 2021-08-19 20 31 39" src="https://user-images.githubusercontent.com/57322002/130064152-18931213-b010-40e3-b868-6311117f8557.png">



### 5. (BETA)실시간 확진자 페이지
  * (1) 실시간 추가 확진자
  * (2) 어제 확진자
  * (3) 실시간 코로나 확진 현황(재난문자)
  <img width="500" alt="스크린샷 2021-08-19 20 31 18" src="https://user-images.githubusercontent.com/57322002/130062083-ad1ffaa2-e936-4770-b767-ad1c754c5653.png">

### 6. cobye 페이지 ( ✅ 2021.08.15 추가 )
  * Info Page : API 응답 에러에 대비하여 (Redirection) 기본 정보 페이지
  * (1) 집계일, 추가 확진자, 누적 확진자
  * (2) cobye information
  * (3) 현재시각 view ( ✅ 2021.08.19 추가 )
  <img width="500" alt="스크린샷 2021-08-19 20 30 46" src="https://user-images.githubusercontent.com/57322002/130062136-0c9ed855-64d1-441c-8d83-faa6b5b550ac.png">


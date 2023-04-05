# EnjoyTrip_Java_Seoul_10_최용혁_ 강수창



![image](https://user-images.githubusercontent.com/68116990/226179152-7afedb2f-7bbb-463c-8d41-690614caf1f9.png)

## 0. SSAFY 관통 프로젝트 Enjoytrip 
>  프론트 구현해보기
<br>

### 1. 요구사항 1~3
>지역별 컨텐츠를 카테고리로 조회할 수 있는 기능 구현 하기.

- HOME 페이지 한 가운데 검색하기 버튼을 배치하였다. <br>
검색 버튼 클릭으로 다음과 같은 모달 창을 띄우도록 구현하였다.



![image](https://user-images.githubusercontent.com/68116990/226179265-3bd10632-1643-4b44-afa9-9339efd9dadd.png)



- 아래와 같이 지역, 카테고리 별로 선택 할 수 있고, 키워드와 관련된 결과를 갤러리 형식으로 보여준다.
![image](https://user-images.githubusercontent.com/68116990/226179333-2deebd37-2350-41d8-a642-fbb2fbad5a51.png)

![image](https://user-images.githubusercontent.com/68116990/226179325-4f904fbf-8d41-4895-b6a5-145a2a087414.png)

- 링크 모양의 표시를 클릭하면 다음과 같이 더 자세한 정보를 모달창으로 볼 수 있다.

![image](https://user-images.githubusercontent.com/68116990/226180066-595cc0d8-f764-469b-ba8e-fb0ea9a29b63.png)



### 2. 요구사항 7~8 
>  회원가입, 로그인 기능 구현하기

#### 가입 기능

- JOIN 페이지에 들어가면 다음과 같은 가입 창이 뜬다. <br>

![image](https://user-images.githubusercontent.com/68116990/226180660-fac86e0d-d99f-4fcd-ad45-3c02b216bc8f.png)



- 유효성 검사

![image](https://user-images.githubusercontent.com/68116990/226186544-23f1f869-1538-4fc2-beed-59859aaabdc3.png)

- 유효성 검사 후 성공시 로컬 스토리지를 이용하여 아이디 중복 검사를 채크하고, 이 과정을 통과하면 가입되도록 구현하였다.

![image](https://user-images.githubusercontent.com/68116990/226186722-a924192f-730b-4958-97e6-7284ce4647a6.png)

#### 로그인 기능

![image](https://user-images.githubusercontent.com/68116990/226187096-2a38b392-ec29-4acc-b7f2-e68f50449d7f.png)


- 로컬 스토리지에 저장된 아이디와 비밀번호를 이용하여 로그인 성공시 모든 창에 있는 LOGIN 키를 LOGOUT으로 바꾸어주고, JOIN 메뉴를 없애주었다.
![image](https://user-images.githubusercontent.com/68116990/226187880-694b838e-02b6-4271-b39a-994d74c0d71c.png)


---
### ToDo : 
- MAP 메뉴 구체화 및 마무리
- 게시판 메뉴
- favorite 메뉴 
- 개인 정보 및 탈퇴 혹은 추가로 좋아요 버튼을 구현하기 위해서는 로컬 스토리지에 단순 아이디 비번이 아닌 stringify, parse, json을 이용한 구현 필요.





# 2022-02-MobileProgramming
2022년도 2학기 모바일프로그래밍 개인과제

### :iphone: 안드로이드 스튜디오를 이용한 간단한 어플 제작 :iphone:

### 간단주제 : 책 판매 어플 (상품 - 책) :books:

### 개발환경
- 안드로이드 스튜디오 
- 개발언어 : JAVA
- SDK 버전 : 안드로이드 12   
  <img width="327" alt="image" src="https://user-images.githubusercontent.com/54923245/198889938-7a2eca21-8f53-423b-8295-da7091d3b496.png"> 

### 설치방법
- 우측 상단의 Code 버튼 클릭 후 Download ZIP 버튼을 클릭 하여 압축파일을 다운받는다.
- 안드로이드 스튜디오에서 해당 파일을 압축해제하여 run 시키면 프로젝트가 실행된다.

### 화면설명
> #### 첫 번째 화면 (Activity_main.xml , MainActivity)
- RelativeLayout 사용
- 로그인이 가능한 화면
- 회원가입 버튼 클릭 시 두 번째 화면으로 이동
- 상품 바로 보러가기 버튼 클릭 시 로그인 유무 관계없이 접근 가능
- 아이디 비밀번호 입력 칸 상단에 비회원상태/회원상태 확인 가능

> #### 두 번째 화면 (Activity_join.xml , JoinActivity)
- RelativeLayout 사용
- ScrollView 사용으로 스크롤 가능
- 회원가입 진행 화면
- 아이디 중복 체크 기능, 글자수 제한: 15 (최소 4글자 입력 조건있음)
- 비밀번호 조건 (특수키, 숫자, 영어대소문자 포함)

  1) ([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])
  2) ([a-z].*[A-Z])|([A-Z].*[a-z])
- 비밀번호 글자 수 제한: 20
- 비밀번호는 5글자 이상 입력해야함 -> 5글자 미만이면 회원가입 불가
- 비밀번호 이중 확인 - 비밀번호와 비밀번호 확인이 동일해야함
- 이름 글자 수 제한: 15
- 전화번호 글자 수 제한: 11
- 주소 글자 수 제한 : 25
- 개인정보 처리방침에 동의 시에만 회원가입 가능

> #### 세 번째 화면 (Activity_product.xml , ProductActivity)
- GridLayout 및 TableLayout 등 사용
- ScrollView 사용으로 스크롤 가능
- 상품 화면
- 회원/비회원 상관없이 접근가능
- 상품목록 하단의 내 정보 보기 버튼 클릭 시

  1) 로그인 후 접근(회원) - 간단한 유저 정보를 보여준다
  2) 로그인 없이 접근(비회원) - 로그인하거나 회원가입 할 수 있는 창으로 연결을 요청한다
  
  
### 유저플로우
> 비회원  
    
    화면 실행(첫 번째 화면) -> 상품 바로 보러가기 버튼 클릭(첫 번째 화면) -> 상품 확인 페이지 접근(세 번째 화면) 
    -> 상품들 하단에 내 정보보기 버튼 클릭 시 로그인 또는 회원가입 유도 창 확인가능 -> 로그인(첫 번째 화면) 또는 회원가입(두 번째 화면) 창으로 이동

> 회원

    화면 실행(첫 번째 화면) -> 아이디 및 비밀번호 입력 후 로그인 버튼 클릭(첫 번째 화면) -> 상품 확인 페이지 접근(세 번째 화면) 
    -> 상품들 하단에 내 정보보기 버튼 클릭 시 회원가입 시 입력한 간단 정보 확인 가능 (아이디, 이름, 전화번호, 주소, 개인정보처리방침 동의 유무)

### 데이터 저장 방식 - Preferences 사용
> count(유저의 고유 넘버), userID, userPW, userName, userPhone, userAddress, ppAgree 를 저장한다

```
SharedPreferences prefs = getSharedPreferences("user_info", 0);
SharedPreferences.Editor editor = prefs.edit();

// 유저마다 고유 숫자가 있으므로 값을 가져와 하나씩 증가하여 숫자를 부여한다.
int cnt = prefs.getInt("count", 0);
if (cnt >= 0) cnt += 1;

String userID = joinID.getText().toString();
String userPW = joinPW.getText().toString();
String userName = joinName.getText().toString();
String userPhone = joinPhone.getText().toString();
String userAddress = joinAddress.getText().toString();
String ppAgree = checkedRadio.getText().toString();

// 유저의 고유 숫자를 포함한 키값에 값들을 저장한다
editor.putInt("count", cnt);
editor.putString("userID" + cnt, userID);
editor.putString("userPW" + cnt, userPW);
editor.putString("userName" + cnt, userName);
editor.putString("userPhone" + cnt, userPhone);
editor.putString("userAddress" + cnt, userAddress);
editor.putString("ppAgree" + cnt, ppAgree);

editor.commit();
```

  
### 실행 화면 예시 (기능 위주)

  > 회원가입 관련
<div>
  <p> - 아이디 확인</p>
  <div>
  <div>
  <img width="265" alt="image" src="https://user-images.githubusercontent.com/54923245/199163601-96195e0a-ec95-4998-82e8-a6588e745588.png">
  <img width="267" alt="image" src="https://user-images.githubusercontent.com/54923245/199163660-12c86843-efef-495c-ad8d-f202704ad5a1.png">
  </div>
  <div>
  <img width="266" alt="image" src="https://user-images.githubusercontent.com/54923245/199163687-13f6ecf3-1e4d-4267-b7f4-573943d3d16b.png">
  <img width="278" alt="image" src="https://user-images.githubusercontent.com/54923245/199185482-00a55e53-6eb7-4258-b06a-867c41664c59.png">
  </div>
  </div>
  <p> - 비밀번호 조건</p>
  <div>
  <img width="256" alt="image" src="https://user-images.githubusercontent.com/54923245/199163936-88d63251-a0b0-4cc7-8a16-b1641a979d9e.png">
  <img width="253" alt="image" src="https://user-images.githubusercontent.com/54923245/199164012-c5908355-da50-4175-811b-d79f821e3912.png">
  </div>
  <p> - 완벽하지 않은 입력들</p>
  <div>
    <img width="270" alt="image" src="https://user-images.githubusercontent.com/54923245/199165052-a7f20bbe-1514-46c5-b3ed-77638746b6b2.png">

  </div>
</div>


  > 로그인 
<div>
  <img width="282" alt="image" src="https://user-images.githubusercontent.com/54923245/199164106-22cce3da-47d7-46ea-b50f-577e311d9c01.png">
  <img width="283" alt="image" src="https://user-images.githubusercontent.com/54923245/199164157-3fa1dc70-6b95-4c33-af3e-ceda0869efdf.png">
</div>


  > 상품페이지 (내 정보보기 버튼 이벤트)
<div>
  <img width="280" alt="image" src="https://user-images.githubusercontent.com/54923245/199164398-24b8b652-359a-4b6c-bc22-857cfc2e69a9.png">
  <img width="283" alt="image" src="https://user-images.githubusercontent.com/54923245/199164362-1edd3df3-656c-4c61-8180-d4804980d0bc.png">
</div>

package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button ProductBtn, loginBtn, JoinBtn;
    EditText userID, userPW; // 입력한 아이디와 비밀번호

    TextView UserState ;

    Boolean LoginCheck = false; // 로그인 유무로 회원/비회원 상태를 알려주기 위한 변수
    Boolean checkUser = false; // 회원인지 아닌지 확인하는 변수 -> 회원일 경우에만 로그인 가능

    int userNumber ;  // 상품페이지에서 내 정보보기 버튼 클릭 시 내 정보를 가져오기 위한 유저만의 고유숫자를 담는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserState = (TextView) findViewById(R.id.UserState);

        ProductBtn = (Button) findViewById(R.id.productBtn);
        ProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);

                // 로그인 유무를 알 수 있는 변수와 유저의 고유 숫자를 함께 넘겨준다
                intent.putExtra("LoginCheck", LoginCheck.booleanValue());
                intent.putExtra("UserNumber", userNumber);
                startActivity(intent);
            }
        });



        // 로그인 버튼 클릭 시
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userID = (EditText) findViewById(R.id.userID);
                userPW = (EditText) findViewById(R.id.userPW);

                SharedPreferences sharedPreferences= getSharedPreferences("user_info", 0);
                // 유저정보를 확인하기 위한 변수를 저장한 프레퍼런스에서 가져온다
                int savedCount = sharedPreferences.getInt("count", 0);

                // 저장된 아이디와 비밀번호가 모두 일치하는 지 확인
                for (int i =1; i <= savedCount; i++) {
                    String id = sharedPreferences.getString("userID"+i, "");
                    String pw = sharedPreferences.getString("userPW"+i, "");
                    // 반복문 중 하나의 계정이라도 일치하면 checkUser를 true로 바꾸고 반복문 정지
                    if (userID.getText().toString().equals(id) && userPW.getText().toString().equals(pw) ){
                        checkUser = true;
                        userNumber = i; // 유저의 고유숫자를 따로 저장
                        break;
                    }
                }
                // 아이디나 비밀번호를 입력하지 않았을 경우
                if(userID.getText().toString().isEmpty() || userPW.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 제대로 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 입력값과 저장된 값이 같을경우
                if(checkUser == true) {
                    LoginCheck = true;
                    UserState.setText("회원 상태입니다.");
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    // 상품페이지에서 내 정보 확인 시 체크하기 위한 변수전달
                    intent.putExtra("LoginCheck", LoginCheck.booleanValue());
                    intent.putExtra("UserNumber", userNumber);
                    startActivity(intent);
                }
                // 입력값과 다를 경우
                else {
                    LoginCheck = false;
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 회원가입 버튼 클릭 시 회원가입 페이지로 이동
        JoinBtn = (Button) findViewById(R.id.joinBtn);
        JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}
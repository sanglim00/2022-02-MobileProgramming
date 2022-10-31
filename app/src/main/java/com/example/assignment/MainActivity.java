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
    EditText userID, userPW;

    TextView UserState ;

    Boolean LoginCheck = false;
    Boolean checkUser = false;

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
                intent.putExtra("LoginCheck", LoginCheck.booleanValue());
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
                int savedCount = sharedPreferences.getInt("count", 0);

                // 저장된 아이디와 비밀번호가 모두 일치하는 지 확인
                for (int i =1; i <= savedCount; i++) {
                    String id = sharedPreferences.getString("userID"+i, "");
                    String pw = sharedPreferences.getString("userPW"+i, "");
                    // 반복문 중 하나의 계정이라도 일치하면 checkUser를 true로 바꾸고 반복문 정지
                    if (userID.getText().toString().equals(id) && userPW.getText().toString().equals(pw) ){
                        checkUser = true;
                        break;
                    }
                }

                if(userID.getText().toString().isEmpty() || userPW.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 제대로 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), checkUser.toString(), Toast.LENGTH_SHORT).show();

                // 입력값과 저장된 값이 같을경우
                if(checkUser == true) {
                    LoginCheck = true;
                    UserState.setText("회원 상태입니다.");
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    // 상품페이지에서 내 정보 확인 시 체크하기 위한 변수전달
                    intent.putExtra("LoginCheck", LoginCheck.booleanValue());
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
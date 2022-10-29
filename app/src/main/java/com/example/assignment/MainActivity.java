package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button ProductBtn, loginBtn, JoinBtn;
    EditText userID, userPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductBtn = (Button) findViewById(R.id.productBtn);
        ProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences= getSharedPreferences("user_info", 0);
        String id = sharedPreferences.getString("userID","");
        String pw = sharedPreferences.getString("userPW", "");

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        userID.setText(id);
        userPW.setText(pw);

        // 로그인 버튼 클릭 시
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 입력값과 저장된 값이 같을경우
                if(userID.getText().toString().equals(id) && userPW.getText().toString().equals(pw)) {
                    Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                    startActivity(intent);
                }
                // 입력값과 다를 경우
                else {
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
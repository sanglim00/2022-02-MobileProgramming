package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    Button checkIDBtn, joinFinishBtn, backBtn;
    private EditText joinID, joinPW, rejoinPW, joinName, joinPhone, joinAddress ;
    private RadioGroup ppRadio;

    boolean checkID = false, checkPW = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 뒤로가기 버튼 클릭 시 다시 첫 화면으로
        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 아이디 중복확인
        checkIDBtn = (Button) findViewById(R.id.checkIDBtn);
        checkIDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("user_info", 0);
                String id = sharedPreferences.getString("userID","");
                joinID = (EditText) findViewById(R.id.joinID);
                TextView idCheckMsg = (TextView) findViewById(R.id.idCheckMsg);

                // 입력값이 비어있을 경우
                if(joinID.getText().toString().isEmpty()) {
                    idCheckMsg.setText("아이디를 입력해주세요");
                    idCheckMsg.setVisibility(View.VISIBLE);
                    checkID = false;
                }
                // 이미 존재하는 아이디일 경우
                else if(joinID.getText().toString().equals(id)) {
                    idCheckMsg.setText("이미 존재하는 아이디입니다. 다른 아이디를 설정해주세요");
                    idCheckMsg.setVisibility(View.VISIBLE);
                    checkID = false;
                }
                else {
                    idCheckMsg.setText("사용 가능한 아이디입니다.");
                    idCheckMsg.setVisibility(View.VISIBLE);
                    checkID = true;
                }

            }
        });

        // 회원가입 하기 버튼 클릭 시
        joinFinishBtn = (Button) findViewById(R.id.joinFinishBtn);
        joinFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinID = (EditText) findViewById(R.id.joinID);
                joinPW = (EditText) findViewById(R.id.joinPW);
                rejoinPW = (EditText) findViewById(R.id.reJoinPW);
                joinName = (EditText) findViewById(R.id.joinName);
                joinPhone = (EditText) findViewById(R.id.joinPhone);
                joinAddress = (EditText) findViewById(R.id.joinAddress);

                // 비밀번호 입력 체크
                if(joinPW.getText().toString().equals(rejoinPW.getText().toString())) checkPW = true;
                else {
                    Toast.makeText(getApplicationContext(), "두 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                    checkPW = false;
                    return ;
                }

                // 회원가입
                if(checkPW && checkID) {
                    SharedPreferences prefs = getSharedPreferences("user_info", 0);
                    SharedPreferences.Editor editor = prefs.edit();

                    String userID = joinID.getText().toString();
                    String userPW = joinPW.getText().toString();
                    String reUserPW = rejoinPW.getText().toString();
                    String userName = joinName.getText().toString();
                    String userPhone = joinPhone.getText().toString();
                    String userAddress = joinAddress.getText().toString();

                    editor.putString("userID", userID);
                    editor.putString("userPW", userPW);
                    editor.putString("reUserPW", reUserPW);
                    editor.putString("userName", userName);
                    editor.putString("userPhone", userPhone);
                    editor.putString("userAddress", userAddress);

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                // 아이디 중복 확인을 하지 않았을 때
                else if (!checkID) {
                    Toast.makeText(getApplicationContext(), "아이디를 확인하세요", Toast.LENGTH_SHORT).show();
                }
                // 입력이 제대로 되어있지 않았을 때
                else {
                    Toast.makeText(getApplicationContext(), "입력을 다시 확인하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    Button backBtn;
    private EditText joinID, joinPW, rejoinPW, joinName, joinPhone, joinAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setUserData(View view) {
        joinID = (EditText) findViewById(R.id.joinID);
        joinPW = (EditText) findViewById(R.id.joinPW);
        rejoinPW = (EditText) findViewById(R.id.reJoinPW);
        joinName = (EditText) findViewById(R.id.joinName);
        joinPhone = (EditText) findViewById(R.id.joinPhone);
        joinAddress = (EditText) findViewById(R.id.joinAddress);

        SharedPreferences prefs = getSharedPreferences("user_info", 0);
        SharedPreferences.Editor editor = prefs.edit();

        String userID = joinID.getText().toString();
        String userPW = joinPW.getText().toString();
        String reUserPW = rejoinPW.getText().toString();
        String userName = joinName.getText().toString();
        String userPhone = joinPhone.getText().toString();
        String userAddress = joinAddress.getText().toString();

        if(userID == null) {
            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(userPW == null) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(reUserPW == null) {
            Toast.makeText(getApplicationContext(), "비밀번호를 다시 한 번 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(userName == null) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(userPhone == null) {
            Toast.makeText(getApplicationContext(), "번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(userAddress == null) {
            Toast.makeText(getApplicationContext(), "주소를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString("userID", userID);
            editor.putString("userPW", userPW);
            editor.putString("reUserPW", reUserPW);
            editor.putString("userName", userName);
            editor.putString("userPhone", userPhone);
            editor.putString("userAddress", userAddress);

            editor.apply();
        }


    }
}
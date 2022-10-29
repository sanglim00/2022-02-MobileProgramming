package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

    public void DialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Intent inIntent = getIntent();
        Boolean loginCheck = inIntent.getBooleanExtra("LoginCheck", false);

        // 로그인 상태라면
        if (loginCheck) {
            SharedPreferences sharedPreferences= getSharedPreferences("user_info", 0);
            String id = sharedPreferences.getString("userID","");
            String name = sharedPreferences.getString("joinName", "");
            String phone = sharedPreferences.getString("joinPhone", "");
            String address = sharedPreferences.getString("joinAddress", "");

            builder.setTitle("내 정보").setMessage("아이디: " + id + "\n이름: " + name +"\n전화번호: " + phone + "\n주소: " + address);
            builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    Log.v("dialog", "close");
                }
            });
        }
        // 비회원 접속 상태라면
        else {
            builder.setTitle("내 정보").setMessage("비회원으로 접속중입니다. \n로그인 또는 회원가입을 진행해주세요.");
            builder.setPositiveButton("회원가입하기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("로그인하기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }




        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
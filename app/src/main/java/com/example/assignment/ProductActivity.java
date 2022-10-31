package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        Button backPtoM = (Button) findViewById(R.id.backPtoM);
        backPtoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void DialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Intent inIntent = getIntent();

        // 내 정보 보기 클릭 시 회원이면 정보를 보여주고 비회원이면 로그인이나 회원가입으로 유도하기 위해 전달받은 변수
        Boolean loginCheck = inIntent.getBooleanExtra("LoginCheck", false);
        // 유저마다의 고유 숫자이다. 로그인 후 내 정보를 확인할때 알맞은 정보를 가져오기 위해 사용한다.
        int userNumber = inIntent.getIntExtra("UserNumber", 0);

        // 로그인 상태라면 (회원일 경우 고유 숫자로 내 정보를 가져와 보여준다)
        if (loginCheck) {
            SharedPreferences sharedPreferences= getSharedPreferences("user_info", 0);
            String id = sharedPreferences.getString("userID"+userNumber,"");
            String name = sharedPreferences.getString("userName"+userNumber, "");
            String phone = sharedPreferences.getString("userPhone"+userNumber, "");
            String address = sharedPreferences.getString("userAddress"+userNumber, "");
            String agree = sharedPreferences.getString("ppAgree"+userNumber, "");

            builder.setTitle("내 정보").setMessage("아이디: " + id + "\n이름: " + name +"\n전화번호: " + phone + "\n주소: " + address + "\n개인정보처리방침: "+agree);
            builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    Log.v("dialog", "close");
                }
            });
        }
        // 비회원 접속 상태라면 로그인 또는 회원가입으로 유도한다.
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
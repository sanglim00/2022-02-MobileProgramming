package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


    }
    public void DialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("내 정보").setMessage("내 정보");
        builder.setPositiveButton("회원가입하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
//                Toast.makeText(getApplicationContext(),"닫기 버튼 클릭", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
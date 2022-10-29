package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    Button checkIDBtn, joinFinishBtn,  backBtn;
    private EditText joinID, joinPW, rejoinPW, joinName, joinPhone, joinAddress ;
    private RadioButton ppRadio;

    SharedPreferences spref;
    SharedPreferences.Editor editor;

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

        checkIDBtn = (Button) findViewById(R.id.checkIDBtn);
        checkIDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("user_info", 0);
                SharedPreferences.Editor editor = prefs.edit();

            }
        });

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

                if(savedInstanceState == null) {
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
            }
        });

    }

}
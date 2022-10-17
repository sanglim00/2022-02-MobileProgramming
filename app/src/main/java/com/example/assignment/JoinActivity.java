package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity {
    Button backBtn;
    private EditText joinID, joinPW, joinPW2, joinName, joinPhone, joinAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinID = (EditText) findViewById(R.id.joinID);
        joinPW = (EditText) findViewById(R.id.joinPW);
//        joinPW2 = (EditText) findViewById(R.id.joinPW2);
        joinName = (EditText) findViewById(R.id.joinName);
        joinPhone = (EditText) findViewById(R.id.joinPhone);
        joinAddress = (EditText) findViewById(R.id.joinAddress);


        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
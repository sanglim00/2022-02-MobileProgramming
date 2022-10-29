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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    Button checkIDBtn, joinFinishBtn, backBtn;
    private EditText joinID, joinPW, rejoinPW, joinName, joinPhone, joinAddress ;
    private RadioGroup ppRadio;

    boolean checkID = false, checkPW = false, checkAgree = false;


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
                    checkID = false;
                }
                // 이미 존재하는 아이디일 경우
                else if(joinID.getText().toString().equals(id)) {
                    idCheckMsg.setText("이미 존재하는 아이디입니다. 다른 아이디를 설정해주세요");
                    checkID = false;
                }
                else {
                    idCheckMsg.setText("사용 가능한 아이디입니다.");
                    checkID = true;
                }
                idCheckMsg.setVisibility(View.VISIBLE);
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

                // 비밀번호 검사 - 숫자, 특수문자가 포함하기
                String symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])";
                // 비밀번호 검사 - 영문자 대소문자 적어도 하나씩 포함하기
                String alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])";

                Pattern Psymbol = Pattern.compile(symbol);
                Pattern Palpha = Pattern.compile(alpha);

                Matcher Msymbol = Psymbol.matcher(joinPW.getText().toString());
                Matcher Malpha = Palpha.matcher(joinPW.getText().toString());

                // 비밀번호 입력 체크
                if (Msymbol.find() && Malpha.find() && joinPW.getText().toString().equals(rejoinPW.getText().toString())) checkPW = true;
                // 비밀번호 검사에 통과하지 못했을 때
                else if (!Msymbol.find() || !Malpha.find()) {
                    Toast.makeText(getApplicationContext(), "비밀번호에 숫자, 특수문자, 대소문자가 포함되어야합니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 비밀번호와 비밀번호 확인이 다를때
                else {
                    Toast.makeText(getApplicationContext(), "두 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                    checkPW = false;
                    return ;
                }

                // 개인정보처리방침 동의 체크
                ppRadio = findViewById( R.id.ppRadio );
                RadioButton checkedRadio = findViewById( ppRadio.getCheckedRadioButtonId());

                // 아무것도 선택 안했을 때
                if(checkedRadio == null) {
                    Toast.makeText(getApplicationContext(), "개인정보 이용약관에 동의하셔야 회원가입이 가능합니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 동의하면 true 아니면 false
                else if( checkedRadio.getText().toString().equals("동의합니다")) checkAgree = true;
                else {
                    checkAgree = false;
                    return;
                }

                // 회원가입
                if(checkPW && checkID && checkAgree) {
                    SharedPreferences prefs = getSharedPreferences("user_info", 0);
                    SharedPreferences.Editor editor = prefs.edit();

                    String userID = joinID.getText().toString();
                    String userPW = joinPW.getText().toString();
                    String reUserPW = rejoinPW.getText().toString();
                    String userName = joinName.getText().toString();
                    String userPhone = joinPhone.getText().toString();
                    String userAddress = joinAddress.getText().toString();
                    String ppAgree = checkedRadio.getText().toString();

                    editor.putString("userID", userID);
                    editor.putString("userPW", userPW);
                    editor.putString("reUserPW", reUserPW);
                    editor.putString("userName", userName);
                    editor.putString("userPhone", userPhone);
                    editor.putString("userAddress", userAddress);
                    editor.putString("ppAgree", ppAgree);

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                // 입력이 제대로 되어있지 않았을 때
                else {
                    Toast.makeText(getApplicationContext(), "입력을 다시 확인하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
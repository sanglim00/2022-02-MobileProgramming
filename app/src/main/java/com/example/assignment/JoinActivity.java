package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private EditText joinID, joinPW, rejoinPW, joinName, joinPhone, joinAddress;
    private RadioGroup ppRadio;

    boolean existID = false, checkID = false, checkPW = false, checkAgree = false;


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

        // 만약 아이디 확인을 한 후 값이 다시 바뀌었다면 아이디 확인을 다시 하도록 한다 checkID가 true일 때만 확인된 것이므로
        joinID = (EditText) findViewById(R.id.joinID);
        joinID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkID = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    public void checkUserId(View view) {
        // 아이디 중복확인
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", 0);
        int savedCount = sharedPreferences.getInt("count", 0);
        TextView idCheckMsg = (TextView) findViewById(R.id.idCheckMsg);

        // 저장된 아이디와 겹치는 경우 existID를 true로 바꾸고 반복문 정지 -> 이미 존재하는 아이디이므로 다른 것으로 바꾸도록한다
        for (int i =1; i <= savedCount; i++) {
            String data =  sharedPreferences.getString("userID"+i, "");
            if (joinID.getText().toString().equals(data)) {
                existID = true;
                break;
            }
            else
                existID = false;
        }
        joinID = (EditText) findViewById(R.id.joinID);


        // 입력값이 비어있을 경우
        if (joinID.getText().toString().isEmpty()) {
            idCheckMsg.setText("아이디를 입력해주세요");
            checkID = false;
        }
        // 이미 존재하는 아이디일 경우
        else if (existID == true) {
            idCheckMsg.setText("이미 존재하는 아이디입니다. 다른 아이디를 설정해주세요");
            checkID = false;
        // 사용가능한 아이디이므로 사용을 허가한다
        } else {
            idCheckMsg.setText("사용 가능한 아이디입니다.");
            checkID = true;
        }
        idCheckMsg.setVisibility(View.VISIBLE);


    }

    public void joinButtonClicked(View view) {
        // 회원가입 하기 버튼 클릭 시

        joinID = (EditText) findViewById(R.id.joinID);
        joinPW = (EditText) findViewById(R.id.joinPW);
        rejoinPW = (EditText) findViewById(R.id.reJoinPW);
        joinName = (EditText) findViewById(R.id.joinName);
        joinPhone = (EditText) findViewById(R.id.joinPhone);
        joinAddress = (EditText) findViewById(R.id.joinAddress);

        // 모든 항목이 입력되었는지 체크
        if (joinID.getText().toString().isEmpty() ||
                joinPW.getText().toString().isEmpty() ||
                rejoinPW.getText().toString().isEmpty() ||
                joinName.getText().toString().isEmpty() ||
                joinPhone.getText().toString().isEmpty() ||
                joinAddress.getText().toString().isEmpty()
        ) {
            Toast.makeText(getApplicationContext(), "입력되지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkID) {
            Toast.makeText(getApplicationContext(), "아이디를 확인해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 비밀번호 검사 - 숫자, 특수문자가 포함하기
        String symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])";
        // 비밀번호 검사 - 영문자 대소문자 적어도 하나씩 포함하기
        String alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])";

        Pattern Psymbol = Pattern.compile(symbol);
        Pattern Palpha = Pattern.compile(alpha);

        Matcher Msymbol = Psymbol.matcher(joinPW.getText().toString());
        Matcher Malpha = Palpha.matcher(joinPW.getText().toString());

        // 비밀번호는 5글자 이상 입력해야만 함
        if (joinPW.getText().toString().length() < 5) {
            Toast.makeText(getApplicationContext(), "비밀번호는 5글자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        // 비밀번호 입력 체크
        if (Msymbol.find() && Malpha.find() && joinPW.getText().toString().equals(rejoinPW.getText().toString()))
            checkPW = true;
        // 비밀번호 검사에 통과하지 못했을 때
        else if (!Msymbol.find() || !Malpha.find()) {
            Toast.makeText(getApplicationContext(), "비밀번호에 숫자, 특수문자, 대소문자가 포함되어야합니다", Toast.LENGTH_SHORT).show();
            return;
        }
        // 비밀번호와 비밀번호 확인이 다를때
        else {
            Toast.makeText(getApplicationContext(), "두 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
            checkPW = false;
            return;
        }

        // 개인정보처리방침 동의 체크
        ppRadio = findViewById(R.id.ppRadio);
        RadioButton checkedRadio = findViewById(ppRadio.getCheckedRadioButtonId());

        // 아무것도 선택 안했을 때
        if (checkedRadio == null) {
            Toast.makeText(getApplicationContext(), "개인정보 이용약관에 동의하셔야 회원가입이 가능합니다", Toast.LENGTH_SHORT).show();
            return;
        }
        // 동의하면 true 아니면 false
        else if (checkedRadio.getText().toString().equals("동의합니다")) checkAgree = true;
        else {
            checkAgree = false;
            Toast.makeText(getApplicationContext(), "개인정보 이용약관에 동의하셔야 회원가입이 가능합니다", Toast.LENGTH_SHORT).show();
            return;
        }

        // 회원가입

        SharedPreferences prefs = getSharedPreferences("user_info", 0);
        SharedPreferences.Editor editor = prefs.edit();

        // 유저마다 고유 숫자가 있으므로 값을 가져와 하나씩 증가하여 숫자를 부여한다.
        int cnt = prefs.getInt("count", 0);
        if (cnt >= 0) cnt += 1;

        String userID = joinID.getText().toString();
        String userPW = joinPW.getText().toString();
        String userName = joinName.getText().toString();
        String userPhone = joinPhone.getText().toString();
        String userAddress = joinAddress.getText().toString();
        String ppAgree = checkedRadio.getText().toString();

        // 유저의 고유 숫자를 포함한 키값에 값들을 저장한다
        editor.putInt("count", cnt);
        editor.putString("userID" + cnt, userID);
        editor.putString("userPW" + cnt, userPW);
        editor.putString("userName" + cnt, userName);
        editor.putString("userPhone" + cnt, userPhone);
        editor.putString("userAddress" + cnt, userAddress);
        editor.putString("ppAgree" + cnt, ppAgree);

        editor.commit();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }

}



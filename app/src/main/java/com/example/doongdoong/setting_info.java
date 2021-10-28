package com.example.doongdoong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.StringTokenizer;

public class setting_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_info);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference genderRef = database.getReference("gender");
        DatabaseReference nameRef= database.getReference("name");
        DatabaseReference birthRef= database.getReference("birth");

        EditText et_name = (EditText)findViewById(R.id.name);
        EditText et_year = (EditText)findViewById(R.id.year);
        EditText et_month = (EditText)findViewById(R.id.month);
        EditText et_day = (EditText)findViewById(R.id.day);


        ImageButton btn_boy = (ImageButton) findViewById(R.id.btn_boy);
        btn_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_boy:
                        btn_boy.setSelected(true);
                        genderRef.setValue("boy");
                        break;
                }
            }
        });
        ImageButton btn_girl = (ImageButton) findViewById(R.id.btn_girl);
        btn_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_girl:
                        btn_girl.setSelected(true);
                        genderRef.setValue("girl");
                        break;
                }
            }
        });

        nameRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                et_name.setText(snapshot.getValue().toString());

            }//onDataChange
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }//onCancelled
        });

        genderRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gender = snapshot.getValue().toString();
                if (gender.equals("girl")){
                    btn_girl.setSelected(true);
                }
                else if (gender.equals("boy")){
                    btn_boy.setSelected(true);
                }
            }//onDataChange
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }//onCancelled
        });
        birthRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringTokenizer st = new StringTokenizer(snapshot.getValue().toString(), ".");
                et_year.setText(st.nextToken());
                et_month.setText(st.nextToken());
                et_day.setText(st.nextToken());
            }//onDataChange
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }//onCancelled
        });


        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting.class);
                startActivity(intent);
            }
        });


        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nameRef.setValue(et_name.getText().toString());
                birthRef.setValue(et_year.getText().toString() + "년 " + et_month.getText().toString() + "월 " + et_day.getText().toString() +"일" );
            }
        });

        RelativeLayout setting_info = (RelativeLayout)findViewById(R.id.setting_info);
        setting_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_name.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(et_year.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(et_month.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(et_day.getWindowToken(), 0);
            }
        });

    }
}
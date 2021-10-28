package com.example.doongdoong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setting_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_info);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference genderRef = database.getReference("gender");
        DatabaseReference nameRef= database.getReference("name");
        DatabaseReference birthRef= database.getReference("birth");

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting.class);
                startActivity(intent);
            }
        });

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

        EditText et_name = (EditText)findViewById(R.id.name);
        EditText et_year = (EditText)findViewById(R.id.year);
        EditText et_month = (EditText)findViewById(R.id.month);
        EditText et_day = (EditText)findViewById(R.id.day);
        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nameRef.setValue(et_name.getText().toString());
                birthRef.setValue(et_year.getText().toString() + "년 " + et_month.getText().toString() + "월 " + et_day.getText().toString() +"일" );
            }
        });
    }
}
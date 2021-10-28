package com.example.doongdoong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class signup_info_child extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_info_child);

        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup_record_start.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_disable:
                        btn_next.setSelected(true);
                }
            }
        });

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup_num.class);
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
                        break;
                }
            }
        });


    }
}
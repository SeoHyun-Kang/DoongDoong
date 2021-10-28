package com.example.doongdoong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        });

        ImageButton btn_check_code = (ImageButton) findViewById(R.id.btn_check_code);
        btn_check_code.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting_code.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_check_code:
                        btn_check_code.setSelected(true);
                }
            }
        });

        ImageButton btn_edit_info = (ImageButton) findViewById(R.id.btn_edit_info);
        btn_edit_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting_info.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_check_code:
                        btn_edit_info.setSelected(true);
                }
            }
        });

        ImageButton btn_edit_record= (ImageButton) findViewById(R.id.btn_edit_basic_record);
        btn_edit_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting_default.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_edit_basic_record:
                        btn_edit_record.setSelected(true);
                }
            }
        });

        //SwitchCompat alert = (SwitchCompat) findViewById(R.id.alert);


    }
}
package com.example.doongdoong;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setting_code extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_code);

        EditText et = (EditText)findViewById(R.id.doong_num);
        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String doong_number = et.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference rootRef = database.getReference("doong_number");

                rootRef.setValue(doong_number);
            }
        });

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), setting.class);
                startActivity(intent);
            }
        });

        RelativeLayout setting_code = (RelativeLayout)findViewById(R.id.setting_code);
        EditText doong_num = (EditText)findViewById(R.id.doong_num) ;
        setting_code.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(doong_num.getWindowToken(), 0);
            }
        });
    }
}
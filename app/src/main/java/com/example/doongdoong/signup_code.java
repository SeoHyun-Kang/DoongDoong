package com.example.doongdoong;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_code extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_code);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference doong_numberRef = database.getReference("doong_number");

        TextView doong_code = (TextView) findViewById(R.id.doong_num);
        ImageButton btn_next = (ImageButton) findViewById(R.id.btn_disable);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);

                switch (view.getId()) {
                    case R.id.btn_disable:
                        btn_next.setSelected(true);
                        doong_numberRef.setValue(doong_code.getText().toString());
                }
            }
        });
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup_record_start.class);
                startActivity(intent);
            }
        });



//        private void hideKeyboard() {
//            InputMethodManager imm = (InputMethodManager) getSystemService(signup_code.INPUT_METHOD_SERVICE);
//            //Find the currently focused view, so we can grab the correct window token from it.
//            View view = getCurrentFocus();
//            //If no view currently has focus, create a new one, just so we can grab a window token from it
//            if (view == null) {
//                view = new View(this);
//            }
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }

//        public boolean onTouchEvent(MotionEvent event) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//            return true;
//        }
//
//        @Override
//        public boolean dispatchTouchEvent(MotionEvent ev) {
//            View view = getCurrentFocus();
//            if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
//                int scrcoords[] = new int[2];
//                view.getLocationOnScreen(scrcoords);
//                float x = ev.getRawX() + view.getLeft() - scrcoords[0];
//                float y = ev.getRawY() + view.getTop() - scrcoords[1];
//                if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
//                    ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
//            }
//            return super.dispatchTouchEvent(ev);
//        }
        RelativeLayout signup_code = (RelativeLayout)findViewById(R.id.signup_code);
        EditText doong_num = (EditText)findViewById(R.id.doong_num) ;
        signup_code.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(doong_num.getWindowToken(), 0);
            }
        });

    }

}
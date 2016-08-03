package com.song.multiitemlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {


    EditText et_phone_number;
    Button bt_action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        bt_action = (Button) findViewById(R.id.bt_action);

        bt_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber=et_phone_number.getText().toString();
                ContactContentActivity.start(MainActivity.this,phoneNumber );
            }
        });
    }
}

package com.example.cai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ASUS on 2017/5/10.
 */

public class Register extends Activity {
    Button button1;
    TextView textView1;
    EditText editText1,editText2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        textView1= (TextView) findViewById(R.id.register_xuxiao);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.this.finish();
            }
        });
        editText1= (EditText) findViewById(R.id.register_zhanghao);
        String zhanghao=editText1.getText().toString();
        editText2= (EditText) findViewById(R.id.register_password);
        String password=editText1.getText().toString();
        button1= (Button) findViewById(R.id.register_finish);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Web.class);
                startActivity(intent);

            }
        });
    }
}

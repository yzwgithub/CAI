package activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cai.R;

import java.util.Map;

import util.Constance;
import util.MyApplication;
import util.SharedHelper;


public class Login extends AppCompatActivity {
    private EditText editText1,editText2;
    private Button button;
    private TextView go_login;
    private CheckBox checkBox1,checkBox2;
    private SharedHelper sharedHelper;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String>data=sharedHelper.read();
        editText1.setText(data.get("username"));
        editText2.setText(data.get("password"));
        if (!data.get("username").equals("")){
            checkBox1.setChecked(true);
            checkBox2.setChecked(true);
        }
    }
    private void login(final String username, final String password){
        final String url= Constance.url+"servlet/LoginServlet?account="+username+"&"+"password="+password;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.equals("200")){
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    Login.this.finish();
                }else if(s.equals("404")) {
                    Toast.makeText(Login.this,"用户名或密码输入错误",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this,"网络连接超时，请检查网络连接！",Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("Login");
        MyApplication.getHttpQueues().add(request);
    }
    private void initView(){
        editText1 = (EditText) findViewById(R.id.main_zhanghao);
        editText2 = (EditText) findViewById(R.id.main_password);
        checkBox1= (CheckBox) findViewById(R.id.remember);
        checkBox2= (CheckBox) findViewById(R.id.self_login);
        button= (Button) findViewById(R.id.main_entry);
        go_login= (TextView) findViewById(R.id.toast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText1.getText().toString();
                String password = editText2.getText().toString();
                login(username,password);
            }
        });
        context=getApplicationContext();
        sharedHelper=new SharedHelper(context);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String username = editText1.getText().toString();
                String password = editText2.getText().toString();
                if (isChecked){
                    checkBox2.setChecked(true);
                    sharedHelper.save(username, password);
                }else sharedHelper.save(null, null);
            }
        });
        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
}

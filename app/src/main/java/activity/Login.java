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

import util.MyApplication;
import util.SharedHelper;


public class Login extends AppCompatActivity {
    private TextView textView1,textView2;
    private EditText editText1,editText2;
    private Button button;
    private CheckBox checkBox1,checkBox2;
    private SharedHelper sharedHelper;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        context=getApplicationContext();
        sharedHelper=new SharedHelper(context);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String username = editText1.getText().toString();
                    String password = editText2.getText().toString();
                if (isChecked)
                    sharedHelper.save(username, password);
                else sharedHelper.save(null, null);
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked&&checkBox1.isChecked()){
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    Login.this.finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String>data=sharedHelper.read();
        editText1.setText(data.get("username"));
        editText2.setText(data.get("password"));
        if (data!=null){
            checkBox1.setChecked(true);
        }
    }
    private void login(){
        String username = editText1.getText().toString();
        String password = editText2.getText().toString();
        final String url="http://192.168.1.159:8080/servlet/LoginServlet?account="+username+"&"+"password="+password;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();
                if (s.equals("登录成功")){
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    Login.this.finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("Login");
        MyApplication.getHttpQueues().add(request);
    }
    private void initView(){
        textView1= (TextView) findViewById(R.id.main_register);
        textView2= (TextView) findViewById(R.id.main_quxiao);
        editText1 = (EditText) findViewById(R.id.main_zhanghao);
        editText2 = (EditText) findViewById(R.id.main_password);
        checkBox1= (CheckBox) findViewById(R.id.remember);
        checkBox2= (CheckBox) findViewById(R.id.self_login);
        button= (Button) findViewById(R.id.main_entry);
    }
}

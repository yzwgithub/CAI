package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cai.R;

import application.MyApplication;


public class Login extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    EditText editText1,editText2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        textView1= (TextView) findViewById(R.id.main_register);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        textView2= (TextView) findViewById(R.id.main_quxiao);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.finish();
            }
        });
        textView3= (TextView) findViewById(R.id.toast);
        button= (Button) findViewById(R.id.main_entry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1 = (EditText) findViewById(R.id.main_zhanghao);
                editText2 = (EditText) findViewById(R.id.main_password);
                final String username = editText1.getText().toString();
                final String password = editText2.getText().toString();
                String url="http://192.168.1.101:8080/servlet/LoginServlet?account="+username+"&"+"password="+password;
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
        });
    }
}

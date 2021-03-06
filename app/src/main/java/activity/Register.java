package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cai.R;

import util.Constance;
import util.MyApplication;

/**
 * Created by ASUS on 2017/7/17.
 */

public class Register extends Activity{
    EditText editText1,editText2,editText3;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
    }
    private void initView(){
        editText1= (EditText) findViewById(R.id.register_zhanghao);
        editText2= (EditText) findViewById(R.id.register_password);
        editText3= (EditText) findViewById(R.id.confirm_password);
        button= (Button) findViewById(R.id.register_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editText1.getText().toString();
                String password=editText2.getText().toString();
                String url= Constance.url+"servlet/RegisterServlet?account="+username+"&"+"password="+password;
                register(url);
    }
    private void register(String url){
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(Register.this,s,Toast.LENGTH_SHORT).show();
                if (s.equals("注册成功")){
                    Intent intent=new Intent(Register.this,Login.class);
                    startActivity(intent);
                    Register.this.finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Register.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("Login");
        MyApplication.getHttpQueues().add(request);
        }
        });
    }
}

package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cai.R;

import java.util.HashMap;
import java.util.Map;

import util.Constance;
import util.MyApplication;

/**
 * Created by ASUS on 2018/5/1.
 */

public class ResetPassword extends AppCompatActivity {
    private EditText editText1,editText;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reset_password);
        initView();
    }

    private void initView(){
        editText1= (EditText) findViewById(R.id.new_password);
        editText= (EditText) findViewById(R.id.ensure_password);
        button= (Button) findViewById(R.id.reset_commit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= Constance.url+"servlet/ResetPasswordServlet?";
                String new_password=editText1.getText().toString();
                String ensure_password=editText.getText().toString();
                sendMessage(url,new_password,ensure_password);
            }
        });
    }

    private void sendMessage(String url,final String new_password,final String ensure_password){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(ResetPassword.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ResetPassword.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map=new HashMap();
                map.put("new_password",new_password);
                map.put("ensure_password",ensure_password);
                return map;
            }
        };
        MyApplication.getHttpQueues().add(request);
    }
}

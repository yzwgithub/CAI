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

public class Suggestion extends AppCompatActivity {
    private EditText text1,text2;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.suggestion);
        initView();
    }

    private void initView(){
        text1= (EditText) findViewById(R.id.s_phone);
        text2= (EditText) findViewById(R.id.s_de);
        button= (Button) findViewById(R.id.s_mit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=Constance.url+"servlet/CommitServlet?";
                String s_phone=text1.getText().toString();
                String s_de=text2.getText().toString();
                sendMassage(url,s_phone,s_de);
            }
        });
    }

    private void sendMassage(String url, final String s_phone, final String s_de){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(Suggestion.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Suggestion.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map=new HashMap();
                map.put("s_phone",s_phone);
                map.put("s_de",s_de);
                return map;
            }
        };
        MyApplication.getHttpQueues().add(request);
    }
}

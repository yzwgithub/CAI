package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cai.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        editText1= (EditText) findViewById(R.id.register_zhanghao);
        editText2= (EditText) findViewById(R.id.register_password);
        editText3= (EditText) findViewById(R.id.confirm_password);
        button= (Button) findViewById(R.id.register_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editText1.getText().toString();
                String password=editText2.getText().toString();
                GetThread getThread=new GetThread(username,password);
                getThread.start();
            }
        });
    }
    class GetThread extends Thread{
        String name;
        String password;
        public GetThread(String name,String password){
            this.name=name;
            this.password=password;
        }

        @Override
        public void run() {
            HttpClient httpClient=new DefaultHttpClient();
            String url="http://192.168.1.122:8080/servlet/RegisterServlet?account="+name+"&"+"password="+password;
            HttpGet httpGet=new HttpGet(url);
            try {
                HttpResponse responce=httpClient.execute(httpGet);
                if (responce.getStatusLine().getStatusCode()==200){
                    HttpEntity entry=responce.getEntity();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(entry.getContent()));
                    String result=reader.readLine();
                    Intent intent=new Intent(Register.this,Login.class);
                    startActivity(intent);
                    Register.this.finish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

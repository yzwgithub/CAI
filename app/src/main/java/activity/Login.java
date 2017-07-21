package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cai.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Login extends Activity {
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
                GetThread getThread=new GetThread(username,password);
                getThread.start();
            }
        });
    }

    class GetThread extends Thread{
        String name;
        String password;
        String result;
        public GetThread(String name, String password){
            this.name=name;
            this.password=password;
        }
        @Override
        public void run() {
            HttpClient httpClient=new DefaultHttpClient();
            String url="http://192.168.1.101:8080/servlet/LoginServlet?account="+name+"&"+"password="+password;
            HttpGet httpGet=new HttpGet(url);
            try {
                HttpResponse responce=httpClient.execute(httpGet);
                if (responce.getStatusLine().getStatusCode()==200){
//                    HttpEntity entry=responce.getEntity();
//                    BufferedReader reader=new BufferedReader(new InputStreamReader(entry.getContent()));
//                    while (reader.readLine()!=null){
//                        result+=reader.readLine();
//                    }
                        Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                        Login.this.finish();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

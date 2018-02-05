package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.cai.R;

import java.util.Map;

import util.SharedHelper;

/**
 * Created by ASUS on 2017/5/10.
 */

public class AppStart extends Activity {
    private SharedHelper sharedHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.startactivity, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(5000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {

            }
            @Override
            public void onAnimationStart(Animation arg0) {

            }

        });
    }
    protected void redirectTo() {
        sharedHelper=new SharedHelper(AppStart.this);
        Map<String,String> data=sharedHelper.read();
        Intent intent;
        if (!data.get("username").equals("")||!data.get("password").equals("")){
             intent= new Intent(this, MainActivity.class);
        }else intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}

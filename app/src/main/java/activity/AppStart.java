package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.cai.R;

/**
 * Created by ASUS on 2017/5/10.
 */

public class AppStart extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

        });
    }

    protected void redirectTo() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}

package activity;

import android.animation.FloatEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.cai.R;
import util.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import adapter.SectionsPagerAdapter;
import fragment.BuyFragment;
import fragment.MainFragment;

/**
 * Created by ASUS on 2017/6/13.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,ViewPager.OnPageChangeListener {

    private BottomNavigationBar bottomNavigationBar;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private SlidingMenu slidingMenu;
    private static final String TAG = "Login";
    private FloatEvaluator mFloatEvaluator;
    private TextView textView1,textView2,textView3,textView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.sliding_menu_layout);
        initView();
        initButtonNavigation();
        initViewPager();
        initSlidingMenu();
    }

    @Override
    public void onTabSelected(int position) {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setCurrentItem(position);

}

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.buttom_bar);
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initButtonNavigation(){
        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.buttom_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_tab_home,"首页")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.mipmap.ic_category,"高级定制")).setActiveColor("#ECECEC")
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);//注册监听事件
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new BuyFragment());
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }
    private void initSlidingMenu(){
        slidingMenu=new SlidingMenu(this);
        slidingMenu.setOnSlideListener(new SlidingMenu.OnSlideListener() {
            @Override
            public void onOpen() {
                Toast.makeText(MainActivity.this, "onOpen", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {
                Toast.makeText(MainActivity.this, "onClose", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDraging(float fraction) {
                Log.i(TAG, "fraction:" + fraction);
                mFloatEvaluator=new FloatEvaluator();
                mFloatEvaluator.evaluate(fraction,0,720);
            }
        });
    }
    private void initView(){
        textView1= (TextView) findViewById(R.id.personal_information);
        textView2= (TextView) findViewById(R.id.my_pay);
        textView3= (TextView) findViewById(R.id.reset_password);
        textView4= (TextView) findViewById(R.id.suggest);
        textView1.setOnClickListener(new onClick());
        textView2.setOnClickListener(new onClick());
        textView3.setOnClickListener(new onClick());
        textView4.setOnClickListener(new onClick());
    }

    private class onClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.personal_information:
                    Intent intent=new Intent(MainActivity.this,PersonalActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_pay:
                    Intent intent1=new Intent(MainActivity.this,MyPay.class);
                    startActivity(intent1);
                    break;
                case R.id.reset_password:
                    Intent intent2=new Intent(MainActivity.this,ResetPassword.class);
                    startActivity(intent2);
                    break;
                case R.id.suggest:
                    Intent intent3=new Intent(MainActivity.this,Suggestion.class);
                    startActivity(intent3);
                    break;
            }
        }
    }
}

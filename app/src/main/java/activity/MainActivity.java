package activity;

import android.animation.FloatEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.cai.R;
import com.example.cai.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import adapter.SectionsPagerAdapter;
import fragement.CatagoryFragment;
import fragement.DiscoverFragment;
import fragement.HomeFragment;
import fragement.UserCentralFragment;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.sliding_menu_layout);
        initBottonNavigation();
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

    private void initBottonNavigation(){
        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.buttom_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_tab_home,"HOME")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.mipmap.ic_category,"CATLOG")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_center,"MINE")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.mipmap.ic_category,"DISC")).setActiveColor("#ECECEC")
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);//注册监听事件
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new CatagoryFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new UserCentralFragment());
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
}

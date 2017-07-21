package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.cai.R;

/**
 * Created by ASUS on 2017/7/18.
 */

public class Test extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        imageView= (ImageView) findViewById(R.id.img_test);
    }
}

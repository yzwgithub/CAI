package fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cai.R;

/**
 * Created by ASUS on 2017/6/13.
 */

public class DiscoverFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement3,container,false);
        return view;
    }

    private static DiscoverFragment instance=null;
    public static DiscoverFragment newInstance() {
        if(instance==null){
            instance= new DiscoverFragment();
        }
        return instance;
    }

    public DiscoverFragment() {
        // Required empty public constructor
    }
}

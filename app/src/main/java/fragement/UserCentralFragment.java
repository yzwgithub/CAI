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

public class UserCentralFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement,container,false);
        return view;
    }
    private static UserCentralFragment instance=null;
    public static UserCentralFragment newInstance() {
        if(instance==null){
            instance= new UserCentralFragment();
        }
        return instance;
    }

    public UserCentralFragment() {
        // Required empty public constructor
    }
}

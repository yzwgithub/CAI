package fragement;

import android.animation.FloatEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cai.DividerItemDecoration;
import com.example.cai.R;
import com.example.cai.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerViewAdapter;

/**
 * Created by ASUS on 2017/6/13.
 */

public class HomeFragment extends Fragment {
    private RecyclerViewAdapter adapter;
    private List<String> datas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_home,container,false);
        return view;
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("item:" + i);
        }
    }
    private void initRecyclerView(){
        RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(adapter=new RecyclerViewAdapter(getActivity(),datas));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initRecyclerView();
        initToolBar();
    }
    private void initToolBar(){
        Toolbar toolbar= (Toolbar) getView().findViewById(R.id.tb_toolbar);
        toolbar.setPopupTheme(R.style.Widget_AppCompat_PopupMenu);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle("易GO");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        break;
                    case  R.id.action_item1:
                        break;
                    case R.id.action_item2:
                        break;

                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

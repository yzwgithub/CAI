package fragement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cai.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.RecyclerViewAdapter;
import util.MyApplication;

/**
 * Created by ASUS on 2017/6/13.
 */

public class HomeFragment extends Fragment {
    final String url="http://192.168.1.103:8080/servlet/MainServlet";
    final String imgurl="http://192.168.1.103:8080/servlet/MainServlet";
    private RecyclerViewAdapter adapter;
    Bitmap []bitmaps;
    String []ImgDiscs=new String [20];
    int finishedNumbers = 0;//已经下载完的图片数
    private boolean isFirstBoot = true;//判断第一次启动，开始下载图片
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_home,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolBar();
        if (isFirstBoot) {
            getImgUrl();
            isFirstBoot=false;
        }
        initRecyclerView();
    }
    private void initData(String []ImgDiscs) {
        this.ImgDiscs=ImgDiscs;
        bitmaps=new Bitmap[20];
        for (int i=0;i<20;i++){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
            bitmaps[i]=bitmap;
        }
    }
    private void initRecyclerView(){
        RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(adapter=new RecyclerViewAdapter(getActivity(),bitmaps,ImgDiscs));
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
    public void downLoadPhoto(String Imgurl){
        ImageRequest imageRequest=new ImageRequest(Imgurl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                bitmaps[finishedNumbers] = bitmap;
                finishedNumbers++;
                adapter.notifyDataSetChanged();
            }
        }, 10, 10, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueues().add(imageRequest);
    }
    public void getImgUrl(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(imgurl, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    JSONArray jsonArray1=new JSONArray(jsonArray.toString());
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray1.getJSONObject(i);
                        downLoadPhoto(object.getString("src"));
                        ImgDiscs[i]=object.getString("disc");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueues().add(jsonArrayRequest);
        initData(ImgDiscs);
    }
}

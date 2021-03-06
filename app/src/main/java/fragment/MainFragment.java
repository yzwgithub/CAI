package fragment;
import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cai.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Listener.OnItemClickListener;
import activity.Img_Detail;
import adapter.RecyclerViewAdapter;
import util.Constance;
import util.MyApplication;
import util.SharedHelper;
import util.SlidingMenu;

/**
 * Created by ASUS on 2017/6/13.
 */

public class MainFragment extends Fragment {
    private RecyclerViewAdapter adapter;
    private SharedHelper sharedHelper;
    private Bitmap []bitmaps=new Bitmap[20];
    private String []ImgDiscs=new String [20];
    private String []ImgDetail=new String[20];
    int finishedNumbers = 0;//已经下载完的图片数
    private boolean isFirstBoot = true;//判断第一次启动，开始下载图片
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragement,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolBar();
        initData();
        initRecyclerView();
        if (isFirstBoot) {
            isFirstBoot=false;
            getImgUrl();
        }
    }

    private void initData(){
        for (int i=0;i<20;i++){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
            bitmaps[i]=bitmap;
        }
    }
    private void initRecyclerView(){
        adapter=new RecyclerViewAdapter(getActivity(),bitmaps,ImgDiscs);
        RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View v,int position) {
                Toast.makeText(getContext(), "点击了第"+position, Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putString("ImgUrl",ImgDetail[position]);
                Intent intent=new Intent(getContext(), Img_Detail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initToolBar(){
        Toolbar toolbar= (Toolbar) getView().findViewById(R.id.tb_toolbar);
        toolbar.setPopupTheme(R.style.Widget_AppCompat_PopupMenu);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle("易GO");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sharedHelper=new SharedHelper(getContext());
                switch (item.getItemId()){
                    case R.id.search:
                        break;
                    case  R.id.action_item1:
                        sharedHelper.save(null, null);
                        getActivity().finish();
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
                Toast.makeText(getContext(), "点击了！", Toast.LENGTH_SHORT).show();
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
        }, 500, 500, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueues().add(imageRequest);
    }
    public void getImgUrl(){
        final String imgUrl= Constance.url+"servlet/MainServlet";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(imgUrl, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    JSONArray jsonArray1=new JSONArray(jsonArray.toString());
                    for (int i=0;i<20;i++){
                        JSONObject object=jsonArray1.getJSONObject(i);
                        ImgDiscs[i]=object.getString("disc");
                        ImgDetail[i]=object.getString("src");
                        downLoadPhoto(ImgDetail[i]);
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
    }

}

package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cai.R;

import java.util.HashMap;
import java.util.Map;

import util.Constance;

/**
 * Created by ASUS on 2017/6/13.
 */

public class BuyFragment extends Fragment {
    private EditText ppt_title;
    private EditText ppt_type;
    private EditText ppt_pages;
    private EditText phone;
    private EditText ppt_describe;
    private Button ppt_finish;
    private String ppt_title_str;
    private String ppt_type_str;
    private String ppt_pages_str;
    private String phone_str;
    private String ppt_describe_str;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fragement, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    public void initView(){
        ppt_title= (EditText) getActivity().findViewById(R.id.ppt_title);
        ppt_type= (EditText) getActivity().findViewById(R.id.ppt_type);
        ppt_pages= (EditText) getActivity().findViewById(R.id.ppt_pages);
        phone= (EditText) getActivity().findViewById(R.id.phone);
        ppt_describe= (EditText) getActivity().findViewById(R.id.ppt_describe);
        ppt_finish= (Button) getActivity().findViewById(R.id.ppt_finish);
        ppt_finish.setOnClickListener(new onClick());
    }
    private void ppt_toString(){
        ppt_title_str=ppt_title.getText().toString();
        ppt_type_str=ppt_type.getText().toString();
        ppt_pages_str=ppt_pages.getText().toString();
        phone_str=phone.getText().toString();
        ppt_describe_str=ppt_describe.getText().toString();
    }
    private void sendRequest(String url){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getContext(), "发送成功！", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "发送失败！", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("ppt_title_str",ppt_title_str);
                map.put("ppt_type_str",ppt_type_str);
                map.put("ppt_pages_str",ppt_pages_str);
                map.put("phone_str",phone_str);
                map.put("ppt_describe_str",ppt_describe_str);
                return map;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }

    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ppt_toString();
            sendRequest(Constance.url+"servlet/CustomerServlet?");
        }
    }
}

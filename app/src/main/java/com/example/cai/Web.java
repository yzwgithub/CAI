package com.example.cai;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by ASUS on 2017/5/10.
 */

public class Web extends Activity{
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView= (WebView) findViewById(R.id.webview);
        String url="http://www.baidu.com";
        webView.loadUrl(url);
    }
}

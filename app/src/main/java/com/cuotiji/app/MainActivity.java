package com.cuotiji.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 错题集 WebView 壳：直接加载线上地址，行为与原网页完全一致。
 * 数据保存在本机浏览器（WebView 的 localStorage）并保留 GitHub Gist 云同步 —— 即"维持现状"。
 */
public class MainActivity extends Activity {

    // 线上错题集地址（与电脑/手机浏览器访问的是同一个应用，数据互通）
    private static final String APP_URL = "https://aca60fe289408b722v2.app.workbuddy.host";

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wv = new WebView(this);
        WebSettings s = wv.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);          // 开启 localStorage，错题数据持久化
        s.setDatabaseEnabled(true);
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);

        // 在 WebView 内打开链接，而不是跳到外部浏览器
        wv.setWebViewClient(new WebViewClient());

        setContentView(wv);
        wv.loadUrl(APP_URL);
    }

    // 返回键：先让网页内后退（分类→题目），再退出 App
    @Override
    public void onBackPressed() {
        if (wv != null && wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

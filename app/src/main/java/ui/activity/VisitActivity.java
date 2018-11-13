package ui.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhonglian.battleworld.R;


import base.MVPBaseActivity;
import contract.activity.VisitContract;
import presenter.activity.VisitPresenter;
import ui.widget.MyActionBar;

public class VisitActivity extends MVPBaseActivity<VisitContract.View, VisitPresenter> implements VisitContract.View {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_visit;
    }

    @Override
    protected void setUpView() {
        WebView vIsitWebView = findViewById(R.id.visit_webview);
        MyActionBar vIsitActionBar = findViewById(R.id.visit_actionbar);

        vIsitActionBar.setTitle(getString(R.string.my_visit));
        vIsitWebView.getSettings().setJavaScriptEnabled(true);
        vIsitWebView.getSettings().setAppCacheEnabled(true);
        //设置 缓存模式
        vIsitWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        vIsitWebView.getSettings().setDomStorageEnabled(true);
        vIsitWebView.loadUrl("http://www.baidu.com");
        vIsitWebView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void setUpData() {

    }

}

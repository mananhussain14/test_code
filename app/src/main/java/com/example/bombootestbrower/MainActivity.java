package com.example.bombootestbrower;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_URL;
    String strURL = "";
    WebView mWebview;
    WebView second,thired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_URL = findViewById(R.id.et_url);
        mWebview = findViewById(R.id.webView);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClickLoadURL(View view) {
        strURL = et_URL.getText().toString().trim();
        if (strURL.length() > 6) {
            if (!strURL.substring(0, 5).equalsIgnoreCase("https")) {
                strURL = "https://" + strURL;
                et_URL.setText(strURL);
            }
        }
        if (strURL.equals(""))
            Toast.makeText(this,"Please enter URL to load",Toast.LENGTH_LONG).show();
        else {

            WebSettings webSettings = mWebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);

            webSettings.setLoadWithOverviewMode(true);
            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            mWebview.setWebViewClient(new WebViewController());
            mWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            mWebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// optizmation

            mWebview.loadUrl(strURL);
        }

    }

    public void onBackButton(View view) {
        if(mWebview.canGoBack()){
            mWebview.goBack();
        }

    }

    public void onFarwardButton(View view) {
        if(mWebview.canGoForward()){
            mWebview.goForward();
        }
    }

    public void onReLoadButton(View view) {
        mWebview.reload();

    }

    public class WebViewController extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
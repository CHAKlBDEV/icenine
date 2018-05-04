package com.shakib.icenine;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView mWebView;
    SwipeRefreshLayout mSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int pin = intent.getIntExtra("pin", 0);
        if(pin == 101099){
            setViewsAndSettings();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                mWebView.reload();
                break;
            case R.id.msg:
                mWebView.loadUrl("https://mbasic.facebook.com/messages");
                break;
            case R.id.others:
                mWebView.loadUrl("https://mbasic.facebook.com/messages/?folder=pending");
                break;
            case R.id.dzdevs:
                mWebView.loadUrl("https://mbasic.facebook.com/groups/328640490565078");
                break;
            case R.id.grps:
                mWebView.loadUrl("https://mbasic.facebook.com/groups");
                break;
            case R.id.active:
                mWebView.loadUrl("https://mbasic.facebook.com/buddylist.php");
                break;
            case R.id.exit:
                exitAct();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewsAndSettings(){
        setContentView(R.layout.activity_main);

        //Views
        mWebView = (WebView) findViewById(R.id.webView);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        //Browser settings
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        //mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                String htmlData = "<center><h2 style=\"color: red;\">Error :P</p></center>";
                mWebView.loadUrl("about:blank");
                mWebView.loadDataWithBaseURL(null,htmlData, "text/html", "UTF-8",null);
                mWebView.invalidate();
            }
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
            }
        });
        mWebView.loadUrl("https://mbasic.facebook.com/messages");

        //swipe action
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
                mSwipe.setRefreshing(false);
            }
        });
    }

    private void exitAct(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        exitAct();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        mWebView.goBack();

    }


}

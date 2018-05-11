package com.shakib.icenine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    public static String MESSAGES_HOME = "https://%s.facebook.com/messages";
    public static String MESSAGES_OTHERS = "https://%s.facebook.com/messages/?folder=pending";
    public static String DZDEV_LINK = "https://%s.facebook.com/groups/328640490565078";
    public static String GROUPS_LINK = "https://%s.facebook.com/groups";
    public static String BUDDYLIST_LINK = "https://%s.facebook.com/buddylist.php";
    public static String MOBILE_ADVANCED = "m";
    public static String MOBILE_BASIC = "mbasic";
    public static String BASIC_FREE = "0";

    WebView mWebView;
    SwipeRefreshLayout mSwipe;
    ProgressBar mProgressBar;

    SharedPreferences mSharedPref;
    SharedPreferences.Editor mPrefEditor;

    private String mMode;

    public void setMode(String m) {
        String toLoad = mWebView.getUrl().replaceFirst(mMode, m);
        Log.i("URL", toLoad);
        mMode = m;
        mPrefEditor.putString("MODE", m);
        mPrefEditor.commit();
        mWebView.loadUrl(toLoad);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int pin = intent.getIntExtra("pin", 0);
        if (pin == 101099) {
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
        switch (item.getItemId()) {
            case R.id.refresh:
                mWebView.reload();
                break;
            case R.id.msg:
                mWebView.loadUrl(getLink(MESSAGES_HOME));
                break;
            case R.id.others:
                mWebView.loadUrl(getLink(MESSAGES_OTHERS));
                break;
            case R.id.dzdevs:
                mWebView.loadUrl(getLink(DZDEV_LINK));
                break;
            case R.id.grps:
                mWebView.loadUrl(getLink(GROUPS_LINK));
                break;
            case R.id.active:
                mWebView.loadUrl(getLink(BUDDYLIST_LINK));
                break;
            case R.id.changepass:
                ChangePass dialogp = new ChangePass();
                dialogp.mAct = this;
                dialogp.show(getFragmentManager(), "CHANGEPASS");
                break;
            case R.id.mode:
                ModePick dialog = new ModePick();
                dialog.mAct = this;
                dialog.show(getFragmentManager(), "MODEPICK");
                break;
            case R.id.stop:
                mWebView.stopLoading();
                break;
            case R.id.exit:
                exitAct();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewsAndSettings() {
        setContentView(R.layout.activity_main);

        //SharedPref
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mPrefEditor = mSharedPref.edit();

        //retrieve prefs
        mMode = mSharedPref.getString("MODE", MOBILE_BASIC);

        //Views
        mWebView = (WebView) findViewById(R.id.webView);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        //Browser settings
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
        mWebView.loadUrl(getLink("https://%s.facebook.com/messages"));

        //swipe action
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
                mSwipe.setRefreshing(false);
            }
        });
    }

    private void exitAct() {
        finish();
    }

    private String getLink(String l) {
        String toReturn = String.format(l, mMode);
        return toReturn;
    }

    @Override
    protected void onPause() {
        exitAct();
        super.onPause();
    }

    public void changePass(String p){
        mPrefEditor.putString("PASS", p);
        mPrefEditor.apply();
    }

    @Override
    public void onBackPressed() {
        mWebView.goBack();

    }


}

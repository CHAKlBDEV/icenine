package com.shakib.icenine;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

    SharedPreferences mSharedPref;
    SharedPreferences.Editor mPrefEditor;

    private String mMode;
    public boolean mIsDisplayingADialog = false;

    public void setMode(String m) {
        mMode = m;
        mPrefEditor.putString("MODE", m);
        mPrefEditor.commit();
        mWebView.loadUrl(getLink(MESSAGES_HOME));
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
                mIsDisplayingADialog = true;
                ModePick dialog = new ModePick();
                dialog.mAct = this;
                dialog.show(getFragmentManager(), "MODEPICK");
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

        //Browser settings
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        //mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                String htmlData = "<center><h2 style=\"color: red;\">Error :P</p></center>";
                mWebView.loadUrl("about:blank");
                mWebView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
                mWebView.invalidate();
            }

            public void onPageFinished(WebView view, String url) {
                //TODO
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
        if(mIsDisplayingADialog) return;
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
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

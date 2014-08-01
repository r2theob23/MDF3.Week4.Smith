package com.example.robertsmith.week4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MyActivity extends Activity {

    public WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/index.html");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class WebAppInterface
    {
        public String mEmail;
        public String mSubject;
        public String mMessage;
        public Context mContext;

        WebAppInterface(Context context){
            mContext = context;
        }

        @JavascriptInterface
        public void saveFormFields(String email, String subject, String message)
        {

            Log.e("I GOT HIT", "YEAAAAA");

            this.mEmail = email;
            this.mSubject = subject;
            this.mMessage = message;


        }

    }
}

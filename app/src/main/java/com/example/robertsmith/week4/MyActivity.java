package com.example.robertsmith.week4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

//Robert Smith
//MDF3 Term 1407
//Web Browser
//This will be a Single Activity application that will serve as an Webview that allows users to send an email

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

    //Class that responds to webview interaction
    public class WebAppInterface
    {
        public String mEmail;
        public String mSubject;
        public String mMessage;
        public Context mContext;

        WebAppInterface(Context context){
            mContext = context;
        }

        //method from JS file
        @JavascriptInterface
        public void saveFormFields(String email, String subject, String message)
        {

            Log.e("I GOT HIT", "YEAAAAA");

            this.mEmail = email;
            this.mSubject = subject;
            this.mMessage = message;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String []{mEmail});
            intent.putExtra(Intent.EXTRA_TEXT, mMessage);
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, mSubject);

            startActivity(Intent.createChooser(intent, "Select Email Client"));


        }

    }
}

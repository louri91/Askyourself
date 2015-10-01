package com.ds.louri.askyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.*;

public class otrosJuegos extends Activity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otros_juegos);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("http://www.elmejorocio.com/juegos/maraton-juego-de-preguntas/");
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }

        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);

    }


}

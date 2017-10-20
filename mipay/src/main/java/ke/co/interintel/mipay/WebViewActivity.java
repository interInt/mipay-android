package ke.co.interintel.mipay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class WebViewActivity extends Activity implements Animation.AnimationListener {

    // Animation
    Animation animLoad;
    ImageView mipay_loading;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final Intent sIntent = getIntent();
        String urlString = sIntent.getDataString();
        mipay_loading = (ImageView)findViewById(R.id.mipay_loading);
        // load the animation
        animLoad = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);

        // set animation listener
        animLoad.setAnimationListener(this);
        mipay_loading.startAnimation(animLoad);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);

        // Set WebView client
        //webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress >=100){
                    mipay_loading.setVisibility(View.GONE);
                    mipay_loading.clearAnimation();
                }
            }
        });
        webView.loadUrl(urlString);



    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (animation == animLoad) {
            Toast.makeText(getApplicationContext(), "Loading Stopped",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}


package ke.co.interintel.mipay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity implements Animation.AnimationListener {

    // Animation
    Animation animLoad;
    ImageView mipay_loading;
    //web UI
    WebView webView;
    ProgressBar progressBar;
    Toolbar toolbar_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //set up the toolbar
        toolbar_webview = (Toolbar)findViewById(R.id.toolbar_webview);
        setSupportActionBar(toolbar_webview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //change back icon
        if (toolbar_webview != null) {
            toolbar_webview.setNavigationIcon(R.drawable.ic_clear_black_24dp);
            toolbar_webview.setTitle(R.string.webview_title);

        }
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar_webview);

        //Get Data passed through intent
        final Intent sIntent = getIntent();
        String urlString = sIntent.getDataString();


        // load the animation
        mipay_loading = (ImageView)findViewById(R.id.mipay_loading);
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
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress >=100){
                    mipay_loading.setVisibility(View.GONE);
                    mipay_loading.clearAnimation();
                    toolbar_webview.setTitle(webView.getTitle());

                }else{
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.loadUrl(urlString);



    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 0:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation != animLoad) {
            Toast.makeText(getApplicationContext(), "Loading Stopped",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}


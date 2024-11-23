package ir.servicea.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import im.delight.android.webview.AdvancedWebView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.app.G;

public class WebViewActivity extends Activity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;
    private SwipeRefreshLayout swiperefreshlayout;
    private String url;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(WebViewActivity.this, AlarmsActivity.class));
    }

    public void onclickBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        G.Activity = this;
        G.context = this;
        TextView txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setText(getIntent().getStringExtra("TITLE"));
        txt_tile_action_bar.setTypeface(G.Bold);
        swiperefreshlayout = findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setRefreshing(true);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
//    mWebView.setMixedContentAllowed(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //mWebView.loadUrl(getIntent().getStringExtra("LINK"));
        url = getIntent().getStringExtra("LINK");
        new MyAsynTask().execute();

    /*    Document document = null;
        try {
            document = Jsoup.connect(getIntent().getStringExtra("LINK")).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        document.getElementsByClass("header").remove();
        document.getElementsByClass("footer").remove();
        mWebView.loadDataWithBaseURL(getIntent().getStringExtra("LINK"), document.toString(), "text/html", "utf-8", "");
 */   }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        G.context = this;
        G.Activity = this;
        mWebView.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        swiperefreshlayout.setRefreshing(true);
    }

    @Override
    public void onPageFinished(String url) {
        swiperefreshlayout.setRefreshing(false);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        swiperefreshlayout.setRefreshing(false);
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {
    }

    private class MyAsynTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected Document doInBackground(Void... voids) {

            Document document = null;
            try {
                document= Jsoup.connect(url).get();
                document.getElementsByClass("header").remove();
                document.getElementsByClass("footer-area").remove();
                document.getElementsByClass("headroom").remove();
                document.getElementsByClass("footer").remove();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            mWebView.loadDataWithBaseURL(url,document.toString(),"text/html","utf-8","");
            mWebView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );

            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
        }
    }

}
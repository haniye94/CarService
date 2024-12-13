package ir.tehranOil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import im.delight.android.webview.AdvancedWebView;
import ir.servicea.R;
import ir.tehranOil.activity.AlarmsActivity;
import ir.tehranOil.app.G;

public class FragmentGuid extends Fragment implements AdvancedWebView.Listener{

    public View view;
    private AdvancedWebView mWebView;
    private SwipeRefreshLayout swiperefreshlayout;

    ImageView img_back;

    public void onclickAlamrs(View v) {
        startActivity(new Intent(getContext(), AlarmsActivity.class));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_webview, container, false);
        TextView txt_tile_action_bar = view.findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setText("معرفی و آموزش");
        txt_tile_action_bar.setTypeface(G.Bold);
        img_back = view.findViewById(R.id.img_back);
        img_back.setVisibility(View.INVISIBLE);
        swiperefreshlayout = view.findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setRefreshing(true);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });
        mWebView = (AdvancedWebView) view.findViewById(R.id.webview);
        mWebView.setListener(getActivity(), this);
//    mWebView.setMixedContentAllowed(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.loadUrl(G.LINK_Introduction);
        return view;
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        G.context = getContext();
        G.Activity = getActivity();
        mWebView.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
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

}
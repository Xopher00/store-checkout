package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Xopher on 11/7/2016.
 */

public class webViewFragment extends Fragment{

    View web;
    String urlstr;//string containing url
    int loaded;

    public webViewFragment() {
        loaded = 0;
    }

    public webViewFragment(String urlstr){
        this.urlstr = urlstr;
        loaded = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        web = inflater.inflate(R.layout.web_view, container, false);
        WebView webview = (WebView) web.findViewById(R.id.webView);

        WebSettings webSettings = webview.getSettings();//set permissions
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient());
        loadDatURL(webview);
        return web;
    }

    public void loadDatURL(WebView wv){
        wv.loadUrl(urlstr);
        loaded = 1;
    }
}

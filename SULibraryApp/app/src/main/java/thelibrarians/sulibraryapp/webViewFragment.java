package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Xopher on 11/7/2016.
 */

public class webViewFragment extends Fragment{

    View web;
    String urlstr;

    public webViewFragment() {}

    public webViewFragment(String urlstr){
        this.urlstr = urlstr;
    }

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        web = inflater.inflate(R.layout.web_view, container, false);
        WebView webview = (WebView) web.findViewById(R.id.webView);

        webview.getSettings().setJavaScriptEnabled(true);


        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                //getActivity().setTitle("Loading...");
                getActivity().setProgress(progress * 1000);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Could not connect to internet" , Toast.LENGTH_SHORT).show();
            }
        });


        webview.loadUrl(urlstr);
        return web;
    }
}

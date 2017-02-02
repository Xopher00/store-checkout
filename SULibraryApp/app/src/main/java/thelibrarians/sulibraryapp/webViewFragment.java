package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * Created by Xopher on 11/7/2016.
 */

public class webViewFragment extends Fragment{

    View web;
    static String urlstr=null;//string containing url
    private static WebView webview = null;
    DrawerToggleListener toggleListener;

    public webViewFragment(){
    }

    public webViewFragment(String urlstr){
        this.urlstr=urlstr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(false);
        web = inflater.inflate(R.layout.web_view, container, false);
        if (webview == null) {
            webview = new WebView(getActivity());
            webview.loadUrl(urlstr);
        }
        else if(webview.getUrl().compareTo(urlstr) != 0){
            webview.loadUrl(urlstr);
        }
        LinearLayout layout = (LinearLayout) web.findViewById(R.id.weblayout);
        layout.removeAllViews();
        WebSettings webSettings = webview.getSettings();//set permissions
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new WebViewClient());
        layout.addView(webview, layout.getLayoutParams());

        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);


        return web;
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        if (getRetainInstance() && webview.getParent() instanceof ViewGroup) {
            ((ViewGroup)webview.getParent()).removeView(webview);
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }
}

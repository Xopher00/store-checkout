package thelibrarians.sulibraryapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChatWebViewFragment extends Fragment {


    View web;
    static String urlstr=null;//string containing url
    private static WebView webview = null;
    DrawerToggleListener toggleListener;

    public ChatWebViewFragment(){
    }

    public ChatWebViewFragment(String urlstr){
        this.urlstr=urlstr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(false);
        web = inflater.inflate(R.layout.web_view, container, false);
        RelativeLayout layout = (RelativeLayout) web.findViewById(R.id.weblayout);
        ImageView loadingscreen = (ImageView)layout.findViewById(R.id.loadscreenback);
        TextView loadingmsg = (TextView) layout.findViewById(R.id.loadingmsg);
        if (webview == null) {
            webview = new WebView(getActivity());
            webview.loadUrl(urlstr);
        }
        else if(webview.getUrl().compareTo(urlstr) != 0){
            webview.loadUrl(urlstr);
        }
        else{
            webview.setVisibility(View.INVISIBLE);
        }
        layout.removeView(webview);
        loadingscreen.setVisibility(View.VISIBLE);
        loadingmsg.setVisibility(View.VISIBLE);
        WebSettings webSettings = webview.getSettings();//set permissions
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                view.setVisibility(View.VISIBLE);
            }
        });
        layout.addView(webview, layout.getLayoutParams());
        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.setVisibility(View.VISIBLE);
        loadingscreen.setVisibility(View.INVISIBLE);
        loadingmsg.setVisibility(View.INVISIBLE);
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

package thelibrarians.sulibraryapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

/**
 * Created by Xopher on 11/7/2016.
 */

public class webViewFragment extends Fragment{

    View web;
    static String urlstr=null;//string containing url
    static String toolbar_name=null;
    private static WebView webview = null;
    DrawerToggleListener toggleListener;
    private SearchView searchView = null;
    ActionBar toolbar;
    static TextView loadingmsg = null;

    public webViewFragment(){
    }

    public webViewFragment(String urlstr, String toolbar_name){
        this.urlstr=urlstr;
        this.toolbar_name = toolbar_name;
    }

    public webViewFragment(String urlstr, SearchView sv, String toolbar_name){
        this.urlstr=urlstr;
        searchView = sv;
        this.toolbar_name = toolbar_name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(false);
        web = inflater.inflate(R.layout.web_view, container, false);
        RelativeLayout layout = (RelativeLayout) web.findViewById(R.id.weblayout);
        loadingmsg = (TextView) layout.findViewById(R.id.loadingmsgwebview);
        loadingmsg.setVisibility(View.VISIBLE);
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
        webview.setVisibility(View.INVISIBLE);
        layout.removeView(webview);
        WebSettings webSettings = webview.getSettings();//set permissions
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                loadingmsg.setVisibility(View.INVISIBLE);
                view.setVisibility(View.VISIBLE);
            }
        });
        layout.addView(webview, layout.getLayoutParams());
        toggleListener = (DrawerToggleListener) getActivity();
		if(!toolbar_name.contentEquals(getResources().getString(R.string.building_maps))) //up arrow in toolbar if not 'building maps'
			toggleListener.toggleDrawer(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

      /*  //if search box is visible then close/make icon
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }*/

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(toolbar_name);

        return web;
    }

    public static WebView getWebView(){
        return webview;
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
        webview.destroy();
        webview = null;
    }
}

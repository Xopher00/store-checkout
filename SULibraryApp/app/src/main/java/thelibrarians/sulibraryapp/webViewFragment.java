package thelibrarians.sulibraryapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Xopher on 11/7/2016.
 */

public class webViewFragment extends Fragment{

    View web;
    String urlstr;

    public webViewFragment(String urlstr){
        this.urlstr = urlstr;
    }

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            web = inflater.inflate(R.layout.web_view, container, false);

        WebView webview = (WebView) web.findViewById(R.id.webView);
        webview.loadUrl(urlstr);
        return web;
    }
}

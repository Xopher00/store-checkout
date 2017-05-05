package thelibrarians.sulibraryapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xopher on 11/7/2016.
 *
 * singleton webview
 */

public class webViewFragment extends Fragment {

    View web;
    static String urlstr = null;//string containing url
    static String toolbar_name = null;
    private static WebView webview = null;
    DrawerToggleListener toggleListener;
    ActionBar toolbar;
    private static webViewFragment webViewFrag;
    private ArrayList<String> previous = new ArrayList<String>();
    private String mLastUrl;
    Log log;

    private webViewFragment() {
    }

    public webViewFragment(String urlstr, String toolbar_name) {
        this.urlstr = urlstr;
        this.toolbar_name = toolbar_name;
    }

    public static webViewFragment getInstance(String url, String toolbarName) {
        if(webViewFrag == null)
            webViewFrag = new webViewFragment();
        urlstr = url;
        toolbar_name = toolbarName;

        return webViewFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(false);
        web = inflater.inflate(R.layout.web_view, container, false);
        RelativeLayout layout = (RelativeLayout) web.findViewById(R.id.weblayout);
        TextView loadingmsg = (TextView) layout.findViewById(R.id.loadingmsg);
        if (webview == null) {
            webview = new WebView(getActivity());
        } else {
            webview.setVisibility(View.INVISIBLE);
        }
        log.i("nick", "load URL");
        webview.loadUrl(urlstr);
        layout.removeView(webview);
        WebSettings webSettings = webview.getSettings();//set permissions
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                log.i("nick", "new URL");
                mLastUrl = urlstr;
                urlstr = url;
                previous.add(mLastUrl);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        layout.addView(webview, layout.getLayoutParams());
        toggleListener = (DrawerToggleListener) getActivity();
        if (!toolbar_name.contentEquals(getResources().getString(R.string.building_maps))) //up arrow in toolbar if not 'building maps'
            toggleListener.toggleDrawer(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.setVisibility(View.VISIBLE);
        loadingmsg.setVisibility(View.INVISIBLE);

      /*  //if search box is visible then close/make icon
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }*/

        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle(toolbar_name);

        return web;
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        if (getRetainInstance() && webview.getParent() instanceof ViewGroup) {
            ((ViewGroup) webview.getParent()).removeView(webview);
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
        previous.clear();
        webview.destroy();
        webview = null;
    }

   /* @Override
    public boolean onTouch(View v, MotionEvent event) {
        WebView.HitTestResult hr = ((WebView) v).getHitTestResult();
        if (hr != null && mLastUrl != null) {
            if (previous.isEmpty() || !previous.get(previous.size() - 1).equals(mLastUrl)) {
                log.i("nick", "add webpage size "+previous.size());
                previous.add(mLastUrl);
            }
        }
        return false;
    }*/

    public int getStackSize() {
        return previous.size();
    }

    public void backPress() {
        int size = previous.size();
        if(size > 1) {
            webview.loadUrl(previous.get(size - 1));
            previous.remove(size - 1);
        }
        log.i("nick", "delete webpage size "+previous.size());
    }
}

package thelibrarians.sulibraryapp;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static thelibrarians.sulibraryapp.MainActivity.pageStack;

/**
 * AboutFragment
 * <br>
 * Displays information about the app and library
 */

public class AboutFragment extends Fragment {

    ActionBar toolbar;
    Menu app_menu;
    MenuItem support;

    /**
     * Standard default empty constructor
     */

    public AboutFragment() {}

    /**
     * Initially creates the view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the view for the fragment
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        View.OnClickListener listener = new View.OnClickListener() {
            /**
             * When clicked, changes fragment
             * @param v View that is clicked
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new PrivacyFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
                pageStack.push(15);
            }
        };
        TextView t4 = (TextView) view.findViewById(R.id.privacy);
        t4.setOnClickListener(listener);
        setHasOptionsMenu(true);

        //modify toolbar
        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.about));

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        app_menu = menu;
        menu.findItem(R.id.support_string).setVisible(true);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

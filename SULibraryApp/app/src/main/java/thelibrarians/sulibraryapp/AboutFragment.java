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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * AboutFragment
 * <br>
 * Displays information about the app and library
 */

public class AboutFragment extends Fragment {

    ActionBar toolbar;

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
            }
        };
        TextView t4 = (TextView) view.findViewById(R.id.privacy);
        t4.setOnClickListener(listener);


        View.OnClickListener supportListener = new View.OnClickListener() {
            /**
             * When clicked, changes fragment
             * @param v View that is clicked
             */
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) {
                    Intent emailer;
                    emailer = new Intent(Intent.ACTION_SENDTO);
                    emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailer.setData(Uri.parse("mailto:"));
                    emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"libapp@salisbury.edu"});
                    emailer.putExtra(android.content.Intent.EXTRA_SUBJECT, new String[]{"SU Libraries App Support"});
                    emailer.putExtra(android.content.Intent.EXTRA_CC, new String[]{"cmwoodall@salisbury.edu"});
                    //SU Libraries App Support
                    startActivity(emailer);
                }
                else{
                    /*Fragment fragment = new ConnectionErrorFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_container, fragment);
                    fragmentTransaction.addToBackStack(null).commit();*/
                }
            }
        };
        TextView t5 = (TextView) view.findViewById(R.id.support);
        t5.setOnClickListener(supportListener);

        //modify toolbar
        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.about));

        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

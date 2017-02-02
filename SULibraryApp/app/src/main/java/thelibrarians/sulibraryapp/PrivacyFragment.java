package thelibrarians.sulibraryapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PrivacyFragment extends Fragment {

    DrawerToggleListener toggleListener;

    public PrivacyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_privacy, container, false);

        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }
}

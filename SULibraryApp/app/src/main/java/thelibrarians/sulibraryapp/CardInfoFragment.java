package thelibrarians.sulibraryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Xopher on 3/28/2017.
 */

public class CardInfoFragment extends Fragment {

    View view;
    Fragment fragment;
    BarCodeFragment ba;
    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mycard, container, false);
        ma = (MainActivity)getActivity();



        return view;
    }


}

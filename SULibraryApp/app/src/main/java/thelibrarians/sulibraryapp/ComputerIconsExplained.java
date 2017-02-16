package thelibrarians.sulibraryapp;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ComputerIconsExplained extends Fragment {
    View view;

    public ComputerIconsExplained() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_computer_icons_explained, container, false);
        TextView undertext = (TextView) view.findViewById(R.id.symbol_explain);
        undertext.setPaintFlags(undertext.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        return view;
    }

}

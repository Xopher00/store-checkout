package thelibrarians.sulibraryapp;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComputerIconsExplained extends Fragment {
    View view;
    ListView list_of_groups; /* The ListView object */
    ArrayList<ListItem> listItems; /* ArrayList of ListItems */
    ListviewX lix; /* The ListViewAdapter object for our ListView */

    public ComputerIconsExplained() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_computer_icons_explained, container, false);
        list_of_groups = (ListView) view.findViewById(R.id.list_computer_explained);
        listItems = new ArrayList<ListItem>();
        ListItem0 li0 = new ListItem0(getActivity(), "What do these symbols mean?");
        li0.getTextView().setTextAppearance(getActivity(), R.style.listHeader); // Looks like a header
        li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // Underlines
        li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null)); // Sets background color to the standard
        listItems.add(li0);
        ListItem2 li2 = new ListItem2(getActivity(), R.drawable.pcavailable, "Available", "Computers in green are currently available.");
        listItems.add(li2);
        li2 = new ListItem2(getActivity(), R.drawable.pcinuse, "Unavailable", "Computers in red are currently unavailable.");
        listItems.add(li2);
        li2 = new ListItem2(getActivity(), R.drawable.pcoff, "Off", "Computers in gray are currently off.");
        listItems.add(li2);
        lix = new ListviewX(getActivity());
        lix.populate(listItems);
        list_of_groups.setAdapter(lix); // ASSIGN THE ADAPTER TO THE LISTVIEW
        return view;
    }

}

package thelibrarians.sulibraryapp;

import android.content.ClipData;
import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ComputerAvailabilityListFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView list_of_groups; // LISTVIEW
    ImgTxtListAdapter ad; // ADAPTER
    String[] room_names; // ROOM NAMES
    String[] group_names; // GROUP NAMES
    String[] room_descriptions; // DESCRIPTIONS
    int[] num_comps; // NUM COMPUTERS
    String[] mapID; // MAPID FOR GRABBING JSON FILE FROM DATABASE
    int[] imgs = {R.drawable.ac1c5_long};

    ImgTxtListAdapter.SectionStructure str; // A SINGLE LIST ITEM
    ArrayList<ImgTxtListAdapter.SectionStructure> section_list; // THE LIST ITSELF

    public ComputerAvailabilityListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =inflater.inflate(R.layout.fragment_computer_availability_list, container, false); // MAKE LAYOUT EDITABLE
        list_of_groups = (ListView)view.findViewById(R.id.list_of_groups); // FIND LISTVIEW IN LAYOUT
        ad = new ImgTxtListAdapter(getContext()); // CREATE ADAPTER IN THIS CONTEXT
        list_of_groups.setAdapter(ad); // ASSIGN THE ADAPTER TO THE LISTVIEW
        list_of_groups.setOnItemClickListener(this); // MAKE LISTVIEW CLICKABLE
        fillList(); // FILL LIST
        return view; // FINALIZE VIEW AND MAKE IT VISIBLE
    }

    private void fillList(){
        /*
            GET STRING ARRAYS FROM RESOURCES
         */
        room_names = getResources().getStringArray(R.array.room_names);
        group_names = getResources().getStringArray(R.array.group_names);
        room_descriptions = getResources().getStringArray(R.array.room_descriptions);
        num_comps = getResources().getIntArray(R.array.num_comps);
        mapID = getResources().getStringArray(R.array.map_ids);
        section_list = ad.getSectionStructure(); // GET ARRAY TO PUT THE ITEMS INTO

        for(int i = 0; i < room_names.length; i++){
            addToList(i); // ADD ITEM FROM POSITION i IN ARRAYS
        }
    }

    private void addToList(int i){
        str = ad.getStr();
        str.setSectionTitle(group_names[i]);
        String for_sub = getSubtitle(i);
        str.setSectionSubtitle(for_sub);
        str.setSectionImage(imgs[0]);
        section_list.add(str);
    }

    private String getSubtitle(int i){

        String for_sub = new String(room_names[i]);
        for_sub = for_sub.concat(" / ");
        Integer nc = new Integer(num_comps[i]);
        for_sub = for_sub.concat(nc.toString());
        for_sub = for_sub.concat(" Computers");
        return for_sub;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = new ComputerAvailabilityDisplayFragment(position);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}

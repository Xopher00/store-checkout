package thelibrarians.sulibraryapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ComputerAvailabilityListFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView list_of_groups; // LISTVIEW
    //ImgTxtListAdapter ad; // ADAPTER
    ListviewAdapter adapter; //adapter
    String[] room_names; // ROOM NAMES
    String[] group_names; // GROUP NAMES
    String[] room_descriptions; // DESCRIPTIONS
    String[] strings; //combined list of titles and subtitles
    int[] num_comps; // NUM COMPUTERS
    String[] mapID; // MAPID FOR GRABBING JSON FILE FROM DATABASE
    int[] views = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[] imgs = {R.drawable.ac102_icon, R.drawable.ac1c20_icon, R.drawable.ac1c5_icon,
                    R.drawable.ac117_icon, R.drawable.ac162_icon, R.drawable.ac2c1_icon,
                    R.drawable.ac261_icon, R.drawable.ac262_icon, R.drawable.ac300_icon};

    //ImgTxtListAdapter.SectionStructure str; // A SINGLE LIST ITEM
    //ArrayList<ImgTxtListAdapter.SectionStructure> section_list; // THE LIST ITSELF

    public ComputerAvailabilityListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =inflater.inflate(R.layout.fragment_computer_availability_list, container, false); // MAKE LAYOUT EDITABLE
        list_of_groups = (ListView)view.findViewById(R.id.list_of_groups); // FIND LISTVIEW IN LAYOUT
        //ad = new ImgTxtListAdapter(getContext()); // CREATE ADAPTER IN THIS CONTEXT
        adapter = new ListviewAdapter(getActivity());
        adapter.setViewTypeAmount(2);
        list_of_groups.setAdapter(adapter); // ASSIGN THE ADAPTER TO THE LISTVIEW
        list_of_groups.setOnItemClickListener(this); // MAKE LISTVIEW CLICKABLE
        fillList(); // FILL LIST
        adapter.populate(views, strings, imgs);
        return view; // FINALIZE VIEW AND MAKE IT VISIBLE
    }

    private void fillList(){
        /*
            GET STRING ARRAYS FROM RESOURCES
         */
        room_names = getResources().getStringArray(R.array.computer_room_names);
        group_names = getResources().getStringArray(R.array.computer_group_names);
        room_descriptions = getResources().getStringArray(R.array.computer_room_descriptions);
        strings = new String[room_names.length * 2];
        num_comps = getResources().getIntArray(R.array.num_computers);
        mapID = getResources().getStringArray(R.array.computer_map_ids);
        //section_list = ad.getSectionStructure(); // GET ARRAY TO PUT THE ITEMS INTO

        int index = 0;
        for(int i = 0; i < room_names.length; i++){
            //addToList(i); // ADD ITEM FROM POSITION i IN ARRAYS
            strings[index] = group_names[i];
            index++;
            strings[index] = getSubtitle(i);
            index++;
        }
    }

 /*   private void addToList(int i){
        str = ad.getStr();                  // Creates a new list element
        str.setSectionTitle(group_names[i]); // Sets the name of the element
        str.setSectionSubtitle(getSubtitle(i)); // Sets the subtitle of the element
        str.setSectionImage(imgs[i]); // Sets the image of the element
        section_list.add(str); //
    }*/

    private String getSubtitle(int i){

        String for_sub = new String(room_names[i]); // Creates the room name string
        for_sub = for_sub.concat(" / "); // Concats to make string
        Integer nc = new Integer(num_comps[i]); // Number of computers
        for_sub = for_sub.concat(nc.toString()); // ||
        for_sub = for_sub.concat(" Computers"); // Concat
        return for_sub; // Returns string

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;
        if(isNetworkAvailable()){
            fragment = new ComputerAvailabilityDisplayFragment(position);
        }
        else {
            fragment = new ConnectionErrorFragment();
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

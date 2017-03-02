package thelibrarians.sulibraryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static thelibrarians.sulibraryapp.SubjectDetailedFragment.position;

public class ComputerAvailabilityListFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView list_of_groups; // LISTVIEW

    String[] room_names,group_names,room_descriptions,strings,mapID,full_strings; //combined list of titles and subtitles
    int[] num_comps,imgs = {R.drawable.ac102_icon, R.drawable.ac1c20_icon, R.drawable.ac1c5_icon,
                    R.drawable.ac117_icon, R.drawable.ac162_icon, R.drawable.ac2c1_icon,
                    R.drawable.ac261_icon, R.drawable.ac262_icon, R.drawable.ac300_icon};
    Integer num_available, num_total; // Integers pulled from the JSON
    SwipeRefreshLayout swipeRefresher;
    String base_url; // URL and result of the URL
    HttpURLConnection conn; // Connection object
    ListviewX lix;
    JSONRetriever jretr;
    ArrayList<ListItem> listItems;
    View view;
    boolean loaded, connected;


    public ComputerAvailabilityListFragment() {
        loaded = false;
        connected = false;
    }

    private class JSONRetriever extends AsyncTask<Void, Void, Void> {

        /*
        * THIS STARTS WHEN JSONRetriever.execute() IS CALLED
        *
        * THIS IS STRICTLY FOR GRABBING THE STRING. DO NOT ATTEMPT TO
        * CALL ANY PARENT CLASS METHODS OR CHANGE ANY UI ELEMENTS IN
        * THIS METHOD. IT WILL FAIL AND YOU WILL BE SAD. I'M SORRY.
        * */

        @Override
        protected Void doInBackground(Void... params) {
            Integer num_rooms = room_names.length;
            Log.e("NUM ROOM NAMES", num_rooms.toString());
            full_strings = new String[room_names.length];
            if(loaded == false) {
                for (int i = 0; i < room_names.length; i++) {
                    try {
                        base_url = getActivity().getResources().getString(R.string.json_url); // First part of all URLs
                        String[] mapIDs = getResources().getStringArray(R.array.computer_map_ids); // Loads array of possible room IDs
                        base_url = base_url.concat(mapIDs[i]); // Adds room IDs to
                        URL url; // URL object
                        StringBuilder response = new StringBuilder(); // Allows string appending
                        String inputLine; // Buffer for inputStream
                        try {
                            url = new URL(base_url); // url passed in
                            try {
                                conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                                conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                                conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                                try {
                                    while ((inputLine = br.readLine()) != null) { // While there are more contents to read
                                        connected = true;
                                        response.append(inputLine); // Append the new data to all grabbed data
                                    }
                                    br.close(); // Close connection
                                } catch (IOException e) {
                                }
                            } catch (IOException e) {
                            }
                        } catch (MalformedURLException e) {
                        }
                        full_strings[i] = response.toString(); // Sets string in parent class to be the string taken from the URL
                    } catch (Exception e) {
                    }
                }
            }
            else{
                cancel(true);
            }
            return null;
        }

        /*
        * THIS STARTS ONCE doInBackground(...) COMPLETES
        *
        * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
        * */

        protected void onPostExecute(Void v){
            fillList();
        }

        protected void onCancelled(){}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        view = inflater.inflate(R.layout.fragment_computer_availability_list, container, false); // MAKE LAYOUT EDITABLE
        swipeRefresher = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshcomplist); // Assigns SwipeRefreshLayout object
        swipeRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){ // OnClickListener
                loaded = false;
                refresh();
                swipeRefresher.setRefreshing(false);
            }
        });
        swipeRefresher.setEnabled(false);
        view.findViewById(R.id.list_of_groups).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.comp_list_loading).setVisibility(View.VISIBLE);
        list_of_groups = (ListView)view.findViewById(R.id.list_of_groups); // FIND LISTVIEW IN LAYOUT
        // Required empty public constructor

        return view; // FINALIZE VIEW AND MAKE IT VISIBLE
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        jretr.cancel(true);
    }

    @Override
    public void onStart(){
        super.onStart();
        lix = new ListviewX(getActivity());
        refresh();
    }

    private void refresh(){
        Log.e("ERROR",Boolean.toString(isNetworkAvailable()));
        if(loaded != true && isNetworkAvailable()) {
            room_names = getResources().getStringArray(R.array.computer_room_names);
            group_names = getResources().getStringArray(R.array.computer_group_names);
            room_descriptions = getResources().getStringArray(R.array.computer_room_descriptions);
            strings = new String[room_names.length * 2];
            num_comps = getResources().getIntArray(R.array.num_computers);
            mapID = getResources().getStringArray(R.array.computer_map_ids);
        /*

        * CONNECT TO URL
         *  */
            jretr = new JSONRetriever();
            jretr.execute();
        }
        else if(!isNetworkAvailable()){
            Fragment fragment = new ConnectionErrorFragment(this);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_container, fragment);
            fragmentTransaction.addToBackStack(null).commit();
        }
        else if(loaded && isNetworkAvailable()){
            fillList();
        }
    }

    private void parseJSON(int i){
        JSONObject j; // Declares JSONObject
        try {
            /* READ DOCUMENTATION PLEASE FOR THE LOVE OF GOD */
            j = new JSONObject(full_strings[i]);
            JSONObject all = j.getJSONObject("all");
            num_total = new Integer((Integer) all.get("total"));
            num_available = new Integer((Integer) all.get("available"));
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    private void fillList(){
        /*
            GET STRING ARRAYS FROM RESOURCES
         */
        //section_list = ad.getSectionStructure(); // GET ARRAY TO PUT THE ITEMS INTO

        loaded = true;
        for(int i = 0; i < full_strings.length;i++){
            Log.e("JSON_STRING", full_strings[i]);
        }
        swipeRefresher.setEnabled(true);
        listItems = new ArrayList<ListItem>();
        ListItem0 li = new ListItem0(getActivity(), "Computer Groups");
        //li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        //li.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
        li.getTextView().setTextColor(Color.parseColor("#8a000000"));
        listItems.add(li);
        for(int i = 0; i < room_names.length; i++){
            addToList(i);
        }
        lix.populate(listItems);
        list_of_groups.setAdapter(lix); // ASSIGN THE ADAPTER TO THE LISTVIEW
        list_of_groups.setOnItemClickListener(this); // MAKE LISTVIEW CLICKABLE
        view.findViewById(R.id.comp_list_loading).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.list_of_groups).setVisibility(View.VISIBLE);
    }

    private void addToList(int i){
        parseJSON(i);
        ListItem2 li2 = new ListItem2(getActivity(), imgs[i], group_names[i], getSubtitle(i));
        if((double)num_available/(double)num_total > .66){
            li2.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_green, null));
        }
        else if((double)num_available/(double)num_total > .33){
            li2.getTextView2().setTextColor(Color.parseColor("#555555"));
        }
        else
            li2.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(),R.color.color_red, null));
        listItems.add(li2);
    }

    private String getSubtitle(int i){

        String for_sub = new String(room_names[i]); // Creates the room name string
        for_sub = for_sub.concat(" / "); // Concats to make string
        Integer nc = new Integer(num_comps[i]); // Number of computers
        for_sub = for_sub.concat(nc.toString()); // ||
        for_sub = for_sub.concat(" Computers / "); // Concat
        for_sub = for_sub.concat(num_available.toString());
        for_sub = for_sub.concat(" Available");
        return for_sub; // Returns string

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(position != 0) {
            Fragment fragment = null;
            if (isNetworkAvailable()) {
                fragment = new ComputerAvailabilityDisplayFragment(position - 1);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            } else {
                fragment = new ConnectionErrorFragment(new ComputerAvailabilityDisplayFragment(position - 1));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }


        }/*
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment);
        fragmentTransaction.addToBackStack(null).commit();*/
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

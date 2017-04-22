package thelibrarians.sulibraryapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * StudyRoomReserveFragment
 * <br>
 * Displays a list of all study rooms
 */
//this fragment displays a list of study rooms in the library, al=nd whether or not they are available

public class StudyRoomReserveFragment extends Fragment implements AdapterView.OnItemClickListener {

    enum JSONMode{
        ROOM_TITLES,
        ROOM_AVAILABILITY
    };

    ListView listViewsrr; //listView study room reservation
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String base_url, room_grab;
    ArrayList<String> availability_urls, availability_grab;
    RoomDetail[] rooms;
    View view;
    TextView loading_msg;
    JSONRetriever jretr,jretr2;
    JSONMode mode;
    public final int[] first_floor_room_ids = {42092,42093};
    public static final String[] sections = {"First Floor", "Other Floors"};
    int[] header_pos;
    ActionBar toolbar;

    /**
    * Default constructor, sets the number of headers. If rooms are added above floor two,
     * increase this number
    * */
    public StudyRoomReserveFragment(){
        header_pos = new int[2];
        availability_urls = new ArrayList<String>();
        availability_grab = new ArrayList<String>();
    }

    /**
     * Initially creates the view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the view for the fragment
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_study_room_reserve, container, false); // Assigns view
        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();
        listViewsrr = (ListView) view.findViewById(R.id.listViewsrr); // Assigns listview
        listViewsrr.setVisibility(View.INVISIBLE);

        loading_msg = (TextView) view.findViewById(R.id.study_list_loading);
        loading_msg.setVisibility(View.VISIBLE);

        listViewsrr.setOnItemClickListener(this);
        mode = JSONMode.ROOM_TITLES;
        assignURLRoomNames();

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.study_room));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mode = JSONMode.ROOM_TITLES;
        availability_urls.clear();
        availability_grab.clear();
        jretr = new JSONRetriever();
        jretr2 = new JSONRetriever();
        jretr.execute();
    }

    @Override
    public void onPause(){
        super.onPause();
        jretr.cancel(true);
        jretr2.cancel(true);
    }

    private class JSONRetriever extends AsyncTask<Void, Void, Void> {
        HttpURLConnection conn; // Connection object

        /*
        * THIS STARTS WHEN JSONRetriever.execute() IS CALLED
        *
        * THIS IS STRICTLY FOR GRABBING THE STRING. DO NOT ATTEMPT TO
        * CALL ANY PARENT CLASS METHODS OR CHANGE ANY UI ELEMENTS IN
        * THIS METHOD. IT WILL FAIL AND YOU WILL BE SAD. I'M SORRY.
        * */

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine; // Buffer for inputStream
                try {
                    if(mode == JSONMode.ROOM_TITLES){
                        url = new URL(base_url); // url passed in
                        try {
                            conn = (HttpURLConnection)url.openConnection(); // Opens new connection
                            conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                            conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                            try {
                                while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                    response.append(inputLine); // Append the new data to all grabbed data
                                br.close(); // Close connection
                            } catch (IOException e) {}
                        } catch (IOException e) {}
                        room_grab = response.toString(); // Sets string in parent class to be the string taken from the URL
                    }
                    if(mode == JSONMode.ROOM_AVAILABILITY){
                        for(int i = 0; i < availability_urls.size(); i++){
                            url = new URL(availability_urls.get(i)); // url passed in
                            try {
                                conn = (HttpURLConnection)url.openConnection(); // Opens new connection
                                conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                                conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                                try {
                                    while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                        response.append(inputLine); // Append the new data to all grabbed data
                                    br.close(); // Close connection
                                } catch (IOException e) {}
                            } catch (IOException e) {}
                            availability_grab.add(response.toString()); // Sets string in parent class to be the string taken from the URL
                            response = new StringBuilder(); // Allows string appending
                        }
                    }
                } catch (MalformedURLException e) {}
            } catch (Exception e) {}
            return null;
        }

        /*
        * THIS STARTS ONCE doInBackground(...) COMPLETES
        *
        * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
        * */

        protected void onPostExecute(Void v){
            parseJSON();
        }

        protected void onCancel(){}
    }

    public void fillList(){
        int room = 0;

        //section 1
        int h = 0;
        header_pos[0] = h;

        ListItem0 li = new ListItem0(getActivity(), sections[0].toUpperCase());
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
        li.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listItems.add(li);

        for (h = 0; h < first_floor_room_ids.length; h++) {
            rooms[room].setSection(sections[0]);
            listItems.add(new ListItem1(getActivity(), rooms[room].icon, rooms[room].name));
            room++;
        }
        header_pos[1] = h+1;

        //section 2
        li = new ListItem0(getActivity(), sections[1].toUpperCase());
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
        li.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listItems.add(li);

        for (int x = 0; x < rooms.length-first_floor_room_ids.length; x++) {
            if(rooms[room].name.compareTo("AC 130") != 0) {
                rooms[room].setSection(sections[1]);
                ListItem2 nli = null;
                if(rooms[room].currently_available) {
                    nli = new ListItem2(getActivity(), rooms[room].icon, rooms[room].name, "Available!");
                    nli.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_green, null));
                }
                else {
                    nli = new ListItem2(getActivity(), rooms[room].icon, rooms[room].name, "Occupied");
                    nli.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_red, null));
                }
                listItems.add(nli);
            }
            room++;
        }

        lix.populate(listItems);
        listViewsrr.setAdapter(lix);
        loading_msg.setVisibility(View.INVISIBLE);
        listViewsrr.setVisibility(View.VISIBLE);
    }

    public void assignURLRoomNames(){
        base_url = "https://api2.libcal.com/1.0/rooms?iid=823&key=d095e46065538df2f67eb7cf7d483896";
    }

    public String assignURLAvailability(Integer room_num){
        String str = "https://api2.libcal.com/1.0/room_availability?iid=823&room_id=";
        str = str.concat(room_num.toString());
        str = str.concat("&limit=150&extend=1&key=d095e46065538df2f67eb7cf7d483896");
        return str;
    }

    public void parseJSON(){
        JSONObject j;
        try{
            if(mode == JSONMode.ROOM_TITLES){
                j = new JSONObject(room_grab);
                JSONArray room_arr = j.getJSONArray("rooms");
                rooms = new RoomDetail[room_arr.length()];
                for(int i = 0; i < room_arr.length(); i++) {
                    rooms[i] = new RoomDetail(room_arr.getJSONObject(i).getString("name"),
                            room_arr.getJSONObject(i).getInt("room_id"),
                            room_arr.getJSONObject(i).getInt("group_id"),
                            room_arr.getJSONObject(i).getString("description"),
                            room_arr.getJSONObject(i).getInt("capacity"),
                            room_arr.getJSONObject(i).getString("directions"));
                    if(rooms[i].getName().compareTo("AC130") != 0){
                        availability_urls.add(assignURLAvailability(rooms[i].getRoomID()));
                    }
                }
                mode = JSONMode.ROOM_AVAILABILITY;
                jretr2.execute();
            }
            else{
                for(int i = 0; i < availability_grab.size(); i++) {
                    j = new JSONObject(availability_grab.get(i));
                    JSONObject avail_arr = j.getJSONObject("availability");
                    if(avail_arr.getInt("timeslots_available") > 0) {
                        JSONArray timeslots = avail_arr.getJSONArray("timeslots");
                        for (int k = 0; k < timeslots.length(); k++) {
                            if (timeslots.getJSONObject(k).getString("status").compareTo("Available") == 0) {
                                String time_hour = timeslots.getJSONObject(k).getString("start").substring(11, 13);
                                String time_minute = timeslots.getJSONObject(k).getString("start").substring(14,16);
                                Calendar time = new GregorianCalendar();
                                if(new Integer(time.get(Calendar.HOUR_OF_DAY)).toString().compareTo(time_hour) == 0
                                        && time.get(Calendar.MINUTE) > Integer.parseInt(time_minute)
                                        && time.get(Calendar.MINUTE) < (Integer.parseInt(time_minute)+30)){
                                    rooms[i].toggleCurrentlyAvailable();
                                }
                            }
                        }
                    }
                }
                fillList();
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    /*https://api2.libcal.com/1.0/room_availability/?iid=823&room_id=%td&limit=150&extend=1&key=d095e46065538df2f67eb7cf7d483896

    http://salisbury.beta.libcal.com/rooms_acc.php?gid=%td */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (header_pos[0] != position && header_pos[1] != position) {
            Fragment p1; FragmentManager fragmentManager; FragmentTransaction fragmentTransaction;
            //CAUTION: section headers count as positions
            //i.e. position 0 is section header 1
            int new_pos;
            if (position < first_floor_room_ids.length)
                new_pos = position - 1;
            else
                new_pos = position - 2;

            if (isNetworkAvailable()) {
                p1 = new StudyRoomDisplayFragment(rooms[new_pos]); // Creates new Fragment
            } else {
                p1 = new ConnectionErrorFragment(new StudyRoomDisplayFragment(rooms[new_pos]));
            }
            fragmentManager = getActivity().getSupportFragmentManager(); // Gets Fragment Manager
            fragmentTransaction = fragmentManager.beginTransaction(); // Begins transaction
            fragmentTransaction.replace(R.id.content_container, p1); // Replaces fragment
            fragmentTransaction.addToBackStack(null).commit(); // Adds this fragment to backstack
            MainActivity.pageStack.push(MainActivity.studyroomPage);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}



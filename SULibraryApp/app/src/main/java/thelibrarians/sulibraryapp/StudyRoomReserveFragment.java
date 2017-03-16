package thelibrarians.sulibraryapp;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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

/**
 * Created by Xopher on 10/3/2016.
 */
//this fragment displays a list of study rooms in the library, al=nd whether or not they are available

public class StudyRoomReserveFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listViewsrr; //listView study room reservation
    //array of items pulled from kris_strings.xml
    //ImgTxtListAdapter itlAdapter;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    RoomDetail[] rooms;
    View view;
    public final int[] first_floor_room_ids = {42092,42093};
    public static final String[] sections = {"First Floor", "Other Floors"};
    ArrayList<String> strings; //sequential list of strings in the listview
    ArrayList<Integer> views; //sequential list of view layouts in the listview
    ArrayList<Integer> icons;//sequential list of icons in the listview
    int[] header_pos;
    ActionBar toolbar;

    /*
    * DEFAULT CONSTRUCTOR
    * */
    public StudyRoomReserveFragment(){
        header_pos = new int[2];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_study_room_reserve, container, false); // Assigns view
        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();
        listViewsrr = (ListView) view.findViewById(R.id.listViewsrr); // Assigns listview

        listViewsrr.setOnItemClickListener(this);
        new JSONRetriever().execute();

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.study_room));

        return view;
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
            try {
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine; // Buffer for inputStream
                try {
                    createURL();
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
                } catch (MalformedURLException e) {}
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
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
            int room = 0;

            //section 1
            int h = 0;
            header_pos[0] = h;

            ListItem0 li = new ListItem0(getActivity(), sections[0]);
            //li.getLayout().setBackgroundColor(Color.parseColor("#999999"));
            li.getTextView().setTextColor(Color.parseColor("#8a000000"));
            listItems.add(li);

            for (h = 0; h < first_floor_room_ids.length; h++) {
                listItems.add(new ListItem1(getActivity(), rooms[room].icon, rooms[room].name));
                room++;
            }
            header_pos[1] = h+1;

            //section 2
            li = new ListItem0(getActivity(), sections[1]);
            //li.getLayout().setBackgroundColor(Color.parseColor("#999999"));
            li.getTextView().setTextColor(Color.parseColor("#8a000000"));
            listItems.add(li);

            for (int x = 0; x < rooms.length-first_floor_room_ids.length; x++) {
                listItems.add(new ListItem1(getActivity(), rooms[room].icon, rooms[room].name));
                room++;
            }

            lix.populate(listItems);
            listViewsrr.setAdapter(lix);
        }
    }

    public void createURL(){
        base_url = "https://api2.libcal.com/1.0/rooms?iid=823&key=d095e46065538df2f67eb7cf7d483896";
    }

    public void parseJSON(){
        JSONObject j;
        try{
            j = new JSONObject(full_string);
            JSONArray room_arr = j.getJSONArray("rooms");
            rooms = new RoomDetail[room_arr.length()];
            for(int i = 0; i < room_arr.length(); i++){
                rooms[i] = new RoomDetail(room_arr.getJSONObject(i).getString("name"),
                        room_arr.getJSONObject(i).getInt("room_id"),
                        room_arr.getJSONObject(i).getInt("group_id"),
                        room_arr.getJSONObject(i).getString("description"),
                        room_arr.getJSONObject(i).getInt("capacity"),
                        room_arr.getJSONObject(i).getString("directions"));
            }
        }catch(JSONException e){
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

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}



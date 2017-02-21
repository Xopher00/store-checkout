package thelibrarians.sulibraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.*;
import java.net.*;


public class ComputerAvailabilityDisplayFragment extends Fragment {

    int position; // position in array
    boolean displayed; // If the connection was made and the information is displayed, TRUE, else, FALSE
    //TableLayout table; //GONNA CHANGE THIS
    ImageView top_img; // The image at the top of the page
    TextView num_computers_available,
            room_description, view_as_map,
            group_name_text; // TextViews in view
    LinearLayout computer_table;
    SwipeRefreshLayout swipeRefresher; // SwipeRefreshLayout Object
    Integer win_a, win_o, win_u,
        mac_a, mac_o, mac_u,
        lin_a, lin_o, lin_u,
        num_all, num_available; // Integers pulled from the JSON
    String base_url,full_string; // URL and result of the URL
    HttpURLConnection conn; // Connection object
    DrawerToggleListener toggleListener;

    View view;

    public ComputerAvailabilityDisplayFragment() {
        // Required empty public constructor
    }

    /*
    * Assigns position
    * */
    @SuppressLint("ValidFragment")
    public ComputerAvailabilityDisplayFragment(int pos){
        position = pos;
    }

    private class JSONRetriever extends AsyncTask<Void, Void, Void>{

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
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        * FORMATTING THE URL
        * */
        base_url = getActivity().getResources().getString(R.string.json_url); // First part of all URLs
        String[] mapIDs = getResources().getStringArray(R.array.computer_map_ids); // Loads array of possible room IDs
        base_url = base_url.concat(mapIDs[position]); // Adds room IDs to

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_computer_availability_display, container, false); // Assigns View
        top_img = (ImageView) view.findViewById(R.id.computer_top_img); // Assigns top image object
        computer_table = (LinearLayout) view.findViewById(R.id.computer_table);
        computer_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ComputerIconsExplained();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
        num_computers_available = (TextView) view.findViewById(R.id.num_computers_available); // Assigns Text object
        room_description = (TextView) view.findViewById(R.id.computer_room_description); // Assigns Text object
        group_name_text = (TextView) view.findViewById(R.id.group_name_detail); // Assigns Text object
        view_as_map = (TextView) view.findViewById(R.id.view_as_map_computer); // Assigns Text object
        swipeRefresher = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh); // Assigns SwipeRefreshLayout object
        swipeRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){ // OnClickListener
                new JSONRetriever().execute();
                swipeRefresher.setRefreshing(false);
            }
        });

        String map_url = new String("<a href=\""); // Beginning of hyperlink
        map_url = map_url.concat(getResources().getString(R.string.view_as_map_computer_url)); // middle of hyperlink
        String[] map_ids = getResources().getStringArray(R.array.computer_map_ids); // map id of room
        map_url = map_url.concat(map_ids[position]); // hyperlink
        map_url = map_url.concat("\">View As Map</a> "); // hyperlink
        view_as_map.setText(Html.fromHtml(map_url)); // Sets text to hyperlink
        view_as_map.setMovementMethod(LinkMovementMethod.getInstance()); // Sets hyperlink to lead to a link

        //table.setVisibility(View.INVISIBLE); // Sets image table as Invisible
        view_as_map.setVisibility(View.INVISIBLE); // Sets View As Map as Invisible
        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);
        view.findViewById(R.id.computer_table).setVisibility(View.INVISIBLE);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }

    @Override
    public void onStart(){
        super.onStart();
        displayed = false; // visuals are not displayed at this point
        /*

        * CONNECT TO URL
         *  */
        new JSONRetriever().execute(); // Starts ASync Task
    }

    private void parseJSON(){
        JSONObject j; // Declares JSONObject
        try {

            /* READ DOCUMENTATION PLEASE FOR THE LOVE OF GOD */

            j = new JSONObject(full_string);
            JSONObject all = j.getJSONObject("all");
            num_all = new Integer((Integer)all.get("total"));
            num_available = new Integer((Integer) all.get("available"));
            JSONObject windows = j.getJSONObject("windows");
            win_a = new Integer((Integer)windows.get("available"));
            win_o = new Integer((Integer)windows.get("off"));
            win_u = new Integer((Integer)windows.get("unavailable"));
            JSONObject mac = j.getJSONObject("mac");
            mac_a = new Integer((Integer)mac.get("available"));
            mac_o = new Integer((Integer)mac.get("off"));
            mac_u = new Integer((Integer)mac.get("unavailable"));
            JSONObject linux = j.getJSONObject("linux");
            lin_a = new Integer((Integer) linux.get("available"));
            lin_o = new Integer((Integer) linux.get("off"));
            lin_u = new Integer((Integer) linux.get("unavailable"));
        }catch(JSONException e){
            e.printStackTrace();
        }

        fillGrid();
    }

    private void fillGrid() {
        room_description.setText(getResources().getStringArray(R.array.computer_room_descriptions)[position]); // Sets description
        group_name_text.setText(getResources().getStringArray(R.array.computer_group_names)[position]); // Sets group name
        /*
        Sets top image
         */
        switch (position) {
            case 0:
                top_img.setImageResource(R.drawable.ac102_long);
                break;
            case 1:
                top_img.setImageResource(R.drawable.ac1c20_long);
                break;
            case 2:
                top_img.setImageResource(R.drawable.ac1c5_long);
                break;
            case 3:
                top_img.setImageResource(R.drawable.ac117_long);
                break;
            case 4:
                top_img.setImageResource(R.drawable.ac162_long);
                break;
            case 5:
                top_img.setImageResource(R.drawable.ac2c1_long);
                break;
            case 6:
                top_img.setImageResource(R.drawable.ac261_long);
                break;
            case 7:
                top_img.setImageResource(R.drawable.ac262_long);
                break;
            case 8:
                top_img.setImageResource(R.drawable.ac300_long);
                break;
        }

        Integer code = new Integer(0); // Initializes integer for response code
        if(conn != null) { // If connection is created
            try {
                code = conn.getResponseCode(); // Gets response code
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (code == HttpURLConnection.HTTP_OK) { // If connection is made
            TextView current_num = (TextView)view.findViewById(R.id.windows_pc_available_num);
            current_num.setText(win_a.toString());
            current_num = (TextView)view.findViewById(R.id.windows_pc_inuse_num);
            current_num.setText(win_u.toString());
            current_num = (TextView)view.findViewById(R.id.windows_pc_off_num);
            current_num.setText(win_o.toString());
            current_num = (TextView)view.findViewById(R.id.linux_pc_available_num);
            current_num.setText(lin_a.toString());
            current_num = (TextView)view.findViewById(R.id.linux_pc_inuse_num);
            current_num.setText(lin_u.toString());
            current_num = (TextView)view.findViewById(R.id.linux_pc_off_num);
            current_num.setText(lin_o.toString());
            current_num = (TextView)view.findViewById(R.id.mac_pc_available_num);
            current_num.setText(mac_a.toString());
            current_num = (TextView)view.findViewById(R.id.mac_pc_inuse_num);
            current_num.setText(mac_u.toString());
            current_num = (TextView)view.findViewById(R.id.mac_pc_off_num);
            current_num.setText(mac_o.toString());
        }
        else{
            Toast toast = Toast.makeText(getContext(),"Could not connect to network, check connection.", Toast.LENGTH_LONG); //  Error message
            toast.show(); // Show message
        }
        view.findViewById(R.id.computer_table).setVisibility(View.VISIBLE);
        view_as_map.setVisibility(View.VISIBLE); //View As Map = VISIBLE
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

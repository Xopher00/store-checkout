package thelibrarians.sulibraryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.ArrayList;
import java.io.*;
import java.lang.*;
import java.net.*;


public class ComputerAvailabilityDisplayFragment extends Fragment {

    int position;
    GridLayout grid;
    ImageView top_img;
    ImageView[] icons;
    TextView test_json_parse;
    int[] top_img_candidates = {R.drawable.ac1c5_long};
    int[] icon_candidates = {R.drawable.pcavailable, R.drawable.pcinuse, R.drawable.pcoff};
    Integer win_a, win_o, win_u,
        mac_a, mac_o, mac_u,
        lin_a, lin_o, lin_u;
    String base_url,full_string;

    public ComputerAvailabilityDisplayFragment() {
        // Required empty public constructor
    }

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
                    HttpURLConnection conn; // Connection object
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
                        Log.d("RESPONSE", response.toString()); // Logs full report
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
        base_url = getActivity().getResources().getString(R.string.json_url);
        String[] mapIDs = getResources().getStringArray(R.array.map_ids);
        base_url = base_url.concat(mapIDs[position]);

        /*

        * CONNECT TO URL
         *  */
        new JSONRetriever().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_computer_availability_display, container, false);
        grid = (GridLayout)view.findViewById(R.id.grid);
        top_img = (ImageView)view.findViewById(R.id.computer_top_img);
        test_json_parse = (TextView)view.findViewById(R.id.json_text);
        return view;
    }

    private void parseJSON(){
        JSONObject j = null;
        try {

            /* READ DOCUMENTATION PLEASE FOR THE LOVE OF GOD */

            j = new JSONObject(full_string);
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

        test_json_parse.setText(win_a.toString());
    }
}

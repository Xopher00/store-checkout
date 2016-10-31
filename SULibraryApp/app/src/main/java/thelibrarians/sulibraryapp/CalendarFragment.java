package thelibrarians.sulibraryapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
 * general fragment for HomeFragment's viewpager
 */

public class CalendarFragment extends Fragment {

    int tab;
    JSONObject jDay;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    JSONObject week1;
    JSONObject week2;
    ArrayList<JSONObject> myweek;

    public CalendarFragment(int position, JSONObject j) {
        tab = position;
        jDay = j;
    }

    public CalendarFragment(int position) {
        //constructor for testing before adding JSON
        tab = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_url = "https://api3.libcal.com/api_hours_grid.php?iid=823&format=json&weeks=2";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        new JSONRetriever().execute();

        TextView tv = (TextView) v.findViewById(R.id.tv);
        tv.setText("home page "+tab);

        return v;
    }

    private void parseJSON() {
        try {
            JSONObject j = new JSONObject(full_string);
            //get info for this week and next week
            //each week object contains objects for each day of the week
            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);

            //get today's date

            //day = 6 - (day of week + 6); use Calendar class

            //build array of 7 day period
            myweek = new ArrayList<JSONObject>();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class JSONRetriever extends AsyncTask<Void, Void, Void> {

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
}

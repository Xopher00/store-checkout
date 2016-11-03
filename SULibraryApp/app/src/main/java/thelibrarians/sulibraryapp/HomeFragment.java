package thelibrarians.sulibraryapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HomeFragment extends Fragment {

    View view;
    FragmentManager fm;
    HomeFragment.SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    JSONObject week1;
    JSONObject week2;
    ArrayList<JSONObject> myweek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        base_url = "https://api3.libcal.com/api_hours_grid.php?iid=823&format=json&weeks=2";

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        fm =  getActivity().getSupportFragmentManager();

        new JSONRetriever().execute();

        //clear fragment backstack when home page is visited
        if(fm.getBackStackEntryCount() > 0)
            fm.popBackStack(fm.getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        return view;
    }





    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new CalendarFragment(position, myweek.get(position));
        }

        @Override
        public int getCount() {
            return 7;
        }
    }

    private void parseJSON() {

        try {
            JSONObject j = new JSONObject(full_string);
            //get info for this week and next week
            //each week object contains objects for each day of the week
            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);

            //get today's date
            // 1=SUNDAY, 7=SATURDAY
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            //build array of 7 day period
            myweek = new ArrayList<JSONObject>();
            for(int x = 0; x < 7; x++) {
                if(day <= 7) {
                    switch (day) {
                        case Calendar.SUNDAY:
                            myweek.add(week1.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week1.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week1.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week1.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week1.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week1.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week1.getJSONObject("Saturday"));
                            break;
                    }
                } else {
                    switch (day-7) {
                        case Calendar.SUNDAY:
                            myweek.add(week2.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week2.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week2.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week2.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week2.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week2.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week2.getJSONObject("Saturday"));
                            break;
                    }
                }
                day++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            parseJSON();
            return null;
        }

        /*
        * THIS STARTS ONCE doInBackground(...) COMPLETES
        *
        * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
        * */

        protected void onPostExecute(Void v){
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new HomeFragment.SectionsPagerAdapter(getChildFragmentManager());
            //mSectionsPagerAdapter.getItem()

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) view.findViewById(R.id.frag_pager);
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }
    }



}

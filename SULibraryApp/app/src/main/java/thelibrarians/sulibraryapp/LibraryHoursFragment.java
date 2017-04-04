package thelibrarians.sulibraryapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;

public class LibraryHoursFragment extends Fragment {

    String date, rendered;
    boolean internet = false;
    int pos = 0;

    JSONObject week1; //each of these objects is defined as one week of JSON information
    JSONObject week2;
    JSONObject week3;
    JSONObject week4;
    JSONObject week5;
    JSONObject week6;
    JSONObject week7;

    ListView listView; //listview displayed holding hours information
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String[] sectionHeader;
    TextView text;
    ActionBar toolbar;

    String base_url, full_string; // URL and result of the URL
    HttpURLConnection conn; // Connection object
    Activity activity;

    ArrayList<JSONObject> myweek;

    public LibraryHoursFragment() {
        Log.i("hours", "default");
    }

    public LibraryHoursFragment(JSONObject j, int p) {
        pos = p;
        internet = true;
        try {
            date = j.getString("date");
            rendered = j.getString("rendered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    url = new URL(base_url); // url passed in
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) {
                            Log.i("hours", "catch 4");
                        }
                    } catch (IOException e) {
                        Log.i("hours", "catch 3");
                    }
                } catch (MalformedURLException e) {
                    Log.i("hours", "catch 2");
                }
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
                Log.i("hours", "catch 1");
            }
            parseJSON();
            return null;
        }

        /*
        * THIS STARTS ONCE doInBackground(...) COMPLETES
        *
        * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
        * */

        protected void onCancelled() {
        }

        protected void onPostExecute(Void v) {  //call weekfunction() to parse the JSON string and populate the listview
            weekFunction();

            lix.populate(listItems);
            listView.setAdapter(lix);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources r = getResources();

        base_url = "https://api3.libcal.com/api_hours_grid.php?iid=823&format=json&weeks=7"; //URL holding JSON string

        View view = inflater.inflate(R.layout.fragment_library_hours, container, false);

        lix = new ListviewX(getActivity());

        listItems = new ArrayList<ListItem>();

        listView = (ListView) view.findViewById(R.id.hours_list); //need to be able to access an xml element with java so that you can modify it dynamically

        text = (TextView) view.findViewById(R.id.hours_text);

        text.setText("The hours listed below are the times that the library service desk and the library stacks are open. Other areas of the library and other departments within the Guerrieri Academic Commons may keep different hours.");

        sectionHeader = getResources().getStringArray(R.array.hours_header);

        new JSONRetriever().execute();

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.lib_hours));

        return view;
    }


    private void weekFunction() { //function accepts the JSON object for each week and divides its information to place in the listview
        try {

            int i = 0;

            listItems.add(new ListItem5(getActivity(), "Today", myweek.get(i).getString("rendered"))); //used for the top row of list view
            i++;

            Calendar cal = Calendar.getInstance(); //Calendar class is used for day, date, month
            int day = cal.DAY_OF_WEEK-1;

            ListItem4 l4;

            for (; i < myweek.size(); i++) {  //display the day, date and month of each listview entry
                l4 = new ListItem4(getActivity(), getMonth(myweek.get(i).getString("date")),
                        getDay(myweek.get(i).getString("date")),
                        getDayOfWeek(day),
                        getTime(myweek.get(i).getString("rendered")));

                if(day == cal.SUNDAY || day == cal.SATURDAY) {  //changing the background color for weekend days
                    l4.getLayout().setBackgroundColor(Color.parseColor("#d9d9d9"));
                }
                listItems.add(l4);  //manually add item in listview
                day++;
                if(day > 7){
                    day -= 7;
                }
            }

            listItems.add(new ListItem0(getActivity(), "Additional hours will be posted as they become available."));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSON() {
        try {

            JSONObject j = new JSONObject(full_string);

            //each of these variables holds one week's worth of JSON information
            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);
            week3 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(2);
            week4 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(3);
            week5 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(4);
            week6 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(5);
            week7 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(6);

            myweek = new ArrayList<JSONObject>();   //creating a custom 7 day week

            //gets today's date
            //Sunday = 1 --- Saturday = 7
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            //getting the day of the week for each entry in the listview; increment by week
            for (int i = 0; i < 49; i++) { //increment to 49 because there are 49 days in 7 weeks displayed
                if (day <= 7) { //week 1
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
                } else if (day > 7 && day <= 14) {
                    switch (day - 7) { //week 2
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
                } else if (day > 14 && day <= 21) {
                    switch (day - 14) { //week 3
                        case Calendar.SUNDAY:
                            myweek.add(week3.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week3.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week3.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week3.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week3.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week3.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week3.getJSONObject("Saturday"));
                            break;
                    }
                } else if (day > 21 && day <= 28) {
                    switch (day - 21) {  //week 4
                        case Calendar.SUNDAY:
                            myweek.add(week4.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week4.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week4.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week4.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week4.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week4.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week4.getJSONObject("Saturday"));
                            break;
                    }
                } else if (day > 28 && day <= 35) {
                    switch (day - 28) {  //week 5
                        case Calendar.SUNDAY:
                            myweek.add(week5.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week5.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week5.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week5.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week5.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week5.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week5.getJSONObject("Saturday"));
                            break;
                    }
                } else if (day > 35 && day <= 42) {
                    switch (day - 35) {  //week 6
                        case Calendar.SUNDAY:
                            myweek.add(week6.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week6.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week6.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week6.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week6.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week6.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week6.getJSONObject("Saturday"));
                            break;
                    }
                } else if (day > 42 && day <= 49) {
                    switch (day - 42) {  //week 7
                        case Calendar.SUNDAY:
                            myweek.add(week7.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week7.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week7.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week7.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week7.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week7.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week7.getJSONObject("Saturday"));
                            break;
                    }
                }
                day++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String getMonth(String d) { //take the JSON information and return the specified month

        String[] parts = d.split("-");
        int month = Integer.parseInt(parts[1]);

        switch (month) { //month in JSON ranges from 1 - 12
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }

        return "unavailable";
    }

    private String getDay(String d) {  //take the JSON information and return the specified date

        String[] parts = d.split("-");  //date ranges from 01 - 31
        int day = Integer.parseInt(parts[2]);

        return String.valueOf(day);

        //return "unavailable";
    }

    private String getDayOfWeek(int dayOfWeek) {  //take the JSON information and return the specified day of the week

        switch(dayOfWeek) {  //day of the week from Calendar class ranges from MONDAY to SUNDAY
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
        }
        return "unavailable";
    }

    private String getTime(String hours){  //take information and return the specified hour range

        switch(hours){ //hours from JSON are as follows
            case "7:30am - 8pm":
                return "7:30am - 8:00pm";
            case "10am - 8pm":
                return "10:00am - 8:00pm";
            case "11am - 2am":
                return "11:00am - 2:00am";
            case "7:30am - 2am":
                return "7:30am - 2:00am";
            case "7:30am - 5pm":
                return "7:30am - 5:00pm";
            case "7:30am - 4pm":
                return "7:30am - 4:00pm";
            case "Closing at 7 PM":
                return "Closing at 7 PM";
            case "24 Hours":
                return "Open 24 Hours";
            case "Library Closed":
                return "Library Closed";
            case "Open at 7:30 AM":
                return "Open at 7:30 AM";
            case "10am - 2am":
                return "10:00am - 2:00am";
            case "10am - 10pm":
                return "10:00am - 10:00pm";
        }
        return "unavailable";
    }
}

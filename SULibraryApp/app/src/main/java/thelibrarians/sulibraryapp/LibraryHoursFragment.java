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

    String weeks, day, times, currently_open, status, hours;
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

    LayerDrawable[] icons;
    ListView listView;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String[] sectionHeader;
    String[] titles;
    TextView text;
<<<<<<< HEAD
    ActionBar toolbar;
    ArrayList<JSONObject> myweek;   //custom 7 day week
=======
>>>>>>> f7b9732890738af4ea057baa08bffbf72bf6e83b

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

        protected void onPostExecute(Void v) {
            weekFunction();
            //weekFunction(week2);
            // weekFunction(week3);
            //  weekFunction(week4);
            //  weekFunction(week5);
            // weekFunction(week6);
            // weekFunction(week7);

        /*
        * 4 = text, text
        *     text
        *     text
        * 5 = text, text
        * */
       /* int cheader = 0;
        int ctitles = 0;
        for(int x = 0; x < titles.length+sectionHeader.length; x++) {
            switch(x) {
                case 0: //headers
                    ListItem0 li0 = new ListItem0(getActivity(), sectionHeader[cheader++]);
                    li0.getTextView().setTextColor(Color.parseColor("#8a000000"));
                    listItems.add(li0);
                    break;
                default:
                    ListItem0 li = new ListItem0(getActivity(), titles[ctitles++]);
                    listItems.add(li);
            }
        }*/

            //populateListView(sectionHeader, null, titles, null, null);
            lix.populate(listItems);
            listView.setAdapter(lix);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources r = getResources();

        base_url = "https://api3.libcal.com/api_hours_grid.php?iid=823&format=json&weeks=7";

        View view = inflater.inflate(R.layout.fragment_library_hours, container, false);

        lix = new ListviewX(getActivity());

        listItems = new ArrayList<ListItem>();

        listView = (ListView) view.findViewById(R.id.hours_list); //need to be able to access an xml element with java so that you can modify it dynamically

        text = (TextView) view.findViewById(R.id.hours_text);

        text.setText("The hours listed below are the times that the library service desk and the library stacks are open. Other areas of the library and other departments within the Guerrieri Academic Commons may keep different hours.");

        sectionHeader = getResources().getStringArray(R.array.hours_header);
        //titles = getResources().getStringArray(R.array.hours);

        new JSONRetriever().execute();


        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.lib_hours));

        return view;
    }


    private void weekFunction() { //function accepts the JSON object for each week and divides its information to place in the listview
        try {
            //weeks = new String((String).get("weeks"));

            //date = new String((String).get("date"));
            //rendered = new String(.get("rendered"));

            int i = 0;
            int position = 5;

            //listItems.add(new ListItem1(activity, R.drawable.socialwork, r.getString(R.string.social)));
            // listItems.add(new ListItem5(activity, hours)); //used for the top row of list view
            // listItems.add(new ListItem4(activity, ));
            // String month = formatMonth(date);

            listItems.add(new ListItem5(getActivity(), "Today", myweek.get(i).getString("rendered"))); //used for the top row of list view
            i++;

            Calendar cal = Calendar.getInstance();

            ListItem4 l4;

            for (; i < myweek.size(); i++) {
                l4 = new ListItem4(getActivity(), getMonth(myweek.get(i).getString("date")),
                        getDay(myweek.get(i).getString("date")),
                        getDayOfWeek(cal.DAY_OF_WEEK + position),
                        myweek.get(i).getString("rendered"));

                if(position == 1 || position == 7) {
                    l4.getLayout().setBackgroundColor(Color.parseColor("#d9d9d9"));
                }
                listItems.add(l4);
                position++;
                if(position > 7){
                    position -= 7;
                }
            }

            //listItems.add(new ListItem5(getActivity(), "Today", "7 am - 10pm"));
            // listItems.add(new ListItem4(getActivity(), "Mar", "30", "Thu", "7 am - 10pm"));
            // formatDay(date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSON() {
        try {

            JSONObject j = new JSONObject(full_string);

            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);
            week3 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(2);
            week4 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(3);
            week5 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(4);
            week6 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(5);
            week7 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(6);

            myweek = new ArrayList<JSONObject>();   //custom 7 day week

            //gets today's date
            //Sunday = 1 --- Saturday = 7
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            for (int i = 0; i < 49; i++) {
                if (day <= 7) {
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
                    switch (day - 7) {
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
                    switch (day - 14) {
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
                    switch (day - 21) {
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
                    switch (day - 28) {
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
                    switch (day - 35) {
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
                    switch (day - 42) {
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

        //day1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0).getJSONObject("Sunday").getJSONObject("times");

    }


    private String getMonth(String d) {
        String[] parts = d.split("-");
        int month = Integer.parseInt(parts[1]);

        switch (month) {
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

    private String getDay(String d) {
        String[] parts = d.split("-");
        int day = Integer.parseInt(parts[2]);

        return String.valueOf(day);

        //return "unavailable";
    }

    private String getDayOfWeek(int dayOfWeek) {
        if(dayOfWeek > 7)
            dayOfWeek -= 7;

        switch(dayOfWeek) {
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

        return "Unavailable";
    }

}
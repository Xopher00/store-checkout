package thelibrarians.sulibraryapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
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
    JSONObject day1;
    LayerDrawable[] icons;
    ListView listView;
    //ImgTxtListAdapter itlAdapter;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String[] sectionHeader;
    String[] titles;
    TextView text;
    ArrayList<JSONObject> myweek;   //custom 7 day week

    String base_url,full_string; // URL and result of the URL
    HttpURLConnection conn; // Connection object
    Activity activity;

    public LibraryHoursFragment() {Log.i("hours","default");}

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
                        conn = (HttpURLConnection)url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) {Log.i("hours", "catch 4");}
                    } catch (IOException e) {Log.i("hours", "catch 3");}
                } catch (MalformedURLException e) {Log.i("hours", "catch 2");}
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {Log.i("hours", "catch 1");}
            parseJSON();
            return null;
        }

        /*
        * THIS STARTS ONCE doInBackground(...) COMPLETES
        *
        * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
        * */

        protected void onCancelled(){}

        protected void onPostExecute(Void v){
            weekFunction(day1);
            //  weekFunction(week2);
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

        //itlAdapter = new ImgTxtListAdapter(getActivity());
        lix = new ListviewX(getActivity());

        listItems = new ArrayList<ListItem>();

        listView = (ListView) view.findViewById(R.id.hours_list); //need to be able to access an xml element with java so that you can modify it dynamically

        text = (TextView) view.findViewById(R.id.hours_text);

        text.setText("The hours listed below are the times that the library service desk and the library stacks are open. Other areas of the library and other departments within the Guerrieri Academic Commons may keep different hours.");

        sectionHeader = getResources().getStringArray(R.array.hours_header);
        //titles = getResources().getStringArray(R.array.hours);

        new JSONRetriever().execute();


        return view;
    }


    private void weekFunction(JSONObject week){ //function accepts the JSON object for each week and divides its information to place in the listview
        try{
            weeks = new String((String)week.get("weeks"));
            times = new String((String) week.get("times"));
            currently_open = new String((String) week.get("currently_open"));
            status = new String((String) week.get("status"));
            hours = new String((String) week.get("hours"));
            date = new String((String) week.get("date"));
            rendered = new String((String) week.get("rendered"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //listItems.add(new ListItem1(activity, R.drawable.socialwork, r.getString(R.string.social)));

       // listItems.add(new ListItem5(activity, hours)); //used for the top row of list view

       // listItems.add(new ListItem4(activity, ));

       // String month = formatMonth(date);

        listItems.add(new ListItem5(getActivity(), "Today", hours)); //used for the top row of list view
        listItems.add(new ListItem5(getActivity(), "Today", "7 am - 10pm"));
        listItems.add(new ListItem4(getActivity(), "Mar", "30", "Thu", "7 am - 10pm"));
       // formatDay(date);

    }

    private void parseJSON(){
        JSONObject j; // Declares JSONObject
        try {

            j = new JSONObject(full_string);

            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);
            week3 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(2);
            week4 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(3);
            week5 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(4);
            week6 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(5);
            week7 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(6);


            day1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0).getJSONObject("Sunday").getJSONObject("times");


        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    /*private String formatMonth(String date){
        char [] day = new char [10]; //string pulled holding entire date
        char [] month = new char [2];  //string created only holding month

       // for(int i = 0; i < date.length(); i++){
            day[i] = date.charAt(i);
        }

        day[6] = month[0]; //pulling only the month so we can determine what abbrev. to display
        day[7] = month[1];

        if(month[0] == '0' && month[1] == '1'){  //month is January
            return "Jan";
        }
        else if(month[0] == '0' && month[1] == '2'){  //month is February
            return "Feb";
        }
        else if(month[0] == '0' && month[1] == '3'){  //month is March
            return "Mar";
        }
        else if(month[0] == '0' && month[1] == '4'){  //month is April
            return "Apr";
        }
        else if(month[0] == '0' && month[1] == '5'){  //month is May
            return "May";
        }
        else if(month[0] == '0' && month[1] == '6'){  //month is June
            return "Jun";
        }
        else if(month[0] == '0' && month[1] == '7'){  //month is July
            return "Jul";
        }
        else if(month[0] == '0' && month[1] == '8'){  //month is August
            return "Aug";
        }
        else if(month[0] == '0' && month[1] == '9'){  //month is September
            return "Sep";
        }
        else if(month[0] == '1' && month[1] == '0'){  //month is October
            return "Oct";
        }
        else if(month[0] == '1' && month[1] == '1'){  //month is November
            return "Nov";
        }
        else
            return "Dec";


        //taking the digit of the month from the string and returning the abbrev. of said
        //month to display in the listview

        //need to do the same for each day of the week
    }
*/
   // private String formatDay(String date){

    //}
}

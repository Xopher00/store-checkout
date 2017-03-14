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

import org.json.JSONException;
import org.json.JSONObject;

public class LibraryHoursFragment extends Fragment {

    String week, day, times, currently_open, status, hours, date, rendered;
    boolean internet = false;
    int pos = 0;

    JSONObject week1;
    JSONObject week2;
    JSONObject week3;
    JSONObject week4;
    JSONObject week5;
    JSONObject week6;
    JSONObject week7;
    LayerDrawable[] icons;
    ListView listView;
    //ImgTxtListAdapter itlAdapter;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String[] sectionHeader;
    String[] titles;
    TextView text;

    String base_url,full_string; // URL and result of the URL
    HttpURLConnection conn; // Connection object

    public LibraryHoursFragment() {Log.i("hello","default");}

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

        protected void onCancelled(){}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        * FORMATTING THE URL
        * */
        base_url = "https://api3.libcal.com/api_hours_grid.php?iid=823&format=json&weeks=7";
       // base_url = getActivity().getResources().getString(R.string.json_url); // First part of all URLs
        //  String[] mapIDs = getResources().getStringArray(R.array.computer_map_ids); // Loads array of possible room IDs
        //  base_url = base_url.concat(mapIDs[position]); // Adds room IDs to
        //  displayed = false; // visuals are not displayed at this point
        /*

        * CONNECT TO URL
         *  */
        new JSONRetriever().execute(); // Starts ASync Task
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources r = getResources();

        View view = inflater.inflate(R.layout.fragment_library_hours, container, false);

        //itlAdapter = new ImgTxtListAdapter(getActivity());
        lix = new ListviewX(getActivity());

        listItems = new ArrayList<ListItem>();

        listView = (ListView) view.findViewById(R.id.hours_list); //need to be able to access an xml element with java so that you can modify it dynamically

        text = (TextView) view.findViewById(R.id.hours_text);

        text.setText("The hours listed below are the times that the library service desk and the library stacks are open. Other areas of the library and other departments within the Guerrieri Academic Commons may keep different hours.");

        sectionHeader = getResources().getStringArray(R.array.hours_header);
        titles = getResources().getStringArray(R.array.hours);

        /*
        * 4 = text, text
        *     text
        *     text
        * 5 = text, text
        * */
        int cheader = 0;
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
        }

        //populateListView(sectionHeader, null, titles, null, null);
        lix.populate(listItems);
        listView.setAdapter(lix);

        return view;
    }

    private void parseJSON(){
        JSONObject j; // Declares JSONObject
        try {

            /* READ DOCUMENTATION PLEASE FOR THE LOVE OF GOD */

            j = new JSONObject(full_string);
            JSONObject GuerrieriAcademicCommons = j.getJSONObject("GuerrieriAcademicCommons");
            week = new String((String)GuerrieriAcademicCommons.get("week"));
            day = new String((String) GuerrieriAcademicCommons.get("day"));
            times = new String((String) GuerrieriAcademicCommons.get("times"));
            currently_open = new String((String) GuerrieriAcademicCommons.get("currentlyopen"));
            status = new String((String) GuerrieriAcademicCommons.get("status"));
            hours = new String((String) GuerrieriAcademicCommons.get("hours"));
            date = new String((String) GuerrieriAcademicCommons.get("date"));
            rendered = new String((String) GuerrieriAcademicCommons.get("rendered"));

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

/*
    public void populateListView(String[] sectionHeader, LayerDrawable[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();
//for each header in header array it is going to go through the loop
        //depending on iteration of loop then we are going to do another loop that is dependent on the number of items below that
        //specific header
        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //case 0 corresponds to the 'Hours' header
            switch(i) {
                case 0:
                    items = 3;
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr(); //itlAdapter = list adapter; places the titles under the header (6 times)
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionDrawable(icons[position]);
                            str.setSectionName("");
                            if(titles != null)
                                str.setSectionTitle(titles[position]);
                            if(subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if(notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
            }
        }
    }*/

}

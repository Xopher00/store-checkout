package thelibrarians.sulibraryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class StudyRoomDisplayFragment extends Fragment{

    int id, position;
    String groupid;
    View roomView;
    TextView roomName,roomAvail,roomCap,roomLoc,roomTime,roomDescription,roomReserve;
    ImageView roomImage;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    LinearLayout roomAll;

    public StudyRoomDisplayFragment(int position){
        this.position = position;
    }

    public StudyRoomDisplayFragment(){}

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomView = inflater.inflate(R.layout.fragment_study_room_display, container, false);
        roomAll = (LinearLayout) roomView.findViewById(R.id.study_room_all);
        roomAll.setVisibility(View.INVISIBLE);

        //create ImageView object
        //assign ImageView id
        roomImage = (ImageView) roomView.findViewById(R.id.roomImage);
        //create TextView Objects
        //assign TextView id's to them
        roomName = (TextView) roomView.findViewById(R.id.roomName);
        roomAvail = (TextView) roomView.findViewById(R.id.roomAvail);
        roomCap = (TextView) roomView.findViewById(R.id.roomCap);
        roomLoc = (TextView) roomView.findViewById(R.id.roomLoc);
        roomDescription = (TextView) roomView.findViewById(R.id.roomDescription);
        roomReserve = (Button) roomView.findViewById(R.id.reserveButton);
        roomReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = new String("http://salisbury.libcal.com/rooms_acc.php?gid=");
                url = url.concat(groupid);
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        new JSONRetriever().execute();
        return roomView;
    }
    private void parseJSON(){
        try {
            JSONObject json_obj = new JSONObject(full_string);
            JSONObject avail = json_obj.getJSONObject("availability");
            roomName.setText(avail.getString("name"));
            roomAvail.setText(calculateAvailability(avail));
            roomCap.setText(avail.getString("capacity"));
            roomLoc.setText(avail.getString("directions"));
            roomDescription.setText(avail.getString("description"));
            setRoomIcon();
        }catch(JSONException e){
            e.printStackTrace();
        }
        roomAll.setVisibility(View.VISIBLE);
    }
    private void createURL(){
        base_url = "https://api2.libcal.com/1.0/room_availability/?iid=823&room_id=";
        String[] study_room_ids = getResources().getStringArray(R.array.study_room_ids);
        String[] group_ids = getResources().getStringArray(R.array.study_room_groups);
        String id = new String();
        if(position < 3) {
            id = study_room_ids[position - 1];
            groupid = group_ids[position - 1];
        }
        else {
            id = study_room_ids[position - 2];
            groupid = group_ids[position - 2];
        }
        base_url = base_url.concat(id);
        base_url = base_url.concat("&limit=150&extend=1&key=d095e46065538df2f67eb7cf7d483896");
    }
    private String calculateAvailability(JSONObject avail){
        if(position < 3)
            return "First come, first serve";
        else{
            try {
                if (avail.getInt("timeslots_available") > 0) {
                    return "Available";
                } else {
                    return "Unavailable";
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return new String();
    }
    private void setRoomIcon(){
        switch(position){
            case 1:
                roomImage.setImageResource(R.drawable.ac139);//used to pass an image to fragment
                break;
            case 2:
                roomImage.setImageResource(R.drawable.ac140);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                break;
            case 8:
                roomImage.setImageResource(R.drawable.ac234);
                break;
            case 9:
                roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                break;
            case 10:
                roomImage.setImageResource(R.drawable.ac236);
                break;
            case 11:
                roomImage.setImageResource(R.drawable.ac237);
                break;
            case 12:
                roomImage.setImageResource(R.drawable.ac238);
                break;
            case 13:
                roomImage.setImageResource(R.drawable.ac239);
                break;
            case 14:
                roomImage.setImageResource(R.drawable.ac240);
                break;
            case 15:
                roomImage.setImageResource(R.drawable.ac241);
                break;
            case 16:
                roomImage.setImageResource(R.drawable.ac242);
                break;
        }
    }
}


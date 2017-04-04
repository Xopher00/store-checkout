package thelibrarians.sulibraryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

    int id;
    RoomDetail room_detail;
    View roomView;
    TextView roomName,roomAvail,roomCap,roomLoc,roomDescription,roomReserve;
    ImageView roomImage;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    LinearLayout roomAll;
    DrawerToggleListener toggleListener;

    public StudyRoomDisplayFragment(RoomDetail rd){
        this.room_detail = rd;
    }

    public StudyRoomDisplayFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            full_string=savedInstanceState.getString("JSON");
        }
        roomView = inflater.inflate(R.layout.fragment_study_room_display, container, false); // Gets View
        roomAll = (LinearLayout) roomView.findViewById(R.id.study_room_all); // Gets Layout
        roomAll.setVisibility(View.INVISIBLE); // Sets View to Invisible until loading is finished
        //create ImageView object
        //assign ImageView id
        roomImage = (ImageView) roomView.findViewById(R.id.roomImage);
        //create TextView Objects
        //assign TextView id's to them
        roomName = (TextView) roomView.findViewById(R.id.roomName); // Assigns TextView from xml
        roomAvail = (TextView) roomView.findViewById(R.id.roomAvail); // Assigns TextView from xml
        roomCap = (TextView) roomView.findViewById(R.id.roomCap); // Assigns TextView from xml
        roomLoc = (TextView) roomView.findViewById(R.id.roomLoc); // Assigns TextView from xml
        roomDescription = (TextView) roomView.findViewById(R.id.roomDescription); // Assigns TextView from xml
        roomReserve = (Button) roomView.findViewById(R.id.reserveButton); // Assigns Button from xml
        roomReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // OnClick
                String url = new String("http://salisbury.libcal.com/rooms_acc.php?gid="); // URL
                url = url.concat(((Integer)room_detail.getGroupID()).toString()); // Adds groupid to complete line
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                webViewFragment webView = new webViewFragment(url, "Reserve A Room");
                ft.replace(R.id.content_container, webView);
                ft.addToBackStack(null).commit();
            }
        });

        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);

        new JSONRetriever().execute(); // Gets JSON string
        return roomView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
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
        }
    }

    private void parseJSON(){
        try {
            JSONObject json_obj = new JSONObject(full_string); // Initializes JSONObject
            JSONObject avail = json_obj.getJSONObject("availability"); // Initializes JSONObject for availability
            roomName.setText(room_detail.getName()); // Gets and sets room name
            roomAvail.setText(calculateAvailability(avail)); // Gets and sets availability message
            roomCap.setText(avail.getString("capacity")); // Gets and sets capacity
            roomLoc.setText(avail.getString("directions")); // Gets and sets directions
            roomDescription.setText(avail.getString("description")); // Gets and sets room description
            setRoomIcon();
        }catch(JSONException e){ // Displays error message
            e.printStackTrace(); // ||
        }
        roomAll.setVisibility(View.VISIBLE); // Sets Layout to Visible
    }

    /*
    * CREATES THE URL FOR RESERVE ROOM SITE
    * */
    private void createURL(){
        base_url = "https://api2.libcal.com/1.0/room_availability/?iid=823&room_id="; // Beginning of URL
        id = room_detail.getRoomID();
        Log.e("ROOM ID", ((Integer)id).toString());
        base_url = base_url.concat(((Integer)id).toString());
        base_url = base_url.concat("&limit=150&extend=1&key=d095e46065538df2f67eb7cf7d483896"); // Ends URL
    }

    /*
    * DETERMINES AVAILABILITY MESSAGE BASED ON JSON REPORT
    * */
    private String calculateAvailability(JSONObject avail){
        try {
            if(room_detail.getSection().compareTo(StudyRoomReserveFragment.sections[0]) == 0) //If room is on First Floor
                return "First Come, First Serve"; //  First come first serve
            else{
                if (avail.getInt("timeslots_available") > 0) // If room is on second floor and can be reserved today
                    return "Available";
                else // If room is on second floor and cannot be reserved today
                    return "Unavailable";
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return new String();
    }

    /*
    * Although rooms are added to array dynamically, the room images are hardcoded. This method
    * must be updated every time a new room is added to the json
    * */
    private void setRoomIcon(){
        if(room_detail.getPicAvailable()){
            String new_name = new String();
            if(room_detail.getName().length() > 8){
                for(int i = 0; i < 3; i++){
                    new_name = new_name.concat(Character.toString(room_detail.getName().charAt(i+5)));
                }
            }
            String study_room_pic_name = new String("studyroom" + new_name);
            roomImage.setImageResource(getResources().getIdentifier(study_room_pic_name,"drawable",getContext().getPackageName()));
        }
    }
}


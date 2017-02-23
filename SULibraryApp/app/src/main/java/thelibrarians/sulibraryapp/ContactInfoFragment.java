package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/19/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class ContactInfoFragment extends Fragment implements AdapterView.OnItemClickListener {

    static int position;
    String url_json;
    ListView listViewct; //listView contacts
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    String[] subitems;
    String[] strings; //sequential list of strings as they appear in the listview
    int[] views = {0, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2}; //sequential list of listview layouts
    int index; //index of icon to be changed when neccesary
    int value; //item number in a string array

    int[] icons = {R.drawable.available, R.drawable.available, R.drawable.available,
            R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call,
            R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor,
            R.drawable.contactcolor, R.drawable.jbellistri, R.drawable.sebrazer,
            R.drawable.spburton, R.drawable.mxchakraborty, R.drawable.hfchaphe, R.drawable.fxchirombo,
            R.drawable.srcooper, R.drawable.thcuster, R.drawable.bddennis, R.drawable.cmeckardt,
            R.drawable.saford, R.drawable.lhanscom, R.drawable.bbhardy, R.drawable.tahorner,
            R.drawable.ijenkins, R.drawable.amjones, R.drawable.apkinsey, R.drawable.jmkreines,
            R.drawable.cklewis, R.drawable.crlong, R.drawable.jmmartin, R.drawable.lmvanveen,
            R.drawable.dtmessick, R.drawable.jlparrigin, R.drawable.impost, R.drawable.arprichard,
            R.drawable.ggrobb, R.drawable.laroye, R.drawable.mxruddy, R.drawable.ahschadt,
            R.drawable.lschiff, R.drawable.eawallace, R.drawable.klwilson, R.drawable.cmwoodall,
            R.drawable.mczimmerman};

    //TxtImgListAdapter itAdapter; //text, THEN image
    //ImgTxtListAdapter itlAdapter; //image, THEN text
    ListviewAdapter adapter; //error origin

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sectionHeader = getResources().getStringArray(R.array.contact_headers);
        //items = getResources().getStringArray(R.array.contact_who);
        //subitems = getResources().getStringArray(R.array.contact_deets);
        strings = getResources().getStringArray(R.array.list_strings);

        //images displayed next to each option in list
        /*int[] icons = {R.drawable.available, R.drawable.available, R.drawable.available,
        R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call,
        R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor,
        R.drawable.contactcolor, R.drawable.jbellistri, R.drawable.sebrazer,
        R.drawable.spburton, R.drawable.mxchakraborty, R.drawable.hfchaphe, R.drawable.fxchirombo,
        R.drawable.srcooper, R.drawable.thcuster, R.drawable.bddennis, R.drawable.cmeckardt,
        R.drawable.saford, R.drawable.lhanscom, R.drawable.bbhardy, R.drawable.tahorner,
        R.drawable.ijenkins, R.drawable.amjones, R.drawable.apkinsey, R.drawable.jmkreines,
        R.drawable.cklewis, R.drawable.crlong, R.drawable.jmmartin, R.drawable.lmvanveen,
        R.drawable.dtmessick, R.drawable.jlparrigin, R.drawable.impost, R.drawable.arprichard,
        R.drawable.ggrobb, R.drawable.laroye, R.drawable.mxruddy, R.drawable.ahschadt,
        R.drawable.lschiff, R.drawable.eawallace, R.drawable.klwilson, R.drawable.cmwoodall,
        R.drawable.mczimmerman};*/



        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        //check whether three chats are available
        url_json = "https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian";
        index = 0; //first icon
        value = 3;
        new JSONRetriever();
        url_json = "https://us.libraryh3lp.com/mobile/makerlab@chat.libraryh3lp.com?skin=22280&identity=Staff";
        index = 1; //second icon
        value = 5;
        new JSONRetriever();
        url_json = "https://us.libraryh3lp.com/mobile/su-crc@chat.libraryh3lp.com?skin=22280&identity=Librarian";
        index = 2; //third icon
        value = 7;
        new JSONRetriever();

        //itAdapter = new TxtImgListAdapter(getActivity());
        //itlAdapter = new ImgTxtListAdapter(getActivity());
        adapter = new ListviewAdapter(getActivity());
        adapter.setViewTypeAmount(2);

        listViewct = (ListView) view.findViewById(R.id.listViewct);

        //add and call populateListView()
        //populateListView(sectionHeader, icons, items, subitems, null);
        adapter.populate(views, strings, icons);

        //listViewct.setAdapter(itAdapter);
        listViewct.setAdapter(adapter);
        listViewct.setOnItemClickListener(this);

        return view;
    }

/*

    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str; //image, THEN text
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();
        TxtImgListAdapter.SectionStructure str1; //text, THEN image
        //ArrayList<TxtImgListAdapter.SectionStructure> sectionList1 = itAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //this fragment has four sections
            switch(i) {
                case 0:

                    //if json says chat not avail
                        //subTitles[i] = "Chat not avail try later"
                    //number of case statements is the number of sections
                    //this fragment has four sections
                    items = 3; //you can live chat with three different staff people
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
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
                case 1:
                    items = 4; //4 different phone numbers
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
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
                case 2:
                    items = 5; //5 different emails
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
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
                case 3:
                    items = 35;//35 different staff members
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
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
    }
*/

    HttpURLConnection conn;
    String full_string;
    static boolean connected =false;

    //check json file for each chat- jsonretriver called three times
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
                    url = new URL(url_json); // url passed in
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
            chatChange();
        }
    }

    //change first three icons respective to whether chats are avail or not
    public void chatChange(){

        Integer code = new Integer(0); // Initializes integer for response code
        if(conn != null) { // If connection is created
            try {
                code = conn.getResponseCode(); // Gets response code
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e("WHATEVER", full_string);
        if (code == HttpURLConnection.HTTP_OK) {
            if (full_string.compareTo("unavailable") == 0 && connected == false)
            {   icons[index] = R.drawable.unavailable;
                strings[value] = "Chat is currently unavailable.";}
            else if (full_string.compareTo("available")==0 && connected == false)
            {   icons[index] = R.drawable.available;
                strings[value] = "Chat is available! <br /> Tap to chat with library staff."; }
            else if (connected == true)
            { icons[index] = R.drawable.available;
                strings[value] = "Chat is available! <br /> Tap to chat with library staff.";}
        }
        else{
            icons[index] = R.drawable.unavailable;
            strings[value] = "Chat is currently unreachable.";
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        Uri uriUrl;
        Intent launchBrowser;

        if(isNetworkAvailable()) {
            //CAUTION: section headers count as positions
            //i.e. position 0 is section header 1
            switch (position) {

                //Three livechats with different parts of the librray
                case 1://CHAT 1 - General Staff
                    uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                    launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    break;
                case 2://CHAT 2 - 3D Printer Lab
                    uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/makerlab@chat.libraryh3lp.com?skin=22280&identity=Staff");//requires login
                    launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    break;
                case 3://CHAT 3 - Librarians
                    uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-crc@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                    launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    break;

                //Section for calling for Library Support
                case 5://call research help 410 548 5988
                    launchPhone("tel:4105485988");//calls launchPhone method
                    break;
                case 6://call circulation 410 543 6130
                    launchPhone("tel:4105436130");
                    break;
                case 7://call toll free 888 543 0148
                    launchPhone("tel:8885430148");
                    break;
                case 8://call app support 410 543 6306
                    launchPhone("tel:4105436306");
                    break;

                //Section for Emailing for Library Support
                case 10://email reserach help
                    launchEmail("libraries@salisbury.edu");//calls launchEmail method
                    break;
                case 11://email circulation
                    launchEmail("illoans@salisbury.edu");
                    break;
                case 12://email interlibray loan
                    launchEmail("libcirc@salisbury.edu");
                    break;
                case 13://email soar@su
                    launchEmail("soar@salisbury.edu");
                    break;
                case 14://email app support
                    launchEmail("cmwoodall@salisbury.edu");
                    break;
                default:
                    break;
            }


            if (position >= 16 && position <= 50)//for the rest of the cases, launch the dialog box to call or email a staff member
                launchDialog(position);//pass the position as a parameter to the dialog launch function
        }
        else{

        }
    }

    //starts a call using phone number passed as argument
    public void launchPhone(String phoneNumber){
        Intent dialer;
        dialer = new Intent(Intent.ACTION_DIAL);
        dialer.setData(Uri.parse(phoneNumber));
        startActivity(dialer);
    }

    //starts an email using address passed as argument
    public void launchEmail(String address){
        Intent emailer;
        emailer = new Intent(Intent.ACTION_SEND);
        emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailer.setType("vnd.android.cursor.item/email");
        emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{address});
        startActivity(emailer);
    }

    public void launchDialog(int pos){
        CallOrClickDialogFragment c1;//creates new fragment
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        Fragment prev;
        Bundle args;//passes position to fragment as an argument via Bundle; this determines which case within the sub fragment is executed
        c1 = new CallOrClickDialogFragment(getActivity());
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        args = new Bundle();
        args.putInt("position", pos);
        c1.setArguments(args);
        c1.show(fragmentTransaction,"dialog");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

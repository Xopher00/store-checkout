package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/19/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ContactInfoFragment extends Fragment implements AdapterView.OnItemClickListener {

    static int position;
    View view;
    private static ChatWebViewFragment chatter_commons = null, chatter_makerlabs = null, chatter_resource = null;
    ListView listViewct; //listView contacts
    String[] strings; //sequential list of strings as they appear in the listview
    int index; //index of icon to be changed when neccesary
    int value; //item number in a string array
    ArrayList<String> chat_avail_urls;
    ArrayList<String> chat_avail_grab;
    ArrayList<Boolean> chat_avail_bools;
    TextView loading_msg;
    ActionBar toolbar;
    //images displayed next to each option in list
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
    ListviewX lix;
    ArrayList<ListItem> listItems;
    JSONRetriever jretr;
    HttpURLConnection conn;
    static boolean connected_makerlabs = false,
                    connected_resources = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strings = getResources().getStringArray(R.array.list_strings);
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        loading_msg = (TextView) view.findViewById(R.id.contact_info_loading);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        loading_msg.setVisibility(View.VISIBLE);

        chat_avail_urls = new ArrayList<String>();
        chat_avail_urls.add("http://libraryh3lp.com/presence/jid/su-allstaff/chat.libraryh3lp.com/text");
        chat_avail_urls.add("http://libraryh3lp.com/presence/jid/makerlab/chat.libraryh3lp.com/text");
        chat_avail_urls.add("http://libraryh3lp.com/presence/jid/su-crc/chat.libraryh3lp.com/text");
        chat_avail_grab = new ArrayList<String>();
        chat_avail_bools = new ArrayList<Boolean>();

        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();

        listViewct = (ListView) view.findViewById(R.id.listViewct);
        listViewct.setVisibility(View.INVISIBLE);

        jretr = new JSONRetriever();
        jretr.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        jretr.cancel(true);
    }

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
                    for (int i = 0; i < chat_avail_urls.size(); i++) {
                        url = new URL(chat_avail_urls.get(i)); // url passed in
                        try {
                            conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                            conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                            conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                            try {
                                while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                    response.append(inputLine); // Append the new data to all grabbed data
                                br.close(); // Close connection
                            } catch (IOException e) {}
                        } catch (IOException e) {}
                        chat_avail_grab.add(response.toString()); // Sets string in parent class to be the string taken from the URL
                        response = new StringBuilder();
                    }
                } catch (MalformedURLException e) {}
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
    public void chatChange() {
        for (int i = 0; i < chat_avail_grab.size(); i++) {
            if (isNetworkAvailable()) {
                switch (i) {
                    case 0:
                        if (chat_avail_grab.get(i).compareTo("unavailable") == 0 && ChatFragment.connected == false) {
                            icons[0] = R.drawable.unavailable;
                            chat_avail_bools.add(new Boolean(false));
                            strings[2] = "Chat is currently unavailable.";
                        } else if (chat_avail_grab.get(i).compareTo("available") == 0 && ChatFragment.connected == false) {
                            chat_avail_bools.add(new Boolean(true));
                            icons[0] = R.drawable.available;
                            strings[2] = "Chat is available! Tap to chat with library staff.";
                        } else if (ChatFragment.connected == true) {
                            chat_avail_bools.add(new Boolean(true));
                            icons[0] = R.drawable.available;
                            strings[2] = "Chat is available! Tap to chat with library staff.";
                        }
                        break;
                    case 1:
                        if (chat_avail_grab.get(i).compareTo("unavailable") == 0 && connected_makerlabs == false) {
                            icons[1] = R.drawable.unavailable;
                            chat_avail_bools.add(new Boolean(false));
                            strings[4] = "Chat is currently unavailable.";
                        } else if (chat_avail_grab.get(i).compareTo("available") == 0 && connected_makerlabs == false) {
                            icons[1] = R.drawable.available;
                            chat_avail_bools.add(new Boolean(true));
                            strings[4] = "Chat is available! Tap to chat with library staff.";
                        } else if (connected_makerlabs == true) {
                            icons[1] = R.drawable.available;
                            chat_avail_bools.add(new Boolean(true));
                            strings[4] = "Chat is available! Tap to chat with library staff.";
                        }
                        break;
                    case 2:
                        if (chat_avail_grab.get(i).compareTo("unavailable") == 0 && connected_resources == false) {
                            icons[2] = R.drawable.unavailable;
                            chat_avail_bools.add(new Boolean(false));
                            strings[6] = "Chat is currently unavailable.";
                        } else if (chat_avail_grab.get(i).compareTo("available") == 0 && connected_resources == false) {
                            icons[2] = R.drawable.available;
                            chat_avail_bools.add(new Boolean(true));
                            strings[6] = "Chat is available! Tap to chat with library staff.";
                        } else if (connected_resources == true) {
                            icons[2] = R.drawable.available;
                            chat_avail_bools.add(new Boolean(true));
                            strings[6] = "Chat is available! Tap to chat with library staff.";
                        }
                        break;
                }
            }
            else {
                icons[index] = R.drawable.unavailable;
                chat_avail_bools.add(new Boolean(false));
                strings[value] = "Chat is currently unreachable.";
            }
        }
        fillList();
    }

    private void fillList(){
        int cstring = 0;
        int cicons = 0;
        int length = icons.length + 4; //four section headers

        for(int x = 0; x < length; x++) {
            switch(x) {
                case 0: //section headers
                case 4:
                case 9:
                case 15:
                    ListItem0 li0 = new ListItem0(getActivity(), strings[cstring++]);
                    //li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                    //li0.getTextView().setTextColor(Color.WHITE);
                    li0.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
                    li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
                    listItems.add(li0);
                    break;
                default:
                    ListItem2 li2 = new ListItem2(getActivity(), icons[cicons++], strings[cstring++], strings[cstring++]);
                    li2.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.listSecondText, null));
                    listItems.add(li2);
            }
        }

        lix.populate(listItems);
        listViewct.setVisibility(View.VISIBLE);
        loading_msg.setVisibility(View.INVISIBLE);
        listViewct.setAdapter(lix);

        listViewct.setOnItemClickListener(this);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.contact_info));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;

        if(isNetworkAvailable()) {
            //CAUTION: section headers count as positions
            //i.e. position 0 is section header 1
            FragmentTransaction ft;
            switch (position) {
                //Three livechats with different parts of the librray
                case 1://CHAT 1 - General Staff
                    if(chat_avail_bools.get(0)) {
                        if (!(MainActivity.chat_webs.containsKey("library_chat")))
                            MainActivity.chat_webs.put("library_chat", new ChatWebViewFragment("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian"));
                        ChatFragment.connected = true;
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, MainActivity.chat_webs.get("library_chat"));
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.contactPage);
                    }
                    break;
                case 2://CHAT 2 - 3D Printer Lab
                    if(chat_avail_bools.get(1)) {
                        if (!(MainActivity.chat_webs.containsKey("makerlabs_chat")))
                            MainActivity.chat_webs.put("makerlabs_chat", new ChatWebViewFragment("https://us.libraryh3lp.com/chat/makerlab@chat.libraryh3lp.com?skin=22280&identity=Staff"));
                        connected_makerlabs = true;
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, MainActivity.chat_webs.get("makerlabs_chat"));
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.contactPage);
                    }
                    break;
                case 3://CHAT 3 - Librarians
                    if(chat_avail_bools.get(2)) {
                        if (!(MainActivity.chat_webs.containsKey("resources_chat")))
                            MainActivity.chat_webs.put("resources", new ChatWebViewFragment("https://us.libraryh3lp.com/chat/su-crc@chat.libraryh3lp.com?skin=22280&identity=Librarian"));
                        connected_resources = true;
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, MainActivity.chat_webs.get("resources"));
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.contactPage);
                    }
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


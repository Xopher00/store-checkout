package thelibrarians.sulibraryapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChatFragment extends Fragment {

    HttpURLConnection conn;
    String full_string;
    View view;
    MainActivity ma;

    @Override
    public void onStart(){
        super.onStart();
        new JSONRetriever().execute();
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
                    url = new URL("http://libraryh3lp.com/presence/jid/su-allstaff/chat.libraryh3lp.com/text"); // url passed in
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

    public void chatChange(){
        TextView noInternet = (TextView) view.findViewById((R.id.nointernet));
        TextView chatIs = (TextView) view.findViewById(R.id.chat_is);
        TextView chatMeUp = (TextView) view.findViewById(R.id.chatMeUp);
        ImageView bubble = (ImageView) view.findViewById(R.id.bubble);

        if(full_string == "unavailable" && ma.getterRobo() == 0){//if there is no chat available
            bubble.setImageResource(R.drawable.chatunavailable1x);
            chatIs.setText("Unavailable" );
            chatIs.setTextColor(Color.parseColor("#ffcc0000"));//make red
            chatMeUp.setText("Try Chatting Later");
            //chatMeUp.setVisibility(View.GONE);
        }//make this button invisible
        else if(full_string == "available" && ma.getterRobo() == 0){
            bubble.setImageResource(R.drawable.chatavailable1x);
            chatIs.setText("Available!");
            chatIs.setTextColor(Color.parseColor("#ff669909"));//make green
            //chatMeUp.setVisibility(View.VISIBLE);//make visible
            chatMeUp.setText("Start a New Chat");}
        else if(ma.getterRobo() == 1){//if user has already started a chat
            bubble.setImageResource(R.drawable.chatavailable1x);
            chatIs.setText("Available!");
            chatIs.setTextColor(Color.parseColor("#ff669909"));
            //chatMeUp.setVisibility(View.VISIBLE);//make visible
            chatMeUp.setText("Continue");}
        else{
            bubble.setImageResource(R.drawable.chatunreachable1x);//if there is no internet avail, fragment displays this
            chatIs.setText("Unreachable");
            chatIs.setTextColor(Color.parseColor("#ffffff"));
            noInternet.setVisibility(View.VISIBLE);
            chatMeUp.setText("Retry");
            chatMeUp.setTextSize(12);
        }
        }

    public ChatFragment(){}

    /*
    JSONObject mac = new JSONObject("http://libraryh3lp.com/presence/jid/su-allstaff/chat.libraryh3lp.com/text").getJSONObject(“available”);
    Integer mac_a = new Integer((Integer) mac.get(“available”));*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        ma = (MainActivity)getActivity();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.setterRobo(1);
                //calls webview fragment, fragment transition using url arg
                webViewFragment cHaT = new webViewFragment("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, cHaT);
                ft.addToBackStack(null).commit();
                /*WebView webview = new WebView(getActivity());
                // webview.loadUrl("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");
                /*Uri uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);*/
            }
            } ;
            TextView ct = (TextView) view.findViewById(R.id.chatMeUp);
            ct.setOnClickListener(listener);
            return view;
        }

        @Override
        public void onDestroy(){
            Log.e("Closed", "ERROR");
            super.onDestroy();
                    }

    }


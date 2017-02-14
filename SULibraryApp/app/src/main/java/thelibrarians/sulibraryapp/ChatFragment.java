package thelibrarians.sulibraryapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
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
    webViewFragment cHaT;
    TextView ct;
    static boolean connected =false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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

        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);*/

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
            if (full_string.compareTo("unavailable") == 0 && connected == false) {//if there is no chat available
                bubble.setImageResource(R.drawable.chatunavailable1x);//sets bubble image to red bubble size 1
                chatIs.setText("Unavailable");
                chatIs.setTextColor(Color.parseColor("#ffcc0000"));//make red
                chatMeUp.setText("Try Chatting Later");//disables button
                ct.setOnClickListener(null);
            } else if (full_string.compareTo("available")==0 && connected == false) {
                bubble.setImageResource(R.drawable.chatavailable1x);//sets bubble image to green bubble size 1
                chatIs.setText("Available!");
                chatIs.setTextColor(Color.parseColor("#ff669909"));//make green
                //chatMeUp.setVisibility(View.VISIBLE);//make visible
                chatMeUp.setText("Start a New Chat");
            } else if (connected == true) {//if user has already started a chat
                bubble.setImageResource(R.drawable.chatavailable1x);
                chatIs.setText("Available!");
                chatIs.setTextColor(Color.parseColor("#ff669909"));//change color to green
                //chatMeUp.setVisibility(View.VISIBLE);//make visible
                chatMeUp.setText("Continue");
            }
        }
        else{
            bubble.setImageResource(R.drawable.chatunreachable1x);//if there is no internet avail, fragment displays this
            chatIs.setText("Unreachable");
            chatIs.setTextColor(Color.parseColor("#777777"));//change color to gray
            chatIs.setTextSize(23);//make text smaller
            noInternet.setVisibility(View.VISIBLE);
            chatMeUp.setText("Retry");
            chatMeUp.setTextSize(16);//make text smaller
        }
        }

    public ChatFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        ma = (MainActivity)getActivity();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cHaT = new webViewFragment("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");
                connected = true;
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, cHaT);
                ft.addToBackStack(null).commit();
            }
            } ;
        ct = (TextView) view.findViewById(R.id.chatMeUp);
        ct.setOnClickListener(listener);
        new JSONRetriever().execute();

        setupSocialMedia();

        return view;
    }


    private void setupSocialMedia() {
        ImageView[] social;
        social = new ImageView[5];
        social[0] = (ImageView) view.findViewById(R.id.facebook);
        social[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*
                Uri uriUrl = Uri.parse("http://fb.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                //*/
                /*
                ft = fm.beginTransaction();
                webView = new webViewFragment("http://fb.com/sulibraries");
                ft.replace(R.id.content_container, webView);
                ft.addToBackStack(null).commit();
                */
            }
        });
        social[1] = (ImageView) view.findViewById(R.id.twitter);
        social[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*
                Uri uriUrl = Uri.parse("http://twitter.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                //*/
                /*
                ft = fm.beginTransaction();
                webView = new webViewFragment("http://twitter.com/sulibraries");
                ft.replace(R.id.content_container, webView);
                ft.addToBackStack(null).commit();
                */
            }
        });
        social[2] = (ImageView) view.findViewById(R.id.instagram);
        social[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*
                Uri uriUrl = Uri.parse("http://instagram.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                //*/
                /*
                ft = fm.beginTransaction();
                webView = new webViewFragment("http://instagram.com/sulibraries");
                ft.replace(R.id.content_container, webView);
                ft.addToBackStack(null).commit();
                */
            }
        });
        social[3] = (ImageView) view.findViewById(R.id.pinterest);
        social[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*
                Uri uriUrl = Uri.parse("http://pinterest.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                //*/
                /*
                ft = fm.beginTransaction();
                webView = new webViewFragment("http://pinterest.com/sulibraries");
                ft.replace(R.id.content_container, webView);
                ft.addToBackStack(null).commit();
                //*/
            }
        });
        social[4] = (ImageView) view.findViewById(R.id.tumblr);
        social[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*
                Uri uriUrl = Uri.parse("http://sulibraries.tumblr.com/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                //*/
                /*
                ft = fm.beginTransaction();
                webView = new webViewFragment("http://sulibraries.tumblr.com/");
                ft.replace(R.id.content_container, webView);
                ft.addToBackStack(null).commit();
                */
            }
        });
    }
}


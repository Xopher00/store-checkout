package thelibrarians.sulibraryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by njraf_000 on 11/28/2016.
 */

public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {

    Bitmap[] icons;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    ListView listView;
    ArrayList<JSONObject> objs;
    JSONArray jArray;
    String strURL[];
    String baseImgURL = "http://libapps.salisbury.edu/news/images/";
    ActionBar toolbar;

    ListviewX lix;
    ArrayList<ListItem> listItems;

    public NewsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_url = "http://libapps.salisbury.edu/news/json.php";

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();
        listView = (ListView) view.findViewById(R.id.news_list);
        new JSONRetriever().execute();

        listView.setOnItemClickListener(this);

        //modify toolbar
        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle("NEWS");


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbar.setTitle("SU Libraries");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url;
        webViewFragment web;

        try {
            url = jArray.getJSONObject(position).getString("url");
            web = new webViewFragment(url);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_container, web).addToBackStack(null).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private class JSONRetriever extends AsyncTask<Void, Void, Void> {

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
                            Log.i("nick", "catch 4");
                        }
                    } catch (IOException e) {
                        Log.i("nick", "catch 3");
                        return null;
                    }
                } catch (MalformedURLException e) {
                    Log.i("nick", "catch 2");
                }
                json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
                Log.i("nick", "catch 1");
            }

            return null;
        }

        protected void onPostExecute(Void v) {
            //parseJSON(json_string);
            objs = new ArrayList<JSONObject>();

            try {
                jArray = new JSONArray(json_string);

                //filter display_in_app
                for(int x = 0; x < jArray.length(); x++) {
                    if(jArray.getJSONObject(x).getString("display_in_app").equals("1")) {
                        objs.add(jArray.getJSONObject(x));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            icons = new Bitmap[objs.size()];

            //populate array of urls
            strURL = new String[objs.size()];
            for(int x = 0; x < objs.size(); x++) {
                try {
                    strURL[x] = baseImgURL + objs.get(x).getString("image");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            new DownloadImageTask().execute(strURL);


        }



    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap[]> {
        public DownloadImageTask() {}
        protected Bitmap[] doInBackground(String[] urls) {

            for(int x = 0; x < objs.size(); x++) {
                String urldisplay = urls[x];
                //Bitmap mIcon = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    icons[x] = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
            return icons;
        }

        protected void onPostExecute(Bitmap[] results) {

            ListItem2 li2;
            ///////////populate icons, titles, and subtitles array////////////
            for(int x = 0; x < objs.size(); x++) {
                try {

                    li2 = new ListItem2(getActivity(), icons[x], objs.get(x).getString("title"), objs.get(x).getString("details"));
                    listItems.add(li2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            lix.populate(listItems);
            listView.setAdapter(lix);
        }
    }
}

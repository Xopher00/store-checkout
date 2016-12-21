package thelibrarians.sulibraryapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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

    int[] views;
    String[] texts;
    Bitmap[] icons;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    //ImgTxtListAdapter itlAdapter;
    ListviewAdapter adapter;
    ListView listView;
    JSONArray jArray;
    String strURL[];
    String baseImgURL = "http://libapps.salisbury.edu/news/images/";

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

        //itlAdapter = new ImgTxtListAdapter(getActivity());
        adapter = new ListviewAdapter(getActivity());
        adapter.setViewTypeAmount(1);
        listView = (ListView) view.findViewById(R.id.news_list);
        new JSONRetriever().execute();


        listView.setOnItemClickListener(this);

        return view;
    }
/*

    public void populateListView(String[] sectionHeader, Bitmap[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        int items = 0;

        items = jArray.length();  //number of new articles
        for(int j = 1; j < items+1; j++) { //start at 1, no section header
            str = itlAdapter.getStr();
            if(j == 0) {
                str.setSectionName(sectionHeader[0]);
                str.setSectionTitle("");
                sectionList.add(str);
            } else {
                if(icons != null)
                    str.setBitmapImg(icons[position]);
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

    }
*/


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

            try {
                jArray = new JSONArray(json_string);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            views = new int[jArray.length()];
            texts = new String[jArray.length() * 2]; //each item in the listview has 2 textviews per 1 jArray item
            icons = new Bitmap[jArray.length()];

            //populate array of urls
            strURL = new String[jArray.length()];
            for(int x = 0; x < jArray.length(); x++) {
                try {
                    strURL[x] = baseImgURL + jArray.getJSONObject(x).getString("image");
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

            for(int x = 0; x < jArray.length(); x++) {
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

            int index = 0;
            ///////////populate icons, titles, and subtitles array////////////
            for(int x = 0; x < jArray.length() * 2; x++) {
                try {
                    if(x % 2 == 0) {
                        views[index] = 2; //view type 2 for ListviewAdapter; each view type is the same
                        texts[x] = jArray.getJSONObject(index).getString("title");
                    } else {
                        texts[x] = jArray.getJSONObject(index).getString("details");
                        index++;
                    }
                    //icons array populated in fillIcons()
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            /////////////////////////////////////////////////////////////////

            //add and call populateListView()
            //populateListView(null, icons, titles, subtitles, null);
            for (int x = 0; x < texts.length; x++) {
                Log.i("nick", "" + texts[x]);
            }
            adapter.populate(views, texts, icons);

            listView.setAdapter(adapter);
        }
    }
}
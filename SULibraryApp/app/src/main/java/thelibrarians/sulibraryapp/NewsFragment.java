package thelibrarians.sulibraryapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    String[] titles;
    String[] subtitles;
    Bitmap[] icons;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    ImgTxtListAdapter itlAdapter;
    ListView listView;
    View listItem;
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

        itlAdapter = new ImgTxtListAdapter(getActivity());
        new JSONRetriever().execute();

        listView = (ListView) view.findViewById(R.id.news_list);
        listView.setOnItemClickListener(this);

        return view;
    }

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

    public void parseJSON(String jString) {

        try {
            jArray = new JSONArray(jString);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    Bitmap[] addElement(Bitmap[] org, Bitmap added) {
        Bitmap[] result = Arrays.copyOf(org, org.length +1);
        result[org.length] = added;
        return result;
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

            titles = new String[jArray.length()];
            subtitles = new String[jArray.length()];
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

            //new DownloadImageTask().execute(strURL);
            fillIcons(strURL);

            ///////////populate icons, titles, and subtitles array////////////
            for(int x = 0; x < jArray.length(); x++) {
                try {
                    titles[x] = jArray.getJSONObject(x).getString("title");
                    subtitles[x] = jArray.getJSONObject(x).getString("details");
                    //icons array populated in fillIcons()
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            /////////////////////////////////////////////////////////////////

            //add and call populateListView()
            populateListView(null, icons, titles, subtitles, null);

            listView.setAdapter(itlAdapter);
        }

        void fillIcons(String[] urls) {
            for(int x = 0; x < urls.length; x++) {
                String urldisplay = urls[0];
                Bitmap mIcon;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon = BitmapFactory.decodeStream(in);
                    icons = addElement(icons, mIcon);
                } catch (Exception e) {
                    //Log.e("Error", e.getMessage());
                    //e.printStackTrace();
                }
            }
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        public DownloadImageTask() {}
        protected Bitmap doInBackground(String[] urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            icons = addElement(icons, result);
        }
    }
}

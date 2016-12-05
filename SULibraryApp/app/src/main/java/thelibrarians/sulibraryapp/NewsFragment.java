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

/**
 * Created by njraf_000 on 11/28/2016.
 */

public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {

    String[] titles;
    String[] subtitles;
    int[] icons;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    ImgTxtListAdapter itlAdapter;
    ListView listView;
    View listItem;
    JSONArray jArray;
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

        icons = new int[]{R.drawable.unavailable,R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable,
                R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable,
                R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable,
                R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable, R.drawable.unavailable};

        //listItem = inflater.inflate(R.layout.list_item, container, false);

        itlAdapter = new ImgTxtListAdapter(getActivity());

        listView = (ListView) view.findViewById(R.id.news_list);
        listView.setOnItemClickListener(this);

        return view;
    }

    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items;  //number of items per section

            //number of case statements is the number of sections
            switch(i) {
                case 0:
                    items = jArray.length();  //number of new articles
                    for(int j = 1; j < items+1; j++) { //start at 1, no section header
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
                        }
                    } catch (IOException e) {
                        return null;
                    }
                } catch (MalformedURLException e) {
                }
                json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
            }

            return null;
        }

        protected void onPostExecute(Void v) {
            parseJSON(json_string);

            ///////////populate icons, titles, and subtitles array////////////
            for(int x = 0; x < jArray.length(); x++) {
                try {
                    new DownloadImageTask((ImageView) listItem.findViewById(R.id.list_image)).execute(jArray.getJSONObject(x).getString("image"));
                    titles[x] = jArray.getJSONObject(x).getString("title");
                    subtitles[x] = jArray.getJSONObject(x).getString("details");
                    baseImgURL += jArray.getJSONObject(x).getString("image");
                    Log.i("nick", "baseImgURL");
                    //icons[x] =
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            /////////////////////////////////////////////////////////////////

            //add and call populateListView()
            populateListView(null, icons, titles, subtitles, null);

            listView.setAdapter(itlAdapter);
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
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
            bmImage.setImageBitmap(result);
        }
    }
}

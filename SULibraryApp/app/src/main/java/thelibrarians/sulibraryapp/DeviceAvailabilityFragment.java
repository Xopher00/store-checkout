package thelibrarians.sulibraryapp;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DeviceAvailabilityFragment extends Fragment {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    SlidingTabLayout mSlidingTabLayout;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    static ArrayList<JSONObject> devices;
    static ArrayList<JSONObject> available_devices;
    static int airsCount = 0, minisCount = 0, prosCount = 0, touchesCount = 0, fitbitsCount = 0, accessoriesCount = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_url = getResources().getString(R.string.device_url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_availability, container, false);

        new JSONRetriever().execute();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this.getChildFragmentManager());
        //mSectionsPagerAdapter.getItem()

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.frag_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Initialize the SlidingTabLayout. Note that the order is important. First init ViewPager and Adapter and only then init SlidingTabLayout
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        //Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //Toast.makeText(getActivity(), "Goodbye", Toast.LENGTH_SHORT).show();
                        break;


                }
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
*/


        return view;
    }

    private void parseJSON() {
        JSONArray jArray;
        devices = new ArrayList<JSONObject>();      //hold all devices
        available_devices = new ArrayList<JSONObject>();    //hold available devices
        int status;  //device status

        Log.i("nick", "parseJSON");
        Log.i("nick", "this - "+available_devices);

        try {
            jArray = new JSONArray(json_string);

            for (int i = 0; i < jArray.length(); i++) {   //loop through JSON array devices
                /*do not show devices with specific status codes
                Status:
                1 available
                2 checked out
                3 temp unavailable
                4 ""
                5 ""
                10 ""
                11 ""
                else do not display
                */
                status = (int) new JSONObject(jArray.getString(i)).getInt("status"); //get device status

                if (status == 1 || status == 2 || status == 3 || status == 4 || status == 5 || status == 10 || status == 11) {  //check status
                    devices.add(new JSONObject(jArray.getString(i)));    //populate array of devices to show

                    if (status == 1) {    //get devices with available status
                        available_devices.add(new JSONObject(jArray.getString(i)));

                    }

                    //count number of devices in each category
                    if (new JSONObject(jArray.getString(i)).getString("device_name").toLowerCase().contains("air"))
                        airsCount++;
                    else if (new JSONObject(jArray.getString(i)).getString("device_name").toLowerCase().contains("mini"))
                        minisCount++;
                    else if (new JSONObject(jArray.getString(i)).getString("device_name").toLowerCase().contains("pro"))
                        prosCount++;
                    else if (new JSONObject(jArray.getString(i)).getString("device_name").toLowerCase().contains("touch"))
                        touchesCount++;
                    else if (new JSONObject(jArray.getString(i)).getString("device_name").toLowerCase().contains("fitbit"))
                        fitbitsCount++;
                    else
                        accessoriesCount++;

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);

            switch (position) {
                case 0:
                    return DeviceFragment.newInstance(position);
                case 1:
                    return DeviceFragment.newInstance(position);
            }
            return DeviceFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SHOW ALL";
                case 1:
                    return "AVAILABLE ONLY";
            }
            return null;
        }
    }


//*

    public static class DeviceFragment extends Fragment implements AdapterView.OnItemClickListener {
        String[] sectionHeader;
        String[] titles;
        String[] subtitles;
        int[] icons;
        ImgTxtListAdapter itlAdapter;
        ListView listView;
        static int tabNumber;

        public static DeviceFragment newInstance(int sectionNumber) {
            tabNumber = sectionNumber;
            DeviceFragment fragment = new DeviceFragment();
            //Bundle args = new Bundle();
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //fragment.setArguments(args);

            return fragment;
        }

        public DeviceFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            sectionHeader = getResources().getStringArray(R.array.device_section_head);
            //titles = getResources().getStringArray(R.array.device_titles);

            try {
                if (tabNumber == 0) {
                    //set titles
                    Log.i("nick", "first tab");
                    for (int a = 0; a < devices.size(); a++) {
                        titles[a] = devices.get(a).getString("device_name");
                        switch (devices.get(a).getInt("status")) {
                            case 1:
                                subtitles[a] = available_devices.get(a).getString("type_name") + " (" +
                                        available_devices.get(a).getString("detail") + ")";
                                icons[a] = R.drawable.available;
                                break;
                            case 2:
                                subtitles[a] = getResources().getString(R.string.device_due) + devices.get(a).getString("due_date");
                                icons[a] = R.drawable.checked_out;
                                break;
                            case 3:
                            case 4:
                            case 5:
                            case 10:
                            case 11:
                                subtitles[a] = getResources().getString(R.string.device_unavailable);
                                icons[a] = R.drawable.unavailable;
                                break;
                            default:
                                subtitles[a] = getResources().getString(R.string.no_info);
                                icons[a] = R.drawable.unavailable;
                        }
                    }
                } else if (tabNumber == 1) {
                    //set titles
                    if(available_devices == null)
                        Log.i("nick", "is null - "+available_devices);
                    for (int a = 0; a < available_devices.size(); a++) {
                        titles[a] = available_devices.get(a).getString("device_name");
                        subtitles[a] = available_devices.get(a).getString("type_name") + " (" +
                                available_devices.get(a).getString("detail") + ")";
                        icons[a] = R.drawable.available;
                    }
                }
            } catch (JSONException e) {}

            View view = inflater.inflate(R.layout.fragment_device_pager, container, false);

            itlAdapter = new ImgTxtListAdapter(getActivity());

            listView = (ListView) view.findViewById(R.id.listView);

            populateListView(sectionHeader, icons, titles, null, null);

            listView.setAdapter(itlAdapter);
            listView.setOnItemClickListener(this);

            return view;
        }

        public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
            int position = 0;  //current position in each item array
            ImgTxtListAdapter.SectionStructure str;
            ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

            for (int i = 0; i < sectionHeader.length; i++) {

                int items = 0;  //number of items per section

                //number of case statements is the number of sections
                switch (i) {
                    case 0: //iPad airs
                        items = airsCount;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                str.setSectionSubtitle("(" + getResources().getString(R.string.device_available));
                                str.setSectionBackground(R.drawable.ipad_airs);
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[position]);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 1: //iPad minis
                        items = minisCount;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                str.setSectionSubtitle("(" + " Available)");
                                str.setSectionBackground(R.drawable.ipad_minis);
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[position]);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 2: //iPad pros
                        items = prosCount;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                str.setSectionSubtitle("(" + " Available)");
                                str.setSectionBackground(R.drawable.ipad_pro);
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[position]);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 3: //iPad touches
                        items = touchesCount;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                str.setSectionSubtitle("(" + " Available)");
                                str.setSectionBackground(R.drawable.ipod_touches);
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[position]);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 4: //fitbits
                        items = fitbitsCount;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                str.setSectionSubtitle("(" + " Available)");
                                str.setSectionBackground(R.drawable.fitbits);
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[position]);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 5: //accessories
                        items = accessoriesCount;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                str.setSectionSubtitle("(" + " Available)");
                                str.setSectionBackground(R.drawable.accessories);
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[position]);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                }
            }
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
    //*/

    //*     //test fragment
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.nav_list_item, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.list_item_title);
            textView.setText("The sec " + getArguments().getInt(ARG_SECTION_NUMBER));
            return rootView;
        }
    }
    //*/

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
                    }
                } catch (MalformedURLException e) {
                }
                json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
            }

            return null;
        }

        protected void onPostExecute(Void v) {
            parseJSON();
        }

    }
}

package thelibrarians.sulibraryapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// This fragment is for checking device availability. Works together with DeviceFragment.java

public class DeviceAvailabilityFragment extends Fragment {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    SlidingTabLayout mSlidingTabLayout;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    View view;
    static ArrayList<JSONObject> devices;
    static ArrayList<JSONObject> available_devices;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_url = getResources().getString(R.string.device_url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_device_availability, container, false);

        new JSONRetriever().execute();

        return view;
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
                    return new DeviceFragment(position, devices, available_devices);
                case 1:
                    return new DeviceFragment(position, devices, available_devices);
            }
            return new DeviceFragment(position, devices, available_devices);
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
            DeviceFragment.parseJSON(json_string);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
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

        }

    }
}

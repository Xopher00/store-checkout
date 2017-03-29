package thelibrarians.sulibraryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import java.util.Calendar;

public class HomeFragment extends Fragment {

    View view;
    FragmentManager fm;
    FragmentTransaction ft;
    HomeFragment.SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    JSONObject week1;
    JSONObject week2;
    ArrayList<JSONObject> myweek;   //custom 7 day week
    boolean hasInternet = false;
    ActionBar toolbar;
    boolean hasStarted = false;

    private int leftPosition = -1, rightPosition = 1, pos = 0;
    private CalendarFragment leftPage = null;
    private CalendarFragment rightPage = null;
    private CalendarFragment curPage = null;
    private CalendarFragment[] week = new CalendarFragment[7];

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        base_url = "https://api3.libcal.com/api_hours_grid.php?iid=823&format=json&weeks=2";

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        fm =  getActivity().getSupportFragmentManager();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new HomeFragment.SectionsPagerAdapter(getChildFragmentManager());
        //mSectionsPagerAdapter.getItem()

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.frag_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(pageChangeListener);

        //clear fragment backstack when home page is visited
        if(fm.getBackStackEntryCount() > 0)
            fm.popBackStack(fm.getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        setupSocialMedia();

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.library));

        new JSONRetriever().execute();

        return view;
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //does not "select" first page on startup. That is done in SectionsPagerAdapter's getItem()
            if(position == rightPosition) { //swipe to page right
                leftPage = curPage;
				pos = position;
				leftPosition = position - 1;
				rightPosition = position + 1;
				
                if(hasInternet) {
                    curPage = new CalendarFragment(myweek.get(position), position);
                    if(position < 6)
                        rightPage = new CalendarFragment(myweek.get(rightPosition), rightPosition);
                } else {
                    curPage = new CalendarFragment(position);
                    if(position < 6)
                        rightPage = new CalendarFragment(rightPosition);
                }
            }

            if(position == leftPosition) { //swipe to page left
                rightPage = curPage;
				pos = position;
				leftPosition = position - 1;
				rightPosition = position + 1;
				
                if(hasInternet) {
                    curPage = new CalendarFragment(myweek.get(position), position);
                    if(position > 0)
                        leftPage = new CalendarFragment(myweek.get(leftPosition), leftPosition);
                } else {
                    curPage = new CalendarFragment(position);
                    if(position > 0)
                        leftPage = new CalendarFragment(leftPosition);
                }
            }
			
			if(position < 6)
				week[rightPosition] = rightPage;
				
			if(position > 0)
				week[leftPosition] = leftPage;

            week[position] = curPage;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

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


    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(!hasStarted) {
                //create first two pages at indecies 0 and 1
                //only when this class is created
                hasStarted = true;
                pos = 0;
                leftPosition = position - 1;
                rightPosition = position + 1;
                if(hasInternet) {
                    curPage = new CalendarFragment(myweek.get(position), position);
                    rightPage = new CalendarFragment(myweek.get(rightPosition), rightPosition);
                } else {
                    curPage = new CalendarFragment(position);
                    rightPage = new CalendarFragment(rightPosition);
                }
                week[0] = curPage;
                week[1] = rightPage;
            }

            return week[position];
        }

        @Override
        public int getCount() {
            return 7;
        } // 7 pages

        @Override
        public int getItemPosition(Object object) {
            //called when mSectionsPagerAdapter.notifyDataSetChanged() is called
            //in onPostExecute() below
            //in other words, when internet is established then change "unavailable" to actual data

            return POSITION_NONE;
        }
    }

    private void parseJSON() {

        try {
            JSONObject j = new JSONObject(full_string);
            //get info for this week and next week
            //each week object contains objects for each day of the week
            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);

            //get today's date
            // 1=SUNDAY, 7=SATURDAY
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            //build array of 7 day period
            myweek = new ArrayList<JSONObject>();
            for(int x = 0; x < 7; x++) {
                if(day <= 7) {
                    switch (day) {
                        case Calendar.SUNDAY:
                            myweek.add(week1.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week1.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week1.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week1.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week1.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week1.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week1.getJSONObject("Saturday"));
                            break;
                    }
                } else {
                    switch (day-7) {
                        case Calendar.SUNDAY:
                            myweek.add(week2.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week2.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week2.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week2.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week2.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week2.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week2.getJSONObject("Saturday"));
                            break;
                    }
                }
                day++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class JSONRetriever extends AsyncTask<Void, Void, Void>{

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
                    url = new URL(base_url); // url passed in
                    try {
                        conn = (HttpURLConnection)url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) {Log.i("nick", "catch 4");}
                    } catch (IOException e) {Log.i("nick", "catch 3"); return null;}
                } catch (MalformedURLException e) {Log.i("nick", "catch 2");}
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {Log.i("nick", "catch 1");}
            hasInternet = true;
            parseJSON();
            return null;
        }

        /*
        * THIS STARTS ONCE doInBackground(...) COMPLETES
        *
        * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
        * */

        protected void onPostExecute(Void v){
            //pages are zero-indexed
            //update current page and adjacent pages
            if(hasInternet) {
                curPage = new CalendarFragment(myweek.get(pos), pos);
                week[pos] = curPage;
                mSectionsPagerAdapter.getItem(pos);
                //if current page not first page
                if (leftPosition != -1) {
                    leftPage = new CalendarFragment(myweek.get(leftPosition), leftPosition);
                    week[leftPosition] = leftPage;
                    mSectionsPagerAdapter.getItem(leftPosition);
                }
                //if current page not last page
                if (rightPosition != 7) {
                    rightPage = new CalendarFragment(myweek.get(rightPosition), rightPosition);
                    week[rightPosition] = rightPage;
                    mSectionsPagerAdapter.getItem(rightPosition);
                }

                mSectionsPagerAdapter.notifyDataSetChanged();
            }
        }
    }



}

package thelibrarians.sulibraryapp;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

/**
 * general fragment for HomeFragment's viewpager
 */

public class CalendarFragment extends Fragment {

    String date, rendered;
    boolean hasInternet = false;
    int position = 0;

    public CalendarFragment() {Log.i("nick", "default");}

    public CalendarFragment(JSONObject j, int p) {
        position = p;
        hasInternet = true;
        try {
            date = j.getString("date");
            rendered = j.getString("rendered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        TextView day = (TextView) v.findViewById(R.id.day);     //numeric day
        TextView month = (TextView) v.findViewById(R.id.month);
        TextView times = (TextView) v.findViewById(R.id.times); //open hours
        TextView weekday = (TextView) v.findViewById(R.id.weekday); //day  of the week


        day.setTypeface(null, Typeface.BOLD);
        month.setTypeface(null, Typeface.BOLD);


//*
        if(savedInstanceState != null && date == null) {
            Log.i("nick", "pull save "+savedInstanceState);
            date = savedInstanceState.getString("date");
            rendered = savedInstanceState.getString("rendered");
            hasInternet = savedInstanceState.getBoolean("internet");
            position = savedInstanceState.getInt("position");
        }
//*/
        Log.i("nick", "savedInstanceState "+savedInstanceState);
Log.i("nick", "date "+date);
        if(hasInternet) {
            day.setText(getDay(date));
            month.setText(getMonth(date));
            times.setText(rendered);
        } else {
            day.setText("Unavailable");
            month.setText("");
            times.setText("Unavailable");
        }


        Calendar cal = Calendar.getInstance();
        weekday.setText(findWeekday(cal.get(Calendar.DAY_OF_WEEK) + position));



        return v;
    }

    public String findWeekday(int dayOfWeek) {

        if(dayOfWeek > 7) dayOfWeek -= 7;

        switch(dayOfWeek) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }

        return "Unavailable";
    }

    //*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(date != null) {
            outState.putString("date", date);
            outState.putString("rendered", rendered);
            outState.putBoolean("internet", hasInternet);
            outState.putInt("position", position);
            Log.i("nick", "save instance "+outState);

        }

    }
//*/
    String getDay(String d) {
        Log.i("nick", "getDay() "+d);
        String[] parts = d.split("-");
        return parts[2];
    }

    String getMonth(String d) {
        String[] parts = d.split("-");
        int month = Integer.parseInt(parts[1]);

        switch(month) {
            case 1:
                return "JANUARY";
            case 2:
                return "FEBRUARY";
            case 3:
                return "MARCH";
            case 4:
                return "APRIL";
            case 5:
                return "MAY";
            case 6:
                return "JUNE";
            case 7:
                return "JULY";
            case 8:
                return "AUGUST";
            case 9:
                return "SEPTEMBER";
            case 10:
                return "OCTOBER";
            case 11:
                return "NOVEMBER";
            case 12:
                return "DECEMBER";
        }

        return "unavailable";
    }

/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onSaveInstanceState(bun);
    }*/
}

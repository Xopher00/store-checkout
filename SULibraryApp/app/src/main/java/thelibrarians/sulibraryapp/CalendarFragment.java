package thelibrarians.sulibraryapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * general fragment for HomeFragment's viewpager
 */

public class CalendarFragment extends Fragment {

    String date, rendered;
    boolean hasInternet = false;
    int position = 0;
    TextView day;
    TextView month;
    TextView times;
    TextView weekday;
    //TextView left_arrow, right_arrow;

    public CalendarFragment() {}

    public CalendarFragment(int p) {
        position = p;
    }

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

    public CalendarFragment(JSONObject j) {
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

        day = (TextView) v.findViewById(R.id.day);     //numeric day
        month = (TextView) v.findViewById(R.id.month); //month name
        times = (TextView) v.findViewById(R.id.times); //open hours
        weekday = (TextView) v.findViewById(R.id.weekday); //day of the week name
        //left_arrow = (TextView) v.findViewById(R.id.left_arrow);
        //right_arrow = (TextView) v.findViewById(R.id.right_arrow);
/*

        if(position == 0) {
            left_arrow.setText("");
            right_arrow.setText(">");
        } else if(position == 6) {
            right_arrow.setText("");
            left_arrow.setText("<");
        } else {
            left_arrow.setText("<");
            right_arrow.setText(">");
        }
*/

        day.setTypeface(null, Typeface.BOLD);
        month.setTypeface(null, Typeface.BOLD);

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
        weekday.setText(findWeekday(cal.get(Calendar.DAY_OF_WEEK))); //if using viewpager seven day week, add '+ position' inside get()



        return v;
    }

    private String findWeekday(int dayOfWeek) {

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

    private String getDay(String d) {
        String[] parts = d.split("-");
        return parts[2];
    }

    private String getMonth(String d) {
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

}

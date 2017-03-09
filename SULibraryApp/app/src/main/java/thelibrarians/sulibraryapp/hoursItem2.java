package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Cannon on 2/28/17.
 */

public class hoursItem2 implements ListItem{

    private int type = 0;
    private TextView text1, text2, text3, text4;
    private LinearLayout linlayout;
    private LinearLayout linlayout2;


    private LayoutInflater inflater;
    private View view;

    hoursItem2(Activity a, String s1, String s2, String s3) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.hours_item_2, null);
        type = 4;
        text1 = (TextView) view.findViewById(R.id.today_2);
        text1.setText(s1);
        text2 = (TextView) view.findViewById(R.id.date_2);
        text2.setText(s2);
        text3 = (TextView) view.findViewById(R.id.day_2);
        text3.setText(s3);
        text4 = (TextView) view.findViewById(R.id.time_2);
        text4.setText(s3);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        linlayout = (LinearLayout) view.findViewById(R.id.list_item2);
        return linlayout;
    }

    public LinearLayout getLayout1() {
        linlayout = (LinearLayout) view.findViewById(R.id.layout_2);
        return linlayout2;
    }

    public TextView getTextView1() {
        return text1;
    }

    public TextView getTextView2() {
        return text2;
    }

    public TextView getTextView3() {
        return text3;
    }

    public TextView getTextView4() {
        return text4;
    }

}

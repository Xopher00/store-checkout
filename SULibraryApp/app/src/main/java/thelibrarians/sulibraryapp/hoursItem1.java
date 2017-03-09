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

public class hoursItem1 implements ListItem {

    private int type = 0;
    private TextView text1, text2;
    private LinearLayout linlayout;

    private LayoutInflater inflater;
    private View view;

    hoursItem1(Activity a, String s1, String s2){
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, null);
        type = 5;
        text1 = (TextView) view.findViewById(R.id.today);
        text1.setText(s1);
        text2 = (TextView) view.findViewById(R.id.time);
        text2.setText(s2);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        linlayout = (LinearLayout) view.findViewById(R.id.list_item1);
        return linlayout;
    }

    public TextView getTextView1() {
        return text1;
    }
    public TextView getTextView2() {
        return text2;
    }
}

package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by njraf_000 on 2/9/2017.
 */

public class ListItem5 implements ListItem {

    private int type = 0;
    private TextView tv1, tv2;
    private LinearLayout layout;

    private LayoutInflater inflater;
    private View view;

    ListItem5(Activity a, String s1, String s2) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_5, null);
        type = 5;
        tv1 = (TextView) view.findViewById(R.id.text_item5_1);
        tv1.setText(s1);
        tv2 = (TextView) view.findViewById(R.id.text_item5_2);
        tv2.setText(s2);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        layout = (LinearLayout) view.findViewById(R.id.layout_item5);
        return layout;
    }

    public TextView getTextView1() {
        return tv1;
    }

    public TextView getTextView2() {
        return tv2;
    }

}

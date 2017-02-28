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

public class ListItem4 implements ListItem {

    private int type = 0;
    private TextView tv1, tv2, tv3, tv4;
    private LinearLayout layout;

    private LayoutInflater inflater;
    private View view;

    ListItem4(Activity a, String s1, String s2, String s3, String s4) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_4, null);
        type = 4;
        tv1 = (TextView) view.findViewById(R.id.text_item4_1);
        tv1.setText(s1);
        tv2 = (TextView) view.findViewById(R.id.text_item4_2);
        tv2.setText(s2);
        tv3 = (TextView) view.findViewById(R.id.text_item4_3);
        tv3.setText(s3);
        tv4 = (TextView) view.findViewById(R.id.text_item4_4);
        tv4.setText(s4);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        layout = (LinearLayout) view.findViewById(R.id.layout_item4);
        return layout;
    }

    public TextView getTextView1() {
        return tv1;
    }

    public TextView getTextView2() {
        return tv2;
    }

    public TextView getTextView3() {
        return tv3;
    }

    public TextView getTextView4() {
        return tv4;
    }

}

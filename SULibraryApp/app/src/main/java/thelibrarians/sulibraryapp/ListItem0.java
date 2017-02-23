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

public class ListItem0 implements ListItem {

    private int type = 0;
    private TextView tv;
    private LinearLayout layout;

    private LayoutInflater inflater;
    private View view;

    ListItem0(Activity a, String s) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_0, null);
        type = 0;
        tv = (TextView) view.findViewById(R.id.text_item0);
        tv.setText(s);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        layout = (LinearLayout) view.findViewById(R.id.layout_item0);
        return layout;
    }

    public TextView getTextView() {
        return tv;
    }

}

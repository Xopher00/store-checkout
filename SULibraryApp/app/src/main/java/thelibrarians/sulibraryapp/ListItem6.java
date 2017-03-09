package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by njraf_000 on 2/9/2017.
 */

public class ListItem6 implements ListItem {

    private int type = 0;
    private ImageView iv;
    private TextView tv1, tv2, tv3;
    private LinearLayout layout;

    private LayoutInflater inflater;
    private View view;

    ListItem6(Activity a, Bitmap bm, String s1, String s2, String s3) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_6, null);
        iv = (ImageView) view.findViewById(R.id.image_item6);
        iv.setImageBitmap(bm);
        init(s1,s2, s3);
    }

    ListItem6(Activity a, int i, String s1, String s2, String s3) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_6, null);
        iv = (ImageView) view.findViewById(R.id.image_item6);
        iv.setImageResource(i);
        init(s1,s2, s3);
    }

    ListItem6(Activity a, Drawable d, String s1, String s2, String s3) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_6, null);
        iv = (ImageView) view.findViewById(R.id.image_item6);
        iv.setImageDrawable(d);
        init(s1,s2, s3);
    }

    private void init(String s1, String s2, String s3) {
        type = 6;
        tv1 = (TextView) view.findViewById(R.id.text_item6_1);
        tv1.setText(s1);
        tv2 = (TextView) view.findViewById(R.id.text_item6_2);
        tv2.setText(s2);
        tv3 = (TextView) view.findViewById(R.id.text_item6_3);
        tv3.setText(s3);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        layout = (LinearLayout) view.findViewById(R.id.layout_item6);
        return layout;
    }

    public ImageView getImageView() {
        return iv;
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

}

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
 * Created by njraf_000 on 2/11/2017.
 */

public class ListItem2 implements ListItem {
    private int type = -1;
    private TextView tv1;
    private TextView tv2;
    private LinearLayout layout;
    private ImageView img;

    private LayoutInflater inflater;
    private View view;

    ListItem2(Activity a, int i, String s1, String s2) {
        init(a, s1, s2);
        img = (ImageView) view.findViewById(R.id.image_item2);
        img.setImageResource(i);
    }

    ListItem2(Activity a, Bitmap i, String s1, String s2) {
        init(a, s1, s2);
        img = (ImageView) view.findViewById(R.id.image_item2);
        img.setImageBitmap(i);
    }

    ListItem2(Activity a, Drawable i, String s1,String s2) {
        init(a, s1, s2);
        img = (ImageView) view.findViewById(R.id.image_item2);
        img.setImageDrawable(i);
    }

    private void init(Activity a, String s1, String s2) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_2, null);
        type = 2;
        tv1 = (TextView) view.findViewById(R.id.text_item2_1);
        tv1.setText(s1);
        tv2 = (TextView) view.findViewById(R.id.text_item2_2);
        tv2.setText(s2);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        layout = (LinearLayout) view.findViewById(R.id.layout_item2);
        return layout;
    }

    public TextView getTextView1() {
        return tv1;
    }

    public TextView getTextView2() {
        return tv2;
    }

    public ImageView getImageView() {
        return img;
    }
}

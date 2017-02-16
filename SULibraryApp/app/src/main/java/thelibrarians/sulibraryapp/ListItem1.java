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

public class ListItem1 implements ListItem {

    private int type = -1;
    private TextView tv;
    private LinearLayout layout;
    private ImageView img;

    private LayoutInflater inflater;
    private View view;

    ListItem1(Activity a, int i, String s) {
        init(a, s);
        img = (ImageView) view.findViewById(R.id.image_item1);
        img.setImageResource(i);
        img.setTag(i);
    }

    ListItem1(Activity a, Bitmap i, String s) {
        init(a, s);
        img = (ImageView) view.findViewById(R.id.image_item1);
        img.setImageBitmap(i);
        img.setTag(i);
    }

    ListItem1(Activity a, Drawable i, String s) {
        init(a, s);
        img = (ImageView) view.findViewById(R.id.image_item1);
        img.setImageDrawable(i);
        img.setTag(i);
    }

    private void init(Activity a, String s) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_1, null);
        type = 1;
        tv = (TextView) view.findViewById(R.id.text_item1);
        tv.setText(s);
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
        layout = (LinearLayout) view.findViewById(R.id.layout_item1);
        return layout;
    }

    public TextView getTextView() {
        return tv;
    }

    public ImageView getImageView() {
        return img;
    }
}

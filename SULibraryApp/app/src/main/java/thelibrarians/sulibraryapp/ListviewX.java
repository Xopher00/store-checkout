package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static thelibrarians.sulibraryapp.ListviewAdapter.inflater;

/**
 * Created by njraf_000 on 2/9/2017.
 */

/*
* view types:
* 0 = text
* 1 = image, text
* 2 = image, text
*            text
* 3 = text
*     text
*/

public class ListviewX extends BaseAdapter {

    static LayoutInflater inflater;
    ArrayList<ListItem> list;

    ListviewX(Activity a) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void populate(ArrayList<ListItem> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        //doesn't do what you think it does
        //no idea what it does
        return 100;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case 0:
                ListItem0 li0 = (ListItem0) list.get(position);
                convertView = li0.getView();
                //convertView = inflater.inflate(R.layout.list_item_0, null);
                //TextView t0 = (TextView) convertView.findViewById(R.id.text_item0);
                //t0.setText(li0.getTextView().getText());
                break;
            case 1:
                ListItem1 li1 = (ListItem1) list.get(position);
                convertView = li1.getView();
                break;
        }
        return convertView;
    }
}

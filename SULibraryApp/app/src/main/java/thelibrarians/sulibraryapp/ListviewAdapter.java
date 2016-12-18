package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/**
 * Created by njraf_000 on 12/17/2016.
 */

public class ListviewAdapter extends BaseAdapter {



/**
 * Created by njraf_000 on 12/16/2016.
 */

/*
*
*
* -pass getActivity into instance call
* -call setViewTypeAmount()
*
*
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

    LayoutInflater inflater;
    ArrayList<Integer> items = new <Integer>ArrayList();
    int viewTypes = 1; //how many unique layouts are used in this instance of the listview
    Queue<String> texts = new LinkedList<String>();
    Queue images = new LinkedList();
    ArrayList<String> text1 = new ArrayList<String>();
    ArrayList<String> text2 = new ArrayList<String>();
    Map<Integer, ViewHolder> map = new HashMap<Integer, ViewHolder>();

    public ListviewAdapter() {}

    public ListviewAdapter(Activity a) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void populate(int[] type, String[] str, int[] img) {
        for (int x = 0; x < type.length; x++)
            items.add(type[x]);
        for (int x = 0; x < str.length; x++)
            texts.add(str[x]);
        for (int x = 0; x < img.length; x++)
            images.add(img[x]);
    }

    public void populate(int[] type, String[] str, Bitmap[] img) {
        ViewHolder holder = new ViewHolder();
        for (int x = 0; x < type.length; x++)
            items.add(type[x]);
        for (int x = 0; x < str.length; x++) {
            text1.add(str[x]);
            text2.add(str[x+1]);
        }
        for (int x = 0; x < img.length; x++) {
            //images.add(img[x]);
            holder.setBitImg(img[x]);
        }


    }

    public void populate(int[] type, String[] str, Drawable[] img) {
        for (int x = 0; x < type.length; x++)
            items.add(type[x]);
        for (int x = 0; x < str.length; x++)
            texts.add(str[x]);
        for (int x = 0; x < img.length; x++)
            images.add(img[x]);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return viewTypes;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //holder = new ViewHolder();
            switch (getItemViewType(position)) {
                case 0:
                    convertView = inflater.inflate(R.layout.list_item_0, null);
                    TextView t0 = (TextView) convertView.findViewById(R.id.text_item0);
                    t0.setText(texts.remove());
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.list_item_1, null);
                    ImageView i1 = (ImageView) convertView.findViewById(R.id.image_item1);
                    TextView t1 = (TextView) convertView.findViewById(R.id.text_item1);

                    if(images.peek() instanceof Bitmap)
                        i1.setImageBitmap((Bitmap)images.remove());
                    else if(images.peek() instanceof Drawable)
                        i1.setImageDrawable((Drawable) images.remove());
                    else {
                        i1.setImageResource((int)images.remove());
                    }
                    t1.setText(texts.remove());
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.list_item_2, null);
                    ImageView i2 = (ImageView) convertView.findViewById(R.id.image_item2);
                    TextView t2_1 = (TextView) convertView.findViewById(R.id.text_item2_1);
                    TextView t2_2 = (TextView) convertView.findViewById(R.id.text_item2_2);

                    if(images.peek() instanceof Bitmap)
                        i2.setImageBitmap((Bitmap)images.remove());
                    else if(images.peek() instanceof Drawable)
                        i2.setImageDrawable((Drawable) images.remove());
                    else {
                        i2.setImageResource((int)images.remove());
                    }
                    t2_1.setText(texts.remove());
                    t2_2.setText(texts.remove());
                    break;
                case 3:
                    convertView = inflater.inflate(R.layout.list_item_3, null);
                    TextView t3_1 = (TextView) convertView.findViewById(R.id.text_item3_1);
                    TextView t3_2 = (TextView) convertView.findViewById(R.id.text_item3_2);

                    t3_1.setText(texts.remove());
                    t3_2.setText(texts.remove());
                    break;
            }
        } else {
            //holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    public void setViewTypeAmount(int n) {
        //how many unique layouts are used in this instance of the listview
        viewTypes = n;
    }

    public class ViewHolder {
        private String txt1;
        private String txt2;
        private int intImg;
        private Bitmap bitImg;
        private Drawable drawImg;

        public void sett1(String s) {txt1=s;}
        public String gett1() {return txt1;}
        public void sett2(String s) {txt2=s;}
        public String gett2() {return txt2;}
        public void setIntImg(int i) {intImg=i;}
        public int getIntImg() {return intImg;}
        public void setBitImg(Bitmap b) {bitImg=b;}
        public Bitmap getBitImg() {return bitImg;}
        public void setDrawImg(Drawable d) {drawImg=d;}
        public Drawable getDrawImg() {return drawImg;}
    }
}

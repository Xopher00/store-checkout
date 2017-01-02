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

    static LayoutInflater inflater;
    ArrayList<Integer> items = new <Integer>ArrayList(); //list of view types/listview item layouts
    int viewTypes = 1; //how many unique layouts are used in this instance of the listview
    //ArrayList images = new ArrayList();
    //ArrayList<String> text1 = new ArrayList<String>(); //strings for the first textview in a listview's item
    //ArrayList<String> text2 = new ArrayList<String>(); //strings for the second textview in a listview's item if it has a second textview
    Map imgMap = new HashMap(); //map listview position to an image
    Map<Integer, String> textMap1 = new HashMap<Integer, String>();
    Map<Integer, String> textMap2 = new HashMap<Integer, String>();

    public ListviewAdapter() {}

    public ListviewAdapter(Activity a) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void populate(int[] type, String[] str, int[] img) {
        int sIndex = 0;
        int iIndex = 0;

        for (int x = 0; x < type.length; x++) {
            items.add(type[x]);
            switch(type[x]) {
                case 0:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    break;
                case 1:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    imgMap.put(x, img[iIndex++]);
                    //images.add(img[iIndex++]);
                    break;
                case 2:
                    textMap1.put(x, str[sIndex]);
                    //text1.add(str[sIndex]);
                    //text2.add(str[++sIndex]);
                    textMap2.put(x, str[++sIndex]);
                    sIndex++;
                    imgMap.put(x, img[iIndex++]);
                    //images.add(img[iIndex++]);

                    break;
                case 3:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    //text2.add(str[++sIndex]);
                    textMap2.put(x, str[sIndex]);
                    sIndex++;
                    break;
            }
        }
    }

    public void populate(int[] type, String[] str, Bitmap[] img) {
        int sIndex = 0;
        int iIndex = 0;

        for (int x = 0; x < type.length; x++) {
            items.add(type[x]);
            switch(type[x]) {
                case 0:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    break;
                case 1:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    imgMap.put(x, img[iIndex++]);
                    //images.add(img[iIndex++]);
                    break;
                case 2:
                    textMap1.put(x, str[sIndex]);
                    //text1.add(str[sIndex]);
                    //text2.add(str[++sIndex]);
                    textMap2.put(x, str[++sIndex]);
                    sIndex++;
                    imgMap.put(x, img[iIndex++]);
                    //images.add(img[iIndex++]);

                    break;
                case 3:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    //text2.add(str[++sIndex]);
                    textMap2.put(x, str[++sIndex]);
                    sIndex++;
                    break;
            }
        }
    }

    public void populate(int[] type, String[] str, Drawable[] img) {
        int sIndex = 0;
        int iIndex = 0;

        for (int x = 0; x < type.length; x++) {
            items.add(type[x]);
            switch(type[x]) {
                case 0:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    break;
                case 1:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    imgMap.put(x, img[iIndex++]);
                    //images.add(img[iIndex++]);
                    break;
                case 2:
                    textMap1.put(x, str[sIndex]);
                    //text1.add(str[sIndex]);
                    //text2.add(str[++sIndex]);
                    textMap2.put(x, str[++sIndex]);
                    sIndex++;
                    imgMap.put(x, img[iIndex++]);
                    //images.add(img[iIndex++]);

                    break;
                case 3:
                    textMap1.put(x, str[sIndex++]);
                    //text1.add(str[sIndex++]);
                    //text2.add(str[++sIndex]);
                    textMap2.put(x, str[++sIndex]);
                    sIndex++;
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 4;
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

            switch (getItemViewType(position)) {
                case 0:
                    convertView = inflater.inflate(R.layout.list_item_0, null);
                    TextView t0 = (TextView) convertView.findViewById(R.id.text_item0);
                    t0.setText(textMap1.get(position));
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.list_item_1, null);
                    ImageView i1 = (ImageView) convertView.findViewById(R.id.image_item1);
                    TextView t1 = (TextView) convertView.findViewById(R.id.text_item1);

                    if(imgMap.get(position) instanceof Bitmap)
                        i1.setImageBitmap((Bitmap) imgMap.get(position));
                    else if(imgMap.get(position) instanceof Drawable)
                        i1.setImageDrawable((Drawable) imgMap.get(position));
                    else {
                        i1.setImageResource((int)imgMap.get(position));
                    }
                    t1.setText(textMap1.get(position));
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.list_item_2, null);
                    ImageView i2 = (ImageView) convertView.findViewById(R.id.image_item2);
                    TextView t2_1 = (TextView) convertView.findViewById(R.id.text_item2_1);
                    TextView t2_2 = (TextView) convertView.findViewById(R.id.text_item2_2);

                    if(imgMap.get(position) instanceof Bitmap)
                        i2.setImageBitmap((Bitmap) imgMap.get(position));
                    else if(imgMap.get(position) instanceof Drawable)
                        i2.setImageDrawable((Drawable) imgMap.get(position));
                    else {
                        i2.setImageResource((int)imgMap.get(position));
                    }
                    t2_1.setText(textMap1.get(position));
                    t2_2.setText(textMap2.get(position));
                    break;
                case 3:
                    convertView = inflater.inflate(R.layout.list_item_3, null);
                    TextView t3_1 = (TextView) convertView.findViewById(R.id.text_item3_1);
                    TextView t3_2 = (TextView) convertView.findViewById(R.id.text_item3_2);

                    t3_1.setText(textMap1.get(position));
                    t3_2.setText(textMap2.get(position));
                    break;
            }

        return convertView;
    }

    public void setViewTypeAmount(int n) {
        //how many unique layouts are used in this instance of the listview
        viewTypes = n;
    }

  /*  public class ViewHolder {
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
    }*/
}

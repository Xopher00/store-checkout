package thelibrarians.sulibraryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by njraf_000 on 10/3/2016.
 */
public class ImgTxtListAdapter extends BaseAdapter {

    LayoutInflater inf;
    ArrayList<SectionStructure> sectionList = new ArrayList<SectionStructure>();
    Context context;

    ImgTxtListAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return sectionList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View vi = arg1;

        vi = inf.inflate(R.layout.list_item, null);

        ImageView icon = (ImageView) vi.findViewById(R.id.list_image);
        TextView title = (TextView)vi.findViewById(R.id.list_title);
        TextView subTitle = (TextView)vi.findViewById(R.id.list_subtitle);
        TextView note = (TextView)vi.findViewById(R.id.list_note);
        LinearLayout item = (LinearLayout)vi.findViewById(R.id.list_item);

        if(sectionList.get(arg0).getSectionTitle() != null && sectionList.get(arg0).getSectionTitle().equalsIgnoreCase("")){
            //title.setText("");
            title.setText(sectionList.get(arg0).getSectionName());
            if(sectionList.get(arg0).getSectionBackground() != -1)
                vi.setBackgroundResource(sectionList.get(arg0).getSectionBackground());
            if(sectionList.get(arg0).getSectionSubtitle() != null)
                subTitle.setText(sectionList.get(arg0).getSectionSubtitle());
        }
        else{
            if(sectionList.get(arg0).getSectionDrawable() == null) {
                icon.setImageResource(sectionList.get(arg0).getSectionImage());
                icon.setTag(sectionList.get(arg0).getSectionImage());
            } else {
                icon.setImageDrawable(sectionList.get(arg0).getSectionDrawable());
            }

            if(sectionList.get(arg0).getBitmapImg() == null) {
                icon.setImageResource(sectionList.get(arg0).getSectionImage());
                icon.setTag(sectionList.get(arg0).getSectionImage());
            } else {
                icon.setImageBitmap(sectionList.get(arg0).getBitmapImg());
            }
            title.setText(sectionList.get(arg0).getSectionTitle());
            subTitle.setText(sectionList.get(arg0).getSectionSubtitle());
            note.setText(sectionList.get(arg0).getSectionNote());
            item.setBackgroundColor(Color.WHITE);
        }

        return vi;
    }

    public SectionStructure getStr() {
        return new SectionStructure();
    }

    public ArrayList getSectionStructure() {
        return sectionList;
    }



    public class SectionStructure{

        public String sectionName;
        public String sectionTitle;
        public String sectionSubtitle;
        public String sectionNote;
        public Bitmap bitmapImg = null;
        public int sectionImage;
        public int sectionBackground = -1;
        public LayerDrawable sectionDrawable = null;

        public String getSectionName() {
            return sectionName;
        }
        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }
        public String getSectionTitle() {
            return sectionTitle;
        }
        public void setSectionTitle(String sectionTitle) {
            this.sectionTitle = sectionTitle;
        }
        public String getSectionSubtitle() {
            return sectionSubtitle;
        }
        public void setSectionSubtitle(String sectionSectionSubtitle) {
            this.sectionSubtitle = sectionSectionSubtitle;
        }
        public String getSectionNote() {
            return sectionNote;
        }
        public void setSectionNote(String sectionNote) {
            this.sectionNote = sectionNote;
        }
        public int getSectionImage() {
            return sectionImage;
        }
        public void setSectionImage(int sectionImage) {
            this.sectionImage = sectionImage;
        }
        public int getSectionBackground() {return sectionBackground;}
        public void setSectionBackground(int sectionBackground) {this.sectionBackground = sectionBackground;}
        public LayerDrawable getSectionDrawable() {
            return sectionDrawable;
        }
        public void setSectionDrawable(LayerDrawable d) {
            sectionDrawable = d;
        }
        public Bitmap getBitmapImg() {return bitmapImg;}
        public void setBitmapImg(Bitmap bit) {bitmapImg = bit;}
    }
}



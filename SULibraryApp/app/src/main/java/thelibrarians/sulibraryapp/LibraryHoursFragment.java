package thelibrarians.sulibraryapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class LibraryHoursFragment extends Fragment {

    String date, rendered;
    boolean internet = false;
    int pos = 0;

    JSONObject week1;
    JSONObject week2;
    JSONObject week3;
    JSONObject week4;
    JSONObject week5;
    JSONObject week6;
    JSONObject week7;
    LayerDrawable[] icons;
    ListView listView;
    //ImgTxtListAdapter itlAdapter;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String[] sectionHeader;
    String[] titles;
    TextView text;
    public LibraryHoursFragment() {Log.i("hello","default");}

    public LibraryHoursFragment(JSONObject j, int p) {
        pos = p;
        internet = true;
        try {
            date = j.getString("date");
            rendered = j.getString("rendered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources r = getResources();

        View view = inflater.inflate(R.layout.fragment_library_hours, container, false);

        //itlAdapter = new ImgTxtListAdapter(getActivity());
        lix = new ListviewX(getActivity());

        listItems = new ArrayList<ListItem>();

        listView = (ListView) view.findViewById(R.id.hours_list); //need to be able to access an xml element with java so that you can modify it dynamically

        text = (TextView) view.findViewById(R.id.hours_text);

        text.setText("The hours listed below are the times that the library service desk and the library stacks are open. Other areas of the library and other departments within the Guerrieri Academic Commons may keep different hours.");

        sectionHeader = getResources().getStringArray(R.array.hours_header);
        titles = getResources().getStringArray(R.array.hours);

        /*
        * 4 = text, text
        *     text
        *     text
        * 5 = text, text
        * */
        int cheader = 0;
        int ctitles = 0;
        for(int x = 0; x < titles.length+sectionHeader.length; x++) {
            switch(x) {
                case 0: //headers
                    ListItem0 li0 = new ListItem0(getActivity(), sectionHeader[cheader++]);
                    li0.getTextView().setTextColor(Color.parseColor("#8a000000"));
                    listItems.add(li0);
                    break;
                default:
                    ListItem0 li = new ListItem0(getActivity(), titles[ctitles++]);
                    listItems.add(li);
            }
        }

        //populateListView(sectionHeader, null, titles, null, null);
        lix.populate(listItems);
        listView.setAdapter(lix);

        return view;
    }
/*
    public void populateListView(String[] sectionHeader, LayerDrawable[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();
//for each header in header array it is going to go through the loop
        //depending on iteration of loop then we are going to do another loop that is dependent on the number of items below that
        //specific header
        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //case 0 corresponds to the 'Hours' header
            switch(i) {
                case 0:
                    items = 3;
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr(); //itlAdapter = list adapter; places the titles under the header (6 times)
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionDrawable(icons[position]);
                            str.setSectionName("");
                            if(titles != null)
                                str.setSectionTitle(titles[position]);
                            if(subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if(notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
            }
        }
    }*/

}

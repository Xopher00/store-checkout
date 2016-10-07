package thelibrarians.sulibraryapp;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Xopher on 10/3/2016.
 */
//this fragment displays a list of study rooms in the library, al=nd whether or not they are available

public class StudyRoomReserveFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listViewsrr; //listView study room reservation
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    //array of icons to be matched to array of items, pulled from drawable folder
    int[] icons ={R.drawable.group_study_medium2x, R.drawable.group_study_medium2x, R.drawable.group_study_small2x,
            R.drawable.group_study_large2x, R.drawable.group_study_large2x,  R.drawable.group_study_small2x, R.drawable.group_study_large2x,
            R.drawable.group_study_medium2x, R.drawable.group_study_large2x, R.drawable.group_study_large2x, R.drawable.group_study_large2x,
            R.drawable.group_study_medium2x, R.drawable.group_study_medium2x, R.drawable.group_study_medium2x, R.drawable.group_study_medium2x };
    ImgTxtListAdapter itlAdapter;

    public StudyRoomReserveFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionHeader = getResources().getStringArray(R.array.study_room_headers);
        items = getResources().getStringArray(R.array.study_rooms);

        View view = inflater.inflate(R.layout.fragment_study_room_reserve, container, false);

        itlAdapter = new ImgTxtListAdapter(getActivity());

        listViewsrr = (ListView) view.findViewById(R.id.listViewsrr);

        //add and call populateListView()
        populateListView(sectionHeader, icons, items, null, null);

        listViewsrr.setAdapter(itlAdapter);
        listViewsrr.setOnItemClickListener(this);

        return view;
    }



    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //this fragment has two sections
            switch(i) {
                case 0:
                    items = 2; //2 first floor study rooms
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
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
                case 1:
                    items = 13; //13 second floor study rooms
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        switch(position) {
            //show respective study room and availability
            case 1://room 139
                break;
            case 2://room 140
                break;
            //case 3 refers to section header 2
            case 4://room 225
                break;
            case 5://room 226
                break;
            case 6://room 227
                break;
            case 7://room 228
                break;
            case 8://room 234
                break;
            case 9://room 235
                break;
            case 10://room 236
                break;
            case 11://room 237
                break;
            case 12://room 238
                break;
            case 13://room 239
                break;
            case 14://room 240
                break;
            case 15://room 241
                break;
            case 16://room 242
                break;
        }
    }
}

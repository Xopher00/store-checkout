package thelibrarians.sulibraryapp;

import android.support.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Xopher on 10/3/2016.
 */
//this fragment displays a list of study rooms in the library, al=nd whether or not they are available

public class StudyRoomReserveFragment extends Fragment implements AdapterView.OnItemClickListener {

    static int position;
    ListView listViewsrr; //listView study room reservation
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    //array of icons to be matched to array of items, pulled from drawable folder
    int[] icons ={R.drawable.group_study_medium, R.drawable.group_study_medium, R.drawable.group_study_small,
            R.drawable.group_study_large, R.drawable.group_study_large,  R.drawable.group_study_small, R.drawable.group_study_large,
            R.drawable.group_study_medium, R.drawable.group_study_large, R.drawable.group_study_large, R.drawable.group_study_large,
            R.drawable.group_study_medium, R.drawable.group_study_medium, R.drawable.group_study_medium, R.drawable.group_study_medium};
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

    /*https://api2.libcal.com/1.0/room_availability/?iid=823&room_id=%td&limit=150&extend=1&key=d095e46065538df2f67eb7cf7d483896

    http://salisbury.beta.libcal.com/rooms_acc.php?gid=%td */

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
        this.position = position;
        Fragment p1; FragmentManager fragmentManager; FragmentTransaction fragmentTransaction;
        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        p1 = new StudyRoomDisplayFragment(position);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, p1);
        fragmentTransaction.addToBackStack(null).commit();
    }
}



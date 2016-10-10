package thelibrarians.sulibraryapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by njraf_000 on 10/3/2016.
 *
 * In each fragment.java:
 * 0. basically copy this entire file
 * 1. setup listView and itlAdapter like in onCreateView() below
 * 2. make arrays for: section headers, icons, titles, subtitles, notes. Only some of these are needed in each fragment
 * 3. paste populateListView() into each fragment that requires it
 * 4. pass arrays to populateListView() in order: section headers, icons, titles, subtitles, notes. Any of them can be null
 * 5. add case statements for each section
 * 6. change 'items' variable inside each case statement to match number of items in that section
 * 7. have the fragment implement AdapterView.OnItemClickListener
 * 8. in onItemClick() add case statements for each list item to determine that item's action.
 * --CAUTION: section headers count as positions
 * --i.e. position 0 is section header 1
 *
 *
 * In each fragment.xml:
 * 1. give your listView a unique id
 *
 */
public class ResearchHelpFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    String[] sectionHeader = {"Section 1","Section 2","Section 3","Section 4"}; //Array for section headers
    String[] titles = {"Thing 1","blog 2","Blog 3","Blog 4"}; //Array for the items under each of the headers
    int[] icons = {};
    ImgTxtListAdapter itlAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_research_help, container, false);

        itlAdapter = new ImgTxtListAdapter(getActivity());

        listView = (ListView) view.findViewById(R.id.listView); //need to be able to access an xml element with java so that you can modify it dynamically

        //add and call populateListView()
        populateListView(sectionHeader, icons, titles, null, null);

        listView.setAdapter(itlAdapter);
        listView.setOnItemClickListener(this);

        return view;
    }


    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            switch(i) {
                case 0:
                    items = 2;
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
                    items = 2;
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

    //when we click on the item to take you to a the next page
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        switch(position) {
            case 1:
                //example of how to switch fragments
                //MainActivity.getNewFragTransaction().replace(R.id.content_frame, new Fragment2()).commit();

                Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/17/2016.
 */

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
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


public class HelpfulLinksFragment extends Fragment implements AdapterView.OnItemClickListener {

    static int position;
    ListView listViewhl; //listView helpful links
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    int[] views;

    //ImgTxtListAdapter itlAdapter;
    ListviewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sectionHeader = getResources().getStringArray(R.array.helpful_headers);
        items = getResources().getStringArray(R.array.helpful_strings);

        View view = inflater.inflate(R.layout.fragment_helpful_links, container, false);

        //itlAdapter = new ImgTxtListAdapter(getActivity());
        adapter = new ListviewAdapter(getActivity());

        listViewhl = (ListView) view.findViewById(R.id.listViewhl);

        views = new int[23];
        for(int x = 0; x < 23; x++) {
            views[x] = 0;
        }



        //add and call populateListView()
        //populateListView(sectionHeader, null, items, null, null);
        int[] icons = new int[0]; //needed for adapter
        adapter.populate(views, items, icons);

        listViewhl.setAdapter(adapter);
        listViewhl.setOnItemClickListener(this);

        int num_of_visible_view=listViewhl.getLastVisiblePosition() -
                listViewhl.getFirstVisiblePosition();

        for(int pos = 0; pos < views.length; pos++) {
            if(pos != 0 && pos != 5 && pos != 10 && pos != 18) {
                updateView(pos);
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void updateView(int p) {
        View v = null;
        v = listViewhl.getAdapter().getView(p, v, listViewhl);
        if(v != null) {
            Log.i("nick", "view not null");
            TextView t = (TextView) v.findViewById(R.id.text_item0);
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.layout_item0);

            ll.setBackgroundColor(Color.WHITE);
            t.setTextColor(Color.BLACK);
        } else {
            Log.i("nick", "view null");
        }
    }

/*
    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //this fragment has four sections
            switch(i) {
                case 0:
                    items = 4; //4 items in first section
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
                    items = 4; //4 items
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
                case 2:
                    items = 7;
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
                case 3:
                    items = 4;
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
    */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;

        Uri uriUrl; Intent launchBrowser;
            //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        switch(position) {

            case 1://Academic Search Complete
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/go.php?c=7603479");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 2://JSTOR
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/go.php?c=7603557");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 3://Science Direct
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/go.php?c=7603627");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 4://Web of Science
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/go.php?c=7603648");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;

            //case 5 is section header HELP WITH CITATIONS

            case 6://SU Libraries Citation Style Guide
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/citation");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 7://EasyBib
                uriUrl = Uri.parse("http://proxy-su.researchport.umd.edu/login?url=http://www.easybib.com/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 8://EndNote Web
                uriUrl = Uri.parse("http://proxy-su.researchport.umd.edu/login?url=https://www.myendnoteweb.com/touch/EndNoteWeb.html");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 9://Purdue OWL
                uriUrl = Uri.parse("https://owl.english.purdue.edu/owl/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;

            //case 10 is section header OTHER LIBRARY RESOURCES

            case 11://Presenting Your Research
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/present");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 12://Copyright
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu/copyright-across-campus");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 13://SOAR@SU
                uriUrl = Uri.parse("https://mdsoar.org/handle/11603/9");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 14://SU Libraries Research Guides
                uriUrl = Uri.parse("http://libraryguides.salisbury.edu");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 15://SU Library Website
                uriUrl = Uri.parse("http://www.salisbury.edu/library");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 16://Nabb Center for Delmarva History
                uriUrl = Uri.parse("http://www.salisbury.edu/nabb/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 17://Curriculum Resource Center
                uriUrl = Uri.parse("http://www.salisbury.edu/seidel/crc/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;

            //case 18 is section header SU LINKS

            case 19://IT Help Desk
                uriUrl = Uri.parse("http://www.salisbury.edu/helpdesk/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 20://Center for Student Achievement
                uriUrl = Uri.parse("http://www.salisbury.edu/achievement/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 21://Writing Center
                uriUrl = Uri.parse("http://www.salisbury.edu/uwc/");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 22://Salisbury University Homepage
                uriUrl = Uri.parse("http://www.salisbury.edu");
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
        }
    }}


package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/19/2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


public class ContactInfoFragment extends Fragment implements AdapterView.OnItemClickListener {

    static int position;
    ListView listViewct; //listView contacts
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    String[] subitems;

    TxtImgListAdapter itAdapter;
    ImgTxtListAdapter itlAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionHeader = getResources().getStringArray(R.array.contact_headers);
        items = getResources().getStringArray(R.array.contact_who);
        subitems = getResources().getStringArray(R.array.contact_deets);

        int[] icons = {R.drawable.available, R.drawable.available, R.drawable.available,
        R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call,
        R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor,
        R.drawable.contactcolor, R.drawable.generic_person, R.drawable.sebrazer,
        R.drawable.generic_person, R.drawable.mxchakraborty, R.drawable.generic_person, R.drawable.generic_person,
        R.drawable.generic_person, R.drawable.generic_person, R.drawable.generic_person, R.drawable.cmeckardt,
        R.drawable.saford, R.drawable.lhanscom, R.drawable.generic_person, R.drawable.generic_person,
        R.drawable.generic_person, R.drawable.generic_person, R.drawable.generic_person, R.drawable.generic_person,
        R.drawable.generic_person, R.drawable.generic_person, R.drawable.jmmartin, R.drawable.generic_person,
        R.drawable.generic_person, R.drawable.jlparrigin, R.drawable.generic_person, R.drawable.arprichard,
        R.drawable.ggrobb, R.drawable.generic_person, R.drawable.generic_person, R.drawable.generic_person,
        R.drawable.generic_person, R.drawable.generic_person, R.drawable.generic_person, R.drawable.cmwoodall,
        R.drawable.generic_person};

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        itAdapter = new TxtImgListAdapter(getActivity());
        itlAdapter = new ImgTxtListAdapter(getActivity());

        listViewct = (ListView) view.findViewById(R.id.listViewhl);

        //add and call populateListView()
        populateListView(sectionHeader, icons, items, subitems, null);

        listViewct.setAdapter(itlAdapter);
        listViewct.setOnItemClickListener(this);

        return view;
    }


    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();
        TxtImgListAdapter.SectionStructure str1;
        ArrayList<TxtImgListAdapter.SectionStructure> sectionList1 = itlAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //this fragment has four sections
            switch(i) {
                case 0:
                    //number of case statements is the number of sections
                    //this fragment has four sections
                    items = 3; //you can live chat with three differnetn staff people
                    for(int j = 0; j < items+1; j++) {
                        str1 = itAdapter.getStr();
                        if(j == 0) {
                            str1.setSectionName(sectionHeader[i]);
                            str1.setSectionTitle("");
                            sectionList1.add(str1);
                        } else {
                            if(icons != null)
                                str1.setSectionImage(icons[position]);
                            str1.setSectionName("");
                            if(titles != null)
                                str1.setSectionTitle(titles[position]);
                            if(subTitles != null)
                                str1.setSectionSubtitle(subTitles[position]);
                            if(notes != null)
                                str1.setSectionNote(notes[position]);
                            sectionList1.add(str1);
                            position++;
                        }
                    }
                    break;
                case 1:
                    items = 4; //4 diff phone no's
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
                    items = 5; //5 diff emails
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
                    items = 35;//35 diff staff members
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

        Uri uriUrl; Intent launchBrowser;
        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        switch(position) {

            case 1://CHAT 1
                uriUrl = Uri.parse("https://us.libraryh3lp.com/chat/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 2://CHAT 2
                uriUrl = Uri.parse("https://us.libraryh3lp.com/chat/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 3://CHAT 3
                uriUrl = Uri.parse("https://us.libraryh3lp.com/chat/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
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

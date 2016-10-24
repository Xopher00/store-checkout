package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/19/2016.
 */

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
        R.drawable.contactcolor, R.drawable.genericperson, R.drawable.sebrazer,
        R.drawable.genericperson, R.drawable.mxchakraborty, R.drawable.genericperson, R.drawable.genericperson,
        R.drawable.genericperson, R.drawable.genericperson, R.drawable.genericperson, R.drawable.cmeckardt,
        R.drawable.saford, R.drawable.lhanscom, R.drawable.genericperson, R.drawable.genericperson,
        R.drawable.genericperson, R.drawable.genericperson, R.drawable.genericperson, R.drawable.genericperson,
        R.drawable.genericperson, R.drawable.genericperson, R.drawable.jmmartin, R.drawable.genericperson,
        R.drawable.genericperson, R.drawable.jlparrigin, R.drawable.genericperson, R.drawable.arprichard,
        R.drawable.ggrobb, R.drawable.genericperson, R.drawable.genericperson, R.drawable.genericperson,
        R.drawable.genericperson, R.drawable.genericperson, R.drawable.genericperson, R.drawable.cmwoodall,
        R.drawable.genericperson};

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

                    //if json says chat not avail
                        //subTitles[i] = "Chat not avail try later"
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

        Uri uriUrl; Intent launchBrowser; Intent dialer = new Intent(Intent.ACTION_DIAL);
        Intent emailer = new Intent(Intent.ACTION_VIEW);
        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        switch(position) {

            case 1://CHAT 1
                uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 2://CHAT 2
                uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/makerlab@chat.libraryh3lp.com?skin=22280&identity=Staff");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case 3://CHAT 3
                uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-crc@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;

            case 5://call research help 410 548 5988
                //dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:4105485988"));
                startActivity(dialer);
                break;
            case 6://call circulation 410 543 6130
               // dialer = new Intent(Intent.ACTION_DIAL);
                callCircAss(dialer);
                break;
            case 7://call toll free 888 543 0148
                //dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:8885430148"));
                startActivity(dialer);
                break;
            case 8://call app support 410 543 6306
                //dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:4105436306"));
                startActivity(dialer);
                break;

            case 10://email reserach help
                //emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"libraries@salisbury.edu"});
                startActivity(emailer);
                break;
            case 11://email circulation
                //emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"illoans@salisbury.edu"});
                startActivity(emailer);
                break;
            case 12://email interlibray loan
               // emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"libcirc@salisbury.edu"});
                startActivity(emailer);
                break;
            case 13://email soar@su
               // emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"soar@salisbury.edu"});
                startActivity(emailer);
                break;
            case 14://email app support
               // emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"cmwoodall@salisbury.edu"});
                startActivity(emailer);
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
            case 18:
                break;
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
            case 23:
                break;
            case 24:
                break;
            case 25:
                break;
            case 26:
                break;
            case 27:
                break;
            case 28:
                break;
            case 29:
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                break;
            case 33:
                break;
            case 34:
                break;
            case 35:
                break;
            case 36:
                break;
            case 37:
                break;
            case 38:
                break;
            case 39:
                break;
            case 40:
                break;
            case 41:
                break;
            case 42:
                break;
            case 43:
                break;
            case 44:
                break;
            case 45:
                break;
            case 46:
                break;
            case 47:
                break;
            case 48:
                break;
            case 49:
                break;
            case 50:
                break;
            case 51:
                break;
            }
    }

    public void callCircAss(Intent dialer){
        dialer.setData(Uri.parse("tel:4105436130"));
        startActivity(dialer);
    }
}

/*
}*/

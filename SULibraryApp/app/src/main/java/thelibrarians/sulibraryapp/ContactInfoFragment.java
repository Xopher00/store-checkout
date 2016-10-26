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

    TxtImgListAdapter itAdapter; //text, THEN image
    ImgTxtListAdapter itlAdapter; //image, THEN text

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionHeader = getResources().getStringArray(R.array.contact_headers);
        items = getResources().getStringArray(R.array.contact_who);
        subitems = getResources().getStringArray(R.array.contact_deets);

        int[] icons = {R.drawable.available, R.drawable.available, R.drawable.available,
        R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call,
        R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor, R.drawable.contactcolor,
        R.drawable.contactcolor, R.drawable.jbellistri, R.drawable.sebrazer,
        R.drawable.spburton, R.drawable.mxchakraborty, R.drawable.hfchaphe, R.drawable.fxchirombo,
        R.drawable.srcooper, R.drawable.thcuster, R.drawable.bddennis, R.drawable.cmeckardt,
        R.drawable.saford, R.drawable.lhanscom, R.drawable.bbhardy, R.drawable.tahorner,
        R.drawable.ijenkins, R.drawable.amjones, R.drawable.apkinsey, R.drawable.jmkreines,
        R.drawable.cklewis, R.drawable.crlong, R.drawable.jmmartin, R.drawable.lmvanveen,
        R.drawable.dtmessick, R.drawable.jlparrigin, R.drawable.impost, R.drawable.arprichard,
        R.drawable.ggrobb, R.drawable.laroye, R.drawable.mxruddy, R.drawable.ahschadt,
        R.drawable.lschiff, R.drawable.eawallace, R.drawable.klwilson, R.drawable.cmwoodall,
        R.drawable.mczimmerman};

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        itAdapter = new TxtImgListAdapter(getActivity());
        itlAdapter = new ImgTxtListAdapter(getActivity());

        listViewct = (ListView) view.findViewById(R.id.listViewct);

        //add and call populateListView()
        populateListView(sectionHeader, icons, items, subitems, null);

        listViewct.setAdapter(itAdapter);
        listViewct.setAdapter(itlAdapter);
        listViewct.setOnItemClickListener(this);

        return view;
    }


    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str; //image, THEN text
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();
        TxtImgListAdapter.SectionStructure str1; //text, THEN image
        ArrayList<TxtImgListAdapter.SectionStructure> sectionList1 = itAdapter.getSectionStructure();

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

        Uri uriUrl; Intent launchBrowser; Intent dialer; Intent emailer;
        Fragment c1; FragmentManager fragmentManager; FragmentTransaction fragmentTransaction;
        Bundle args;

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
                dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:4105485988"));
                startActivity(dialer);
                break;
            case 6://call circulation 410 543 6130
                dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:4105436130"));
                startActivity(dialer);
                break;
            case 7://call toll free 888 543 0148
                dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:8885430148"));
                startActivity(dialer);
                break;
            case 8://call app support 410 543 6306
                dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:4105436306"));
                startActivity(dialer);
                break;

            case 10://email reserach help
                emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"libraries@salisbury.edu"});
                startActivity(emailer);
                break;
            case 11://email circulation
                emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"illoans@salisbury.edu"});
                startActivity(emailer);
                break;
            case 12://email interlibray loan
                emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"libcirc@salisbury.edu"});
                startActivity(emailer);
                break;
            case 13://email soar@su
                emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"soar@salisbury.edu"});
                startActivity(emailer);
                break;
            case 14://email app support
                emailer = new Intent(Intent.ACTION_VIEW);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setType("vnd.android.cursor.item/email");
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"cmwoodall@salisbury.edu"});
                startActivity(emailer);
                break;

            //diff cases for 38 diff staff members
            case 16:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();//pass an argument to the new fragment
                args.putInt("position", position);//pass position
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 17:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 18:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 19:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 20:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 21:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 22:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 23:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 24:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 25:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 26:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 27:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 28:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 29:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 30:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 31:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 32:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 33:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 34:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 35:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 36:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 37:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 38:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 39:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 40:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 41:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 42:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 43:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 44:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 45:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 46:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 47:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 48:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 49:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 50:
                c1 = new CallOrClickDialogFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, c1);
                args = new Bundle();
                args.putInt("position", position);
                c1.setArguments(args);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            }
    }
}

/*
}*/

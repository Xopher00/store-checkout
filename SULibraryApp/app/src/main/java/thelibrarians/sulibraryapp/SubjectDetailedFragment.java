package thelibrarians.sulibraryapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
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

import java.util.ArrayList;

import javax.security.auth.Subject;

public class SubjectDetailedFragment extends Fragment {

    static int position;
    View view;
    ImageView imgView;
    ImageView icon;
    ImageView rectangle;
    ImageView circle;
    TextView title;
    LayerDrawable[] staff_icons;
    ListView listView;
    ImgTxtListAdapter itlAdapter;
    String[] sectionHeader;
    String[] titles;
    DrawerToggleListener toggleListener;


    public SubjectDetailedFragment() {
        // Required empty public constructor
    }

    public SubjectDetailedFragment(int tab){
        position = tab;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources r = getResources();

        View view = inflater.inflate(R.layout.subject_detailed, container, false);

        itlAdapter = new ImgTxtListAdapter(getActivity());

        listView = (ListView) view.findViewById(R.id.subject_list); //need to be able to access an xml element with java so that you can modify it dynamically

        listView.setAdapter(itlAdapter);
       // listView.setOnItemClickListener(this);


        //list of statements is used to create the header at the top of every
        // individual subject's fragment
        icon = (ImageView) view.findViewById(R.id.acc_icon);  //icon for the subject
        rectangle = (ImageView) view.findViewById(R.id.acc_header);  //rectangle portion
        circle = (ImageView) view.findViewById(R.id.acc_circle);  //circle that icon is placed on
        title = (TextView) view.findViewById(R.id.acc_title);  //text displaying name of subject

       staff_icons = new LayerDrawable[8];

        //takes section headers and titles from .xml strings file
        sectionHeader = getResources().getStringArray(R.array.subject_headers);

            switch (position) {
                //Accounting & Legal Studies
                case 0:
                    titles = getResources().getStringArray(R.array.librarian);
                    //imgView.setImageResource(R.drawable.ggrobb);
                    //Referencing the XML that will create the overall header for the page
                    icon.setImageResource(R.drawable.accounting3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Accounting & Legal Studies");

                    //Create Librarian Icon for Subject
                    Drawable[] staffIconALS; //creates an array of layers for each icon
                    staffIconALS = new Drawable[2];
                    staffIconALS[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
                    staffIconALS[1] = r.getDrawable(R.drawable.ggrobb3x); //R = abstraction to the file system
                    LayerDrawable layerDrawable = new LayerDrawable(staffIconALS); //merges the two layers together
                    staffIconALS[0] = layerDrawable;
                    staff_icons[0] = layerDrawable;

                    break;
                //Anthropology
                case 1:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.anthropology3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Anthropology");
                    break;
                //Applied Health Physiology
                case 2:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.ahp3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Applied Health & Physiology");
                    break;
                //Art & Art History
                case 3:
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.art3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Art & Art History");
                    break;
                //Biology
                case 4:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.biology3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Biology");
                    break;
                //Business
                case 5:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.business3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Business");
                    break;
                //Chemistry
                case 6:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.chemistry3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Chemistry");
                    break;
                //Communication Arts
                case 7:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.comm3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Communication Arts");
                    break;
                //Computer Science
                case 8:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.compsci3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Computer Science");
                    break;
                //Conflict Analysis & Dispute Resolution
                case 9:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.cadr3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Conflict Analysis & Dispute Resolution");
                    break;
                //Dance
                case 10:
                    //img.setImageResource(R.drawable.arprichard);
                    icon.setImageResource(R.drawable.dance3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Dance");
                    break;
                //Economics & Finance
                case 11:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.econ3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Economics & Finance");
                    break;
                //Education
                case 12:
                    //img.setImageResource(R.drawable.saford);
                    icon.setImageResource(R.drawable.education3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Education");
                    break;
                //Engineering
                case 13:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.engineering3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Engineering");
                    break;
                //English
                case 14:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.english3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("English");
                    break;
                //English Language Institute
                case 15:
                    //img.setImageResource(R.drawable.lhanscom);
                    icon.setImageResource(R.drawable.eli3x);
                    circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("English Language Institute");
                    break;
                //Environmental Studies
                case 16:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.environ3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Environmental Studies");
                    break;
                //Geography & Geosciences
                case 17:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.geog3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Geography & Geosciences");
                    break;
                //Government Information
                case 18:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.govt3x);
                    circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Government Information");
                    break;
                //Health & Sport Sciences
                case 19:
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.hss3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Health & Sport Sciences");
                    break;
                //History
                case 20:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.history3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("History");
                    break;
                //Information & Decision Sciences
                case 21:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.ids3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Information & Decision Sciences");
                    break;
                //Interdisciplinary Studies
                case 22:
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.inter3x);
                    circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Interdisciplinary Studies");
                    break;
                //Management & Marketing
                case 23:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.mgmt3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Management & Marketing");
                    break;
                //Mathematics
                case 24:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.math3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Mathematics");
                    break;
                //Medical Laboratory Science
                case 25:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.mls3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Medical Laboratory Science");
                    break;
                //Military Science
                case 26:
                    icon.setImageResource(R.drawable.mil3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Military Science");
                    break;
                //Modern Languages
                case 27:
                    icon.setImageResource(R.drawable.modlang3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Modern Languages");
                    break;
                //Music
                case 28:
                    icon.setImageResource(R.drawable.music3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Music");
                    break;
                //Nursing
                case 29:
                    icon.setImageResource(R.drawable.nursing3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Nursing");
                    break;
                //Philosophy
                case 30:
                    icon.setImageResource(R.drawable.philosophy3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Philosophy");
                    break;
                //Physical Education
                case 31:
                    icon.setImageResource(R.drawable.physed3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Physical Education");
                    break;
                //Physics
                case 32:
                    icon.setImageResource(R.drawable.physics);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Physics");
                    break;
                //Political Science
                case 33:
                    icon.setImageResource(R.drawable.polisci3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Political Science");
                    break;
                //Psychology
                case 34:
                    icon.setImageResource(R.drawable.psychology3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Psychology");
                    break;
                //Respiratory Therapy
                case 35:
                    icon.setImageResource(R.drawable.resp3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Respiratory Therapy");
                    break;
                //Social Work
                case 36:
                    icon.setImageResource(R.drawable.socialwork3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Social Work");
                    break;
                //Sociology
                case 37:
                    icon.setImageResource(R.drawable.sociology3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Sociology");
                    break;
                //Theatre
                case 38:
                    icon.setImageResource(R.drawable.theatre3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Theatre");
                    break;


        }

        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);

        populateListView(sectionHeader, null, titles, null, null);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }

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
            //case 0 corresponds to the 'Subject Librarian' header
            switch(i) {
                case 0:
                    items = 5;
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
                //case 1 corresponds to the 'Resources Guides' header
                case 1:
                    items = 2;
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr(); //itlAdapter = list adapter; places the titles under the header (39 times)
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
                //case 3 corresponds to the 'Resources Guides' header
                case 2:
                    items = 4;
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr(); //itlAdapter = list adapter; places the titles under the header (39 times)
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
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        webViewFragment webView;
        Intent launchBrowser;

        switch(position) {
            //Link for Accounting & Legal Studies Subject Guide
            case 1:
                //example of how to switch fragments
                //MainActivity.getNewFragTransaction().replace(R.id.content_frame, new Fragment2()).commit();

                /*
                Uri topicUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary");
                launchBrowser = new Intent(Intent.ACTION_VIEW, topicUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/acctlegal");
                fragmentTransaction.replace(R.id.content_container, webView);

                //Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
                break;
            //Link for All Accounting & Legal Studies Research Guides
            case 2:
                /*
                Uri keywordUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/keywords");
                launchBrowser = new Intent(Intent.ACTION_VIEW, keywordUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/accounting");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Link for ABI/INFORM Global
            case 3:
                /*
                Uri fbooksUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/findbooks");
                launchBrowser = new Intent(Intent.ACTION_VIEW, fbooksUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://search.proquest.com");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Link for Business Source Premier
            case 4:
                /*
                Uri articleUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/findarticles");
                launchBrowser = new Intent(Intent.ACTION_VIEW, articleUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://eds.a.ebscohost.com/ehost/search/basic?sid=87c9bc86-0e4b-44ad-b1ec-87732e9b88c6%40sessionmgr4010%vid=0&hid=4102");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Critically Evaluate Information URL
            case 5:
                /*
                Uri evaluateUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/criticallyevaluate");
                launchBrowser = new Intent(Intent.ACTION_VIEW, evaluateUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/howdoilibrary/criticallyevaluate");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Create an Annotated Bibliography URL
            case 6:
                /*
                Uri bibUrl = Uri.parse("http://libraryguides.salisbury.edu/c.php?g=327806&p=3146470");
                launchBrowser = new Intent(Intent.ACTION_VIEW, bibUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/c.php?g=327806&p=3146470");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Accounting & Legal Studies
            case 8:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(0));
                break;
            //Anthropology
            case 9:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(1));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Applied Health & Physiology
            case 10:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(2));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Art & Art History
            case 11:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(3));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Biology
            case 12:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(4));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Business
            case 13:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(5));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Chemistry
            case 14:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(6));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Communication Arts
            case 15:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(7));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Computer Science
            case 16:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(8));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Conflict Analysis & Dispute Resolution
            case 17:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(9));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Dance
            case 18:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(10));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Economics & Finance
            case 19:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(11));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Education
            case 20:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(12));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Engineering
            case 21:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(13));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //English
            case 22:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(14));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //English Language Institute
            case 23:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(15));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Environmental Studies
            case 24:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(16));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Geography & Geosciences
            case 25:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(17));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Government Information
            case 26:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(18));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Health & Sports Sciences
            case 27:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(19));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //History
            case 28:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(20));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Information & Decision Sciences
            case 29:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(21));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Interdisciplinary Studies
            case 30:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(22));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Management & Marketing
            case 31:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(23));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Mathematics
            case 32:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(24));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Medical Laboratory Science
            case 33:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(25));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Military Science
            case 34:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(26));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Modern Languages
            case 35:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(27));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Music
            case 36:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(28));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Nursing
            case 37:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(29));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Philosophy
            case 38:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(30));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Physical Education
            case 39:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(31));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Physics
            case 40:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(32));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Political Science
            case 41:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(33));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Psychology
            case 42:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(34));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Respiratory Therapy
            case 43:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(35));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Social Work
            case 44:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(36));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Sociology
            case 45:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(37));
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //Theatre
            case 46:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(38));
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }

        fragmentTransaction.addToBackStack(null).commit();
        //having this commented out lets all other subjects be selected but
        //not having it commented only lets accounting & legal studies to be selected
    }

}

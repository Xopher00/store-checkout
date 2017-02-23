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

public class SubjectDetailedFragment extends Fragment implements AdapterView.OnItemClickListener{

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
        //circle = (ImageView) view.findViewById(R.id.acc_circle);  //circle that icon is placed on
        title = (TextView) view.findViewById(R.id.acc_title);  //text displaying name of subject

       staff_icons = new LayerDrawable[8];

        //takes section headers and titles from .xml strings file
        sectionHeader = getResources().getStringArray(R.array.subject_headers);
        titles = getResources().getStringArray(R.array.subject_links);

            switch (position) {
                //Accounting & Legal Studies
                case 0:
                    //imgView.setImageResource(R.drawable.ggrobb);
                    //Referencing the XML that will create the overall header for the page
                    icon.setImageResource(R.drawable.accounting);
                    //circle.setImageResource(R.drawable.custom_circle_yellow);
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
                    //titles = getResources().getStringArray(R.array.anthro);
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.anthropology);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Anthropology");
                    break;
                //Applied Health Physiology
                case 2:
                    //titles = getResources().getStringArray(R.array.ahp);
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.ahp);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Applied Health & Physiology");
                    break;
                //Art & Art History
                case 3:
                    //titles = getResources().getStringArray(R.array.art);
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.art);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Art & Art History");
                    break;
                //Biology
                case 4:
                    //titles = getResources().getStringArray(R.array.bio);
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.biology);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Biology");
                    break;
                //Business
                case 5:
                    //titles = getResources().getStringArray(R.array.bus);
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.business);
                    //circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Business");
                    break;
                //Chemistry
                case 6:

                    //titles = getResources().getStringArray(R.array.chem);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.chemistry);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Chemistry");
                    break;
                //Communication Arts
                case 7:

                    //titles = getResources().getStringArray(R.array.comm);

                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.comm);
                    //circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Communication Arts");
                    break;
                //Computer Science
                case 8:

                    //titles = getResources().getStringArray(R.array.comp);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.compsci);
                    //circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Computer Science");
                    break;
                //Conflict Analysis & Dispute Resolution
                case 9:

                    //titles = getResources().getStringArray(R.array.cadr);

                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.cadr);
                    //circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Conflict Analysis & Dispute Resolution");
                    break;
                //Dance
                case 10:

                    //titles = getResources().getStringArray(R.array.dance);

                    //img.setImageResource(R.drawable.arprichard);
                    icon.setImageResource(R.drawable.dance);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Dance");
                    break;
                //Economics & Finance
                case 11:

                    //titles = getResources().getStringArray(R.array.econ);

                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.economy);
                   // circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Economics & Finance");
                    break;
                //Education
                case 12:

                    //titles = getResources().getStringArray(R.array.edu);

                    //img.setImageResource(R.drawable.saford);
                    icon.setImageResource(R.drawable.education);
                   // circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Education");
                    break;
                //Engineering
                case 13:

                    //titles = getResources().getStringArray(R.array.engin);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.engineering);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Engineering");
                    break;
                //English
                case 14:

                    //titles = getResources().getStringArray(R.array.engl);

                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.english);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("English");
                    break;
                //English Language Institute
                case 15:

                    //titles = getResources().getStringArray(R.array.eli);

                    //img.setImageResource(R.drawable.lhanscom);
                    icon.setImageResource(R.drawable.eli);
                   // circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("English Language Institute");
                    break;
                //Environmental Studies
                case 16:

                    //titles = getResources().getStringArray(R.array.env);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.environ);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Environmental Studies");
                    break;
                //Geography & Geosciences
                case 17:

                    //titles = getResources().getStringArray(R.array.geog);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.geog);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Geography & Geosciences");
                    break;
                //Government Information
                case 18:

                    //titles = getResources().getStringArray(R.array.govt);

                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.govt);
                   // circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Government Information");
                    break;
                //Health & Sport Sciences
                case 19:

                    //titles = getResources().getStringArray(R.array.hss);

                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.hss);
                   // circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Health & Sport Sciences");
                    break;
                //History
                case 20:

                    //titles = getResources().getStringArray(R.array.hist);

                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.history);
                  //  circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("History");
                    break;
                //Information & Decision Sciences
                case 21:

                    //titles = getResources().getStringArray(R.array.info);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.ids);
                    //circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Information & Decision Sciences");
                    break;
                //Interdisciplinary Studies
                case 22:

                    //titles = getResources().getStringArray(R.array.inter);

                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.inter);
                   // circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Interdisciplinary Studies");
                    break;
                //Management & Marketing
                case 23:

                    //titles = getResources().getStringArray(R.array.mgmt);

                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.mgmt);
                   // circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Management & Marketing");
                    break;
                //Mathematics
                case 24:

                    //titles = getResources().getStringArray(R.array.math);

                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.math);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Mathematics");
                    break;
                //Medical Laboratory Science
                case 25:

                    //titles = getResources().getStringArray(R.array.med);

                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.mls);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Medical Laboratory Science");
                    break;
                //Military Science
                case 26:

                    //titles = getResources().getStringArray(R.array.mil);

                    icon.setImageResource(R.drawable.mil);
                   // circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Military Science");
                    break;
                //Modern Languages
                case 27:

                    //titles = getResources().getStringArray(R.array.modl);

                    icon.setImageResource(R.drawable.modlang);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Modern Languages");
                    break;
                //Music
                case 28:

                    //titles = getResources().getStringArray(R.array.music);

                    icon.setImageResource(R.drawable.music);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Music");
                    break;
                //Nursing
                case 29:

                    //titles = getResources().getStringArray(R.array.nurse);

                    icon.setImageResource(R.drawable.nursing);
                   // circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Nursing");
                    break;
                //Philosophy
                case 30:

                    //titles = getResources().getStringArray(R.array.phil);

                    icon.setImageResource(R.drawable.philosophy);
                    //circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Philosophy");
                    break;
                //Physical Education
                case 31:

                    //titles = getResources().getStringArray(R.array.physed);

                    icon.setImageResource(R.drawable.physed);
                   // circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Physical Education");
                    break;
                //Physics
                case 32:

                    //titles = getResources().getStringArray(R.array.phys);

                    icon.setImageResource(R.drawable.physics);
                    //circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Physics");
                    break;
                //Political Science
                case 33:

                    //titles = getResources().getStringArray(R.array.polit);

                    icon.setImageResource(R.drawable.polisci);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Political Science");
                    break;
                //Psychology
                case 34:

                    //titles = getResources().getStringArray(R.array.psych);

                    icon.setImageResource(R.drawable.psychology);
                    //circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Psychology");
                    break;
                //Respiratory Therapy
                case 35:

                    //titles = getResources().getStringArray(R.array.resp);

                    icon.setImageResource(R.drawable.resp);
                    //circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Respiratory Therapy");
                    break;
                //Social Work
                case 36:

                    //titles = getResources().getStringArray(R.array.soc);

                    icon.setImageResource(R.drawable.socialwork);
                    //circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Social Work");
                    break;
                //Sociology
                case 37:

                    //titles = getResources().getStringArray(R.array.socio);

                    icon.setImageResource(R.drawable.sociology);
                    //circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Sociology");
                    break;
                //Theatre
                case 38:

                    //titles = getResources().getStringArray(R.array.thea);

                    icon.setImageResource(R.drawable.theatre);
                   // circle.setImageResource(R.drawable.custom_circle_blue);
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


        }

        fragmentTransaction.addToBackStack(null).commit();
        //having this commented out lets all other subjects be selected but
        //not having it commented only lets accounting & legal studies to be selected
    }

}

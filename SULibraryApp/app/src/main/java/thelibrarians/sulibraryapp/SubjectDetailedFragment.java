package thelibrarians.sulibraryapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class SubjectDetailedFragment extends Fragment implements AdapterView.OnItemClickListener{

    static int tab;
    View view;
    ImageView imgView, icon,rectangle,circle;
    TextView title;
    LayerDrawable[] staff_icons;
    ListView listView;
    ImgTxtListAdapter itlAdapter;
    String[] sectionHeader,titles,database_names,database_urls;
    DrawerToggleListener toggleListener;
    Integer[] databases;
    ListView listViewsrr;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    int num_research_guides;

    public SubjectDetailedFragment() {}
    public SubjectDetailedFragment(int pos){
        tab = pos;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.subject_detailed, container, false);
        icon = (ImageView) view.findViewById(R.id.acc_icon);  //icon for the subject
        rectangle = (ImageView) view.findViewById(R.id.acc_header);  //rectangle portion
        title = (TextView) view.findViewById(R.id.acc_title);  //text displaying name of subject
        databases = new Integer[]{};
        database_names = getResources().getStringArray(R.array.database_name);
        database_urls = getResources().getStringArray(R.array.database_url);
        sectionHeader = getResources().getStringArray(R.array.subject_headers);

        num_research_guides = 2;
            switch (tab) {
                //Accounting & Legal Studies
                case 0:
                    //Referencing the XML that will create the overall header for the page
                    icon.setImageResource(R.drawable.accounting);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Accounting & Legal Studies");
                    titles = getResources().getStringArray(R.array.acct);
                    databases= new Integer[]{0,1,2,3};
                    break;
                //Anthropology
                case 1:
                    titles = getResources().getStringArray(R.array.anthro);
                    icon.setImageResource(R.drawable.anthropology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Anthropology");
                    titles = getResources().getStringArray(R.array.anthro);
                    databases=new Integer[]{4,5,6};
                    break;
                //Applied Health Physiology
                case 2:
                    titles = getResources().getStringArray(R.array.ahp);
                    icon.setImageResource(R.drawable.ahp);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Applied Health & Physiology");
                    databases=new Integer[]{7,8,9,10,11,12,13,14};
                    break;
                //Art & Art History
                case 3:
                    titles = getResources().getStringArray(R.array.art);
                    icon.setImageResource(R.drawable.art);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Art & Art History");
                    databases = new Integer[]{15,16,17};
                    break;
                //Biology
                case 4:
                    titles = getResources().getStringArray(R.array.bio);
                    icon.setImageResource(R.drawable.biology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Biology");
                    databases = new Integer[]{19,20,21,4,14,15};
                    break;
                //Business
                case 5:
                    titles = getResources().getStringArray(R.array.bus);
                    icon.setImageResource(R.drawable.business);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Business");
                    databases = new Integer[]{0, 22,1, 2, 23};
                    break;
                //Chemistry
                case 6:
                    icon.setImageResource(R.drawable.chemistry);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Chemistry");
                    titles = getResources().getStringArray(R.array.chem);
                    databases = new Integer[]{24,21,14,25,15};
                    break;
                //Communication Arts
                case 7:
                    titles = getResources().getStringArray(R.array.comm);
                    icon.setImageResource(R.drawable.comm);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Communication Arts");
                    databases = new Integer[]{26,27,4,6};
                    break;
                //Computer Science
                case 8:
                    titles = getResources().getStringArray(R.array.comp);
                    icon.setImageResource(R.drawable.compsci);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Computer Science");
                    databases = new Integer[]{4,14,15};
                    break;
                //Conflict Analysis & Dispute Resolution
                case 9:
                    titles = getResources().getStringArray(R.array.cadr);
                    icon.setImageResource(R.drawable.cadr);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Conflict Analysis & Dispute Resolution");
                    databases = new Integer[]{7, 26,4,28};
                    break;
                //Dance
                case 10:
                    titles = getResources().getStringArray(R.array.dance);
                    icon.setImageResource(R.drawable.dance);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Dance");
                    databases = new Integer[]{7,29,4};
                    break;
                //Economics & Finance
                case 11:
                    titles = getResources().getStringArray(R.array.econ);
                    icon.setImageResource(R.drawable.economy);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Economics & Finance");
                    databases = new Integer[]{0,1, 30,23, 31};
                    break;
                //Education
                case 12:
                    titles = getResources().getStringArray(R.array.edu);
                    icon.setImageResource(R.drawable.education);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Education");
                    databases = new Integer[]{32,33,34,35,36,37};
                    break;
                //Engineering
                case 13:
                    titles = getResources().getStringArray(R.array.engin);
                    icon.setImageResource(R.drawable.engineering);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Engineering");
                    databases = new Integer[]{7,38,4,14,15};
                    break;
                //English
                case 14:
                    titles = getResources().getStringArray(R.array.engl);
                    icon.setImageResource(R.drawable.english);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("English");
                    databases = new Integer[]{};
                    break;
                //English Language Institute
                case 15:
                    titles = getResources().getStringArray(R.array.eli);
                    icon.setImageResource(R.drawable.eli);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("English Language Institute");
                    break;
                //Environmental Studies
                case 16:
                    titles = getResources().getStringArray(R.array.env);
                    icon.setImageResource(R.drawable.environ);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Environmental Studies");
                    break;
                //Geography & Geosciences
                case 17:
                    titles = getResources().getStringArray(R.array.geog);
                    icon.setImageResource(R.drawable.geog);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Geography & Geosciences");
                    break;
                //Government Information
                case 18:
                    titles = getResources().getStringArray(R.array.govt);
                    icon.setImageResource(R.drawable.govt);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Government Information");
                    titles = getResources().getStringArray(R.array.govt);
                    databases = new Integer[]{};
                    break;
                //Health & Sport Sciences
                case 19:
                    titles = getResources().getStringArray(R.array.hss);
                    icon.setImageResource(R.drawable.hss);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Health & Sport Sciences");
                    break;
                //History
                case 20:
                    titles = getResources().getStringArray(R.array.hist);
                    icon.setImageResource(R.drawable.history);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("History");
                    break;
                //Information & Decision Sciences
                case 21:
                    titles = getResources().getStringArray(R.array.info);
                    icon.setImageResource(R.drawable.ids);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Information & Decision Sciences");
                    break;
                //Interdisciplinary Studies
                case 22:
                    titles = getResources().getStringArray(R.array.inter);
                    icon.setImageResource(R.drawable.inter);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Interdisciplinary Studies");
                    titles = getResources().getStringArray(R.array.inter);
                    databases = new Integer[]{7,4,48};
                    num_research_guides = 1;
                    break;
                //Management & Marketing
                case 23:

                    titles = getResources().getStringArray(R.array.mgmt);
                    icon.setImageResource(R.drawable.mgmt);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Management & Marketing");
                    break;
                //Mathematics
                case 24:
                    titles = getResources().getStringArray(R.array.math);
                    icon.setImageResource(R.drawable.math);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Mathematics");
                    break;
                //Medical Laboratory Science
                case 25:
                    titles = getResources().getStringArray(R.array.med);
                    icon.setImageResource(R.drawable.mls);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Medical Laboratory Science");
                    break;
                //Military Science
                case 26:
                    titles = getResources().getStringArray(R.array.mil);
                    icon.setImageResource(R.drawable.mil);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Military Science");
                    break;
                //Modern Languages
                case 27:
                    titles = getResources().getStringArray(R.array.modl);
                    icon.setImageResource(R.drawable.modlang);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Modern Languages");
                    break;
                //Music
                case 28:
                    titles = getResources().getStringArray(R.array.music);
                    icon.setImageResource(R.drawable.music);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Music");
                    break;
                //Nursing
                case 29:
                    titles = getResources().getStringArray(R.array.nurse);
                    icon.setImageResource(R.drawable.nursing);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Nursing");
                    break;
                //Philosophy
                case 30:
                    titles = getResources().getStringArray(R.array.phil);
                    icon.setImageResource(R.drawable.philosophy);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Philosophy");
                    break;
                //Physical Education
                case 31:
                    titles = getResources().getStringArray(R.array.physed);
                    icon.setImageResource(R.drawable.physed);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Physical Education");
                    num_research_guides = 1;
                    break;
                //Physics
                case 32:
                    titles = getResources().getStringArray(R.array.phys);
                    icon.setImageResource(R.drawable.physics);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Physics");
                    break;
                //Political Science
                case 33:
                    titles = getResources().getStringArray(R.array.polit);
                    icon.setImageResource(R.drawable.polisci);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Political Science");
                    break;
                //Psychology
                case 34:
                    titles = getResources().getStringArray(R.array.psych);
                    icon.setImageResource(R.drawable.psychology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Psychology");
                    break;
                //Respiratory Therapy
                case 35:
                    titles = getResources().getStringArray(R.array.resp);
                    icon.setImageResource(R.drawable.resp);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Respiratory Therapy");
                    break;
                //Social Work
                case 36:
                    titles = getResources().getStringArray(R.array.soc);
                    icon.setImageResource(R.drawable.socialwork);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Social Work");
                    break;
                //Sociology
                case 37:
                    titles = getResources().getStringArray(R.array.socio);
                    icon.setImageResource(R.drawable.sociology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Sociology");
                    break;
                //Theatre
                case 38:
                    titles = getResources().getStringArray(R.array.thea);
                    icon.setImageResource(R.drawable.theatre);
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
        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();
        listViewsrr = (ListView) view.findViewById(R.id.subject_list); // Assigns listview
        listViewsrr.setOnItemClickListener(this);
        for(int i=0; i<sectionHeader.length; i++){
            switch(i) {
                case 0:
                    ListItem0 li = new ListItem0(getActivity(), titles[i]);
                    li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                    li.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
                    listItems.add(li);
                    position++;
                    String[] sectionTitles = getResources().getStringArray(R.array.subject_detailed_constant);
                    for(int j = 0; j < 4; j++) {
                        listItems.add(new ListItem3(getActivity(), sectionTitles[j],titles[j+1]));
                        position++;
                    }
                    break;
                case 1:
                    ListItem0 li2 = new ListItem0(getActivity(), sectionHeader[i]);
                    li2.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                    li2.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
                    listItems.add(li2);
                    for(int j = 0; j < 2; j++) {
                        if(titles[position].compareTo("") != 0) {
                            listItems.add(new ListItem0(getActivity(), titles[position]));
                        }
                        position++;
                    }
                    break;
                case 2:
                    if(databases.length != 0) {
                        ListItem0 li3 = new ListItem0(getActivity(), sectionHeader[i]);
                        li3.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                        li3.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
                        listItems.add(li3);
                        position++;
                        for (int j = 0; j < databases.length; j++) {
                            listItems.add(new ListItem0(getActivity(), database_names[databases[j]]));
                            position++;
                        }
                    }
                    break;
            }
        }
        lix.populate(listItems);
        listViewsrr.setAdapter(lix);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position > 0 && position < 5){
            switch (position) {
                case 2:
                    Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                    dialer.setData(Uri.parse("tel:" + titles[2])); // Passes URI to intent
                    startActivity(dialer); // Starts activity
                    break;
                case 3:
                    Intent emailer = new Intent(Intent.ACTION_SEND);
                    emailer.setType("message/rfc822");
                    emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailer.setType("vnd.android.cursor.item/email");
                    emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{titles[3]});
                    try {
                        startActivity(Intent.createChooser(emailer, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
        else if (position > 5){
            String database_url;
            webViewFragment webview;
            FragmentTransaction ft;
            switch(num_research_guides){
                case 0:
                    database_url = database_urls[databases[position - 6]];
                    webview = new webViewFragment(database_url);
                    ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_container, webview);
                    ft.addToBackStack(null).commit();
                    break;
                case 1:
                    if(position == 6){
                        String research_url = new String();
                        if(titles[7].compareTo("") == 0)
                            research_url = titles[8];
                        else if(titles[8].compareTo("") == 0)
                            research_url = titles[7];
                        webview = new webViewFragment(research_url);
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                    }
                    else if(position > 7){
                        database_url = database_urls[databases[position - 8]];
                        webview = new webViewFragment(database_url);
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                    }
                    break;
                case 2:
                    if(position == 6){
                        String research_url = titles[7];
                        webview = new webViewFragment(research_url);
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                    }
                    else if(position == 7){
                        String research_url = titles[8];
                        webview = new webViewFragment(research_url);
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                    }
                    else if(position > 8){
                        database_url = database_urls[databases[position - 9]];
                        webview = new webViewFragment(database_url);
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                    }
                    break;
            }
        }
    }
}

package thelibrarians.sulibraryapp;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.res.Resources.*;

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

    //referencing the fragment_research_help.xml in the ResearchHelpFragment.java
    ListView listView;
    //Array for section headers
    //Referencing jessica_strings.xml
    String[] sectionHeader;
    //Array for the items under each of the headers
    //Referencing the jessica_strings.xml
    String[] titles;
    //string of icons that will be next to each title
    LayerDrawable[] icons;
    ImgTxtListAdapter itlAdapter;
    int[] backgroundImage = {};
    int[] overLayImage = {};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_research_help, container, false);

        Resources r = getResources();

        icons = new LayerDrawable[45];

        sectionHeader = getResources().getStringArray(R.array.research_headers);
        titles = getResources().getStringArray(R.array.resources_titles);

        itlAdapter = new ImgTxtListAdapter(getActivity());

       listView = (ListView) view.findViewById(R.id.listView); //need to be able to access an xml element with java so that you can modify it dynamically

        //add and call populateListView()
        //first null = subtitles
        //second null = notes(i.e. room reservations has a text on the right: not reservable, reservable)


        listView.setAdapter(itlAdapter);
        listView.setOnItemClickListener(this);

        //LIBRARY BASICS ICONS

        //Research Topic Icon
        Drawable[] topicLayer; //creates an array of layers for each icon
        topicLayer = new Drawable[2];
        topicLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
        topicLayer[1] = r.getDrawable(R.drawable.topic); //R = abstraction to the file system
        LayerDrawable layerDrawable = new LayerDrawable(topicLayer); //merges the two layers together
        icons[0] = layerDrawable;

        //Develop Keywords Icon
        Drawable[] keywordLayer; //creates an array of layers for each icon
        keywordLayer = new Drawable[2];
        keywordLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
        keywordLayer[1] = r.getDrawable(R.drawable.keywords); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(keywordLayer); //merges the two layers together
        icons[1] = layerDrawable;

      //  String keyword_url = new String(getResources().getString(R.string.kw_url));

        //Find Books & eBooks Icon
        Drawable[] findbookLayer; //creates an array of layers for each icon
        findbookLayer = new Drawable[2];
        findbookLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
        findbookLayer[1] = r.getDrawable(R.drawable.books); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(findbookLayer); //merges the two layers together
        icons[2] = layerDrawable;

      //  String books_url = new String(getResources().getString(R.string.book_url));

        //Find Articles Icon
        Drawable[] findArticles; //creates an array of layers for each icon
        findArticles = new Drawable[2];
        findArticles[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
        findArticles[1] = r.getDrawable(R.drawable.articles); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(findArticles); //merges the two layers together
        icons[3] = layerDrawable;

     //   String articles_url = new String(getResources().getString(R.string.article_url));

        //Critically Evaluate Information Icon
        Drawable[] evaluateLayer; //creates an array of layers for each icon
        evaluateLayer = new Drawable[2];
        evaluateLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
        evaluateLayer[1] = r.getDrawable(R.drawable.evaluate); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(evaluateLayer); //merges the two layers together
        icons[4] = layerDrawable;

    //    String eval_url = new String(getResources().getString(R.string.evaluate_url));

        //Create Annotated Bibliography Icon
        Drawable[] bibLayer; //creates an array of layers for each icon
        bibLayer = new Drawable[2];
        bibLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
        bibLayer[1] = r.getDrawable(R.drawable.bibliography); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(bibLayer); //merges the two layers together
        icons[5] = layerDrawable;

    //    String bibliography_url = new String(getResources().getString(R.string.bib_url));

        //RESOURCES BY SUBJECT ICONS

        //Create Accounting & Legal Studies Icon
        Drawable[] accountLayer; //creates an array of layers for each icon
        accountLayer = new Drawable[2];
        accountLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
        accountLayer[1] = r.getDrawable(R.drawable.accounting); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(accountLayer); //merges the two layers together
        icons[6] = layerDrawable;

        //Create Anthropology Icon
        Drawable[] anthroLayer; //creates an array of layers for each icon
        anthroLayer = new Drawable[2];
        anthroLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        anthroLayer[1] = r.getDrawable(R.drawable.anthropology); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(anthroLayer); //merges the two layers together
        icons[7] = layerDrawable;

        //Create Applied Healh Physiology Icon
        Drawable[] ahpLayer; //creates an array of layers for each icon
        ahpLayer= new Drawable[2];
        ahpLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        ahpLayer[1] = r.getDrawable(R.drawable.ahp); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(ahpLayer); //merges the two layers together
        icons[8] = layerDrawable;

        //Create Art & Art History Icon
        Drawable[] artLayer; //creates an array of layers for each icon
        artLayer = new Drawable[2];
        artLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        artLayer[1] = r.getDrawable(R.drawable.art); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(artLayer); //merges the two layers together
        icons[9] = layerDrawable;

        //Create Biology Icon
        Drawable[] bioLayer; //creates an array of layers for each icon
        bioLayer = new Drawable[2];
        bioLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        bioLayer[1] = r.getDrawable(R.drawable.biology); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(bioLayer); //merges the two layers together
        icons[10] = layerDrawable;

        //Create Business Icon
        Drawable[] busLayer; //creates an array of layers for each icon
        busLayer = new Drawable[2];
        busLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
        busLayer[1] = r.getDrawable(R.drawable.business); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(busLayer); //merges the two layers together
        icons[11] = layerDrawable;

        //Create Chemistry Icon
        Drawable[] chemLayer; //creates an array of layers for each icon
        chemLayer = new Drawable[2];
        chemLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        chemLayer[1] = r.getDrawable(R.drawable.chemistry); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(chemLayer); //merges the two layers together
        icons[12] = layerDrawable;

        //Create Communication Arts Icon
        Drawable[] commLayer; //creates an array of layers for each icon
        commLayer = new Drawable[2];
        commLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        commLayer[1] = r.getDrawable(R.drawable.comm); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(commLayer); //merges the two layers together
        icons[13] = layerDrawable;

        //Create Computer Science Icon
        Drawable[] csLayer; //creates an array of layers for each icon
        csLayer = new Drawable[2];
        csLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        csLayer[1] = r.getDrawable(R.drawable.compsci); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(csLayer); //merges the two layers together
        icons[14] = layerDrawable;

        //Create CADR Icon
        Drawable[] cadrLayer; //creates an array of layers for each icon
        cadrLayer = new Drawable[2];
        cadrLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        cadrLayer[1] = r.getDrawable(R.drawable.cadr); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(cadrLayer); //merges the two layers together
        icons[15] = layerDrawable;

        //Create Dance Icon
        Drawable[] danceLayer; //creates an array of layers for each icon
        danceLayer = new Drawable[2];
        danceLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        danceLayer[1] = r.getDrawable(R.drawable.dance); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(danceLayer); //merges the two layers together
        icons[16] = layerDrawable;

        //Create Economics & Finance Icon
        Drawable[] econLayer; //creates an array of layers for each icon
        econLayer = new Drawable[2];
        econLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
        econLayer[1] = r.getDrawable(R.drawable.econ); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(econLayer); //merges the two layers together
        icons[17] = layerDrawable;

        //Create Education Icon
        Drawable[] eduLayer; //creates an array of layers for each icon
        eduLayer = new Drawable[2];
        eduLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
        eduLayer[1] = r.getDrawable(R.drawable.education); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(eduLayer); //merges the two layers together
        icons[18] = layerDrawable;

        //Create Engineering Icon
        Drawable[] enginLayer; //creates an array of layers for each icon
        enginLayer = new Drawable[2];
        enginLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        enginLayer[1] = r.getDrawable(R.drawable.engineering); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(enginLayer); //merges the two layers together
        icons[19] = layerDrawable;

        //Create English Icon
        Drawable[] englLayer; //creates an array of layers for each icon
        englLayer = new Drawable[2];
        englLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        englLayer[1] = r.getDrawable(R.drawable.english); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(busLayer); //merges the two layers together
        icons[20] = layerDrawable;

        //Create English Language Institute Icon
        Drawable[] eliLayer; //creates an array of layers for each icon
        eliLayer = new Drawable[2];
        eliLayer[0] = r.getDrawable(R.drawable.custom_circle_red); //r = real runtime object that you can use to call getDrawable method
        eliLayer[1] = r.getDrawable(R.drawable.eli); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(eliLayer); //merges the two layers together
        icons[21] = layerDrawable;

        //Create Environmental Studies Icon
        Drawable[] envLayer; //creates an array of layers for each icon
        envLayer = new Drawable[2];
        envLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        envLayer[1] = r.getDrawable(R.drawable.environ); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(envLayer); //merges the two layers together
        icons[22] = layerDrawable;

        //Create Geography and Geosciences Icon
        Drawable[] geoLayer; //creates an array of layers for each icon
        geoLayer = new Drawable[2];
        geoLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        geoLayer[1] = r.getDrawable(R.drawable.geog); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(geoLayer); //merges the two layers together
        icons[23] = layerDrawable;

        //Create Government Information Icon
        Drawable[] govLayer; //creates an array of layers for each icon
        govLayer = new Drawable[2];
        govLayer[0] = r.getDrawable(R.drawable.custom_circle_red); //r = real runtime object that you can use to call getDrawable method
        govLayer[1] = r.getDrawable(R.drawable.govt); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(govLayer); //merges the two layers together
        icons[24] = layerDrawable;

        //Create Health and Sports Sciences Icon
        Drawable[] hssLayer; //creates an array of layers for each icon
        hssLayer = new Drawable[2];
        hssLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
        hssLayer[1] = r.getDrawable(R.drawable.hss); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(hssLayer); //merges the two layers together
        icons[25] = layerDrawable;

        //Create History Icon
        Drawable[] hisLayer; //creates an array of layers for each icon
        hisLayer = new Drawable[2];
        hisLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        hisLayer[1] = r.getDrawable(R.drawable.history); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(hisLayer); //merges the two layers together
        icons[26] = layerDrawable;

        //Create Information & Decision Sciences Icon
        Drawable[] infoLayer; //creates an array of layers for each icon
        infoLayer = new Drawable[2];
        infoLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
        infoLayer[1] = r.getDrawable(R.drawable.ids); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(infoLayer); //merges the two layers together
        icons[27] = layerDrawable;

        //Create Interdisciplinary Studies Icon
        Drawable[] interLayer; //creates an array of layers for each icon
        interLayer = new Drawable[2];
        interLayer[0] = r.getDrawable(R.drawable.custom_circle_red); //r = real runtime object that you can use to call getDrawable method
        interLayer[1] = r.getDrawable(R.drawable.inter); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(interLayer); //merges the two layers together
        icons[28] = layerDrawable;

        //Create Management and Marketing Icon
        Drawable[] manageLayer; //creates an array of layers for each icon
        manageLayer = new Drawable[2];
        manageLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
        manageLayer[1] = r.getDrawable(R.drawable.mgmt); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(manageLayer); //merges the two layers together
        icons[29] = layerDrawable;

        //Create Math Icon
        Drawable[] mathLayer; //creates an array of layers for each icon
        mathLayer = new Drawable[2];
        mathLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        mathLayer[1] = r.getDrawable(R.drawable.math); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(mathLayer); //merges the two layers together
        icons[30] = layerDrawable;

        //Create Medical Lab Science Icon
        Drawable[] medLayer; //creates an array of layers for each icon
        medLayer = new Drawable[2];
        medLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        medLayer[1] = r.getDrawable(R.drawable.mls); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(medLayer); //merges the two layers together
        icons[31] = layerDrawable;

        //Create Military Science Icon
        Drawable[] militaryLayer; //creates an array of layers for each icon
        militaryLayer = new Drawable[2];
        militaryLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
        militaryLayer[1] = r.getDrawable(R.drawable.mil); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(militaryLayer); //merges the two layers together
        icons[32] = layerDrawable;

        //Create Modern Languages Icon
        Drawable[] modernLayer; //creates an array of layers for each icon
        modernLayer = new Drawable[2];
        modernLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        modernLayer[1] = r.getDrawable(R.drawable.modlang); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(modernLayer); //merges the two layers together
        icons[33] = layerDrawable;

        //Create Music Icon
        Drawable[] musicLayer; //creates an array of layers for each icon
        musicLayer = new Drawable[2];
        musicLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        musicLayer[1] = r.getDrawable(R.drawable.music); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(musicLayer); //merges the two layers together
        icons[34] = layerDrawable;

        //Create Nursing Icon
        Drawable[] nursingLayer; //creates an array of layers for each icon
        nursingLayer = new Drawable[2];
        nursingLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        nursingLayer[1] = r.getDrawable(R.drawable.nursing); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(nursingLayer); //merges the two layers together
        icons[35] = layerDrawable;

        //Create Philosophy Icon
        Drawable[] philLayer; //creates an array of layers for each icon
        philLayer = new Drawable[2];
        philLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        philLayer[1] = r.getDrawable(R.drawable.philosophy); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(philLayer); //merges the two layers together
        icons[36] = layerDrawable;

        //Create Physical Education Icon
        Drawable[] physedLayer; //creates an array of layers for each icon
        physedLayer = new Drawable[2];
        physedLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
        physedLayer[1] = r.getDrawable(R.drawable.physed); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(physedLayer); //merges the two layers together
        icons[37] = layerDrawable;

        //Create Physics Icon
        Drawable[] physicsLayer; //creates an array of layers for each icon
        physicsLayer = new Drawable[2];
        physicsLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        physicsLayer[1] = r.getDrawable(R.drawable.physics); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(physicsLayer); //merges the two layers together
        icons[38] = layerDrawable;

        //Create Political Science Icon
        Drawable[] poliLayer; //creates an array of layers for each icon
        poliLayer = new Drawable[2];
        poliLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        poliLayer[1] = r.getDrawable(R.drawable.polisci); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(poliLayer); //merges the two layers together
        icons[39] = layerDrawable;

        //Create Psychology Icon
        Drawable[] psychLayer; //creates an array of layers for each icon
        psychLayer = new Drawable[2];
        psychLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        psychLayer[1] = r.getDrawable(R.drawable.psychology); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(psychLayer); //merges the two layers together
        icons[40] = layerDrawable;

        //Create Respiratory Therapy Icon
        Drawable[] resLayer; //creates an array of layers for each icon
        resLayer = new Drawable[2];
        resLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
        resLayer[1] = r.getDrawable(R.drawable.resp); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(resLayer); //merges the two layers together
        icons[41] = layerDrawable;

        //Create Social Work Icon
        Drawable[] socialLayer; //creates an array of layers for each icon
        socialLayer = new Drawable[2];
        socialLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
        socialLayer[1] = r.getDrawable(R.drawable.socialwork); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(socialLayer); //merges the two layers together
        icons[42] = layerDrawable;

        //Create Sociology Icon
        Drawable[] socLayer; //creates an array of layers for each icon
        socLayer = new Drawable[2];
        socLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        socLayer[1] = r.getDrawable(R.drawable.sociology); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(socLayer); //merges the two layers together
        icons[43] = layerDrawable;

        //Create Theatre Icon
        Drawable[] theatreLayer; //creates an array of layers for each icon
        theatreLayer = new Drawable[2];
        theatreLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
        theatreLayer[1] = r.getDrawable(R.drawable.theatre); //R = abstraction to the file system
        layerDrawable = new LayerDrawable(theatreLayer); //merges the two layers together
        icons[44] = layerDrawable;

        populateListView(sectionHeader, icons, titles, null, null);

        return view;
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
            //case 0 corresponds to the 'Library Basics' header
            switch(i) {
                case 0:
                    items = 6;
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
                //case 1 corresponds to the 'Resources by Subject' header
                case 1:
                    items = 39;
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

    //when we click on the item to take you to a the next page
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(position) {
            //Select a Research Topic URL
            case 1:
                //example of how to switch fragments
                //MainActivity.getNewFragTransaction().replace(R.id.content_frame, new Fragment2()).commit();

                Uri topicUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, topicUrl);
                startActivity(launchBrowser);

                //Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
                break;
            //Develop Keywords URL
            case 2:
                Uri keywordUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/keywords");
                launchBrowser = new Intent(Intent.ACTION_VIEW, keywordUrl);
                startActivity(launchBrowser);
                break;
            //Find Books & eBooks URL
            case 3:
                Uri fbooksUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/findbooks");
                launchBrowser = new Intent(Intent.ACTION_VIEW, fbooksUrl);
                startActivity(launchBrowser);
                break;
            //Find Articles URL
            case 4:
                Uri articleUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/findarticles");
                launchBrowser = new Intent(Intent.ACTION_VIEW, articleUrl);
                startActivity(launchBrowser);
                break;
            //Critically Evaluate Information URL
            case 5:
                Uri evaluateUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/criticallyevaluate");
                launchBrowser = new Intent(Intent.ACTION_VIEW, evaluateUrl);
                startActivity(launchBrowser);
                break;
            //Create an Annotated Bibliography URL
            case 6:
                Uri bibUrl = Uri.parse("http://libraryguides.salisbury.edu/c.php?g=327806&p=3146470");
                launchBrowser = new Intent(Intent.ACTION_VIEW, bibUrl);
                startActivity(launchBrowser);
                break;
            case 8:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(0));
                fragmentTransaction.addToBackStack(null).commit();
                break;


        }
    }
}

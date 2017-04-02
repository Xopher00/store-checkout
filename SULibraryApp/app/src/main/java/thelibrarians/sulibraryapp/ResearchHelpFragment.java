package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.res.Resources.*;

public class ResearchHelpFragment extends Fragment implements AdapterView.OnItemClickListener {

    //referencing the fragment_research_help.xml in the ResearchHelpFragment.java
    ListView listView;
    //Array for section headers
    //Referencing jessica_strings.xml
    //String[] sectionHeader;
    //Array for the items under each of the headers
    //Referencing the jessica_strings.xml
    //String[] titles;
    //string of icons that will be next to each title
    Drawable[] icons;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    int[] backgroundImage = {};
    int[] overLayImage = {};
    String[] strings; //sequential list of strings as they appear in the listview
    int[] views; //ints that correspond to view layouts in the listview
    final int NON_HEADERS = 45;
    final int HEADERS = 2;
    ImageView icon;
    TextView loading_msg;
    Activity activity;
    ActionBar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_research_help, container, false);

        Resources r = getResources();
		
		lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();
        activity = getActivity();

        loading_msg = (TextView) view.findViewById(R.id.research_list_loading);
        loading_msg.setVisibility(View.VISIBLE);

        //icons = new Drawable[NON_HEADERS];
        //icon = (ImageView) view.findViewById(R.id.acc_icon);  //icon for the subject
        //strings = new String[NON_HEADERS + HEADERS];
        //views = new int[NON_HEADERS + HEADERS];

        //sectionHeader = getResources().getStringArray(R.array.research_headers);
        //titles = getResources().getStringArray(R.array.resources_titles);

        listView = (ListView) view.findViewById(R.id.listView); //need to be able to access an xml element with java so that you can modify it dynamically

        listView.setVisibility(View.INVISIBLE);
        //add and call populateListView()
        //first null = subtitles
        //second null = notes(i.e. room reservations has a text on the right: not reservable, reservable)


        //LIBRARY BASICS ICONS
        //strings[0] = r.getString(R.string.lib_basic);
        //views[0] = 0;
		ListItem0 li0 = new ListItem0(activity, r.getString(R.string.lib_basic));
        //li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(r, R.color.colorPrimary, null));
        li0.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
        li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listItems.add(li0);


        //Research Topic Icon
//        Drawable[] topicLayer; //creates an array of layers for each icon
//        topicLayer = new Drawable[2];
//        topicLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
//        topicLayer[1] = r.getDrawable(R.drawable.topic); //R = abstraction to the file system
//        LayerDrawable layerDrawable = new LayerDrawable(topicLayer); //merges the two layers together
        // icons[0] = getResources().getDrawable(R.drawable.research);
        // strings[1] = r.getString(R.string.topic);
        // views[1] = 1;
		listItems.add(new ListItem1(activity, R.drawable.research, r.getString(R.string.topic)));

        //Develop Keywords Icon
//        Drawable[] keywordLayer; //creates an array of layers for each icon
//        keywordLayer = new Drawable[2];
//        keywordLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
//        keywordLayer[1] = r.getDrawable(R.drawable.keywords); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(keywordLayer); //merges the two layers together
        // icons[1] = getResources().getDrawable(R.drawable.keyword);
        // strings[2] = r.getString(R.string.keywords);
        // views[2] = 1;
		listItems.add(new ListItem1(activity, R.drawable.keyword, r.getString(R.string.keywords)));

      //  String keyword_url = new String(getResources().getString(R.string.kw_url));

        //Find Books & eBooks Icon
 //       Drawable[] findbookLayer; //creates an array of layers for each icon
 //       findbookLayer = new Drawable[2];
 //       findbookLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
 //       findbookLayer[1] = r.getDrawable(R.drawable.books); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(findbookLayer); //merges the two layers together
        // icons[2] = getResources().getDrawable(R.drawable.book);
        // strings[3] = r.getString(R.string.ebook);
        // views[3] = 1;
		listItems.add(new ListItem1(activity, R.drawable.book, r.getString(R.string.ebook)));

      //  String books_url = new String(getResources().getString(R.string.book_url));

        //Find Articles Icon
 //       Drawable[] findArticles; //creates an array of layers for each icon
 //       findArticles = new Drawable[2];
 //       findArticles[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
 //       findArticles[1] = r.getDrawable(R.drawable.articles); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(findArticles); //merges the two layers together
        // icons[3] = getResources().getDrawable(R.drawable.articles);
        // strings[4] = r.getString(R.string.article);
        // views[4] = 1;
		listItems.add(new ListItem1(activity, R.drawable.articles, r.getString(R.string.article)));

     //   String articles_url = new String(getResources().getString(R.string.article_url));

        //Critically Evaluate Information Icon
 //       Drawable[] evaluateLayer; //creates an array of layers for each icon
 //       evaluateLayer = new Drawable[2];
 //       evaluateLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
 //       evaluateLayer[1] = r.getDrawable(R.drawable.evaluate); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(evaluateLayer); //merges the two layers together
        // icons[4] = getResources().getDrawable(R.drawable.evaluate);
        // strings[5] = r.getString(R.string.eval_info);
        // views[5] = 1;
		listItems.add(new ListItem1(activity, R.drawable.evaluate, r.getString(R.string.eval_info)));

    //    String eval_url = new String(getResources().getString(R.string.evaluate_url));

        //Create Annotated Bibliography Icon
 //       Drawable[] bibLayer; //creates an array of layers for each icon
 //       bibLayer = new Drawable[2];
 //       bibLayer[0] = r.getDrawable(R.drawable.custom_circle_primary); //r = real runtime object that you can use to call getDrawable method
 //       bibLayer[1] = r.getDrawable(R.drawable.bibliography); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(bibLayer); //merges the two layers together
        // icons[5] = getResources().getDrawable(R.drawable.bib);
        // strings[6] = r.getString(R.string.bib);
        // views[6] = 1;
		listItems.add(new ListItem1(activity, R.drawable.bib, r.getString(R.string.bib)));

    //    String bibliography_url = new String(getResources().getString(R.string.bib_url));

        //RESOURCES BY SUBJECT ICONS
        // views[7] = 0;
        // strings[7] = r.getString(R.string.res_subj);
		li0 = new ListItem0(activity, r.getString(R.string.res_subj));
        //li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(r, R.color.colorPrimary, null));
        li0.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
        li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listItems.add(li0);

        //Create Accounting & Legal Studies Icon
       // icon.setImageResource(R.drawable.accounting);
        // icons[6] = getResources().getDrawable(R.drawable.accounting);
        // strings[8] = r.getString(R.string.legal);
        // views[8] = 1;
		listItems.add(new ListItem1(activity, R.drawable.accounting, r.getString(R.string.legal)));

        //Create Anthropology Icon
        //Drawable[] anthroLayer; //creates an array of layers for each icon
//        anthroLayer = new Drawable[2];
//        anthroLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        anthroLayer[1] = r.getDrawable(R.drawable.anthropology); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(anthroLayer); //merges the two layers together
        // icons[7] = getResources().getDrawable(R.drawable.anthropology);
        // strings[9] = r.getString(R.string.anthro);
        // views[9] = 1;
		listItems.add(new ListItem1(activity, R.drawable.anthropology, r.getString(R.string.anthro)));

        //Create Applied Healh Physiology Icon
 //       Drawable[] ahpLayer; //creates an array of layers for each icon
 //       ahpLayer= new Drawable[2];
 //       ahpLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
 //       ahpLayer[1] = r.getDrawable(R.drawable.ahp); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(ahpLayer); //merges the two layers together
        // icons[8] = getResources().getDrawable(R.drawable.ahp);
        // strings[10] = r.getString(R.string.physiology);
        // views[10] = 1;
		listItems.add(new ListItem1(activity, R.drawable.ahp, r.getString(R.string.physiology)));

        //Create Art & Art History Icon
 //       Drawable[] artLayer; //creates an array of layers for each icon
 //       artLayer = new Drawable[2];
 //       artLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
 //       artLayer[1] = r.getDrawable(R.drawable.art); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(artLayer); //merges the two layers together
        // icons[9] = getResources().getDrawable(R.drawable.art);
        // strings[11] = r.getString(R.string.art_history);
        // views[11] = 1;
		listItems.add(new ListItem1(activity, R.drawable.art, r.getString(R.string.art_history)));

        //Create Biology Icon
//        Drawable[] bioLayer; //creates an array of layers for each icon
//        bioLayer = new Drawable[2];
//        bioLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        bioLayer[1] = r.getDrawable(R.drawable.biology); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(bioLayer); //merges the two layers together
        // icons[10] = getResources().getDrawable(R.drawable.biology);
        // strings[12] = r.getString(R.string.bio);
        // views[12] = 1;
		listItems.add(new ListItem1(activity, R.drawable.biology, r.getString(R.string.bio)));

        //Create Business Icon
//        Drawable[] busLayer; //creates an array of layers for each icon
//        busLayer = new Drawable[2];
//        busLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
 //       busLayer[1] = r.getDrawable(R.drawable.business); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(busLayer); //merges the two layers together
        // icons[11] = getResources().getDrawable(R.drawable.business);
        // strings[13] = r.getString(R.string.buisness);
        // views[13] = 1;
		listItems.add(new ListItem1(activity, R.drawable.business, r.getString(R.string.buisness)));

        //Create Chemistry Icon
//        Drawable[] chemLayer; //creates an array of layers for each icon
//        chemLayer = new Drawable[2];
//        chemLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        chemLayer[1] = r.getDrawable(R.drawable.chemistry); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(chemLayer); //merges the two layers together
        //icons[12] = getResources().getDrawable(R.drawable.chemistry);
        //strings[14] = r.getString(R.string.chem);
        //views[14] = 1;
        listItems.add(new ListItem1(activity, R.drawable.chemistry, r.getString(R.string.chem)));

        //Create Communication Arts Icon
//        Drawable[] commLayer; //creates an array of layers for each icon
//        commLayer = new Drawable[2];
//        commLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        commLayer[1] = r.getDrawable(R.drawable.comm); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(commLayer); //merges the two layers together
        //icons[13] = getResources().getDrawable(R.drawable.comm);
        //strings[15] = r.getString(R.string.comm);
        //views[15] = 1;
        listItems.add(new ListItem1(activity, R.drawable.comm, r.getString(R.string.comm)));

        //Create Computer Science Icon
 //       Drawable[] csLayer; //creates an array of layers for each icon
 //       csLayer = new Drawable[2];
 //       csLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
 //       csLayer[1] = r.getDrawable(R.drawable.compsci); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(csLayer); //merges the two layers together
        //icons[14] = getResources().getDrawable(R.drawable.compsci);
        //strings[16] = r.getString(R.string.compsci);
        //views[16] = 1;
        listItems.add(new ListItem1(activity, R.drawable.compsci, r.getString(R.string.compsci)));

        //Create CADR Icon
//        Drawable[] cadrLayer; //creates an array of layers for each icon
//        cadrLayer = new Drawable[2];
//        cadrLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        cadrLayer[1] = r.getDrawable(R.drawable.cadr); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(cadrLayer); //merges the two layers together
        //icons[15] = getResources().getDrawable(R.drawable.cadr);
        //strings[17] = r.getString(R.string.conflict);
        //views[17] = 1;
        listItems.add(new ListItem1(activity, R.drawable.cadr, r.getString(R.string.conflict)));

        //Create Dance Icon
  //      Drawable[] danceLayer; //creates an array of layers for each icon
  //      danceLayer = new Drawable[2];
  //      danceLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
  //      danceLayer[1] = r.getDrawable(R.drawable.dance); //R = abstraction to the file system
  //      layerDrawable = new LayerDrawable(danceLayer); //merges the two layers together
        //icons[16] = getResources().getDrawable(R.drawable.dance);
        //strings[18] = r.getString(R.string.dance);
        //views[18] = 1;
        listItems.add(new ListItem1(activity, R.drawable.dance, r.getString(R.string.dance)));

        //Create Economics & Finance Icon
  //      Drawable[] econLayer; //creates an array of layers for each icon
  //      econLayer = new Drawable[2];
  //      econLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
  //      econLayer[1] = r.getDrawable(R.drawable.econ); //R = abstraction to the file system
  //      layerDrawable = new LayerDrawable(econLayer); //merges the two layers together
        //icons[17] = getResources().getDrawable(R.drawable.economy);
        //strings[19] = r.getString(R.string.econ);
        //views[19] = 1;
        listItems.add(new ListItem1(activity, R.drawable.economy, r.getString(R.string.econ)));

        //Create Education Icon
 //       Drawable[] eduLayer; //creates an array of layers for each icon
 //       eduLayer = new Drawable[2];
 //       eduLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
 //       eduLayer[1] = r.getDrawable(R.drawable.education); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(eduLayer); //merges the two layers together
        //icons[18] = getResources().getDrawable(R.drawable.education);
        //strings[20] = r.getString(R.string.education);
        //views[20] = 1;
        listItems.add(new ListItem1(activity, R.drawable.education, r.getString(R.string.education)));

        //Create Engineering Icon
 //       Drawable[] enginLayer; //creates an array of layers for each icon
 //       enginLayer = new Drawable[2];
 //       enginLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
 //       enginLayer[1] = r.getDrawable(R.drawable.engineering); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(enginLayer); //merges the two layers together
        //icons[19] = getResources().getDrawable(R.drawable.engineering);
        //strings[21] = r.getString(R.string.engineer);
        //views[21] = 1;
        listItems.add(new ListItem1(activity, R.drawable.engineering, r.getString(R.string.engineer)));

        //Create English Icon
  //      Drawable[] englLayer; //creates an array of layers for each icon
  //      englLayer = new Drawable[2];
  //      englLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
  //      englLayer[1] = r.getDrawable(R.drawable.english); //R = abstraction to the file system
  //      layerDrawable = new LayerDrawable(busLayer); //merges the two layers together
        //icons[20] = getResources().getDrawable(R.drawable.english);
        //strings[22] = r.getString(R.string.english);
        //views[22] = 1;
        listItems.add(new ListItem1(activity, R.drawable.english, r.getString(R.string.english)));

        //Create English Language Institute Icon
//        Drawable[] eliLayer; //creates an array of layers for each icon
//        eliLayer = new Drawable[2];
//        eliLayer[0] = r.getDrawable(R.drawable.custom_circle_red); //r = real runtime object that you can use to call getDrawable method
//        eliLayer[1] = r.getDrawable(R.drawable.eli); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(eliLayer); //merges the two layers together
        //icons[21] = getResources().getDrawable(R.drawable.eli);
        //strings[23] = r.getString(R.string.english_institute);
        //views[23] = 1;
        listItems.add(new ListItem1(activity, R.drawable.eli, r.getString(R.string.english_institute)));

        //Create Environmental Studies Icon
//        Drawable[] envLayer; //creates an array of layers for each icon
//        envLayer = new Drawable[2];
//        envLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        envLayer[1] = r.getDrawable(R.drawable.environ); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(envLayer); //merges the two layers together
        //icons[22] = getResources().getDrawable(R.drawable.environ);
        //strings[24] = r.getString(R.string.environ);
        //views[24] = 1;
        listItems.add(new ListItem1(activity, R.drawable.environ, r.getString(R.string.environ)));

        //Create Geography and Geosciences Icon
 //       Drawable[] geoLayer; //creates an array of layers for each icon
 //       geoLayer = new Drawable[2];
 //       geoLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
 //       geoLayer[1] = r.getDrawable(R.drawable.geog); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(geoLayer); //merges the two layers together
        //icons[23] = getResources().getDrawable(R.drawable.geog);
        //strings[25] = r.getString(R.string.geog);
        //views[25] = 1;
        listItems.add(new ListItem1(activity, R.drawable.geog, r.getString(R.string.geog)));

        //Create Government Information Icon
 //       Drawable[] govLayer; //creates an array of layers for each icon
 //       govLayer = new Drawable[2];
 //       govLayer[0] = r.getDrawable(R.drawable.custom_circle_red); //r = real runtime object that you can use to call getDrawable method
 //       govLayer[1] = r.getDrawable(R.drawable.govt); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(govLayer); //merges the two layers together
        //icons[24] = getResources().getDrawable(R.drawable.govt);
        //strings[26] = r.getString(R.string.govt);
        //views[26] = 1;
        listItems.add(new ListItem1(activity, R.drawable.govt, r.getString(R.string.govt)));

        //Create Health and Sports Sciences Icon
 //       Drawable[] hssLayer; //creates an array of layers for each icon
 //       hssLayer = new Drawable[2];
 //       hssLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
 //       hssLayer[1] = r.getDrawable(R.drawable.hss); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(hssLayer); //merges the two layers together
        //icons[25] = getResources().getDrawable(R.drawable.hss);
        //strings[27] = r.getString(R.string.sport_sci);
        //views[27] = 1;
        listItems.add(new ListItem1(activity, R.drawable.hss, r.getString(R.string.sport_sci)));

        //Create History Icon
//        Drawable[] hisLayer; //creates an array of layers for each icon
//        hisLayer = new Drawable[2];
//        hisLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        hisLayer[1] = r.getDrawable(R.drawable.history); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(hisLayer); //merges the two layers together
        //icons[26] = getResources().getDrawable(R.drawable.history);
        //strings[28] = r.getString(R.string.history);
        //views[28] = 1;
        listItems.add(new ListItem1(activity, R.drawable.history, r.getString(R.string.history)));

        //Create Information & Decision Sciences Icon
//        Drawable[] infoLayer; //creates an array of layers for each icon
//        infoLayer = new Drawable[2];
//        infoLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
//        infoLayer[1] = r.getDrawable(R.drawable.ids); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(infoLayer); //merges the two layers together
        //icons[27] = getResources().getDrawable(R.drawable.ids);
        //strings[29] = r.getString(R.string.info);
        //views[29] = 1;
        listItems.add(new ListItem1(activity, R.drawable.ids, r.getString(R.string.info)));

        //Create Interdisciplinary Studies Icon
//        Drawable[] interLayer; //creates an array of layers for each icon
//        interLayer = new Drawable[2];
//        interLayer[0] = r.getDrawable(R.drawable.custom_circle_red); //r = real runtime object that you can use to call getDrawable method
//        interLayer[1] = r.getDrawable(R.drawable.inter); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(interLayer); //merges the two layers together
        //icons[28] = getResources().getDrawable(R.drawable.inter);
        //strings[30] = r.getString(R.string.interdisciplinary);
        //views[30] = 1;
        listItems.add(new ListItem1(activity, R.drawable.inter, r.getString(R.string.interdisciplinary)));

        //Create Management and Marketing Icon
//        Drawable[] manageLayer; //creates an array of layers for each icon
//        manageLayer = new Drawable[2];
//        manageLayer[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
//        manageLayer[1] = r.getDrawable(R.drawable.mgmt); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(manageLayer); //merges the two layers together
        //icons[29] = getResources().getDrawable(R.drawable.mgmt);
        //strings[31] = r.getString(R.string.market);
        //views[31] = 1;
        listItems.add(new ListItem1(activity, R.drawable.mgmt, r.getString(R.string.market)));

        //Create Math Icon
 //       Drawable[] mathLayer; //creates an array of layers for each icon
 //       mathLayer = new Drawable[2];
 //       mathLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
 //       mathLayer[1] = r.getDrawable(R.drawable.math); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(mathLayer); //merges the two layers together
        //icons[30] = getResources().getDrawable(R.drawable.math);
        //strings[32] = r.getString(R.string.math);
        //views[32] = 1;
        listItems.add(new ListItem1(activity, R.drawable.math, r.getString(R.string.math)));

        //Create Medical Lab Science Icon
//        Drawable[] medLayer; //creates an array of layers for each icon
//        medLayer = new Drawable[2];
//        medLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        medLayer[1] = r.getDrawable(R.drawable.mls); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(medLayer); //merges the two layers together
        //icons[31] = getResources().getDrawable(R.drawable.mls);
        //strings[33] = r.getString(R.string.med_lab);
        //views[33] = 1;
        listItems.add(new ListItem1(activity, R.drawable.mls, r.getString(R.string.med_lab)));

        //Create Military Science Icon
//        Drawable[] militaryLayer; //creates an array of layers for each icon
//        militaryLayer = new Drawable[2];
//        militaryLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
//        militaryLayer[1] = r.getDrawable(R.drawable.mil); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(militaryLayer); //merges the two layers together
        //icons[32] = getResources().getDrawable(R.drawable.mil);
        //strings[34] = r.getString(R.string.military_sci);
        //views[34] = 1;
        listItems.add(new ListItem1(activity, R.drawable.mil, r.getString(R.string.military_sci)));

        //Create Modern Languages Icon
//        Drawable[] modernLayer; //creates an array of layers for each icon
//        modernLayer = new Drawable[2];
//        modernLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        modernLayer[1] = r.getDrawable(R.drawable.modlang); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(modernLayer); //merges the two layers together
        //icons[33] = getResources().getDrawable(R.drawable.modlang);
        //strings[35] = r.getString(R.string.lang);
        //views[35] = 1;
        listItems.add(new ListItem1(activity, R.drawable.modlang, r.getString(R.string.lang)));

        //Create Music Icon
//        Drawable[] musicLayer; //creates an array of layers for each icon
//        musicLayer = new Drawable[2];
//        musicLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        musicLayer[1] = r.getDrawable(R.drawable.music); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(musicLayer); //merges the two layers together
        //icons[34] = getResources().getDrawable(R.drawable.music);
        //strings[36] = r.getString(R.string.music);
        //views[36] = 1;
        listItems.add(new ListItem1(activity, R.drawable.music, r.getString(R.string.music)));

        //Create Nursing Icon
//        Drawable[] nursingLayer; //creates an array of layers for each icon
//        nursingLayer = new Drawable[2];
//        nursingLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        nursingLayer[1] = r.getDrawable(R.drawable.nursing); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(nursingLayer); //merges the two layers together
        //icons[35] = getResources().getDrawable(R.drawable.nursing);
        //strings[37] = r.getString(R.string.nursing);
        //views[37] = 1;
        listItems.add(new ListItem1(activity, R.drawable.nursing, r.getString(R.string.nursing)));

        //Create Philosophy Icon
//        Drawable[] philLayer; //creates an array of layers for each icon
//        philLayer = new Drawable[2];
//        philLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        philLayer[1] = r.getDrawable(R.drawable.philosophy); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(philLayer); //merges the two layers together
        //icons[36] = getResources().getDrawable(R.drawable.philosophy);
        //strings[38] = r.getString(R.string.philosophy);
        //views[38] = 1;
        listItems.add(new ListItem1(activity, R.drawable.philosophy, r.getString(R.string.philosophy)));

        //Create Physical Education Icon
//        Drawable[] physedLayer; //creates an array of layers for each icon
//        physedLayer = new Drawable[2];
//        physedLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
//        physedLayer[1] = r.getDrawable(R.drawable.physed); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(physedLayer); //merges the two layers together
        //icons[37] = getResources().getDrawable(R.drawable.physed);
        //strings[39] = r.getString(R.string.phys_ed);
        //views[39] = 1;
        listItems.add(new ListItem1(activity, R.drawable.physed, r.getString(R.string.phys_ed)));

        //Create Physics Icon
//        Drawable[] physicsLayer; //creates an array of layers for each icon
//        physicsLayer = new Drawable[2];
//        physicsLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        physicsLayer[1] = r.getDrawable(R.drawable.physics); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(physicsLayer); //merges the two layers together
        //icons[38] = getResources().getDrawable(R.drawable.physics);
        //strings[40] = r.getString(R.string.physics);
        //views[40] = 1;
        listItems.add(new ListItem1(activity, R.drawable.physics, r.getString(R.string.physics)));

        //Create Political Science Icon
//        Drawable[] poliLayer; //creates an array of layers for each icon
//        poliLayer = new Drawable[2];
//        poliLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        poliLayer[1] = r.getDrawable(R.drawable.polisci); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(poliLayer); //merges the two layers together
        //icons[39] = getResources().getDrawable(R.drawable.polisci);
        //strings[41] = r.getString(R.string.poli_sci);
        //views[41] = 1;
        listItems.add(new ListItem1(activity, R.drawable.polisci, r.getString(R.string.poli_sci)));

        //Create Psychology Icon
       // Drawable[] psychLayer; //creates an array of layers for each icon
//        psychLayer = new Drawable[2];
//        psychLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        psychLayer[1] = r.getDrawable(R.drawable.psychology); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(psychLayer); //merges the two layers together
        //icons[40] = getResources().getDrawable(R.drawable.psychology);
        //strings[42] = r.getString(R.string.psychology);
        //views[42] = 1;
        listItems.add(new ListItem1(activity, R.drawable.psychology, r.getString(R.string.psychology)));

        //Create Respiratory Therapy Icon
//        Drawable[] resLayer; //creates an array of layers for each icon
//        resLayer = new Drawable[2];
//        resLayer[0] = r.getDrawable(R.drawable.custom_circle_green); //r = real runtime object that you can use to call getDrawable method
//        resLayer[1] = r.getDrawable(R.drawable.resp); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(resLayer); //merges the two layers together
        //icons[41] = getResources().getDrawable(R.drawable.resp);
        //strings[43] = r.getString(R.string.respiratory);
        //views[43] = 1;
        listItems.add(new ListItem1(activity, R.drawable.resp, r.getString(R.string.respiratory)));

        //Create Social Work Icon
//        Drawable[] socialLayer; //creates an array of layers for each icon
//        socialLayer = new Drawable[2];
//        socialLayer[0] = r.getDrawable(R.drawable.custom_circle_purple); //r = real runtime object that you can use to call getDrawable method
//        socialLayer[1] = r.getDrawable(R.drawable.socialwork); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(socialLayer); //merges the two layers together
        //icons[42] = getResources().getDrawable(R.drawable.socialwork);
        //strings[44] = r.getString(R.string.social);
        //views[44] = 1;
        listItems.add(new ListItem1(activity, R.drawable.socialwork, r.getString(R.string.social)));

        //Create Sociology Icon
//        Drawable[] socLayer; //creates an array of layers for each icon
//        socLayer = new Drawable[2];
//        socLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
//        socLayer[1] = r.getDrawable(R.drawable.sociology); //R = abstraction to the file system
//        layerDrawable = new LayerDrawable(socLayer); //merges the two layers together
        //icons[43] = getResources().getDrawable(R.drawable.sociology);
        //strings[45] = r.getString(R.string.sociology);
        //views[45] = 1;
        listItems.add(new ListItem1(activity, R.drawable.sociology, r.getString(R.string.sociology)));

        //Create Theatre Icon
 //       Drawable[] theatreLayer; //creates an array of layers for each icon
 //       theatreLayer = new Drawable[2];
 //       theatreLayer[0] = r.getDrawable(R.drawable.custom_circle_blue); //r = real runtime object that you can use to call getDrawable method
 //       theatreLayer[1] = r.getDrawable(R.drawable.theatre); //R = abstraction to the file system
 //       layerDrawable = new LayerDrawable(theatreLayer); //merges the two layers together
        //icons[44] = getResources().getDrawable(R.drawable.theatre);
        //strings[46] = r.getString(R.string.theatre);
        //views[46] = 1;
        listItems.add(new ListItem1(activity, R.drawable.theatre, r.getString(R.string.theatre)));

        //populateListView(sectionHeader, icons, titles, null, null);

        //adapter.populate(views, strings, icons);

        lix.populate(listItems);
        listView.setAdapter(lix);
        loading_msg.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(this);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.research));

        return view;
    }

    //when we click on the item to take you to a the next page
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        webViewFragment webView;
        Intent launchBrowser;

        switch(position) {
            //Select a Research Topic URL
            case 1:
                //example of how to switch fragments
                //MainActivity.getNewFragTransaction().replace(R.id.content_frame, new Fragment2()).commit();

                /*
                Uri topicUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary");
                launchBrowser = new Intent(Intent.ACTION_VIEW, topicUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/howdoilibrary", "Research Topic");
                fragmentTransaction.replace(R.id.content_container, webView);

                //Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
                break;
            //Develop Keywords URL
            case 2:
                /*
                Uri keywordUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/keywords");
                launchBrowser = new Intent(Intent.ACTION_VIEW, keywordUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/howdoilibrary/keywords", "Keywords");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Books & eBooks URL
            case 3:
                /*
                Uri fbooksUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/findbooks");
                launchBrowser = new Intent(Intent.ACTION_VIEW, fbooksUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/howdoilibrary/findbooks", "Find Books");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Articles URL
            case 4:
                /*
                Uri articleUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/findarticles");
                launchBrowser = new Intent(Intent.ACTION_VIEW, articleUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/howdoilibrary/findarticles", "Find Articles");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Critically Evaluate Information URL
            case 5:
                /*
                Uri evaluateUrl = Uri.parse("http://libraryguides.salisbury.edu/howdoilibrary/criticallyevaluate");
                launchBrowser = new Intent(Intent.ACTION_VIEW, evaluateUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/howdoilibrary/criticallyevaluate", "Evaluate Information");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Create an Annotated Bibliography URL
            case 6:
                /*
                Uri bibUrl = Uri.parse("http://libraryguides.salisbury.edu/c.php?g=327806&p=3146470");
                launchBrowser = new Intent(Intent.ACTION_VIEW, bibUrl);
                startActivity(launchBrowser);
                */

                webView = new webViewFragment("http://libraryguides.salisbury.edu/c.php?g=327806&p=3146470", "Annotated Bibliography");
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
                //webView = new webViewFragment("http://libraryguides.salisbury.edu/");
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(1));
                break;
            //Applied Health & Physiology
            case 10:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(2));
                break;
            //Art & Art History
            case 11:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(3));
                break;
            //Biology
            case 12:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(4));
                break;
            //Business
            case 13:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(5));
                break;
            //Chemistry
            case 14:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(6));
                break;
            //Communication Arts
            case 15:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(7));
                break;
            //Computer Science
            case 16:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(8));
                break;
            //Conflict Analysis & Dispute Resolution
            case 17:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(9));
                break;
            //Dance
            case 18:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(10));
                break;
            //Economics & Finance
            case 19:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(11));
                break;
            //Education
            case 20:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(12));
                break;
            //Engineering
            case 21:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(13));
                break;
            //English
            case 22:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(14));
                break;
            //English Language Institute
            case 23:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(15));
                break;
            //Environmental Studies
            case 24:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(16));
                break;
            //Geography & Geosciences
            case 25:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(17));
                break;
            //Government Information
            case 26:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(18));
                break;
            //Health & Sports Sciences
            case 27:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(19));
                break;
            //History
            case 28:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(20));
                break;
            //Information & Decision Sciences
            case 29:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(21));
                break;
            //Interdisciplinary Studies
            case 30:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(22));
                break;
            //Management & Marketing
            case 31:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(23));
                break;
            //Mathematics
            case 32:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(24));
                break;
            //Medical Laboratory Science
            case 33:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(25));
                break;
            //Military Science
            case 34:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(26));
                break;
            //Modern Languages
            case 35:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(27));
                break;
            //Music
            case 36:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(28));
                break;
            //Nursing
            case 37:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(29));
                break;
            //Philosophy
            case 38:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(30));
                break;
            //Physical Education
            case 39:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(31));
                break;
            //Physics
            case 40:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(32));
                break;
            //Political Science
            case 41:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(33));
                break;
            //Psychology
            case 42:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(34));
                break;
            //Respiratory Therapy
            case 43:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(35));
                break;
            //Social Work
            case 44:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(36));
                break;
            //Sociology
            case 45:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(37));
                break;
            //Theatre
            case 46:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(38));
                break;
        }

        fragmentTransaction.addToBackStack(null).commit();
    }

}
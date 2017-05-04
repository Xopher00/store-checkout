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

public class ResearchHelpFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    Drawable[] icons;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    TextView loading_msg;
    Activity activity;
    ActionBar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_research_help, container, false);

        Resources r = getResources();

        //creating custom listview
		lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();
        activity = getActivity();

        //show loading message to the user
        loading_msg = (TextView) view.findViewById(R.id.research_list_loading);
        loading_msg.setVisibility(View.VISIBLE);

        listView = (ListView) view.findViewById(R.id.listView); //need to be able to access an xml element with java so that you can modify it dynamically

        listView.setVisibility(View.INVISIBLE);

		ListItem0 li0 = new ListItem0(activity, r.getString(R.string.lib_basic));

        li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(r, R.color.listHeader, null));
        li0.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
        li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listItems.add(li0);

        //manually add first items into the listview (Library Basics items)
		listItems.add(new ListItem1(activity, R.drawable.research, r.getString(R.string.topic)));

		listItems.add(new ListItem1(activity, R.drawable.keyword, r.getString(R.string.keywords)));

		listItems.add(new ListItem1(activity, R.drawable.book, r.getString(R.string.ebook)));

		listItems.add(new ListItem1(activity, R.drawable.articles, r.getString(R.string.article)));

		listItems.add(new ListItem1(activity, R.drawable.evaluate, r.getString(R.string.eval_info)));

		listItems.add(new ListItem1(activity, R.drawable.bib, r.getString(R.string.bib)));

		li0 = new ListItem0(activity, r.getString(R.string.res_subj));

        li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(r, R.color.listHeader, null));
        li0.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
        li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        listItems.add(li0);
        //manually adding all subject information into the listview
        listItems.add(new ListItem1(activity, R.drawable.accounting, r.getString(R.string.legal)));
        listItems.add(new ListItem1(activity, R.drawable.anthropology, r.getString(R.string.anthro)));
        listItems.add(new ListItem1(activity, R.drawable.ahp, r.getString(R.string.physiology)));
        listItems.add(new ListItem1(activity, R.drawable.art, r.getString(R.string.art_history)));
        listItems.add(new ListItem1(activity, R.drawable.biology, r.getString(R.string.bio)));
		listItems.add(new ListItem1(activity, R.drawable.business, r.getString(R.string.buisness)));
        listItems.add(new ListItem1(activity, R.drawable.chemistry, r.getString(R.string.chem)));
        listItems.add(new ListItem1(activity, R.drawable.comm, r.getString(R.string.comm)));
        listItems.add(new ListItem1(activity, R.drawable.compsci, r.getString(R.string.compsci)));
        listItems.add(new ListItem1(activity, R.drawable.cadr, r.getString(R.string.conflict)));
        listItems.add(new ListItem1(activity, R.drawable.dance, r.getString(R.string.dance)));
        listItems.add(new ListItem1(activity, R.drawable.economy, r.getString(R.string.econ)));
        listItems.add(new ListItem1(activity, R.drawable.education, r.getString(R.string.education)));
        listItems.add(new ListItem1(activity, R.drawable.engineering, r.getString(R.string.engineer)));
        listItems.add(new ListItem1(activity, R.drawable.english, r.getString(R.string.english)));
        listItems.add(new ListItem1(activity, R.drawable.eli, r.getString(R.string.english_institute)));
        listItems.add(new ListItem1(activity, R.drawable.environ, r.getString(R.string.environ)));
        listItems.add(new ListItem1(activity, R.drawable.geog, r.getString(R.string.geog)));
        listItems.add(new ListItem1(activity, R.drawable.govt, r.getString(R.string.govt)));
        listItems.add(new ListItem1(activity, R.drawable.hss, r.getString(R.string.sport_sci)));
        listItems.add(new ListItem1(activity, R.drawable.history, r.getString(R.string.history)));
        listItems.add(new ListItem1(activity, R.drawable.ids, r.getString(R.string.info)));
        listItems.add(new ListItem1(activity, R.drawable.inter, r.getString(R.string.interdisciplinary)));
        listItems.add(new ListItem1(activity, R.drawable.mgmt, r.getString(R.string.market)));
        listItems.add(new ListItem1(activity, R.drawable.math, r.getString(R.string.math)));
        listItems.add(new ListItem1(activity, R.drawable.mls, r.getString(R.string.med_lab)));
        listItems.add(new ListItem1(activity, R.drawable.mil, r.getString(R.string.military_sci)));
        listItems.add(new ListItem1(activity, R.drawable.modlang, r.getString(R.string.lang)));
        listItems.add(new ListItem1(activity, R.drawable.music, r.getString(R.string.music)));
        listItems.add(new ListItem1(activity, R.drawable.nursing, r.getString(R.string.nursing)));
        listItems.add(new ListItem1(activity, R.drawable.philosophy, r.getString(R.string.philosophy)));
        listItems.add(new ListItem1(activity, R.drawable.physed, r.getString(R.string.phys_ed)));
        listItems.add(new ListItem1(activity, R.drawable.physics, r.getString(R.string.physics)));
        listItems.add(new ListItem1(activity, R.drawable.polisci, r.getString(R.string.poli_sci)));
        listItems.add(new ListItem1(activity, R.drawable.psychology, r.getString(R.string.psychology)));
        listItems.add(new ListItem1(activity, R.drawable.resp, r.getString(R.string.respiratory)));
        listItems.add(new ListItem1(activity, R.drawable.socialwork, r.getString(R.string.social)));
        listItems.add(new ListItem1(activity, R.drawable.sociology, r.getString(R.string.sociology)));
        listItems.add(new ListItem1(activity, R.drawable.theatre, r.getString(R.string.theatre)));

        lix.populate(listItems);
        listView.setAdapter(lix);
        loading_msg.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(this);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.research));

        return view;
    }

    //when we click on the item to take you to the desired page
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        webViewFragment webView = null;
        Intent launchBrowser;

        switch(position) {
            //Select a Research Topic URL
            case 1:
                webView = webViewFragment.getInstance("http://libraryguides.salisbury.edu/howdoilibrary", "Research Topic");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Develop Keywords URL
            case 2:
                webView = webViewFragment.getInstance("http://libraryguides.salisbury.edu/howdoilibrary/keywords", "Keywords");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Books & eBooks URL
            case 3:
                webView = webViewFragment.getInstance("http://libraryguides.salisbury.edu/howdoilibrary/findbooks", "Find Books");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Articles URL
            case 4:
                webView = webViewFragment.getInstance("http://libraryguides.salisbury.edu/howdoilibrary/findarticles", "Find Articles");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Critically Evaluate Information URL
            case 5:
                webView = webViewFragment.getInstance("http://libraryguides.salisbury.edu/howdoilibrary/criticallyevaluate", "Evaluate Information");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Create an Annotated Bibliography URL
            case 6:
                webView = webViewFragment.getInstance("http://libraryguides.salisbury.edu/c.php?g=327806&p=3146470", "Annotated Bibliography");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Accounting & Legal Studies
            case 8:
                //add to every case statement for the resources by subject
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(0));
                break;
            //Anthropology
            case 9:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(1));
                break;
            //Applied Health & Physiology
            case 10:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(2));
                break;
            //Art & Art History
            case 11:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(3));
                break;
            //Biology
            case 12:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(4));
                break;
            //Business
            case 13:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(5));
                break;
            //Chemistry
            case 14:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(6));
                break;
            //Communication Arts
            case 15:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(7));
                break;
            //Computer Science
            case 16:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(8));
                break;
            //Conflict Analysis & Dispute Resolution
            case 17:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(9));
                break;
            //Dance
            case 18:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(10));
                break;
            //Economics & Finance
            case 19:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(11));
                break;
            //Education
            case 20:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(12));
                break;
            //Engineering
            case 21:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(13));
                break;
            //English
            case 22:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(14));
                break;
            //English Language Institute
            case 23:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(15));
                break;
            //Environmental Studies
            case 24:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(16));
                break;
            //Geography & Geosciences
            case 25:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(17));
                break;
            //Government Information
            case 26:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(18));
                break;
            //Health & Sports Sciences
            case 27:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(19));
                break;
            //History
            case 28:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(20));
                break;
            //Information & Decision Sciences
            case 29:
                fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(21));
                break;
            //Interdisciplinary Studies
            case 30:
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
        //add to back stack -> create the sequence of pages visited to come back to later if necessary
        if(webView == null)
            fragmentTransaction.addToBackStack(null).commit();
        else
            fragmentTransaction.addToBackStack("web").commit();

        MainActivity.pageStack.push(MainActivity.researchPage);
    }

}
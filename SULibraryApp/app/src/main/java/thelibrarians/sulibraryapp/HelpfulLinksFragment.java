package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/17/2016.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

    ListView listViewhl; //listView helpful links

    //array of items pulled from strings.xml
    String[] strings; //all text for the listview
    ListviewX lix;
    ArrayList<ListItem> listItems;
    ActionBar toolbar;
    TextView loading_msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sectionHeader = getResources().getStringArray(R.array.helpful_headers);
        strings = getResources().getStringArray(R.array.helpful_strings);

        View view = inflater.inflate(R.layout.fragment_helpful_links, container, false);

        loading_msg = (TextView) view.findViewById(R.id.helpful_links_loading);


        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();

        listViewhl = (ListView) view.findViewById(R.id.listViewhl);
        listViewhl.setVisibility(View.INVISIBLE);

        for(int x = 0; x <= 22; x++) {
            ListItem0 li = new ListItem0(getActivity(), strings[x]);
            switch(x) {
                case 0:
                case 5:
                case 10:
                case 18:
                    //li.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
                    li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
                    li.getTextView().setTextAppearance(getActivity(), R.style.listHeader);
                    li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    break;
            }
            listItems.add(li);
        }

        lix.populate(listItems);
        listViewhl.setVisibility(View.VISIBLE);
        loading_msg.setVisibility(View.INVISIBLE);
        listViewhl.setAdapter(lix);
        listViewhl.setOnItemClickListener(this);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.helpful_links));


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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
    }
}


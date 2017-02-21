package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/19/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
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

    //static int position;
    ListView listViewct; //listView contacts
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    String[] subitems;
    String[] strings; //sequential list of strings as they appear in the listview
    int listLength;
    int[] views = {0, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2}; //sequential list of listview layouts

    ListviewX lix;
    ArrayList<ListItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionHeader = getResources().getStringArray(R.array.contact_headers);
        items = getResources().getStringArray(R.array.contact_who);
        subitems = getResources().getStringArray(R.array.contact_deets);
        //strings = getResources().getStringArray(R.array.list_strings);

        //images displayed next to each option in list
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

        listLength = sectionHeader.length + icons.length;


        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();

        int cheaders = 0, cbodys = 0;
        ListItem0 li0;
        ListItem2 li2;
        for(int x = 0; x < listLength; x++) {
            switch(x) {
                case 0:
                case 4:
                case 9:
                case 15:
                    li0 = new ListItem0(getActivity(), sectionHeader[cheaders]);
                    li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                    li0.getTextView().setTextColor(Color.WHITE);
                    listItems.add(li0);
                    cheaders++;
                    break;
                default:
                    li2 = new ListItem2(getActivity(), icons[cbodys], items[cbodys], subitems[cbodys]);
                    listItems.add(li2);
                    cbodys++;
            }
        }


        listViewct = (ListView) view.findViewById(R.id.listViewct);
        lix.populate(listItems);
        listViewct.setAdapter(lix);
        listViewct.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Uri uriUrl;
        Intent launchBrowser;

        if(isNetworkAvailable()) {
            //CAUTION: section headers count as positions
            //i.e. position 0 is section header 1
            switch (position) {

                //Three livechats with different parts of the librray
                case 1://CHAT 1 - General Staff
                    uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                    launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    break;
                case 2://CHAT 2 - 3D Printer Lab
                    uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/makerlab@chat.libraryh3lp.com?skin=22280&identity=Staff");//requires login
                    launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    break;
                case 3://CHAT 3 - Librarians
                    uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-crc@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                    launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    break;

                //Section for calling for Library Support
                case 5://call research help 410 548 5988
                    launchPhone("tel:4105485988");//calls launchPhone method
                    break;
                case 6://call circulation 410 543 6130
                    launchPhone("tel:4105436130");
                    break;
                case 7://call toll free 888 543 0148
                    launchPhone("tel:8885430148");
                    break;
                case 8://call app support 410 543 6306
                    launchPhone("tel:4105436306");
                    break;

                //Section for Emailing for Library Support
                case 10://email reserach help
                    launchEmail("libraries@salisbury.edu");//calls launchEmail method
                    break;
                case 11://email circulation
                    launchEmail("illoans@salisbury.edu");
                    break;
                case 12://email interlibray loan
                    launchEmail("libcirc@salisbury.edu");
                    break;
                case 13://email soar@su
                    launchEmail("soar@salisbury.edu");
                    break;
                case 14://email app support
                    launchEmail("cmwoodall@salisbury.edu");
                    break;
                default:
                    break;
            }


            if (position >= 16 && position <= 50)//for the rest of the cases, launch the dialog box to call or email a staff member
                launchDialog(position);//pass the position as a parameter to the dialog launch function
        }
        else{

        }
    }

    //starts a call using phone number passed as argument
    public void launchPhone(String phoneNumber){
        Intent dialer;
        dialer = new Intent(Intent.ACTION_DIAL);
        dialer.setData(Uri.parse(phoneNumber));
        startActivity(dialer);
    }

    //starts an email using address passed as argument
    public void launchEmail(String address){
        Intent emailer;
        emailer = new Intent(Intent.ACTION_SEND);
        emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailer.setType("vnd.android.cursor.item/email");
        emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{address});
        startActivity(emailer);
    }

    public void launchDialog(int pos){
        CallOrClickDialogFragment c1;//creates new fragment
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        Fragment prev;
        Bundle args;//passes position to fragment as an argument via Bundle; this determines which case within the sub fragment is executed
        c1 = new CallOrClickDialogFragment(getActivity());
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        args = new Bundle();
        args.putInt("position", pos);
        c1.setArguments(args);
        c1.show(fragmentTransaction,"dialog");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

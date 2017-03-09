
package thelibrarians.sulibraryapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DrawerToggleListener {

    //create classes
    DrawerLayout drawer;
    Toolbar toolbar;
    static FragmentManager fm;
    FragmentTransaction ft;
    ActionBarDrawerToggle drawerToggle;
    FrameLayout frame;
    ListView navList;
    SearchView searchView;

    //Fragment class instances
    Fragment currentFragment;
    HomeFragment home = new HomeFragment();
    LibraryHoursFragment libHours = new LibraryHoursFragment();
    ResearchHelpFragment researchHelp = new ResearchHelpFragment();
    ComputerAvailabilityListFragment computerAvailable = new ComputerAvailabilityListFragment();
    StudyRoomReserveFragment studyRoomReserve = new StudyRoomReserveFragment();
    DeviceAvailabilityFragment deviceAvailable = new DeviceAvailabilityFragment();
    AboutFragment about = new AboutFragment();
    HelpfulLinksFragment help = new HelpfulLinksFragment();
    ContactInfoFragment contact = new ContactInfoFragment();
    ChatFragment chat = new ChatFragment();
    NewsFragment news = new NewsFragment();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        frame = (FrameLayout) findViewById(R.id.content_container);

        //navigation drawer list view
        navList = (ListView) findViewById(R.id.drawer_list);
        setUpNavList();
        navList.setOnItemClickListener(this);

        //navigation drawer setup
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(drawerToggle);  //animate hamburger icon (i think)
        drawerToggle.syncState();

        //set app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("SU Libraries");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        fm = getSupportFragmentManager();
        if (savedInstanceState == null)/*when app is started, nothing has happened*/ {
            //only adds home fragment to frame layout if nothing has happened previously
            ft = fm.beginTransaction(); //new instance of fragment transaction class
            ft.add(R.id.content_container, home).commit(); //by default frame layout is empty, so we have to add a new fragment, in this case home, to it
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        menu.findItem(R.id.filter_icon).setVisible(false);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerToggle.isDrawerIndicatorEnabled()) { //if nav drawer
                    drawer.openDrawer(GravityCompat.START);
                } else { //if up arrow
                    getSupportFragmentManager().popBackStackImmediate();
                }

                //if search box is visible then close/make icon
                if (!searchView.isIconified()) {
                    searchView.clearFocus();
                    searchView.setIconified(true);
                }

                //if keyboard is up then close
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;
            case R.id.filter_icon:
                fm.beginTransaction().replace(R.id.content_container, DeviceFilterFragment.getInstance()).addToBackStack(null).commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //drawer item clicked listener
        Log.i("nick", "nav " + position);
        //if page changes
        /*if(position != 14)
            ft = fm.beginTransaction(); //new fragment transaction*/
        ft = fm.beginTransaction();

        //replace fragment depending on which item u click in the menu bar
        switch (position)/*position in the array*/ {
            case 1:
                //HOME
                currentFragment = home;
                ft.replace(R.id.content_container, currentFragment);//replace current fragment with home fragment
                break;
            case 2:
                //LIBRARY HOURS
                currentFragment = libHours;
                ft.replace(R.id.content_container, currentFragment);
                break;
            case 3:
                // MY CARD
                break;
            case 4:
                // CHAT
                currentFragment = chat;
                ft.replace(R.id.content_container, currentFragment);
                break;
            case 6:
                //RESEARCH HELP
                currentFragment = researchHelp;
                ft.replace(R.id.content_container, currentFragment);
                break;
            case 7:
                //STUDY ROOM RESERVATIONS
                if (isNetworkAvailable()) {
                    currentFragment = studyRoomReserve;
                } else {
                    currentFragment = new ConnectionErrorFragment(studyRoomReserve);
                }
                ft.replace(R.id.content_container, currentFragment); //replace current fragment with study room reservations fragment
                break;
            case 8:
                //COMPUTER AVAILABILITY
                currentFragment = computerAvailable;
                ft.replace(R.id.content_container, currentFragment);
                break;
            case 9:
                //DEVICE AVAILABILITY
                if (isNetworkAvailable()) {
                    currentFragment = deviceAvailable;
                } else
                    currentFragment = new ConnectionErrorFragment(deviceAvailable);
                ft.replace(R.id.content_container, currentFragment);
                break;
            case 10:
                //HELPFUL LINKS
                if (isNetworkAvailable()) {
                    currentFragment = help;
                } else {
                    currentFragment = new ConnectionErrorFragment(help);
                }
                ft.replace(R.id.content_container, currentFragment);
                break;
            case 12:
                // NEWS
                if (isNetworkAvailable()) {
                    currentFragment = news;
                } else {
                    currentFragment = new ConnectionErrorFragment(news);
                }
                ft.replace(R.id.content_container, currentFragment);//replace current fragment with home fragment
                break;
            case 13:
                //BUILDING MAPS
                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("http://libapps.salisbury.edu/maps/");
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("http://libapps.salisbury.edu/maps/"));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;
            //ft.replace(R.id.content_container, buildingMaps);//replace current fragment with building maps fragment
            case 14:
                //CONTACT INFORMATION
                currentFragment = contact;
                ft.replace(R.id.content_container, currentFragment);
                break;
            /*case 14:
                //SUPPORT
                if(isNetworkAvailable()) {
                    Intent emailer;
                    emailer = new Intent(Intent.ACTION_SENDTO);
                    emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailer.setData(Uri.parse("mailto:"));
                    emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"libapp@salisbury.edu"});
                    emailer.putExtra(android.content.Intent.EXTRA_SUBJECT, new String[]{"SU Libraries App Support"});
                    emailer.putExtra(android.content.Intent.EXTRA_CC, new String[]{"cmwoodall@salisbury.edu"});
                    //SU Libraries App Support
                    startActivity(emailer);
                }
                else{
                    currentFragment = new ConnectionErrorFragment();
                }
                break;*/
            case 15:
                //ABOUT
                currentFragment = about;
                ft.replace(R.id.content_container, currentFragment);//replace current fragment with about fragment
                break;
        }

        //if page changes
        //add previous transaction/fragment to stack
        // so user can go back to it
       /* if(position != 14)
            ft.addToBackStack(null).commit();*/

        ft.addToBackStack(null).commit();

        drawer.closeDrawers();
    }


    @Override
    public void onBackPressed() {
        //define function of phone's back button
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else if (!searchView.isIconified()) {
            searchView.clearFocus();
            searchView.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }

    private void setUpNavList() {
        /*sla = new SeparatedListAdapter(getApplicationContext());
        listItems = getResources().getStringArray(R.array.user_links);
        ArrayAdapter<String> arr_ad1 = new ArrayAdapter<String>(this, R.layout.drawer_view, listItems);
        sla.addSection("User Links", arr_ad1);
        listHelpfulLinks = getResources().getStringArray(R.array.helpful_links);
        ArrayAdapter<String> arr_ad2 = new ArrayAdapter<String>(this, R.layout.drawer_view, listHelpfulLinks);
        sla.addSection("Helpful Links", arr_ad2);
        navList.setAdapter(sla);*/
/*
        ListviewAdapter adapter = new ListviewAdapter(this);
        navList.setAdapter(adapter);
        int[] types = {0, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4};
        String[] strings = getResources().getStringArray(R.array.nav_links);

        adapter.populate(types, strings, icons);*/


        ListviewX lix = new ListviewX(this);
        String[] strings = getResources().getStringArray(R.array.nav_links);
        ArrayList<ListItem> listItems = new ArrayList<ListItem>();

        ListItem0 li;
        for(int x = 0; x < 16; x++) {
            li = new ListItem0(this, strings[x]);
            Log.d("ERROR", new Integer(x).toString());
            switch (x) {
                case 0:
                case 5:
                case 11:
                    //li.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
                    //li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
                    li.getTextView().setTextColor(Color.parseColor("#8a000000"));
                    break;
            }
            listItems.add(li);
        }

        lix.populate(listItems);
        navList.setAdapter(lix);

    }

    @Override
    public void toggleDrawer(boolean toggle) { //toggles home icon between nav drawer and up arrow
        drawerToggle.setDrawerIndicatorEnabled(toggle);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            ft = fm.beginTransaction();
            Fragment currentFragment;
            //searches worldcat library database for whatever query string contains
            if (isNetworkAvailable()) { //concatenates databases url with query string
                currentFragment = new webViewFragment("http://salisbury.worldcat.org/m/search?q=" + query, searchView);
            } else {
                currentFragment = new ConnectionErrorFragment(new webViewFragment("http://salisbury.worldcat.org/m/search?q=" + query, searchView));
            }

            ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();

            searchView.clearFocus();
        }
    }
}

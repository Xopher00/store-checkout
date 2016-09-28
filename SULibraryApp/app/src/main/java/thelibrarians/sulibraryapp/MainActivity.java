package thelibrarians.sulibraryapp;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //create classes
    DrawerLayout drawer;
    Toolbar toolbar;
    FragmentManager fm;
    FragmentTransaction ft;
    ActionBarDrawerToggle drawerToggle;
    FrameLayout frame;
    ListView navList;
    String[] listItems;

    //Fragment class instances
    HomeFragment home = new HomeFragment();
    LibraryHoursFragment libHours = new LibraryHoursFragment();
    ResearchHelpFragment researchHelp = new ResearchHelpFragment();


    DeviceAvailabilityFragment deviceAvailable = new DeviceAvailabilityFragment();
    MapsBuildingFragment buildingMaps = new MapsBuildingFragment();



    AboutFragment about = new AboutFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = (FrameLayout) findViewById(R.id.content_container);

        //navigation drawer list view
        navList = (ListView) findViewById(R.id.drawer_list);
        listItems = getResources().getStringArray(R.array.nav_items);
        navList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_view, listItems));
        navList.setOnItemClickListener(this);

        //navigation drawer setup
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close)
        {

            public void onDrawerClosed(View view)
            {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView)
            {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(drawerToggle);  //animate hamburger icon (i think)
        drawerToggle.syncState();

        //set app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("SU Library");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        fm = getSupportFragmentManager();
        if(savedInstanceState == null) {
            //display home fragment
            ft = fm.beginTransaction(); //new instance of fragment transaction class
            ft.add(R.id.content_container, home).commit(); //by default frame layout is empty, so we have to add a new fragment, in this case home, to it
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.search_icon:
                Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item1:
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
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

        ft = fm.beginTransaction(); //new fragment transaction

        //replace fragment depending on which item u click in the menu bar
        switch(position)/*position in the array*/ {
            case 0:
                ft.replace(R.id.content_container, home);//replace current fragment with home fragment
                break;
            case 1:
                ft.replace(R.id.content_container, libHours);
                break;
            case 2:
                ft.replace(R.id.content_container, researchHelp);
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                ft.replace(R.id.content_container, deviceAvailable);
                break;
            case 6:
                ft.replace(R.id.content_container, buildingMaps);//replace current fragment with building maps fragment
                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:
                ft.replace(R.id.content_container, about);//replace current fragment with about fragment
                break;
        }
        //add previous transaction/fragment to stack
        // so user can go back to it
        ft.addToBackStack(null).commit();

        drawer.closeDrawers();
    }


    @Override
    public void onBackPressed() {
        //define function of phone's back button
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}

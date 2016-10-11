package thelibrarians.sulibraryapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeviceAvailabilityFragment extends Fragment {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_availability, container, false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            return DeviceFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SHOW ALL";
                case 1:
                    return "AVAILABLE ONLY";
            }
            return null;
        }
    }



//*

    public static class DeviceFragment extends Fragment implements  AdapterView.OnItemClickListener {
        String[] sectionHeader;
        String[] titles;
        String[] subtitles;
        int[] icons = {R.drawable.group_study_large3x, R.drawable.group_study_large3x, R.drawable.group_study_large3x,
                R.drawable.group_study_large3x};
        ImgTxtListAdapter itlAdapter;
        ListView listView;
        static int tabNumber;

        public static DeviceFragment newInstance(int sectionNumber) {
            DeviceFragment fragment = new DeviceFragment();
            //Bundle args = new Bundle();
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //fragment.setArguments(args);
            tabNumber = sectionNumber;
            return fragment;
        }

        public DeviceFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            sectionHeader = getResources().getStringArray(R.array.device_section_head);
            titles = getResources().getStringArray(R.array.device_titles);

            View view = inflater.inflate(R.layout.fragment_device_pager, container, false);

            itlAdapter = new ImgTxtListAdapter(getActivity());

            listView = (ListView) view.findViewById(R.id.listView);

            //add and call populateListView()
            populateListView(sectionHeader, icons, titles, null, null);

            listView.setAdapter(itlAdapter);
            listView.setOnItemClickListener(this);

            return view;
        }

        public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
            int position = 0;  //current position in each item array
            ImgTxtListAdapter.SectionStructure str;
            ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

            for (int i = 0; i < sectionHeader.length; i++) {

                int items = 0;  //number of items per section

                //number of case statements is the number of sections
                switch (i) {
                    case 0:
                        items = 2;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[i] + " #" + j);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 1:
                        items = 1;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[i] + " #" + j);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 2:
                        items = 1;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[i] + " #" + j);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                    case 3:
                        items = 0;
                        for (int j = 0; j < items + 1; j++) {
                            str = itlAdapter.getStr();
                            if (j == 0) {
                                str.setSectionName(sectionHeader[i]);
                                str.setSectionTitle("");
                                sectionList.add(str);
                            } else {
                                if (icons != null)
                                    str.setSectionImage(icons[position]);
                                str.setSectionName("");
                                if (titles != null)
                                    str.setSectionTitle(titles[i] + " #" + j);
                                if (subTitles != null)
                                    str.setSectionSubtitle(subTitles[position]);
                                if (notes != null)
                                    str.setSectionNote(notes[position]);
                                sectionList.add(str);
                                position++;
                            }
                        }
                        break;
                }
            }
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
    //*/

    /*
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_device_pager, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.textTest);
            textView.setText("The sec " + getArguments().getInt(ARG_SECTION_NUMBER));
            return rootView;
        }
    }
    //*/
}

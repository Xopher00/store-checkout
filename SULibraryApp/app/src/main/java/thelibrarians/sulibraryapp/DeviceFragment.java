package thelibrarians.sulibraryapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This fragment is for each tab of the DeviceAvailabilityFragment.java
 */
public class DeviceFragment extends Fragment implements AdapterView.OnItemClickListener {
    String[] sectionHeader;
    String[] titles;
    String[] subtitles;
    int[] icons;
    ImgTxtListAdapter itlAdapter;
    ListView listView;
    static ArrayList<JSONObject> devices;
    static ArrayList<JSONObject> available_devices;
    int tabNumber;
    static int airsCount = 0, minisCount = 0, prosCount = 0, touchesCount = 0, fitbitsCount = 0, accessoriesCount = 0;
    static int availAirs = 0, availMinis = 0, availPros = 0, availTouches = 0, availFitbits = 0, availAccess = 0, totalAvail = 0;

    public DeviceFragment(int sectionNumber, ArrayList<JSONObject> d, ArrayList<JSONObject> ad) {
        tabNumber = sectionNumber;
    }

    public DeviceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sectionHeader = getResources().getStringArray(R.array.device_section_head);
        //titles = getResources().getStringArray(R.array.device_titles);

        try {
            if (tabNumber == 0) {
                //set arrays
                titles = new String[devices.size()];
                subtitles = new String[devices.size()];
                icons = new int[devices.size()];

                int countAvail = 0; //count available devices for case 1

                for (int a = 0; a < devices.size(); a++) {
                    titles[a] = devices.get(a).getString("device_name");
                    switch (devices.get(a).getInt("status")) {
                        case 1:
                            subtitles[a] = available_devices.get(countAvail).getString("type_name") + " (" +
                                    available_devices.get(countAvail).getString("detail") + ")";
                            icons[a] = R.drawable.available;
                            countAvail++;
                            break;
                        case 2:
                            subtitles[a] = getResources().getString(R.string.device_due) + " " + devices.get(a).getString("due_date");
                            icons[a] = R.drawable.checked_out;
                            break;
                        case 3:
                        case 4:
                        case 5:
                        case 10:
                        case 11:
                            subtitles[a] = getResources().getString(R.string.device_unavailable);
                            icons[a] = R.drawable.unavailable;
                            break;
                        default:
                            subtitles[a] = getResources().getString(R.string.no_info);
                            icons[a] = R.drawable.unavailable;
                    }
                }
            } else if (tabNumber == 1) {

                //set arrays for available devices
                titles = new String[totalAvail];
                subtitles = new String[totalAvail];
                icons = new int[totalAvail];

                for (int a = 0; a < totalAvail; a++) {
                    titles[a] = available_devices.get(a).getString("device_name");
                    subtitles[a] = available_devices.get(a).getString("type_name") + " (" +
                            available_devices.get(a).getString("detail") + ")";
                    icons[a] = R.drawable.available;
                }
            }
        } catch (JSONException e) {
        }

        View view = inflater.inflate(R.layout.fragment_device_pager, container, false);

        itlAdapter = new ImgTxtListAdapter(getActivity());

        listView = (ListView) view.findViewById(R.id.listView);

        populateListView(sectionHeader, icons, titles, subtitles, null);

        listView.setAdapter(itlAdapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each array, shared between arrays
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for (int i = 0; i < sectionHeader.length; i++) {

            int items = 0;  //number of items per section

            //number of case statements matches the number of sections
            switch (i) {
                case 0: //iPad airs
                    if (tabNumber == 0)
                        items = airsCount;
                    else
                        items = availAirs;

                    for (int j = 0; j < items + 1; j++) {
                        str = itlAdapter.getStr();
                        if (j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            str.setSectionSubtitle("(" + getResources().getString(R.string.device_available) + ")");
                            str.setSectionBackground(R.drawable.ipad_airs);
                            sectionList.add(str);
                        } else {
                            if (icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if (titles != null)
                                str.setSectionTitle(titles[position]);
                            if (subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if (notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
                case 1: //iPad minis
                    if (tabNumber == 0)
                        items = minisCount;
                    else
                        items = availMinis;

                    for (int j = 0; j < items + 1; j++) {
                        str = itlAdapter.getStr();
                        if (j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            str.setSectionSubtitle("(" + getResources().getString(R.string.device_available));
                            str.setSectionBackground(R.drawable.ipad_minis);
                            sectionList.add(str);
                        } else {
                            if (icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if (titles != null)
                                str.setSectionTitle(titles[position]);
                            if (subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if (notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
                case 2: //iPad pros
                    if (tabNumber == 0)
                        items = prosCount;
                    else
                        items = availPros;

                    for (int j = 0; j < items + 1; j++) {
                        str = itlAdapter.getStr();
                        if (j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            str.setSectionSubtitle("(" + getResources().getString(R.string.device_available));
                            str.setSectionBackground(R.drawable.ipad_pro);
                            sectionList.add(str);
                        } else {
                            if (icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if (titles != null)
                                str.setSectionTitle(titles[position]);
                            if (subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if (notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
                case 3: //iPad touches
                    if (tabNumber == 0)
                        items = touchesCount;
                    else
                        items = availTouches;

                    for (int j = 0; j < items + 1; j++) {
                        str = itlAdapter.getStr();
                        if (j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            str.setSectionSubtitle("(" + getResources().getString(R.string.device_available));
                            str.setSectionBackground(R.drawable.ipod_touches);
                            sectionList.add(str);
                        } else {
                            if (icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if (titles != null)
                                str.setSectionTitle(titles[position]);
                            if (subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if (notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
                case 4: //fitbits
                    if (tabNumber == 0)
                        items = fitbitsCount;
                    else
                        items = availFitbits;

                    for (int j = 0; j < items + 1; j++) {
                        str = itlAdapter.getStr();
                        if (j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            str.setSectionSubtitle("(" + getResources().getString(R.string.device_available));
                            str.setSectionBackground(R.drawable.fitbits);
                            sectionList.add(str);
                        } else {
                            if (icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if (titles != null)
                                str.setSectionTitle(titles[position]);
                            if (subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if (notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
                case 5: //accessories
                    if (tabNumber == 0)
                        items = accessoriesCount;
                    else
                        items = availAccess;

                    for (int j = 0; j < items + 1; j++) {
                        str = itlAdapter.getStr();
                        if (j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            str.setSectionSubtitle("(" + getResources().getString(R.string.device_available));
                            str.setSectionBackground(R.drawable.accessories);
                            sectionList.add(str);
                        } else {
                            if (icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if (titles != null)
                                str.setSectionTitle(titles[position]);
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

    public static void parseJSON(String jString) {
        //make arrays of devices and available devices

        JSONObject j;
        JSONArray jArray;
        ArrayList<JSONObject> airsList = new ArrayList<JSONObject>();
        ArrayList<JSONObject> minisList = new ArrayList<JSONObject>();
        ArrayList<JSONObject> prosList = new ArrayList<JSONObject>();
        ArrayList<JSONObject> touchesList = new ArrayList<JSONObject>();
        ArrayList<JSONObject> fitbitsList = new ArrayList<JSONObject>();
        ArrayList<JSONObject> accessoriesList = new ArrayList<JSONObject>();
        ArrayList<JSONObject> tempDevices = new ArrayList<JSONObject>();
        devices = new ArrayList<JSONObject>();      //hold all devices
        available_devices = new ArrayList<JSONObject>();    //hold available devices
        int status;  //device status

        availAirs = 0;
        availMinis = 0;
        availPros = 0;
        availTouches = 0;
        availFitbits = 0;
        availAccess = 0;
        totalAvail = 0;

        airsCount = 0;
        minisCount = 0;
        prosCount = 0;
        touchesCount = 0;
        fitbitsCount = 0;
        accessoriesCount = 0;

        try {
            jArray = new JSONArray(jString);

            //populate tempDevices
            for (int x = 0; x < jArray.length(); x++) {
                tempDevices.add(x, new JSONObject(jArray.getString(x)));
            }

            //loop through JSON devices array
            while (tempDevices.size() > 0) {
                /*do not show devices with specific status codes
                Status:
                1 available
                2 checked out
                3 temp unavailable
                4 ""
                5 ""
                10 ""
                11 ""
                else do not display
                */
                j = tempDevices.get(0);
                status = (int) j.getInt("status"); //get device status

                if (status == 1 || status == 2 || status == 3 || status == 4 || status == 5 || status == 10 || status == 11) {  //check status

                    //count number of devices in each category
                    if (j.getString("device_name").toLowerCase().contains("air")) {
                        airsCount++;
                        airsList.add(j);
                    } else if (j.getString("device_name").toLowerCase().contains("mini")) {
                        minisCount++;
                        minisList.add(j);
                    } else if (j.getString("device_name").toLowerCase().contains("pro")) {
                        prosCount++;
                        prosList.add(j);
                    } else if (j.getString("device_name").toLowerCase().contains("touch")) {
                        touchesCount++;
                        touchesList.add(j);
                    } else if (j.getString("device_name").toLowerCase().contains("fitbit")) {
                        fitbitsCount++;
                        fitbitsList.add(j);
                    } else {
                        accessoriesCount++;
                        accessoriesList.add(j);
                    }
                }
                tempDevices.remove(0);
            }

            //populate devices array in order of devices
            populateDevices(devices, available_devices, airsList);
            populateDevices(devices, available_devices, minisList);
            populateDevices(devices, available_devices, prosList);
            populateDevices(devices, available_devices, touchesList);
            populateDevices(devices, available_devices, fitbitsList);
            populateDevices(devices, available_devices, accessoriesList);

            airsList = null;
            minisList = null;
            prosList = null;
            touchesList = null;
            fitbitsList = null;
            accessoriesList = null;

            totalAvail = availAirs + availMinis + availPros + availTouches + availFitbits + availAccess;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void populateDevices(ArrayList<JSONObject> dev, ArrayList<JSONObject> avDev, ArrayList<JSONObject> list) {
        for (int x = 0; x < list.size(); x++) {
            dev.add(list.get(x));    //populate array of devices to show
            try {
                if (list.get(x).getInt("status") == 1) {    //get devices with available status
                    available_devices.add(list.get(x));
                    if (list.get(x).getString("device_name").toLowerCase().contains("air"))
                        availAirs++;
                    else if (list.get(x).getString("device_name").toLowerCase().contains("mini"))
                        availMinis++;
                    else if (list.get(x).getString("device_name").toLowerCase().contains("pro"))
                        availPros++;
                    else if (list.get(x).getString("device_name").toLowerCase().contains("touch"))
                        availTouches++;
                    else if (list.get(x).getString("device_name").toLowerCase().contains("fitbit"))
                        availFitbits++;
                    else
                        availAccess++;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //click a listview item

        //View v = (View) parent.getItemAtPosition(position);

        ImageView pic = (ImageView) view.findViewById(R.id.list_image);

        if ((Integer)pic.getTag() != null) {  //if clicked item is not section header
            TextView title = (TextView) view.findViewById(R.id.list_title);

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.device_availability_dialog);
            dialog.setTitle(title.getText().toString());

            TextView m1 = (TextView) dialog.findViewById(R.id.device_message1_dialog);
            TextView m2 = (TextView) dialog.findViewById(R.id.device_message2_dialog);
            TextView m3 = (TextView) dialog.findViewById(R.id.device_message3_dialog);
            Button b = (Button) dialog.findViewById(R.id.device_dialog_ok);

            if ((int) pic.getTag() == R.drawable.available) {
                //device is available
                m1.setText(getResources().getString(R.string.device_avail_dialog));
                m2.setText(getResources().getString(R.string.device_status_reminder_dialog));
            } else if ((int) pic.getTag() == R.drawable.checked_out) {
                //device is checked out
                TextView subtitle = (TextView) view.findViewById(R.id.list_subtitle);
                m1.setText(String.format(getResources().getString(R.string.device_checkout_dialog), subtitle.getText().toString()));
                m2.setText(getResources().getString(R.string.device_status_reminder_dialog));
            } else {
                //device is not available
                m1.setText(getResources().getString(R.string.device_navail_dialog1));
                m2.setText(getResources().getString(R.string.device_navail_dialog2));
                m3.setText(getResources().getString(R.string.device_navail_dialog3));
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) m3
                        .getLayoutParams();

                mlp.setMargins(80, 0, 80, 80);
            }

            b.setText(getResources().getString(R.string.ok));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }
}

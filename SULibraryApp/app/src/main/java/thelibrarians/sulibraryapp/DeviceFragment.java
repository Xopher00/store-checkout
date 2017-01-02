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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * This fragment is for each tab of the DeviceAvailabilityFragment.java
 * 
 */
public class DeviceFragment extends Fragment implements AdapterView.OnItemClickListener {
    String[] sectionHeader;
    String[] titles;
    String[] subtitles;
	ArrayList<Integer> iconsArr = new ArrayList<Integer>();
    int[] icons;
	ArrayList<Integer> typesArr = new ArrayList<Integer>();
	int[] types; //sequential list of view types to be added to the listview
	ArrayList<String> strArr = new ArrayList<String>();
	String[] strings; //sequential list of strings to be added to the listview
	
    //ImgTxtListAdapter itlAdapter;
	ListviewAdapter adapter;
    ListView listView;
    static DeviceFilterFragment deviceFilter;
    int tabNumber;
    static int airsCount = 0, minisCount = 0, prosCount = 0, touchesCount = 0, fitbitsCount = 0, accessoriesCount = 0, totalCount = 0; //only tracks devices to be displayed
    static int availAirs = 0, availMinis = 0, availPros = 0, availTouches = 0, availFitbits = 0, availAccess = 0, totalAvail = 0; //only tracks devices to be displayed
	
	static ArrayList<JSONObject> devices; //hold all devices with certain statuses and filter
    static ArrayList<JSONObject> available_devices; //hold available devices that are not filtered
	
	static ArrayList<JSONObject> airsList;
    static ArrayList<JSONObject> minisList;
    static ArrayList<JSONObject> prosList;
    static ArrayList<JSONObject> touchesList;
    static ArrayList<JSONObject> fitbitsList;
    static ArrayList<JSONObject> accessoriesList;

    static ArrayList<JSONObject> availAirsList;
    static ArrayList<JSONObject> availMinisList;
    static ArrayList<JSONObject> availProsList;
    static ArrayList<JSONObject> availTouchesList;
    static ArrayList<JSONObject> availFitbitsList;
    static ArrayList<JSONObject> availAccessoriesList;

    public DeviceFragment(int sectionNumber) {
        tabNumber = sectionNumber;

    }

    public DeviceFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null)
            tabNumber = savedInstanceState.getInt("tab");

		devices = new ArrayList<JSONObject>();
		available_devices = new ArrayList<JSONObject>();
        deviceFilter = DeviceFilterFragment.getInstance();
		
		adapter = new ListviewAdapter(getActivity());
        adapter.setViewTypeAmount(2);

        filter(); //filter devices and add them to the list

        View view = inflater.inflate(R.layout.fragment_device_pager, container, false);

        //itlAdapter = new ImgTxtListAdapter(getActivity());

        listView = (ListView) view.findViewById(R.id.listView);

        //populateListView(sectionHeader, icons, titles, subtitles, null);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", tabNumber);
    }

    private void filter() {
        //search devices array for devices that should be filtered and filters them

        if(!deviceFilter.getDeviceMask().contains(Integer.valueOf(0))) { //airs not filtered
            populateDevices(airsList);

            //section header//
            typesArr.add(3);
            strArr.add(getResources().getString(R.string.airs));
            strArr.add("(" + availAirs + " " + getResources().getString(R.string.device_available) + ")");

            //section items//
            if(tabNumber == 0) {
                addToList(airsList);
            } else {
                addToList(availAirsList);
            }

        }
        if(!deviceFilter.getDeviceMask().contains(Integer.valueOf(1))) { //minis not filtered
            populateDevices(minisList);

            //section header//
            typesArr.add(3);
            strArr.add(getResources().getString(R.string.minis));
            strArr.add("(" + availMinis+ " " + getResources().getString(R.string.device_available) + ")");

            //section items//
            if(tabNumber == 0) {
                addToList(minisList);
            } else {
                addToList(availMinisList);
            }
        }
        if(!deviceFilter.getDeviceMask().contains(Integer.valueOf(2))) { //pros not filtered
            populateDevices(prosList);
            typesArr.add(3);
            strArr.add(getResources().getString(R.string.pros));
            strArr.add("(" + availPros+ " " + getResources().getString(R.string.device_available) + ")");

            //section items//
            if(tabNumber == 0) {
                addToList(prosList);
            } else {
                addToList(availProsList);
            }
        }
        if(!deviceFilter.getDeviceMask().contains(Integer.valueOf(3))) { //touches not filtered
            populateDevices(touchesList);
            typesArr.add(3);
            strArr.add(getResources().getString(R.string.touches));
            strArr.add("(" + availTouches+ " " + getResources().getString(R.string.device_available) + ")");

            //section items//
            if(tabNumber == 0) {
                addToList(touchesList);
            } else {
                addToList(availTouchesList);
            }
        }
        if(!deviceFilter.getDeviceMask().contains(Integer.valueOf(4))) { //fitbits not filtered
            populateDevices(fitbitsList);
            typesArr.add(3);
            strArr.add(getResources().getString(R.string.fitbits));
            strArr.add("(" + availFitbits+ " " + getResources().getString(R.string.device_available) + ")");

            //section items//
            if(tabNumber == 0) {
                addToList(fitbitsList);
            } else {
                addToList(availFitbitsList);
            }
        }
        if(!deviceFilter.getDeviceMask().contains(Integer.valueOf(5))) { //accessories not filtered
            populateDevices(accessoriesList);
            typesArr.add(3);
            strArr.add(getResources().getString(R.string.accessories));
            strArr.add("(" + availAccess+ " " + getResources().getString(R.string.device_available) + ")");

            //section items//
            if(tabNumber == 0) {
                addToList(accessoriesList);
            } else {
                addToList(availAccessoriesList);
            }
        }

        //arrayList to array
        strings = new String[strArr.size()];
        for(int x = 0; x < strArr.size(); x++) {
            strings[x] = strArr.get(x);
        }

        icons = new int[iconsArr.size()];
        for(int x = 0; x < iconsArr.size(); x++) {
            icons[x] = iconsArr.get(x);
        }

        types = new int[typesArr.size()];
        for(int x = 0; x < typesArr.size(); x++) {
            types[x] = typesArr.get(x);
        }

        //populate listview
        adapter.populate(types, strings, icons);
    }
	
	public void addToList(ArrayList<JSONObject> list) {
		for(int a = 0; a < list.size(); a++) {
			JSONObject ob = list.get(a);
			typesArr.add(2);
			try {
                //item name
                strArr.add(ob.getString("device_name"));

                //item icon
                switch (ob.getInt("status")) {
                    case 1:
                        iconsArr.add(R.drawable.available);
                        //item detail
                        if (!ob.getString("detail").equals("null"))
                            strArr.add(ob.getString("type_name") + " (" + ob.getString("detail") + ")");
                        else
                            strArr.add(ob.getString("type_name"));
                        break;
                    case 2:
                        iconsArr.add(R.drawable.checked_out);
                        //item detail
                        strArr.add(getResources().getString(R.string.device_due) + " " + devices.get(a).getString("due_date"));
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 10:
                    case 11:
                        iconsArr.add(R.drawable.unavailable);
                        //item detail
                        strArr.add(getResources().getString(R.string.device_unavailable));
                        break;
                    default:
                        iconsArr.add(R.drawable.unavailable);
                        //item detail
                        strArr.add(getResources().getString(R.string.device_unavailable));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
		}
	}
	
/*
    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each array, shared between arrays
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for (int i = 0; i < sectionHeader.length; i++) {

            int items = 0;  //number of items per section

            if (!deviceFilter.getDeviceMask().contains(Integer.valueOf(i))) { //if the device is not filtered
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
                                str.setSectionSubtitle("(" + availAirs + " " + getResources().getString(R.string.device_available) + ")");
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
                                str.setSectionSubtitle("(" + availMinis + " " + getResources().getString(R.string.device_available)+")");
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
                                str.setSectionSubtitle("(" + availPros + " " + getResources().getString(R.string.device_available)+")");
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
                                str.setSectionSubtitle("(" + availTouches + " " + getResources().getString(R.string.device_available)+")");
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
                                str.setSectionSubtitle("(" + availFitbits + " " + getResources().getString(R.string.device_available)+")");
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
                                str.setSectionSubtitle("(" + availAccess + " " + getResources().getString(R.string.device_available)+")");
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
    }
*/
    public static void parseJSON(String jString) {
        //make arrays of devices and available devices

        JSONObject j;
        JSONArray jArray;
		
		airsList = new ArrayList<JSONObject>();
        minisList = new ArrayList<JSONObject>();
        prosList = new ArrayList<JSONObject>();
        touchesList = new ArrayList<JSONObject>();
        fitbitsList = new ArrayList<JSONObject>();
        accessoriesList = new ArrayList<JSONObject>();
		
		availAirsList = new ArrayList<JSONObject>();
        availMinisList = new ArrayList<JSONObject>();
        availProsList = new ArrayList<JSONObject>();
        availTouchesList = new ArrayList<JSONObject>();
        availFitbitsList = new ArrayList<JSONObject>();
        availAccessoriesList = new ArrayList<JSONObject>();
		
        ArrayList<JSONObject> tempDevices = new ArrayList<JSONObject>();    //hold all devices regardless of status and filter
        int status;  //device status

        nullCounts();

        try {
            jArray = new JSONArray(jString);

            //populate tempDevices; may hold devices that will not appear in listview
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
						if(j.getInt("status") == 1) {
							availAirs++;
							availAirsList.add(j);
						}
                    } else if (j.getString("device_name").toLowerCase().contains("mini")) {
                        minisCount++;
                        minisList.add(j);
						if(j.getInt("status") == 1) {
							availMinis++;
							availMinisList.add(j);
						}
                    } else if (j.getString("device_name").toLowerCase().contains("pro")) {
                        prosCount++;
                        prosList.add(j);
						if(j.getInt("status") == 1) {
							availPros++;
						}
                    } else if (j.getString("device_name").toLowerCase().contains("touch")) {
                        touchesCount++;
                        touchesList.add(j);
						if(j.getInt("status") == 1) {
							availTouches++;
							availTouchesList.add(j);
						}
                    } else if (j.getString("device_name").toLowerCase().contains("fitbit")) {
                        fitbitsCount++;
                        fitbitsList.add(j);
						if(j.getInt("status") == 1) {
							availFitbits++;
							availFitbitsList.add(j);
						}
                    } else {
                        accessoriesCount++;
                        accessoriesList.add(j);
						if(j.getInt("status") == 1) {
							availAccess++;
							availAccessoriesList.add(j);
						}
                    }
                }
                tempDevices.remove(0);
            }
            totalCount = airsCount + minisCount + prosCount + touchesCount + fitbitsCount + accessoriesCount;

            totalAvail = availAirs + availMinis + availPros + availTouches + availFitbits + availAccess;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void populateDevices(ArrayList<JSONObject> list) {
        //populate array of devices; count available number of each device

        for (int x = 0; x < list.size(); x++) {
            try {
                devices.add(list.get(x));    //populate array of devices to show

                if (list.get(x).getInt("status") == 1) {    //get devices with available status
					available_devices.add(list.get(x));
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

        if ((Integer) pic.getTag() != null) {  //if clicked item is not section header
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

    public static void nullCounts() {
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
        totalCount = 0;
    }
}

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
    ArrayList<ListItem> listItems;
    ListviewX lix;
    ListView listView;
    static DeviceFilterFragment deviceFilter;
    int tabNumber;
    static int airsCount = 0, minisCount = 0, prosCount = 0, touchesCount = 0, fitbitsCount = 0, accessoriesCount = 0, totalCount = 0; //only tracks devices to be displayed
    static int availAirs = 0, availMinis = 0, availPros = 0, availTouches = 0, availFitbits = 0, availAccess = 0, totalAvail = 0; //only tracks devices to be displayed

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

        deviceFilter = DeviceFilterFragment.getInstance();

        lix = new ListviewX(getActivity());
        listItems = new ArrayList<ListItem>();

        filter(); //filter devices and add them to the list

        View view = inflater.inflate(R.layout.fragment_device_pager, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(lix);
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

        String availableString = getResources().getString(R.string.device_available) + ")";
        boolean[] mask = deviceFilter.getDeviceMask();

        ListItem3 li3;

        if(!mask[0]) { //airs not filtered
            //section header//
            li3 = new ListItem3(getActivity(), getResources().getString(R.string.airs), "(" + availAirs + " " + availableString);
            li3.getLayout().setBackgroundResource(R.drawable.ipad_airs);
            listItems.add(li3);

            //section items//
            if(tabNumber == 0) {
                addToList(airsList);
            } else {
                addToList(availAirsList);
            }

        }
        if(!mask[1]) { //minis not filtered
            //section header//
            li3 = new ListItem3(getActivity(), getResources().getString(R.string.minis), "(" + availMinis + " " + availableString);
            li3.getLayout().setBackgroundResource(R.drawable.ipad_minis);
            listItems.add(li3);

            //section items//
            if(tabNumber == 0) {
                addToList(minisList);
            } else {
                addToList(availMinisList);
            }
        }
        if(!mask[2]) { //pros not filtered

            li3 = new ListItem3(getActivity(), getResources().getString(R.string.pros), "(" + availPros + " " + availableString);
            li3.getLayout().setBackgroundResource(R.drawable.ipad_pro);
            listItems.add(li3);

            //section items//
            if(tabNumber == 0) {
                addToList(prosList);
            } else {
                addToList(availProsList);
            }
        }
        if(!mask[3]) { //touches not filtered

            li3 = new ListItem3(getActivity(), getResources().getString(R.string.touches), "(" + availTouches + " " + availableString);
            li3.getLayout().setBackgroundResource(R.drawable.ipod_touches);
            listItems.add(li3);

            //section items//
            if(tabNumber == 0) {
                addToList(touchesList);
            } else {
                addToList(availTouchesList);
            }
        }
        if(!mask[4]) { //fitbits not filtered

            li3 = new ListItem3(getActivity(), getResources().getString(R.string.fitbits), "(" + availFitbits + " " + availableString);
            li3.getLayout().setBackgroundResource(R.drawable.fitbits);
            listItems.add(li3);

            //section items//
            if(tabNumber == 0) {
                addToList(fitbitsList);
            } else {
                addToList(availFitbitsList);
            }
        }
        if(!mask[5]) { //accessories not filtered

            li3 = new ListItem3(getActivity(), getResources().getString(R.string.accessories), "(" + availAccess + " " + availableString);
            li3.getLayout().setBackgroundResource(R.drawable.accessories);
            listItems.add(li3);

            //section items//
            if(tabNumber == 0) {
                addToList(accessoriesList);
            } else {
                addToList(availAccessoriesList);
            }
        }

        //populate listview
        lix.populate(listItems);
    }
	
	public void addToList(ArrayList<JSONObject> list) {
        //populates arrays to be added to the listview

        String itemName;
        String itemStatusString;
        int itemImage;

		for(int a = 0; a < list.size(); a++) {
			JSONObject ob = list.get(a);
			try {
                //item name
                itemName = ob.getString("device_name");

                //item icon
                switch (ob.getInt("status")) {
                    case 1:
                        itemImage = R.drawable.available;
                        //item detail
                        if (!ob.getString("detail").equals("null"))
                            itemStatusString = ob.getString("type_name") + " (" + ob.getString("detail") + ")";
                        else
                            itemStatusString = ob.getString("type_name");
                        break;
                    case 2:
                        itemImage = R.drawable.checked_out;
                        //item detail
                        itemStatusString = getResources().getString(R.string.device_due) + " " + formatDate(ob.getString("due_date"));
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 10:
                    case 11:
                        itemImage = R.drawable.unavailable;
                        //item detail
                        itemStatusString = getResources().getString(R.string.device_unavailable);
                        break;
                    default:
                        itemImage = R.drawable.unavailable;
                        //item detail
                        itemStatusString = getResources().getString(R.string.device_unavailable);
                }

                listItems.add(new ListItem2(getActivity(), itemImage, itemName, itemStatusString));
            } catch (JSONException e) {
                e.printStackTrace();
            }
		}
	}

    private String formatDate(String d) {
        String date = null;
        String[] pieces = d.split("-");

        switch(pieces[1]) {
            case "01":
                date = "January";
                break;
            case "02":
                date = "February";
                break;
            case "03":
                date = "March";
                break;
            case "04":
                date = "April";
                break;
            case "05":
                date = "May";
                break;
            case "06":
                date = "June";
                break;
            case "07":
                date = "July";
                break;
            case "08":
                date = "August";
                break;
            case "09":
                date = "September";
                break;
            case "10":
                date = "October";
                break;
            case "11":
                date = "November";
                break;
            case "12":
                date = "December";
        }

        date += " " + pieces[2] + ", " + pieces[0];

        return date;
    }

    public static void parseJSON(String jString) {
        //make arrays of devices and available devices

        JSONObject j;
        JSONArray jArray;
        int status;  //device status
		
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

        nullCounts();

        try {
            jArray = new JSONArray(jString);

            //loop through JSON devices array
            for (int x = 0; x < jArray.length(); x++) {
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
                j = new JSONObject(jArray.getString(x));
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
                            availProsList.add(j);
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
            }
            totalCount = airsCount + minisCount + prosCount + touchesCount + fitbitsCount + accessoriesCount;

            totalAvail = availAirs + availMinis + availPros + availTouches + availFitbits + availAccess;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //click a listview item

        ListItem li = (ListItem) parent.getItemAtPosition(position);

        if (li.getType() == 2) {  //if clicked item is not section header
            ListItem2 li2 = (ListItem2) li;

            TextView title = (TextView) li2.getTextView1();
			ImageView pic = (ImageView) li2.getImageView();

            Log.i("nick", "title "+title.getText());

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.device_availability_dialog);
            dialog.setTitle(title.getText().toString());

            TextView m1 = (TextView) dialog.findViewById(R.id.device_message1_dialog);
            TextView m2 = (TextView) dialog.findViewById(R.id.device_message2_dialog);
            TextView m3 = (TextView) dialog.findViewById(R.id.device_message3_dialog);
            Button b = (Button) dialog.findViewById(R.id.device_dialog_ok);

            String tag = String.valueOf(pic.getTag()); //tag is set in each ListItem class

            //set text on dialog
            if (tag.equals(String.valueOf(R.drawable.available))) { //device is available
                //device is available
                m1.setText(getResources().getString(R.string.device_avail_dialog));
                m2.setText(getResources().getString(R.string.device_status_reminder_dialog));
            } else if (tag.equals(String.valueOf(R.drawable.checked_out))) { //device is checked out
                //device is checked out
                TextView subtitle = (TextView) view.findViewById(R.id.text_item2_2);
                m1.setText(String.format(getResources().getString(R.string.device_checkout_dialog), subtitle.getText().toString()));
                m2.setText(getResources().getString(R.string.device_status_reminder_dialog));
            } else { //device is out of circulation
                //device is not available
                m1.setText(getResources().getString(R.string.device_navail_dialog1));
                m2.setText(getResources().getString(R.string.device_navail_dialog2));
                m3.setText(getResources().getString(R.string.device_navail_dialog3));
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) m3.getLayoutParams();

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

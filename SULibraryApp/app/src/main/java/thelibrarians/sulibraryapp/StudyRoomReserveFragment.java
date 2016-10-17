package thelibrarians.sulibraryapp;

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

/**
 * Created by Xopher on 10/3/2016.
 */
//this fragment displays a list of study rooms in the library, al=nd whether or not they are available

public class StudyRoomReserveFragment extends Fragment implements AdapterView.OnItemClickListener {

    static int position;
    ListView listViewsrr; //listView study room reservation
    //array of headers pulled from kris_strings.xml
    String[] sectionHeader;
    //array of items pulled from kris_strings.xml
    String[] items;
    //array of icons to be matched to array of items, pulled from drawable folder
    int[] icons ={R.drawable.group_study_medium, R.drawable.group_study_medium, R.drawable.group_study_small,
            R.drawable.group_study_large, R.drawable.group_study_large,  R.drawable.group_study_small, R.drawable.group_study_large,
            R.drawable.group_study_medium, R.drawable.group_study_large, R.drawable.group_study_large, R.drawable.group_study_large,
            R.drawable.group_study_medium, R.drawable.group_study_medium, R.drawable.group_study_medium, R.drawable.group_study_medium};
    ImgTxtListAdapter itlAdapter;

    public StudyRoomReserveFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sectionHeader = getResources().getStringArray(R.array.study_room_headers);
        items = getResources().getStringArray(R.array.study_rooms);

        View view = inflater.inflate(R.layout.fragment_study_room_reserve, container, false);

        itlAdapter = new ImgTxtListAdapter(getActivity());

        listViewsrr = (ListView) view.findViewById(R.id.listViewsrr);

        //add and call populateListView()
        populateListView(sectionHeader, icons, items, null, null);

        listViewsrr.setAdapter(itlAdapter);
        listViewsrr.setOnItemClickListener(this);

        return view;
    }

    /*https://api2.libcal.com/1.0/room_availability/?iid=823&room_id=%td&limit=150&extend=1&key=d095e46065538df2f67eb7cf7d483896

    http://salisbury.beta.libcal.com/rooms_acc.php?gid=%td */

    public void populateListView(String[] sectionHeader, int[] icons, String[] titles, String[] subTitles, String[] notes) {
        int position = 0;  //current position in each item array
        ImgTxtListAdapter.SectionStructure str;
        ArrayList<ImgTxtListAdapter.SectionStructure> sectionList = itlAdapter.getSectionStructure();

        for(int i=0; i<sectionHeader.length; i++){

            int items = 0;  //number of items per section

            //number of case statements is the number of sections
            //this fragment has two sections
            switch(i) {
                case 0:
                    items = 2; //2 first floor study rooms
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if(titles != null)
                                str.setSectionTitle(titles[position]);
                            if(subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if(notes != null)
                                str.setSectionNote(notes[position]);
                            sectionList.add(str);
                            position++;
                        }
                    }
                    break;
                case 1:
                    items = 13; //13 second floor study rooms
                    for(int j = 0; j < items+1; j++) {
                        str = itlAdapter.getStr();
                        if(j == 0) {
                            str.setSectionName(sectionHeader[i]);
                            str.setSectionTitle("");
                            sectionList.add(str);
                        } else {
                            if(icons != null)
                                str.setSectionImage(icons[position]);
                            str.setSectionName("");
                            if(titles != null)
                                str.setSectionTitle(titles[position]);
                            if(subTitles != null)
                                str.setSectionSubtitle(subTitles[position]);
                            if(notes != null)
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
        this.position = position;
        Fragment p1; FragmentManager fragmentManager; FragmentTransaction fragmentTransaction;
        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        switch(position) {
            //show respective study room and availability
            case 1://room 139
                p1 = new RoomDeetsFragment();//opens new room_deets fragment
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 2://room 140
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            //case 3 refers to section header 2
            case 4://room 225
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 5://room 226
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 6://room 227
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 7://room 228
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 8://room 234
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 9://room 235
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 10://room 236
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 11://room 237
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 12://room 238
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 13://room 239
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 14://room 240
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 15://room 241
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 16://room 242
                p1 = new RoomDeetsFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, p1);
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }
    }

    //inner class for RoomDeetsFragment, to be called when item is clicked on
    public static class RoomDeetsFragment extends Fragment{

        int id;
        TextView roomName;
        TextView roomAvail;
        TextView roomCap;
        TextView roomLoc;
        TextView roomTime;
        TextView roomWall;
        TextView roomBoard;
        TextView roomReserve;
        ImageView roomImage;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            View roomView = inflater.inflate(R.layout.fragment_room_deets, container, false);

            //create ImageView object
            //assign ImageView id
            roomImage = (ImageView) roomView.findViewById(R.id.roomImage);

            //create TextView Objects
            //assign TextView id's to them
            roomName = (TextView) roomView.findViewById(R.id.roomName);
            roomAvail = (TextView) roomView.findViewById(R.id.roomAvail);
            roomCap = (TextView) roomView.findViewById(R.id.roomCap);
            roomLoc = (TextView) roomView.findViewById(R.id.roomLoc);
            roomWall = (TextView) roomView.findViewById(R.id.roomWall);
            roomTime = (TextView) roomView.findViewById(R.id.roomTime);
            roomBoard = (TextView) roomView.findViewById(R.id.roomBoard);
            roomReserve = (TextView) roomView.findViewById(R.id.roomReserve);

            //passes parameters to new room_deets fragment depending on which item clicked
            //parameters contain specific info pertaining to respective room, corresponding to item in string array
            switch(position){
                    case 1:
                        //room id is 42092; group id is 14005
                        roomView.setId(id/42092);
                        roomImage.setImageResource(R.drawable.ac139);//used to pass an image to fragment
                        roomName.setText("Room 139");
                        roomAvail.setText("First Come, First Served");
                        roomReserve.setVisibility(View.GONE);
                        roomWall.setVisibility(View.GONE);
                        roomCap.setText("Up to 6 People");
                        roomLoc.setText("First Floor, near Cafe");
                        roomBoard.setText("1 Whiteboard");
                        break;
                    case 2:
                        //room id is 42093; group id is 14006
                        roomView.setId(id/42093);
                        roomImage.setImageResource(R.drawable.ac140);
                        roomName.setText("Room 140");
                        roomAvail.setText("First Come, First Served");
                        roomReserve.setVisibility(View.GONE);
                        roomWall.setVisibility(View.GONE);
                        roomCap.setText("Up to 6 People");
                        roomLoc.setText("First Floor, near Cafe");
                        roomBoard.setText("1 Whiteboard");
                        break;
                    case 4:
                        //room id is 42094; group id is 14007
                        roomView.setId(id/42094);
                        roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                        roomName.setText("Room 225");
                        roomCap.setText("Up to 4 People");
                        roomLoc.setText("Second floor, across from ID&D");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("1 Whiteboard");
                        roomWall.setText("Window (facing Henson lawn");
                        break;
                    case 5:
                        //room id is 42095; group id is 14008
                        roomView.setId(id/42095);
                        roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                        roomName.setText("Room 226");
                        roomCap.setText("Up to 10 People");
                        roomLoc.setText("Second floor, across from ID&D");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setText("Window (facing Henson lawn");
                        break;
                    case 6:
                        //room id is 42096; group id is 14009
                        roomView.setId(id/42096);
                        roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                        roomName.setText("Room 227");
                        roomCap.setText("Up to 10 People");
                        roomLoc.setText("Second floor, across from ID&D");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setVisibility(View.GONE);
                        break;
                    case 7:
                        //room id is 42097; group id is 14010
                        roomView.setId(id/42096);
                        roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                        roomName.setText("Room 228");
                        roomCap.setText("Up to 4 People");
                        roomLoc.setText("Second floor, across from ID&D");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setVisibility(View.GONE);
                        break;
                    case 8:
                        //room id is 42099; group id is 14013
                        roomView.setId(id/42099);
                        roomImage.setImageResource(R.drawable.ac234);
                        roomName.setText("Room 234");
                        roomCap.setText("Up to 10 People");
                        roomLoc.setText("Second floor, overlooking atrium");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setText("Glass wall facing atrium");
                        break;
                    case 9:
                        //room id is 42100; group id is 14014
                        roomView.setId(id/42100);
                        roomImage.setImageResource(R.drawable.ac225_26_27_28_35);
                        roomName.setText("Room 235");
                        roomCap.setText("Up to 6 People");
                        roomLoc.setText("Second floor, overlooking atrium");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setText("Glass wall facing atrium");
                        break;
                    case 10:
                        //room id is 42101; group id is 14015
                        roomView.setId(id/42101);
                        roomImage.setImageResource(R.drawable.ac236);
                        roomName.setText("Room 236");
                        roomCap.setText("Up to 10 People");
                        roomLoc.setText("Second floor, overlooking atrium");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setText("Glass wall facing atrium");
                        break;
                    case 11:
                        //room id is 42102; group id is 14016
                        roomView.setId(id/42102);
                        roomImage.setImageResource(R.drawable.ac237);
                        roomName.setText("Room 237");
                        roomCap.setText("Up to 10 People");
                        roomLoc.setText("Second floor, overlooking atrium");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setText("Glass wall facing atrium");
                        break;
                    case 12:
                        //room id is 42105; group id is 14017
                        roomView.setId(id/42105);
                        roomImage.setImageResource(R.drawable.ac238);
                        roomName.setText("Room 238");
                        roomCap.setText("Up to 10 People");
                        roomLoc.setText("Second floor, overlooking atrium");
                        roomTime.setVisibility(View.GONE);
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setText("Glass wall facing atrium");
                        break;
                    case 13:
                        //room id is 42106; group id is 14018
                        roomView.setId(id/42106);
                        roomImage.setImageResource(R.drawable.ac239);
                        roomName.setText("Room 239");
                        roomCap.setText("Up to 8 People");
                        roomLoc.setText("Second floor, near Cafe");
                        roomBoard.setText("1 Whiteboard");
                        roomWall.setText("Glass wall facing atrium");
                        break;
                    case 14:
                        //room id is 42107; group id is 14019
                        roomView.setId(id/42107);
                        roomImage.setImageResource(R.drawable.ac240);
                        roomName.setText("Room 240");
                        roomCap.setText("Up to 8 People");
                        roomLoc.setText("Second floor, near Cafe");
                        roomBoard.setText("1 Whiteboard");
                        roomWall.setVisibility(View.GONE);
                        break;
                    case 15:
                        //room id is 42108; group id is 14020
                        roomView.setId(id/42108);
                        roomImage.setImageResource(R.drawable.ac241);
                        roomName.setText("Room 241");
                        roomCap.setText("Up to 8 People");
                        roomLoc.setText("Second floor, near Cafe");
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setVisibility(View.GONE);
                        break;
                    case 16:
                        //room id is 42109; group id is 14021
                        roomView.setId(id/42109);
                        roomImage.setImageResource(R.drawable.ac242);
                        roomName.setText("Room 242");
                        roomCap.setText("Up to 8 People");
                        roomLoc.setText("Second floor, near Cafe");
                        roomBoard.setText("2 Whiteboards");
                        roomWall.setVisibility(View.GONE);
                        break;
                }

            return roomView;
        }
    }
}



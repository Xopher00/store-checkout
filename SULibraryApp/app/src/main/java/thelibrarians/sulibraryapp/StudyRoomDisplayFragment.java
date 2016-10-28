package thelibrarians.sulibraryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StudyRoomDisplayFragment extends Fragment{

    int id, position;
    Integer groupid;
    TextView roomName;
    TextView roomAvail;
    TextView roomCap;
    TextView roomLoc;
    TextView roomTime;
    TextView roomWall;
    TextView roomBoard;
    TextView roomReserve;
    ImageView roomImage;

    public StudyRoomDisplayFragment(int position){
        this.position = position;
    }

    public StudyRoomDisplayFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View roomView = inflater.inflate(R.layout.fragment_study_room_display, container, false);

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
        roomReserve = (Button) roomView.findViewById(R.id.reserveButton);
        roomReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = new String("http://salisbury.libcal.com/rooms_acc.php?gid=");
                url = url.concat(groupid.toString());
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        //passes parameters to new room_deets fragment depending on which item clicked
        //parameters contain specific info pertaining to respective room, corresponding to item in string array
        switch(position){
            case 1:
                //room id is 42092; group id is 14005
                roomView.setId(id/42092);
                groupid=14005;
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
                groupid=14006;
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
                groupid=14007;
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
                groupid=14008;
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
                groupid=14009;
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
                groupid=14010;
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
                groupid=14013;
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
                groupid = 14014;
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
                groupid = 14015;
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
                groupid = 14016;
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
                groupid = 14017;
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
                groupid=14018;
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
                groupid = 14019;
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
                groupid = 14020;
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
                groupid = 14201;
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


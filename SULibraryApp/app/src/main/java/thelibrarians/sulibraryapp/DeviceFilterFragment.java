package thelibrarians.sulibraryapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by njraf_000 on 10/22/2016.
 */

public class DeviceFilterFragment extends Fragment {

    View view;
    static DeviceFilterFragment df = null;
    ArrayList<Integer> deviceMask;
    AppCompatCheckBox[] checkboxes;

    private DeviceFilterFragment() {
        deviceMask = new ArrayList<Integer>();
        checkboxes = new AppCompatCheckBox[6];


    }

    public static DeviceFilterFragment getInstance() {
        if (df == null)
            df = new DeviceFilterFragment();

        return df;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_device_filter, container, false);


        checkboxes[0] = (AppCompatCheckBox) view.findViewById(R.id.air_check);
        checkboxes[1] = (AppCompatCheckBox) view.findViewById(R.id.mini_check);
        checkboxes[2] = (AppCompatCheckBox) view.findViewById(R.id.pro_check);
        checkboxes[3] = (AppCompatCheckBox) view.findViewById(R.id.touch_check);
        checkboxes[4] = (AppCompatCheckBox) view.findViewById(R.id.fitbit_check);
        checkboxes[5] = (AppCompatCheckBox) view.findViewById(R.id.accessory_check);

        for (int x = 0; x < checkboxes.length; x++) {
            checkboxes[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMask(v);
                }
            });
        }

        return view;
    }


    public ArrayList getDeviceMask() {
        return deviceMask;
    }

    public AppCompatCheckBox[] getCheckboxes() {
        return checkboxes;
    }

    public void setMask(View v) {

        switch(v.getId()) {
            case R.id.air_check:
                if (checkboxes[0].isChecked())
                    deviceMask.remove(Integer.valueOf(0));
                else
                    deviceMask.add(Integer.valueOf(0));
                break;
            case R.id.mini_check:
                if (deviceMask.contains(Integer.valueOf(1)))
                    deviceMask.remove(Integer.valueOf(1));
                else
                    deviceMask.add(Integer.valueOf(1));
                break;
            case R.id.pro_check:
                if (deviceMask.contains(Integer.valueOf(2)))
                    deviceMask.remove(Integer.valueOf(2));
                else
                    deviceMask.add(Integer.valueOf(2));
                break;
            case R.id.touch_check:
                if (deviceMask.contains(Integer.valueOf(3)))
                    deviceMask.remove(Integer.valueOf(3));
                else
                    deviceMask.add(Integer.valueOf(3));
                break;
            case R.id.fitbit_check:
                if (deviceMask.contains(Integer.valueOf(4)))
                    deviceMask.remove(Integer.valueOf(4));
                else
                    deviceMask.add(Integer.valueOf(4));
                break;
            case R.id.accessory_check:
                if (deviceMask.contains(Integer.valueOf(5)))
                    deviceMask.remove(Integer.valueOf(5));
                else
                    deviceMask.add(Integer.valueOf(5));
                break;
        }

    }
}

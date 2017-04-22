package thelibrarians.sulibraryapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by njraf_000 on 10/22/2016.
 */

public class DeviceFilterFragment extends Fragment {

    View view;
    static DeviceFilterFragment df = null;
    static boolean[] deviceMask = {false, false, false, false, false, false};
    AppCompatCheckBox[] checkboxes;
    DrawerToggleListener toggleListener;

    public DeviceFilterFragment() {
        //use getInstance()

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

        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }

    public boolean[] getDeviceMask() {
        return deviceMask;
    }

    public AppCompatCheckBox[] getCheckboxes() {
        return checkboxes;
    }

    public void setMask(View v) {

        switch(v.getId()) {
            case R.id.air_check:
                if (checkboxes[0].isChecked())
                    deviceMask[0] = false;
                else
                    deviceMask[0] = true;
                break;
            case R.id.mini_check:
                if (checkboxes[1].isChecked())
                    deviceMask[1] = false;
                else
                    deviceMask[1] = true;
                break;
            case R.id.pro_check:
                if (checkboxes[2].isChecked())
                    deviceMask[2] = false;
                else
                    deviceMask[2] = true;
                break;
            case R.id.touch_check:
                if (checkboxes[3].isChecked())
                    deviceMask[3] = false;
                else
                    deviceMask[3] = true;
                break;
            case R.id.fitbit_check:
                if (checkboxes[4].isChecked())
                    deviceMask[4] = false;
                else
                    deviceMask[4] = true;
                break;
            case R.id.accessory_check:
                if (checkboxes[5].isChecked())
                    deviceMask[5] = false;
                else
                    deviceMask[5] = true;
                break;
        }

    }
}

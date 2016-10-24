package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/21/2016.
 */

/*This fragment opens a dialog box on the screen within the Contact info fragment, which asks the
user if they want to either call or email a specified staff member, or cancel
*/

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
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



public class CallOrClickDialogFragment extends DialogFragment  {

    String[] names = getResources().getStringArray(R.array.contact_who);

    String staff = "";
    String email = "";
    String phone = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        int position = args.getInt("position", 0);//get arguments and pass to integer

        //depending on case from which frag is created, diff args are passed for diff staff
        switch(position){
            case 23:staff="Jan Bellistri";email="jlbellistri@salisbury.edu";phone="tel:4105436130";
                break;
            case 24:staff="Susan Brazer";email="sebrazer@salisbury.edu";phone="tel:4105464370";
                break;
            case 25:staff="S. Pilar Burton";email="";phone="tel:4105436312";
                break;//no email- ignore or make special case?
            case 26:staff="Mou Chakraborty";email="mxchakraborty@salisbury.edu";phone="tel:4105436131";
                break;
            case 27:staff="Helen Chaphe";email="hfchaphe@salisbury.edu";phone="tel:4105436130";
                break;
            case 28:staff="Fanuel Chirombo";email="fxchirombo@salisbury.edu";phone="tel:4106770116";
                break;
            case 29:staff="Stacy Cooper";email="srcooper@salisbury.edu";phone="tel:4105436130";
                break;
            case 30:staff="Tara Custer";email="thcuster@salisbury.edu";phone="tel:4105462642";
                break;
            case 31:staff="Beverly Dennis";email="bddennis@salisbury.edu";phone="tel:4105436132";
                break;
            case 32:staff="Caroline Eckardt";email="cmeckardt@salisbury.edu";phone="tel:4105487972";
                break;
            case 33:staff="Stephen Ford";email="saford@salisbury.edu";phone="tel:4105485972";
                break;
            case 34:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 35:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 36:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 37:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 38:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 39:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 40:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 41:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 42:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 43:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 44:staff="Leigh Ann Roye";email="";phone="tel:4105436312";//this is the only staff without an email.
                break; //ignore or make a special case? unlikely anyone will try actually contact her so I could get away wth it...
            case 45:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 46:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 47:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 48:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 49:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 50:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
            case 51:staff=names[position];email="@salisbury.edu";phone="tel:410";
                break;
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_call_click + staff)
                .setPositiveButton(R.string.call, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        //user calls staff
                        Intent dialer = new Intent(Intent.ACTION_DIAL);
                        dialer.setData(Uri.parse(phone));
                        startActivity(dialer);
                    }
                })
                .setPositiveButton(R.string.click, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        //user emails staff
                        Intent emailer = new Intent(Intent.ACTION_VIEW);
                        emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        emailer.setType("vnd.android.cursor.item/email");
                        emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {email});
                        startActivity(emailer);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

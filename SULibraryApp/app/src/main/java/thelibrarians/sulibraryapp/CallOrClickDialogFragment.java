package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 10/21/2016.
 */

/*This fragment opens a dialog box on the screen within the Contact info fragment, which asks the
user if they want to either call or email a specified staff member, or cancel
*/

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;


public class CallOrClickDialogFragment extends DialogFragment  {

    Activity act; // Activity needed because Fragment not "attached"

    //import string array containing names of staff
    String[] names;

    //init strings whose contents will be determined by arg
    String staff;
    String email;
    String phone;

    // Constructor passes activity object
    public CallOrClickDialogFragment(Activity act){
        this.act = act; // Allocates value to local activity object
        names = act.getResources().getStringArray(R.array.contact_who);
        staff = ""; // Assigns name
        email = ""; // Assigns email
        phone = ""; // Assigns phone
    }

    // Creates dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments(); // Gets passed int value
        int position = args.getInt("position", 0);//get arguments and pass to integer

        staff = names[position-4]; // Assigns name
        //depending on case from which frag is created, diff args are passed for diff staff
        switch (position) {
            case 16:
                email = "jlbellistri@salisbury.edu"; // Assigns email
                phone = "tel:4105436130"; // Assigns phone number
                break;
            case 17:
                email = "sebrazer@salisbury.edu";
                phone = "tel:4105464370";
                break;
            case 18:
                phone = "tel:4105436312";
                break;//no email- ignore or make special case?
            case 19:
                email = "mxchakraborty@salisbury.edu";
                phone = "tel:4105436131";
                break;
            case 20:
                email = "hfchaphe@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 21:
                email = "fxchirombo@salisbury.edu";
                phone = "tel:4106770116";
                break;
            case 22:
                email = "srcooper@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 23:
                email = "thcuster@salisbury.edu";
                phone = "tel:4105462642";
                break;
            case 24:
                email = "bddennis@salisbury.edu";
                phone = "tel:4105436132";
                break;
            case 25:
                email = "cmeckardt@salisbury.edu";
                phone = "tel:4105487972";
                break;
            case 26:
                email = "saford@salisbury.edu";
                phone = "tel:4105485972";
                break;
            case 27:
                email = "lahanscom@salisbury.edu";
                phone = "tel:4105436206";
                break;
            case 28:
                email = "bbhardy@salisbury.edu";
                phone = "tel:4105436133";
                break;
            case 29:
                email = "tahorner@salisbury.edu";
                phone = "tel:4105436312";
                break;
            case 30:
                email = "iljenkins@salisbury.edu";
                phone = "tel:4106774642";
                break;
            case 31:
                email = "amjones@salisbury.edu";
                phone = "tel:4106775478";
                break;
            case 32:
                email = "apkinsey@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 33:
                email = "jmkreines@salisbury.edu";
                phone = "tel:4106775339";
                break;
            case 34:
                email = "cklewis@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 35:
                email = "cslong@salisbury.edu";
                phone = "tel:4105482154";
                break;
            case 36:
                email = "jmmartin@salisbury.edu";
                phone = "tel:4105436135";
                break;
            case 37:
                email = "lmvanveen@salisbury.edu";
                phone = "tel:4105482193";
                break;
            case 38:
                email = "dtmessick@salisbury.edu";
                phone = "tel:4105436429";
                break;
            case 39:
                email = "jlparrigin@salisbury.edu";
                phone = "tel:4106770131";
                break;
            case 40:
                email = "impost@salisbury.edu";
                phone = "tel:4106770020";
                break;
            case 41:
                email = "arprichard@salisbury.edu";
                phone = "tel:4106770118";
                break;
            case 42:
                email = "ggrobb@salisbury.edu";
                phone = "tel:4105436234";
                break;
            case 43:
                phone = "tel:4105436312";//this is the only staff without an email.
                break; //ignore or make a special case? unlikely anyone will try actually contact her so I could get away wth it...
            case 44:
                email = "mxruddy@salisbury.edu";
                phone = "tel:4105484571";
                break;
            case 45:
                email = "ahschadt@salisbury.edu";
                phone = "tel:4105483236";
                break;
            case 46:
                email = "ljschiff@salisbury.edu";
                phone = "tel:4105489183";
                break;
            case 47:
                email = "eawallace@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 48:
                email = "klwilson@salisbury.edu";
                phone = "tel:4105436026";
                break;
            case 49:
                email = "cmwoodall@salisbury.edu";
                phone = "tel:4105436306";
                break;
            case 50:
                email = "mczimmerman@salisbury.edu";
                phone = "tel:4106770110";
                break;
        }

        // Use the Builder class for convenient dialog construction
        String ask = act.getResources().getString(R.string.contact_ask);
        ask = ask.concat(" ");
        ask = ask.concat(staff);
        ask = ask.concat("?");
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if(email.compareTo("") != 0) {
            String[] items = {"Call", "Email", "Cancel"};
            builder = builder.setTitle(ask)
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                                    dialer.setData(Uri.parse("tel:" + phone)); // Passes URI to intent
                                    startActivity(dialer); // Starts activity
                                    break;
                                case 1:
                                    Intent emailer = new Intent(Intent.ACTION_SEND); // Creates a new email send intent
                                    emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Sets flags for new intent
                                    emailer.setType("vnd.android.cursor.item/email"); // Sets type for new intent
                                    emailer.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); // Adds email address as default in To: field
                                    startActivity(emailer); // Starts activity
                                    break;
                                case 2:
                                    dismiss(); // Closes dialog
                                    break;
                            }
                        }
                    });
        }
        else{
            String[] items = {"Call", "Cancel"};
            builder = builder.setTitle(ask)
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                                    dialer.setData(Uri.parse("tel:" + phone)); // Passes URI to intent
                                    startActivity(dialer); // Starts activity
                                    break;
                                case 1:
                                    dismiss(); // Closes dialog
                                    break;
                            }
                        }
                    });
        }
        //Create the AlertDialog object and return it
        return builder.create(); // creates builder
    }
}


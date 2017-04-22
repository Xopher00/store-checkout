package thelibrarians.sulibraryapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

import static thelibrarians.sulibraryapp.CardInfoFragment.BAR_CODE;
import static thelibrarians.sulibraryapp.CardInfoFragment.FIRST_NAME;
import static thelibrarians.sulibraryapp.CardInfoFragment.LAST_NAME;

public class BarCodeFragment extends Fragment {

    Bundle bundle;
    View view;
    Fragment fragment;
    MainActivity ma;
    CardInfoFragment cardInfo;
    TextView ct, rt, nom, tv;
    ImageView iv;//invisible by default
    LinearLayout layout;//invisible by default
    View.OnClickListener ctListener, rtListener;
    // barcode data
    String barcode_data, firstName, lastName, fullName;//invisible by default
    ActionBar toolbar;
    SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("good", "onViewStateRestored");
        Activity activity = getActivity();

        //If Statement checks if keys for firstname, lastname, and barcode number in SharedPreference
        //are not null before making their contents visible
        if((activity.getSharedPreferences(FIRST_NAME, 0).getString(FIRST_NAME, null) != null ) &&
                (activity.getSharedPreferences(LAST_NAME, 0).getString(LAST_NAME, null) != null &&
                        (activity.getSharedPreferences(BAR_CODE, 0).getString(BAR_CODE, null) != null))) {
                //log statement used for debugging
                Log.e("good", "get barcode");
                //grabs name and bar code data from shared preferences
                settings = activity.getSharedPreferences(FIRST_NAME, 0);
                firstName = settings.getString(FIRST_NAME, null);
                settings = activity.getSharedPreferences(LAST_NAME, 0);
                lastName = settings.getString(LAST_NAME, null);
                settings = activity.getSharedPreferences(BAR_CODE, 0);
                barcode_data = settings.getString(BAR_CODE, null);
                ct.setText("Edit Card");//change text of add card
                layout.setVisibility(View.VISIBLE);//make layout containing barcode visible
                rt.setVisibility(View.VISIBLE);//make remove button visible
                nom.setVisibility(View.VISIBLE);//make name textbox visible
                tv.setVisibility(View.VISIBLE);//make barcode number textbox visible
                iv.setVisibility(View.VISIBLE);//make barcode visible
        }
        //name text
        nom = (TextView) view.findViewById(R.id.nom);
        fullName = firstName + " " + lastName;//concatenates first & last names into a single string
        nom.setText(fullName); //sets the text for the nom textfield = to fullName

        // barcode image
        iv = (ImageView)  view.findViewById(R.id.iv);
        try {//calls a set of methods at the bottom of this class file to convert barcode number into an actual barcode
            Bitmap bitmap = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 600, 300);
            iv.setImageBitmap(bitmap); //sets this barcode in the image field
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //displays the barcode number below the actual barcode in its own textfield
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(barcode_data);

        //log statement used for debugging
        Log.e("good", "end of onViewStateRestored");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("good", "onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mycard, container, false);
        ma = (MainActivity) getActivity();
        layout = (LinearLayout) view.findViewById(R.id.l);

        //set of actions to be done upon clicking Add Card/Edit Card
        ctListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a fragment to add a new card.
                // user will input values for barcode_data, firstName & lastName
                cardInfo = new CardInfoFragment();
                FragmentTransaction ft = ma.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, cardInfo);//opens a new instance of CardInfoFragment for taking in cardinfo
                Log.e("good", "barcode->mycard");
                ft.addToBackStack(null).commit();//adds current fragment to backstack before commiting
                MainActivity.pageStack.push(MainActivity.cardPage);
            }
        } ;

        //set of actions to be done upon clicking Remove
        rtListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*remove a card */
                //checks if remove button is visible before doing anything else as a form of errorchecking
                if(rt.getVisibility() == View.VISIBLE) {
                    //clears name and barcode from sharedpreferences
                    getContext().getSharedPreferences(FIRST_NAME, 0).edit().clear().apply();
                    getContext().getSharedPreferences(LAST_NAME, 0).edit().clear().apply();
                    getContext().getSharedPreferences(BAR_CODE, 0).edit().clear().apply();
                    iv.setImageBitmap(null);//sets image to null
                    Log.e("good", "remove card");
                    ct.setText("Add Card");//change text of add card
                    rt.setVisibility(View.GONE);//make remove button visible
                    nom.setVisibility(View.INVISIBLE);//makes textfields invisible
                    tv.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    layout.setVisibility(View.INVISIBLE);//make layout containing barcode invisible
                }
            }
        } ;

        //sets buttons and button listeners
        ct = (TextView) view.findViewById(R.id.addCard);
        ct.setOnClickListener(ctListener);
        rt = (TextView) view.findViewById(R.id.Remove);
        rt.setOnClickListener(rtListener);

        //name text
        nom = (TextView) view.findViewById(R.id.nom);
        fullName = firstName + " " + lastName;//concat first & last names
        nom.setText(fullName);

        // barcode image
        iv = (ImageView)  view.findViewById(R.id.iv);
        try {
            Bitmap bitmap = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 600, 300);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //barcode text
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(barcode_data);

        //rt.setVisibility(View.GONE);//make remove button visible
        nom.setText(null);
        tv.setText(null);
        iv.setImageBitmap(null);
        //change toolbar title
        toolbar = ma.getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.card));

        Log.e("good", "before return view");
        return view;
    }



    //call methods for generating barcode
    /**************************************************************
     * getting from com.google.zxing.client.android.encode.QRCodeEncoder
     *
     * See the sites below
     * http://code.google.com/p/zxing/
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
     */

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}
                /*if ((activity.getSharedPreferences(FIRST_NAME, 0).getString(FIRST_NAME, null).compareTo("null") != 0)
                && (activity.getSharedPreferences(LAST_NAME, 0).getString(LAST_NAME, null).compareTo("null") != 0)
                && (activity.getSharedPreferences(BAR_CODE, 0).getString(BAR_CODE, null).compareTo("null") != 0))*/

                /*@Override
                public void onResume() {
                super.onResume();
                getFragmentManager().beginTransaction().replace(R.id.content_container, this).commit();
                Log.e("good", "resume");
                }*/
                /* @Override
                public void onAttach(Context context) {
                super.onAttach(context);
                if (bundle != null){
                //grabs name and bar code data from bundle
                firstName = bundle.getString("one");
                lastName = bundle.getString("two");
                barcode_data = bundle.getString("three");
                }}*/
                /*FragmentManager fm = getFragmentManager();
                fm.popBackStack();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content_container, barCode).addToBackStack(null);
                Log.e("good", "card removed");
                ft.commit();*/
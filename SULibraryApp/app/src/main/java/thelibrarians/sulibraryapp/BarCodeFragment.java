package thelibrarians.sulibraryapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.Map;

public class BarCodeFragment extends Fragment {

    View view;
    Fragment fragment;
    MainActivity ma;
    CardInfoFragment cardInfo;
    TextView ct, rt, et;
    View.OnClickListener ctListener, rtListener, etListener;
    // barcode data
    String barcode_data, firstName, lastName, fullName;
    ActionBar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout l = new LinearLayout(getContext());
        l.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        l.setOrientation(LinearLayout.VERTICAL);

        //name text
        TextView nom = new TextView(getContext());
        nom.setGravity(Gravity.CENTER_HORIZONTAL);
        fullName = firstName + " " + lastName;//concat first & last names
        nom.setText(fullName);
        l.addView(nom);

        // barcode data
        barcode_data = "123456";//somehow gets data in from other fragment or user
        // barcode image
        Bitmap bitmap = null;
        ImageView iv = new ImageView(getContext());
        try {
            bitmap = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 600, 300);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        l.addView(iv);

        //barcode text
        TextView tv = new TextView(getContext());
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(barcode_data);
        l.addView(tv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mycard, container, false);
        ma = (MainActivity)getActivity();
        ctListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a fragment to add a new card.
                // user will input values for barcode_data, firstName & lastName
                cardInfo = new CardInfoFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, cardInfo);
                ft.addToBackStack(null).commit();
            }
        } ;
        rtListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a fragment to remove a card
            }
        } ;
        etListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a fragment to edit existing card info
                cardInfo = new CardInfoFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, cardInfo);
                ft.addToBackStack(null).commit();
            }
        } ;
        ct = (TextView) view.findViewById(R.id.addCard);
        ct.setOnClickListener(ctListener);
        rt = (TextView) view.findViewById(R.id.Remove);
        rt.setOnClickListener(rtListener);
        et = (TextView) view.findViewById(R.id.editCard);
        et.setOnClickListener(etListener);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.card));

        return view;
    }


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

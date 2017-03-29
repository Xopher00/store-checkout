package thelibrarians.sulibraryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

/**
 * Created by njraf_000 on 3/23/2017.
 */

public class CustomAlertDialogBuilder extends AlertDialog {

    private final Context mContext;
    private TextView mTitle;
    private TextView mMessage;

    public CustomAlertDialogBuilder(Context context) {
        super(context);
        mContext = context;

        View customTitle = View.inflate(mContext, R.layout.alert_dialog_title, null);
        mTitle = (TextView) customTitle.findViewById(R.id.alert_title);
        setCustomTitle(customTitle);

        View customMessage = View.inflate(mContext, R.layout.alert_dialog_message, null);
        mMessage = (TextView) customMessage.findViewById(R.id.dialog_message);
        setView(customMessage);

        //int divierId = context.getResources().getIdentifier("android:id/titleDivider", null, null);
        //View divider = customTitle.findViewById(divierId);
        //divider.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resources = mContext.getResources();
        int color = ContextCompat.getColor(mContext, R.color.colorPrimary);
        int titleDividerId = resources.getIdentifier("titleDivider", "id", "android");
        View titleDivider = getWindow().getDecorView().findViewById(titleDividerId);
        titleDivider.setBackgroundColor(color); // change divider color
    }

    @Override
    public void setTitle(int textResId) {
        mTitle.setText(textResId);
    }
    @Override
    public void setTitle(CharSequence text) {
        mTitle.setText(text);
    }

    public void setMessage(int textResId) {
        mMessage.setText(textResId);
    }

    @Override
    public void setMessage(CharSequence text) {
        mMessage.setText(text);
    }

}
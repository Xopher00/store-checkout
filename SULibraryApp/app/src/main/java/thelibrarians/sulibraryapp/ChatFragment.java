package thelibrarians.sulibraryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian");//requires login
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
            } ;
            TextView ct = (TextView) view.findViewById(R.id.chatMeUp);
            ct.setOnClickListener(listener);
            return view;
        }
    }

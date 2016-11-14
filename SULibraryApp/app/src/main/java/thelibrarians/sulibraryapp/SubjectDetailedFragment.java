package thelibrarians.sulibraryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.security.auth.Subject;

public class SubjectDetailedFragment extends Fragment {

    static int position;
    View view;
    ImageView img;
    ImageView icon;
    ImageView rectangle;
    ImageView circle;
    TextView title;

    public SubjectDetailedFragment() {
        // Required empty public constructor
    }

    public SubjectDetailedFragment(int tab){
        position = tab;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.subject_detailed, container, false);

            switch (position) {
                //Accounting & Legal Studies
                case 0:
                    img.setImageResource(R.drawable.ggrobb);
                    //Referencing the XML that will create the overall header for the page
                    icon.setImageResource(R.drawable.accounting3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Accounting & Legal Studies");
                    break;
                //Anthropology
                case 1:
                    //img.setImageResource(R.drawable.jlparrigin);
                    break;
                //Applied Health Physiology
                case 2:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Art & Art History
                case 3:
                    //img.setImageResource(R.drawable.cmeckardt);
                    break;
                //Biology
                case 4:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Business
                case 5:
                    //img.setImageResource(R.drawable.ggrobb);
                    break;
                //Chemistry
                case 6:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Communication Arts
                case 7:
                    //img.setImageResource(R.drawable.jlparrigin);
                   break;
                //Computer Science
                case 8:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Conflict Analysis & Dispute Resolution
                case 9:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Dance
                case 10:
                    //img.setImageResource(R.drawable.arprichard);
                    break;
                //Economics & Finance
                case 11:
                    //img.setImageResource(R.drawable.ggrobb);
                    break;
                //Education
                case 12:
                    //img.setImageResource(R.drawable.saford);
                    break;
                //Engineering
                case 13:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //English
                case 14:
                    //img.setImageResource(R.drawable.jlparrigin);
                    break;
                //English Language Institute
                case 15:
                    //img.setImageResource(R.drawable.lhanscom);
                    break;
                //Environmental Studies
                case 16:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Geography & Geosciences
                case 17:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Government Information
                case 18:
                    //img.setImageResource(R.drawable.ggrobb);
                    break;
                //Health & Sport Sciences
                case 19:
                    //img.setImageResource(R.drawable.cmeckardt);
                    break;
                //History
                case 20:
                    //img.setImageResource(R.drawable.jlparrigin);
                    break;
                //Information & Decision Sciences
                case 21:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Interdisciplinary Studies
                case 22:
                    //img.setImageResource(R.drawable.cmeckardt);
                    break;
                //Management & Marketing
                case 23:
                    //img.setImageResource(R.drawable.ggrobb);
                    break;
                //Mathematics
                case 24:
                    //img.setImageResource(R.drawable.sebrazer);
                    break;
                //Medical Laboratory Science
                case 25:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    break;

        }

        return view;
    }

}

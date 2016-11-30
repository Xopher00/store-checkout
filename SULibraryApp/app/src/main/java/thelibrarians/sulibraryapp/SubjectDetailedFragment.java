package thelibrarians.sulibraryapp;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
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
    ImageView imgView;
    ImageView icon;
    ImageView rectangle;
    ImageView circle;
    TextView title;
    LayerDrawable[] icons;

    public SubjectDetailedFragment() {
        // Required empty public constructor
    }

    public SubjectDetailedFragment(int tab){
        position = tab;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources r = getResources();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.subject_detailed, container, false);
        //imgView = (ImageView) view.findViewById(R.id.imgView);
        icon = (ImageView) view.findViewById(R.id.acc_icon);
        rectangle = (ImageView) view.findViewById(R.id.acc_header);
        circle = (ImageView) view.findViewById(R.id.acc_circle);
        title = (TextView) view.findViewById(R.id.acc_title);
        icons = new LayerDrawable[8];
            switch (position) {
                //Accounting & Legal Studies
                case 0:
                    //imgView.setImageResource(R.drawable.ggrobb);
                    //Referencing the XML that will create the overall header for the page
                    icon.setImageResource(R.drawable.accounting3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Accounting & Legal Studies");

                    //Create Librarian Icon for Subject
                    Drawable[] staffIconALS; //creates an array of layers for each icon
                    staffIconALS = new Drawable[2];
                    staffIconALS[0] = r.getDrawable(R.drawable.custom_circle_yellow); //r = real runtime object that you can use to call getDrawable method
                    staffIconALS[1] = r.getDrawable(R.drawable.ggrobb3x); //R = abstraction to the file system
                    LayerDrawable layerDrawable = new LayerDrawable(staffIconALS); //merges the two layers together
                    staffIconALS[0] = layerDrawable;
                    break;
                //Anthropology
                case 1:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.anthropology3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Anthropology");
                    break;
                //Applied Health Physiology
                case 2:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.ahp3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Applied Health & Physiology");
                    break;
                //Art & Art History
                case 3:
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.art3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Art & Art History");
                    break;
                //Biology
                case 4:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.biology3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Biology");
                    break;
                //Business
                case 5:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.business3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Business");
                    break;
                //Chemistry
                case 6:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.chemistry3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Chemistry");
                    break;
                //Communication Arts
                case 7:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.comm3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Communication Arts");
                    break;
                //Computer Science
                case 8:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.compsci3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Computer Science");
                    break;
                //Conflict Analysis & Dispute Resolution
                case 9:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.cadr3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Conflict Analysis & Dispute Resolution");
                    break;
                //Dance
                case 10:
                    //img.setImageResource(R.drawable.arprichard);
                    icon.setImageResource(R.drawable.dance3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Dance");
                    break;
                //Economics & Finance
                case 11:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.econ3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Economics & Finance");
                    break;
                //Education
                case 12:
                    //img.setImageResource(R.drawable.saford);
                    icon.setImageResource(R.drawable.education3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Education");
                    break;
                //Engineering
                case 13:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.engineering3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Engineering");
                    break;
                //English
                case 14:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.english3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("English");
                    break;
                //English Language Institute
                case 15:
                    //img.setImageResource(R.drawable.lhanscom);
                    icon.setImageResource(R.drawable.eli3x);
                    circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("English Language Institute");
                    break;
                //Environmental Studies
                case 16:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.environ3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Environmental Studies");
                    break;
                //Geography & Geosciences
                case 17:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.geog3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Geography & Geosciences");
                    break;
                //Government Information
                case 18:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.govt3x);
                    circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Government Information");
                    break;
                //Health & Sport Sciences
                case 19:
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.hss3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Health & Sport Sciences");
                    break;
                //History
                case 20:
                    //img.setImageResource(R.drawable.jlparrigin);
                    icon.setImageResource(R.drawable.history3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("History");
                    break;
                //Information & Decision Sciences
                case 21:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.ids3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Information & Decision Sciences");
                    break;
                //Interdisciplinary Studies
                case 22:
                    //img.setImageResource(R.drawable.cmeckardt);
                    icon.setImageResource(R.drawable.inter3x);
                    circle.setImageResource(R.drawable.custom_circle_red);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Interdisciplinary Studies");
                    break;
                //Management & Marketing
                case 23:
                    //img.setImageResource(R.drawable.ggrobb);
                    icon.setImageResource(R.drawable.mgmt3x);
                    circle.setImageResource(R.drawable.custom_circle_yellow);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Management & Marketing");
                    break;
                //Mathematics
                case 24:
                    //img.setImageResource(R.drawable.sebrazer);
                    icon.setImageResource(R.drawable.math3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Mathematics");
                    break;
                //Medical Laboratory Science
                case 25:
                    //img.setImageResource(R.drawable.mxchakraborty);
                    icon.setImageResource(R.drawable.mls3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Medical Laboratory Science");
                    break;
                //Military Science
                case 26:
                    icon.setImageResource(R.drawable.mil3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Military Science");
                    break;
                //Modern Languages
                case 27:
                    icon.setImageResource(R.drawable.modlang3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Modern Languages");
                    break;
                //Music
                case 28:
                    icon.setImageResource(R.drawable.music3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Music");
                    break;
                //Nursing
                case 29:
                    icon.setImageResource(R.drawable.nursing3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Nursing");
                    break;
                //Philosophy
                case 30:
                    icon.setImageResource(R.drawable.philosophy3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Philosophy");
                    break;
                //Physical Education
                case 31:
                    icon.setImageResource(R.drawable.physed3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Physical Education");
                    break;
                //Physics
                case 32:
                    icon.setImageResource(R.drawable.physics);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Physics");
                    break;
                //Political Science
                case 33:
                    icon.setImageResource(R.drawable.polisci3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Political Science");
                    break;
                //Psychology
                case 34:
                    icon.setImageResource(R.drawable.psychology3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Psychology");
                    break;
                //Respiratory Therapy
                case 35:
                    icon.setImageResource(R.drawable.resp3x);
                    circle.setImageResource(R.drawable.custom_circle_green);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Respiratory Therapy");
                    break;
                //Social Work
                case 36:
                    icon.setImageResource(R.drawable.socialwork3x);
                    circle.setImageResource(R.drawable.custom_circle_purple);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Social Work");
                    break;
                //Sociology
                case 37:
                    icon.setImageResource(R.drawable.sociology3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Sociology");
                    break;
                //Theatre
                case 38:
                    icon.setImageResource(R.drawable.theatre3x);
                    circle.setImageResource(R.drawable.custom_circle_blue);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Theatre");
                    break;

        }

        return view;
    }

}

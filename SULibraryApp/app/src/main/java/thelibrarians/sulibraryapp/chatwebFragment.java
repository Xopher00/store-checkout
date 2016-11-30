package thelibrarians.sulibraryapp;

/**
 * Created by Xopher on 11/18/2016.
 */

//this fragment makes sure there is only ever one webview in existence for the chat fragment
public class chatwebFragment extends webViewFragment {
    private static chatwebFragment single = null;
    private chatwebFragment(String urlstr){this.urlstr = urlstr;}
    public chatwebFragment(){getInstance();}

    //sets sttaic variable equal to webview
    public static chatwebFragment getInstance(String urlstr){

        if(single == null){
            single = new chatwebFragment(urlstr);
        }
        return single;
    }

    //overloaded function called when webview already exists, assumed webview already exists and returns single
    public static chatwebFragment getInstance(){
        return single;
    }

}

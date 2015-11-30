package ggc.apresentaoandroid;

import android.app.Activity;

/**
 * Created by Gilson on 29/11/2015.
 */
public class Constantes {
    private static Activity _Activity = null;

    public static Activity getActivity(){
        return _Activity;
    }

    public static void setActivity(Activity Activity){
        _Activity = Activity;
    }
}

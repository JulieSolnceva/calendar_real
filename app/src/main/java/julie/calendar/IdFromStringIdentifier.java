package julie.calendar;

import android.app.Activity;

public class IdFromStringIdentifier {

    public static int get(Activity activity, String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return activity.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

package julie.calendar.Feature;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Юлия on 29.01.2016.
 */
public class FeatureTest {
    public String thisClassName="FeatureTest";

    public static void  open(FragmentActivity activity,  FeatureTest ff ){
        FeatureUI ui=new FeatureUI();
        ui.write(activity, ff);

    }
}

package julie.calendar.Event;

import android.support.v4.app.FragmentActivity;

import julie.calendar.Feature.Time.FeatureTime;

/**
 * Created by Юлия on 21.01.2016.
 */
public class EventTime extends Event {

    public EventTime(FragmentActivity activity) throws Exception{
       super();
       FeatureTime featureTime=new FeatureTime(activity);

       features.add(featureTime);
    }

}

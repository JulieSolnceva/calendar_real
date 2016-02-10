package julie.calendar.Event;

import android.support.v4.app.FragmentActivity;

import julie.calendar.Feature.Teacher.FeatureTeacher;

/**
 * Created by Юлия on 26.01.2016.
 */
public class EventChild extends Event {

    public EventChild(FragmentActivity activity)  throws Exception{
      //  super(activity);
        FeatureTeacher featureTeacher=new FeatureTeacher(activity);
          features.add(featureTeacher);
    }

    public int getSectionId(){
        return 1;
    }
}

package julie.calendar.Event;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import julie.calendar.Feature.Feature;
import julie.calendar.Feature.FeatureTest;

/**
 * Created by Юлия on 29.01.2016.
 */
public class EventUI {
    Event event;
    FragmentActivity activity;

    public EventUI(FragmentActivity activity){
     //   this.event=event;
        this.activity=activity;
    }


    public void drawEditUI(LinearLayout eventLayout,LayoutInflater ltInflater) {
        Feature feature;
        Log.d("тест", "features.size()=" + event.features.size());
        for (int i = 0; i < event.features.size(); i++) {
            feature = event.features.get(i);
            feature.drawEditUI(eventLayout, ltInflater);
        }
    }

    public  void set(){
        FeatureTest featureTest=new FeatureTest();
        featureTest.thisClassName="dfgdgdfg";
        featureTest.open(activity,featureTest);

    }
}

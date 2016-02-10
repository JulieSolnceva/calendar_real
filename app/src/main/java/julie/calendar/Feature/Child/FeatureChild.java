package julie.calendar.Feature.Child;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import julie.calendar.Feature.Feature;
import julie.calendar.Feature.FeatureOnClickPerson;

/**
 * Created by Юлия on 14.01.2016.
 */
public class FeatureChild extends Feature {

    public FeatureChild(FragmentActivity activity) {
        super(activity);
      //  featureOnClickEdit= new FeatureOnClickEditPerson();
        featureOnClick= new FeatureOnClickPerson();
    }


    public boolean getRequired(){
        return true;
    }

    public String getDbColumnName(){
        return "person_id";
    }

    public String getDbTableName(){
        return "child_event";
    }

    public void drawEditUI( LinearLayout eventLayout, LayoutInflater ltInflater){}
    public String  save(){ return null;};
    public Object getValueNew(){
        return valueNew;
    }
}


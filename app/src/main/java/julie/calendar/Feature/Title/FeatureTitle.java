package julie.calendar.Feature.Title;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import julie.calendar.Feature.Feature;
import julie.calendar.Feature.FeatureEditView;
import julie.calendar.Feature.FeatureOnClickText;
import julie.calendar.IdFromStringIdentifier;

/**
 * Created by Юлия on 14.01.2016.
 */
public class FeatureTitle extends Feature {

    TextView storageViewEdit;

    public FeatureTitle(FragmentActivity activity) {
        super(activity);
        featureOnClick= new FeatureOnClickText();
        editBox=new FeatureEditView();
        editBox.layout="text_edit";
        editBox.titleId="textEditTitle";
        editBox.storageId="titleEdit";
        editBox.title="titleTitle";
    }

    public boolean getRequired(){
        return true;
    }

    public String getDbColumnName(){
        return "title";
    }

    public String getDbTableName(){
        return null;
    }


    public void drawEditUI(LinearLayout eventLayout, LayoutInflater ltInflater){
        Log.d("тест", "FetureTitle drawEditUI");
        // получение значений объекта editBox для формирования вьюх интерфейса
        int boxId= IdFromStringIdentifier.get(activity, editBox.layout, "layout", activity.getPackageName());
        int titleId= IdFromStringIdentifier.get(activity, editBox.titleId, "id", activity.getPackageName());
        int title= IdFromStringIdentifier.get(activity, editBox.title, "string", activity.getPackageName());
        int storageId= IdFromStringIdentifier.get(activity, editBox.storageId, "id", activity.getPackageName());

        // формирования вью интерфейса
        View box = ltInflater.inflate(boxId, eventLayout, false);

        storageViewEdit = (TextView) box.findViewById(storageId);
        TextView titleView = (TextView) box.findViewById(titleId);
        titleView.setText(activity.getResources().getString(title));
        box.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        box.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        eventLayout.addView(box);
        Log.d("тест", "FeatureTitle drawEditUI add");
    }


    public Object getValueNew(){
       if(storageViewEdit!=null)
            valueNew=storageViewEdit.getText();
       return valueNew;
    }
}

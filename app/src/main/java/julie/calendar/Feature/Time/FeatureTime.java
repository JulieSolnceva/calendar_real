package julie.calendar.Feature.Time;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import julie.calendar.Feature.Feature;
import julie.calendar.Feature.FeatureEditView;
import julie.calendar.IdFromStringIdentifier;

public class FeatureTime extends Feature {
    TextView storageViewEdit;

    public FeatureTime(FragmentActivity activity) {
        super(activity);
        editBox=new FeatureEditView();
        editBox.layout="time_edit";
        editBox.titleId="timeEditTitle";
        editBox.storageId="timeStorage";
        editBox.title= "timeTitle";
        editBox.setButtonId="setButton";
        //featureOnClickEdit= new FeatureOnClickEditTime(activity);
    }


    public boolean getRequired(){
        return true;
    }

    public String getDbColumnName(){
        return "time";
    }

    public String getDbTableName(){
        return null;
    }


    public void drawEditUI( LinearLayout eventLayout, LayoutInflater ltInflater){
        Log.d("тест", "FeatureTime drawEditUI");

        // получение значений объекта editBox для формирования вьюх интерфейса
        int boxId= IdFromStringIdentifier.get(activity, editBox.layout, "layout", activity.getPackageName());
        int titleId= IdFromStringIdentifier.get(activity, editBox.titleId, "id", activity.getPackageName());
        int title= IdFromStringIdentifier.get(activity, editBox.title, "string", activity.getPackageName());
        int storageId= IdFromStringIdentifier.get(activity, editBox.storageId, "id", activity.getPackageName());

        // формирования вью интерфейса
        View box = ltInflater.inflate(boxId, eventLayout, false);

        TextView titleView = (TextView) box.findViewById(titleId);
        titleView.setText(activity.getResources().getString(title));


       storageViewEdit = (TextView) box.findViewById(storageId);

        if(editBox.setButtonId!=null) {
            int setButtonId = IdFromStringIdentifier.get(activity, editBox.setButtonId, "id", activity.getPackageName());
            Button setButton = (Button) box.findViewById(setButtonId);

            //назначение метода на onClick
            setButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // performOnClickEdit(storageViewEdit);
                   FeatureOnClickEditTime editTime=new FeatureOnClickEditTime(activity);
                   editTime.openTimeDialog(storageViewEdit, valueNew);

                }
            });
        }
        box.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        box.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        eventLayout.addView(box);

        Log.d("тест", "FeatureTime drawEditUI +add");
    }

    public Object getValueNew(){
        return valueNew;
    }


}

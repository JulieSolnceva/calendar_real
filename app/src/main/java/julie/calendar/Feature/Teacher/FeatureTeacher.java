package julie.calendar.Feature.Teacher;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import julie.calendar.Feature.Feature;
import julie.calendar.Feature.FeatureEditView;
import julie.calendar.IdFromStringIdentifier;

/**
 * Created by Юлия on 25.01.2016.
 */
public class FeatureTeacher extends Feature {
    TextView storageViewEdit;

    public FeatureTeacher(FragmentActivity activity) {
        super(activity);
        editBox=new FeatureEditView();
        editBox.layout="teacher_edit";
        editBox.titleId="teacherEditTitle";
        editBox.storageId="teacherEdit";
        editBox.title= "teacherTitle";
    }


    public boolean getRequired(){
        return false;
    }

    public String getDbColumnName(){
        return "person_id";
    }

    public String getDbTableName(){
        return "teacher_event";
    }

    public void drawEditUI( LinearLayout eventLayout, LayoutInflater ltInflater){
        Log.d("тест", "FeatureTeacher drawEditUI");

        // получение значений объекта editBox для формирования вьюх интерфейса
        int boxId= IdFromStringIdentifier.get(activity, editBox.layout, "layout", activity.getPackageName());
        int titleId= IdFromStringIdentifier.get(activity, editBox.titleId, "id", activity.getPackageName());
        int title= IdFromStringIdentifier.get(activity, editBox.title, "string", activity.getPackageName());
        int storageId= IdFromStringIdentifier.get(activity, editBox.storageId, "id", activity.getPackageName());

        // формирования вью интерфейса
        View box = ltInflater.inflate(boxId, eventLayout, false);

        TextView titleView = (TextView) box.findViewById(titleId);
        titleView.setText(activity.getResources().getString(title));

        storageViewEdit = (EditText) box.findViewById(storageId);

        box.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        box.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        eventLayout.addView(box);
        Log.d("тест", "FeatureTeacher +add");
    }


    public Object getValueNew(){
        if(storageViewEdit!=null)
            valueNew=storageViewEdit.getText();
        return valueNew;
    }
}

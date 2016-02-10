package julie.calendar.Feature.Time;

import android.app.TimePickerDialog;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import julie.calendar.R;

/**
 * класс задаёт реакцию на выбор опции "задать время" - открывается диалог выбора времени
 */

public class FeatureOnClickEditTime {
    FragmentActivity activity;
    public TextView storageView;
    Object  value;

    public FeatureOnClickEditTime(FragmentActivity activity){
        this.activity=activity;
    }

    public void openTimeDialog(View view, Object  featureValue) {
            storageView=(TextView) view;
            value=featureValue;
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    storageView.setText( selectedHour + ":" + selectedMinute);
                    value=selectedHour + ":" + selectedMinute;
                }
            }, hour, minute, true);
            mTimePicker.setTitle(activity.getResources().getString(R.string.TimePickerDialogTitle));
            mTimePicker.show();
    }
}

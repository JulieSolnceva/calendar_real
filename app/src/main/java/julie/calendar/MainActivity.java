package julie.calendar;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import julie.calendar.Event.EventChild;
import julie.calendar.Event.EventUI;
import julie.calendar.Exeptions.InsertEventException;

public class MainActivity extends FragmentActivity {
    EventChild event;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout eventLayout=(LinearLayout)findViewById(R.id.eventBox);
        LayoutInflater ltInflater = getLayoutInflater();


        EventUI ui= new EventUI(this);
        ui.set();

        /*



        try {
            event=new EventChild(this);
            event.drawEditUI(eventLayout, ltInflater);
        }
        catch (Exception e){

            Log.d("тестMain", e.getClass().toString()+"  "+e.getMessage());
        }
*/

      //  EventTime eventTime=new EventTime(this);
      // eventTime.drawEditUI(eventLayout, ltInflater);

    }

    //сохранение события
    public void save(View v){
        //todo запихнуть в асинхронную задачу
        try {
            event.addEvent();
        }
        catch (InsertEventException e){
            //todo обработчик ошибки!!
            Toast.makeText(this, e.getMessage(),  Toast.LENGTH_LONG).show();
        }
    }

}

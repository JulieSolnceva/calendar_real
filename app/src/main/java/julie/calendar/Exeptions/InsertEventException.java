package julie.calendar.Exeptions;

import android.content.Context;
import julie.calendar.R;

/**
 * Исключение при ошибке добавления события и его свойств
 */


public class InsertEventException extends Exception {
    String message;

    public InsertEventException(Context context){
        super();
        message=context.getString(R.string.eventInsertError);
    }
    public String getMessage(){
        return  message;
    }
}

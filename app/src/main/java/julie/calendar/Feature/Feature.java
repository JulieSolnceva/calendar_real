package julie.calendar.Feature;

import android.content.ContentValues;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * свойство
 */
public abstract class Feature {
    public Object value; //значение поля
    public Object valueNew; //значение поля, заданное заново, но ещё не сохранённое
    protected FragmentActivity activity;
    public FeatureEditView editBox; //набор параметров для формирования блока редактирования свойства
    public FeatureOnClick featureOnClick; // задание поведения при клике на поле в режиме просмотра
  // public FeatureOnClickEdit featureOnClickEdit; // задание поведения при клике на поле в режиме редактирования


    public boolean required; //признак обязательное - необязательное свойство
    // (обязательное [required=true] хранится в таблице event, необязательное [required=false] - в связной таблице(например: тоблица для связи события и исполнителя)

    public String dbColumnName; //название стролбца в таблице event для сохранения обязательных полей
    public String dbTableName; //название связанной таблицы (для необязательных свойств)

    public Feature(FragmentActivity activity) {
       // this.activity=activity;


        initialization();
    }
    public void  performOnClick(){
        featureOnClick.onClick();
    }

  /*
    public void  performOnClickEdit(View view){
        featureOnClickEdit.onClickEdit(view);
    }*/

    //отрисовка UI для редактирования свойства
    public abstract void drawEditUI( LinearLayout eventLayout, LayoutInflater ltInflater);

    //метод, который отдаёт новое значение свойства (при редактировании например)
    public abstract Object getValueNew();

    //установка поля required
    public abstract boolean getRequired();

    //установка поля dbColumnName
    public abstract String getDbColumnName();

    //установка поля dbTableName
    public abstract String getDbTableName();


   private void initialization(){
       required=getRequired();
       dbTableName=getDbTableName();
       dbColumnName=getDbColumnName();
   }


    //получение значения свойства из UI для редактирования и сохранение этого значения
    //возвращает ContentValues для сохранение значения в БД
    public ContentValues getValueForSave(){
        ContentValues cv = new ContentValues();
        if(getValueNew()!=null) {
            String result = getValueNew().toString();
            cv.put(dbColumnName,result);
        }
        else cv.putNull(dbColumnName);
        return cv;
    }


}

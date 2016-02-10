package julie.calendar.Event;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import julie.calendar.Exeptions.InsertEventException;
import julie.calendar.Feature.Feature;

/**
 * класс СОБЫТИЕ
 *
 * обязательные поля: title, time
 */
public abstract class Event {
    private  final String TABLE_NAME="event";
    public ArrayList<Feature> features;
    private SQLiteDatabase dbActive;

    int sectionId;

    public Event()  throws Exception{
       // this.activity=activity;
       // dbActive= DB.getInstance(activity).dbActive;

        Log.d("тест", "Event! dbActive.isOpen() ="+dbActive.isOpen());
        features = new ArrayList<Feature>();

     //   FeatureTime featureTime=new FeatureTime(activity);
     //   FeatureTitle featureTitle=new FeatureTitle(activity);

    //    features.add(featureTitle);
     //   features.add(featureTime);
        initialization();
    }

   // public  abstract  int getSectionId();

    private void initialization(){

       //// TODO: 27.01.2016 написать проверку на существование sectionId в таблице section
        // если нет, то надо добавлять как-то????
      //  sectionId=getSectionId();
    }



  //добавление нового события (запускать в Async Task)
    public void addEvent()throws InsertEventException {
        ContentValues values=getValuesRequiredFeatures();
        values.put("section_id",sectionId);
        dbActive.beginTransaction();
        try {
                long id=dbActive.insert(TABLE_NAME, null, values);
                if(id!=-1)
                    insertNoRequiredFeatures(id);
              //  else throw new InsertEventException(activity);
                dbActive.setTransactionSuccessful();
        }
        catch (InsertEventException e){
            throw e;
        }
        finally {
            dbActive.endTransaction();
        }
    }

    //получение значений обязательных свойств
    private ContentValues getValuesRequiredFeatures(){
        ContentValues cv = new ContentValues();
        Feature feature;

        for (int i = 0; i < features.size(); i++) {
            feature = features.get(i);
            if(feature.required) {
                cv.putAll(feature.getValueForSave());
            }
        }
        return cv;
    }

    //получение значений необязательных свойств и сохранение их в БД
    private void insertNoRequiredFeatures(long id) throws InsertEventException {
        ContentValues cv = new ContentValues();
        cv.put("event_id",id);
        Feature feature;
      /*
        try{
            for (int i = 0; i < features.size(); i++) {
                feature = features.get(i);
                if(!feature.required) {
                    ContentValues values = new ContentValues();
                    values.putAll(cv);
                    values.putAll(feature.getValueForSave());
                    //если ошибка при добавлении, то вся транзакция откатывается
                  //  if(-1==dbActive.insert(feature.dbTableName, null, values))
                   //     throw new InsertEventException(activity);
                }
            }
        }
        catch (InsertEventException e){
            throw e;
        }*/
    }




}




package julie.calendar.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String LOG_TAG="DBHelper";
    private String dbFolder;
    public String dbPath;
    public String dbPathFile;
    private Context context;
    private static final String DB_NAME = "calendar";


    public SQLiteDatabase myDataBase;

    public DbHelper(Context cont) {
        super(cont, DB_NAME, null, 1);
        context=cont;
        dbFolder= "/data/data/" + context.getPackageName() + "/databases/";
      //  dbPath=dbFolder+ DB_NAME;
    }


    private String getDBFile(){
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return null;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        dbPath=sdPath.getAbsolutePath() + "/" + "Calendar/";


        return sdPath.getAbsolutePath() + "/" + "Calendar/"+DB_NAME;
    }



    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     * */
    public void createDataBase() throws Exception {
        if(!checkDataBase())
            copyDataBase();
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase() throws Exception {
        dbPathFile=getDBFile();

        Log.d(LOG_TAG, "dbPathFile=" +dbPathFile+"  dbPath="+dbPath);
        if( dbPathFile!=null) {
            File dbFile = new File(dbPathFile);
            return dbFile.exists();
        }
        //todo обработать ошибку "недоступна SD карта"
        else throw new Exception();
    }


    private void copyDataBase() throws Exception{
        Log.d(LOG_TAG, "copyDataBase");
        //Открываем локальную БД как входящий поток
        InputStream myInput = context.getAssets().open(DB_NAME);

        //вызывая этот метод создаем пустую базу, позже она будет перезаписана
        this.getReadableDatabase();



        // добавляем свой каталог к пути
        File sdPath = new File(dbPath);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, DB_NAME);
        Log.d(LOG_TAG, "copyDataBase 1");
        //-----------------------------------




      /*
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            // пишем данные
            bw.write("Содержимое файла на SD");
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/


     //-----------------------------

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(sdFile);
        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();

        Log.d(LOG_TAG, "copyDataBase 2");
    }

    public void openDataBase() throws SQLiteException {
        myDataBase = SQLiteDatabase.openDatabase(dbPathFile, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db){}


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}

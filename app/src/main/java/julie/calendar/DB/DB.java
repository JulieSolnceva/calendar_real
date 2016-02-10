package julie.calendar.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import julie.calendar.Adapters.AdapterForTestDB;
import julie.calendar.R;

public class DB {
    private static DB uniqueInstance;

    public SQLiteDatabase dbActive;
    private DbHelper dbHelper;
    private Context context;
    private static final String TEST_FILE_PREFIX = "test_";

    private DB(Context c) throws Exception {
        context=c;
        dbHelper=null;
        getDBConnect();
    }

    public static synchronized DB getInstance(Context c) throws Exception{
        if(uniqueInstance==null){
            uniqueInstance=new DB(c) ;
        }
        return uniqueInstance;
    }

    public void getDBConnect() throws Exception {
        try {
            Log.d("тест", "DB getDBConnect()");
            if (context.getString(R.string.db_mode).equals("develop")) {
                Log.d("тест DB", "develop");
                dbHelper = new DbHelper(context);
                dbHelper.createDataBase();
                dbHelper.openDataBase();
                dbActive = dbHelper.myDataBase;
            }

            if (context.getString(R.string.db_mode).equals("test")) {
                Log.d("тест DB", "test");
                RenamingDelegatingContext contextR
                        = new RenamingDelegatingContext(context, TEST_FILE_PREFIX);

                AdapterForTestDB testAdapter = new AdapterForTestDB(contextR);
                testAdapter.open();
                dbActive = testAdapter.db;
            }
        }
        catch(Exception e){
            Log.d("тест DB error", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public void closeDBConnect(){
        if(dbHelper!=null)
            dbHelper.close();
    }
}


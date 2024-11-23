package ir.servicea.app;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StateOpenHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "data.sqlite";
    private final static String DATABASE_PATH = "/databases/";
    private final static int DATABASE_VERSION = 5;

    private static Context context;
    private static final String TAG = "SeasonOpenHelper";


    public StateOpenHelper(@Nullable Context context){
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    }

    public void copyDatabaseFromAsset() throws IOException {
        InputStream myInput = context.getAssets().open( DATABASE_NAME);
        String outFileName = getDatabasePath();
        File f = new File(context.getApplicationInfo().dataDir + DATABASE_PATH);
        if (!f.exists()){
            f.mkdir();
        }
        OutputStream myoutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0){
            myoutput.write(buffer, 0, length);
        }
        myoutput.flush();
        myoutput.close();
        myInput.close();
        Log.d(TAG, "copyDatabaseFromAsset: ");
    }


    public SQLiteDatabase openDatabase() throws SQLException {
        File dbFile = new File(getDatabasePath());
        Log.d(TAG, "openDatabase:  " + dbFile.toString());
        if (!dbFile.exists()){
            this.getWritableDatabase();
            try {
                copyDatabaseFromAsset();
            } catch (IOException e){
                throw new RuntimeException("Error creating source database");
            }
        }
        Log.d(TAG, "openDatabase: ");
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS| SQLiteDatabase.CREATE_IF_NECESSARY);
    }


    private static String getDatabasePath(){
       String path = context.getApplicationInfo().dataDir + DATABASE_PATH + DATABASE_NAME;
       Log.d(TAG, "getDatabasePath: " + path);
       return path ;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

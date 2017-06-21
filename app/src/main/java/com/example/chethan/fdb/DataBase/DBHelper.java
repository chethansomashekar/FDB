package com.example.chethan.fdb.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Chethan on 5/24/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context) {
        super(context, DataBaseName.DB_NAME, null, DataBaseName.DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(DataBaseName.CREATE_TB);
        }catch (Exception ex)
        {
            Toast.makeText(context, "updated", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DataBaseName.TB_NAME);
        onCreate(db);

    }
}

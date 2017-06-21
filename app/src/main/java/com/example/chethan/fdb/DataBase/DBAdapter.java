package com.example.chethan.fdb.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import static com.example.chethan.fdb.DataBase.DataBaseName.ROW_ID;
import static com.example.chethan.fdb.DataBase.DataBaseName.TB_NAME;

/**
 * Created by Chethan on 5/24/2017.
 */

public class DBAdapter {
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;

    public DBAdapter(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public DBAdapter openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            Toast.makeText(context, "table not opened", Toast.LENGTH_LONG).show();
        }
        return this;
    }

    public void closeDB() {
        try {
            helper.close();

        } catch (SQLException e) {
            Toast.makeText(context, "table not closed", Toast.LENGTH_LONG).show();
        }
    }

    public long add(String name) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(DataBaseName.NAME, name);
            return db.insert(TB_NAME, ROW_ID, cv);
        } catch (SQLException e) {
            Toast.makeText(context, "table not inserted", Toast.LENGTH_LONG).show();
        }
        return 0;
    }

    public Cursor getPendingData() {
        String[] columns = {ROW_ID, DataBaseName.NAME};
        return db.query(TB_NAME, columns, "done=?", new String[] {String.valueOf(false)}, null, null, null,null);
    }

    public Cursor getFinishedData() {
        String[] columns = {ROW_ID, DataBaseName.NAME};
        return db.query(TB_NAME, columns, "done=?", new String[] {String.valueOf(true)}, null, null, null,null);
    }

    public  boolean updatePendingData(int id){
    openDB();
    db.execSQL("UPDATE "+ TB_NAME +" SET done='true' WHERE id = " + id);
    closeDB();
    return  true;
    }

    public  boolean updateFinishedData(int id){
        openDB();
        db.execSQL("UPDATE "+ TB_NAME +" SET done='false' WHERE id = " + id);
        closeDB();
        return  true;
    }

}



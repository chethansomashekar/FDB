package com.example.chethan.fdb.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.chethan.fdb.DataBase.DataBaseName.ROW_ID;
import static com.example.chethan.fdb.DataBase.DataBaseName.TB_NAME;

/**
 * Created by Chethan on 5/24/2017.
 */

public class DBAdapter {
    Context c;

    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper = new DBHelper(c);
    }



    //OPEN DATABASE
    public DBAdapter openDB() {
        try {
            db = helper.getWritableDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE DATABASE
    public void closeDB() {
        try {
            helper.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //INSERT
    public long add(String name) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(DataBaseName.NAME, name);


            return db.insert(TB_NAME, ROW_ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    //RETRIEVE
    public Cursor getAllPlayers() {

        String[] columns = {ROW_ID, DataBaseName.NAME};

        return db.query(TB_NAME, columns, "done=?", new String[] {String.valueOf(false)}, null, null, null,null);



    }
    public Cursor getAllPlayers2() {


        String[] columns = {ROW_ID, DataBaseName.NAME};

        return db.query(TB_NAME, columns, "done=?", new String[] {String.valueOf(true)}, null, null, null,null);



    }
//
public  boolean updatedata(int id){
    openDB();

   db.execSQL("UPDATE "+ TB_NAME +" SET done='true' WHERE id = " + id);
    closeDB();
    return  true;
}
    public  boolean updatedata2(int id){
        openDB();

        db.execSQL("UPDATE "+ TB_NAME +" SET done='false' WHERE id = " + id);
        closeDB();
        return  true;
    }


}



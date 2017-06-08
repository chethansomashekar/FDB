package com.example.chethan.fdb.DataBase;

/**
 * Created by Chethan on 5/24/2017.
 */

public class DataBaseName { //COLUMNS
    static final String ROW_ID="id";
    static final String NAME = "name";
    static String DONE = "done";
   // static final Boolean


    //DB PROPERTIES
    static final String DB_NAME="DataBase";
    static final String TB_NAME="d_TB";
    static final int DB_VERSION='1';

    static final String CREATE_TB="CREATE TABLE d_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,done  boolean DEFAULT false);";
}

package com.example.sqlitefileassets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    /* In the MainActivity
    private static final String DATABASE_NAME = "Empresa";
    private static final String DATABASE_NAME_2 = "Empresa2";
    private static final int DATABASE_VERSION = 2;
    private static final int DATABASE_VERSION_2 = 1;
    */

    int option;

    //SQL sentence to create a table
    String sqlCreate = "CREATE TABLE Clients (codi INTEGER primary key, nom TEXT NOT NULL, Lat REAL, Lon REAL)";
    String sqlCreate2 = "CREATE TABLE Clients (codi INTEGER primary key, nom TEXT NOT NULL, Lat REAL, Lon REAL)";

    // The constructor: Create a helper object to create, open, and/or manage a database.
    // Pay attention to your Android API level!!
    public MySQLiteHelper(Context context, String dbName, int dbVersion, int opt) {
        // We will send dbName and dbNumber from the SQLiteActivity
        super (context, dbName, null, dbVersion);
        this.option = opt;
    }

    // Called if the DB is accessed but not yet created
    @Override
    public void onCreate(SQLiteDatabase db) {
        switch (this.option) {
            case 1:
                db.execSQL(sqlCreate);
                break;
            case 2:
                db.execSQL(sqlCreate2);
                break;
        }
    }

    // Called, if the database version is increased in your application code.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (this.option) {
            // Per poder insertar els mateixos registres en període de proves.
            case 1:
                // Deleting the previous version of the table
                db.execSQL("DROP TABLE IF EXISTS Clients ");
                db.execSQL (sqlCreate);   // Creating a new version
                break;
            case 2:
                // Do nothing
                break;
        }
    }

}

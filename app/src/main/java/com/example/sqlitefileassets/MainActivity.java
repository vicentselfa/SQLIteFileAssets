package com.example.sqlitefileassets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends MainMenu {

    // Creating and opening the database Empresa, table Clients
    MySQLiteHelper connexion;
    // Accessing in write mode
    SQLiteDatabase db ;
    EditText code, name, lat, lon;
    TextView list;

    // Values by default
    static String DATABASE_NAME = "BD";
    static int DATABASE_VERSION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Managing the DB.
        // Opt 1: The last db. Opt 2: the new one with restrictions: primary key + not null values
        connexion = new MySQLiteHelper(this, MainActivity.DATABASE_NAME, MainActivity.DATABASE_VERSION, 1);
        db = connexion.getWritableDatabase();

    }

    public void showClients(View view) {

        Log.d("db", "Name:" + MainActivity.DATABASE_NAME + " Number: " + MainActivity.DATABASE_VERSION);

        // A Cursor to get the result of the query
        Cursor c = db.rawQuery("SELECT codi, nom, lat, lon  FROM Clients", null);
        // Moving across all the rows
        if (c.moveToFirst()) {
            do {
                String cod = c.getString(0); String nom = c.getString(1);
                String lat = c.getString(2); String lon = c.getString(3);
                // list.append(" " + cod + " " + nom + " " + lat + " " + lon + "\n");
                Log.i("sql", "Clients: " + nom);
            } while(c.moveToNext());
        }
    }

    // The attributes
    InputStream in;  BufferedReader reader; String line;
    public void insertClients(View view) {
        try {
            in = this.getAssets().open("clients.sql");
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                Log.i("sql", "Client insertat: " + line);
                db.execSQL(line);
            }
        } catch (Exception e) {
            Log.i("sql", "Error: " + e);
        }
    }

}
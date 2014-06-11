package com.example.pt_assistant;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PT_SQLiteHelper extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";
 
    public PT_SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PATIENT_TABLE = "CREATE TABLE patient ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name TEXT, "+
                "injuryID INTERGER )";
       
        // create books table
        db.execSQL(CREATE_PATIENT_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS patient");
 
        // create fresh books table
        this.onCreate(db);
    }

}

package com.example.auditgpt;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AuditDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "AuditLogs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PROMPT = "prompt";
    private static final String COLUMN_RESPONSE = "response";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PROMPT + " TEXT,"
                + COLUMN_RESPONSE + " TEXT,"
                + COLUMN_TIMESTAMP + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void saveAudit(String prompt, String response) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROMPT, prompt);
        values.put(COLUMN_RESPONSE, response);
        values.put(COLUMN_TIMESTAMP, System.currentTimeMillis());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}

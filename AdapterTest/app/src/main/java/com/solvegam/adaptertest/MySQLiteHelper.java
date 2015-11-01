package com.solvegam.adaptertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stas on 31.10.2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "Test.db";

    public static final String CREATE_TEST_QUERY = "CREATE TABLE IF NOT EXISTS " + MyDbContract.Test.TABLE_NAME + " ("
            + MyDbContract.Test._ID + " INTEGER primary key AUTOINCREMENT, "
            + MyDbContract.Test.NAME + " TEXT, "
            + MyDbContract.Test.DESC + " TEXT);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEST_QUERY);

        fillInitialData(db);
    }

    private void fillInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(MyDbContract.Test.NAME, "Test 1");
        values.put(MyDbContract.Test.DESC, "Test 1 desc");
        db.insert(MyDbContract.Test.TABLE_NAME, null, values);
        values.clear();

        values.put(MyDbContract.Test.NAME, "Test 2");
        values.put(MyDbContract.Test.DESC, "Test 2 desc");
        db.insert(MyDbContract.Test.TABLE_NAME, null, values);
        values.clear();

        values.put(MyDbContract.Test.NAME, "Test 3");
        values.put(MyDbContract.Test.DESC, "Test 3 desc");
        db.insert(MyDbContract.Test.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyDbContract.Test.TABLE_NAME);
        onCreate(db);
    }
}

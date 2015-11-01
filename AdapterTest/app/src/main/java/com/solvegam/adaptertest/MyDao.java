package com.solvegam.adaptertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stas on 31.10.2015.
 */
public class MyDao {

    private static final String[] ALL_COLUMNS = {MyDbContract.Test.NAME, MyDbContract.Test.DESC};

    private MySQLiteHelper dbHelper;

    public MyDao(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public List<Item> getAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MyDbContract.Test.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);
        List<Item> result = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result.add(new Item(
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDbContract.Test.NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDbContract.Test.DESC))));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return result;
    }

}

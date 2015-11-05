package com.solvegam.adaptertest;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

/**
 * Created by Stas on 05.11.2015.
 */
public class MyLoader  extends AsyncTaskLoader<Cursor> {

    private static final String[] ALL_COLUMNS = {MyDbContract.Test.NAME, MyDbContract.Test.DESC};

    private MySQLiteHelper dbHelper;

    public MyLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MyDbContract.Test.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);

        return cursor;
    }


}

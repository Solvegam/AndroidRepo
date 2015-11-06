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

    private MySQLiteHelper dbHelper = new MySQLiteHelper(getContext());
    private CursorQueryHendler cqh;

    public MyLoader(Context context, CursorQueryHendler cqh) {
        super(context);
        this.cqh = cqh;
        System.out.println("Выполнил загрузку лоадера");
    }

    @Override
    public Cursor loadInBackground() {

        Cursor cursor = cqh.getAllItems();
        System.out.println("Выполнил получение курсора");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return cursor;
    }


}

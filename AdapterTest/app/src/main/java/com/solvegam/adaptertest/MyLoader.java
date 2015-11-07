package com.solvegam.adaptertest;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;

/**
 * Created by Stas on 05.11.2015.
 */
public class MyLoader  extends AsyncTaskLoader<Cursor> {

    private final Uri commonUri = Uri.parse("content://com.solvegam.adaptertest/items");

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

        Cursor cursor = getContext().getContentResolver().query(commonUri,null,null,null,null);
        System.out.println("Выполнил получение курсора");

        return cursor;
    }


    protected void onStartLoading()
    {
        forceLoad();
    }

}

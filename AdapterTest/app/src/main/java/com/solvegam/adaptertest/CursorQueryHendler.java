package com.solvegam.adaptertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Stas on 06.11.2015.
 */
public class CursorQueryHendler {

    private MySQLiteHelper dbHelper;
    private SQLiteDatabase dataBase;

    String [] columns = new String[]{MyDbContract.Test._ID,MyDbContract.Test.NAME,MyDbContract.Test.DESC};


    public CursorQueryHendler (Context context)
    {
        dbHelper = new MySQLiteHelper(context);
        dataBase = dbHelper.getWritableDatabase();
    }

    public Cursor getAllItems ()
    {
        Cursor cursor = dataBase.query(MyDbContract.Test.TABLE_NAME,columns,null,null,null,null,null);

        return cursor;
    }

    public Cursor getFilteredItems (CharSequence constraint)
    {
        if (constraint == null || constraint.equals(""))
        {
            System.out.println("Сработал пустой фильтр");
            return getAllItems();
        }
        else {
            Cursor cursor = dataBase.query(MyDbContract.Test.TABLE_NAME, columns, MyDbContract.Test.NAME + " IS ?", new String[]{constraint.toString()}, null, null, null);
            System.out.println("Создан новый курсор");
            return cursor;
        }
    }

    public void closeDB ()
    {
        if (dbHelper != null) dbHelper.close();
    }
}

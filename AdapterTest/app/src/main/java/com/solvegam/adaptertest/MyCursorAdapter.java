package com.solvegam.adaptertest;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filterable;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Stas on 31.10.2015.
 */
public class MyCursorAdapter extends CursorAdapter implements Filterable {
    private static final String[] ALL_COLUMNS = {MyDbContract.Test.NAME, MyDbContract.Test.DESC};

    public MyCursorAdapter(Context context, boolean autoRequery) {
        super(context, new MySQLiteHelper(context).getWritableDatabase().query(MyDbContract.Test.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null), autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = new View(context);

        View returnView = view.findViewById(R.id.item_layout);

        return returnView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.name_view);
        TextView age = (TextView) view.findViewById(R.id.age_view);

        name.setText(cursor.getString(2));
        age.setText(cursor.getString(3));
    }



}

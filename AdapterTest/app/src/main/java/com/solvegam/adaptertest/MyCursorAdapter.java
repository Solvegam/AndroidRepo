package com.solvegam.adaptertest;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Stas on 31.10.2015.
 */
public class MyCursorAdapter extends CursorAdapter {
    //здесь пришлось вставить MyDbContract.Test._ID, так как в CursorAdapter нужно, чтобы resultSet содержал колонку id
    private static final String[] ALL_COLUMNS = {MyDbContract.Test._ID,MyDbContract.Test.NAME, MyDbContract.Test.DESC};

    public MyCursorAdapter(Context context,Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        TextView name = (TextView) view.findViewById(R.id.name_view);
        TextView age = (TextView) view.findViewById(R.id.age_view);

        view.setTag(new ViewHolder(name,age));

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();

        TextView name = holder.getName();
        TextView age = holder.getAge();

        name.setText(cursor.getString(1));
        age.setText(cursor.getString(2));
    }

    private class ViewHolder {
        private final TextView name;
        private final TextView age;

        public ViewHolder (TextView name, TextView age)
        {
            this.name = name;
            this.age = age;
        }

        public TextView getName() {
            return name;
        }

        public TextView getAge() {
            return age;
        }
    }
}

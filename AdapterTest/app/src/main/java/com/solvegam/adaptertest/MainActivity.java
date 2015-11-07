package com.solvegam.adaptertest;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

    ArrayList<Item> itemList;

    private MyCursorAdapter adapter;



    private MyLoader loader;

    private CursorQueryHendler queryHendler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyCursorAdapter(this,null,true);

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                Uri uri = Uri.parse("content://com.solvegam.adaptertest/items/"+constraint);
                return getContentResolver().query(uri,null,null,null,null);
            }
        });
        Button filterButton = (Button) findViewById(R.id.send_filter_button);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getFilter().filter(((EditText) findViewById(R.id.filter)).getText());
            }
        });

        Button queryButton = (Button) findViewById(R.id.query_button);

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("content://com.solvegam.adaptertest/items/" + ((EditText) findViewById(R.id.filter)).getText());
                adapter.swapCursor(getContentResolver().query(uri, null, null, null, null));
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("content://com.solvegam.adaptertest/items/" + ((EditText) findViewById(R.id.filter)).getText());
                getContentResolver().delete(uri, null, null);
            }
        });

        Button updateButton = (Button) findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = ((EditText) findViewById(R.id.filter)).getText().toString().split(":")[1];
                ContentValues cv = new ContentValues();
                cv.put(MyContract.Items.NAME,value);
                cv.put(MyContract.Items.DESC,"this item was updated by MyContentProvider");

                Uri uri = Uri.parse("content://com.solvegam.adaptertest/items/" + ((EditText) findViewById(R.id.filter)).getText().toString().split(":")[0]);
                getContentResolver().update(uri, cv, null, null);
            }
        });

        Button insertButton = (Button) findViewById(R.id.insert_button);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = ((EditText) findViewById(R.id.filter)).getText().toString();

                ContentValues cv = new ContentValues();
                cv.put(MyContract.Items.NAME,value);
                cv.put(MyContract.Items.DESC,"this item was added by MyContentProvider");
                Uri uri = Uri.parse("content://com.solvegam.adaptertest/items/");
                getContentResolver().insert(uri,cv);
            }
        });

        ((ListView) findViewById(R.id.main_list_view)).setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        loader = new MyLoader(this,queryHendler);
        System.out.println("Зашел в метод onCreateLoader");
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        System.out.println("Зашел в метод он лоад финиш");
        adapter.changeCursor(data);

        // чтобы наше активити отслеживало изменение курсора
        startManagingCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        queryHendler.closeDB();
    }
}

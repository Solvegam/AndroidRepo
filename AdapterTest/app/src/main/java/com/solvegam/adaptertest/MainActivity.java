package com.solvegam.adaptertest;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
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

        getLoaderManager().initLoader(0,null,this);

        queryHendler = new CursorQueryHendler(this);

//        adapter = new MyCursorAdapter(this,queryHendler.getAllItems(),true);

        adapter = new MyCursorAdapter(this,null,true);

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return queryHendler.getFilteredItems(constraint);
            }
        });
        Button filterButton = (Button) findViewById(R.id.send_filter_button);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               adapter.getFilter().filter(((EditText) findViewById(R.id.filter)).getText());
            }
        });

        ((ListView) findViewById(R.id.main_list_view)).setAdapter(adapter);
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loader.reset();
        System.out.println("выполнился метод он reset");
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

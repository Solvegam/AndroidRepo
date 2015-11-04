package com.solvegam.adaptertest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        itemList = (ArrayList<Item>)new MyDao(this).getAll();

//        код ниже использовался для показа функций адаптера
//        itemList = new ArrayList<>();

//        for (int i = 0; i < 10; i++)
//        {
//            itemList.add(new Item("Name "+i,(i*10)+""));
//        }
//        Код ниже нужен был для реализации фильтра через текстовое поле
//        EditText text = (EditText)findViewById(R.id.filter);

//        final MyAdapter adapter = new MyAdapter(itemList,this);

//        text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Filter filter = adapter.getFilter();
//                filter.filter(s.toString());
//            }
//        });

        final MyCursorAdapter adapter = new MyCursorAdapter(this,true);
        Button filterButton = (Button) findViewById(R.id.send_filter_button);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter filter = adapter.getFilter();
                filter.filter(((EditText)findViewById(R.id.filter)).getText().toString());
            }
        });

                ((ListView) findViewById(R.id.main_list_view)).setAdapter(adapter);
    }


}

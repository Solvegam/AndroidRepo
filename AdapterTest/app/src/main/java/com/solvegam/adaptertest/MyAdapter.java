package com.solvegam.adaptertest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Stas on 29.10.2015.
 */
public class MyAdapter extends BaseAdapter implements Filterable{

    private ArrayList<Item> dataSource;
    private Context context;
    private ArrayList<Item> filteredDataSource;

    public MyAdapter(ArrayList<Item> list, Context context) {
        dataSource = list;
        this.context = context;
        filteredDataSource = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return filteredDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView name;
        TextView age;

        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

            name = (TextView)convertView.findViewById(R.id.name_view);
            age = (TextView)convertView.findViewById(R.id.age_view);

            convertView.setTag(new ViewHolder(name,age));
        }
        else
        {
            ViewHolder holder = (ViewHolder)convertView.getTag();
            name = holder.getName();
            age = holder.getAge();
        }

        Item item = filteredDataSource.get(position);

        name.setText(item.getName());
        age.setText(item.getAge()+"");

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

               if (constraint != null || constraint.length() != 0){
                    for (int i = 0 ; i < filteredDataSource.size(); i++)
                    {
                        if (!filteredDataSource.get(i).getName().equals(constraint.toString()))
                        {
                            filteredDataSource.remove(i);
                            i--;
                        }
                    }
                   filteredDataSource.trimToSize(); // может быть здесь съедалась бы память
                }

                FilterResults fr = new FilterResults();
                fr.values = filteredDataSource;
                fr.count = filteredDataSource.size();
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    notifyDataSetChanged();
                }
            }
        };
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

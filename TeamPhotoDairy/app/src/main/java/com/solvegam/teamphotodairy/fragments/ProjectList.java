package com.solvegam.teamphotodairy.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solvegam.teamphotodairy.MainScreen;
import com.solvegam.teamphotodairy.R;

/**
 * Created by Stas on 25.10.2015.
 */
public class ProjectList extends Fragment {


    TextClick textClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.project_list,null);

        LinearLayout mainLayout = (LinearLayout) v.findViewById(R.id.projectList);

        for ( int i = 0; i < 10; i++)
        {
            TextView text = new TextView(getActivity());
            text.setText("Project " + (i + 1));
            text.setTextSize(25);
            final String message = "Description of the " + text.getText().toString();

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        textClick.textClick(message);
                }
            });
            mainLayout.addView(text);
        }
        return mainLayout;
    }

    public interface TextClick {

        void textClick(String message);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
        textClick = (TextClick) activity;
            }
            catch (ClassCastException e) {
                throw new ClassCastException(getActivity()+" this activity must implement TextClick interface");
            }
    }
}

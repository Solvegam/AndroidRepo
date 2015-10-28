package com.solvegam.teamphotodairy.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solvegam.teamphotodairy.R;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartScreen extends Fragment {



    public StartScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Task startScreen = new Task();
        startScreen.execute();

        return inflater.inflate(R.layout.fragment_start_screen, container, false);
    }

    public class Task extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            getActivity().getFragmentManager().popBackStack();

            return null;
         }
    }





}

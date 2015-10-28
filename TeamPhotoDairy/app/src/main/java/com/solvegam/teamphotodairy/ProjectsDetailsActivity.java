package com.solvegam.teamphotodairy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProjectsDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_details);

        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.project_content_layout_vertical);

        ((TextView) fragment.getView().findViewById(R.id.description_of_project)).setText(getIntent().getStringExtra(MainScreen.MESSAGE));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Intent intent = new Intent(this,MainScreen.class);
            startActivity(intent);
        }
    }
}

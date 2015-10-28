package com.solvegam.teamphotodairy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solvegam.teamphotodairy.fragments.ProjectContent;
import com.solvegam.teamphotodairy.fragments.ProjectList;
import com.solvegam.teamphotodairy.fragments.StartScreen;

public class MainScreen extends Activity implements ProjectList.TextClick{

    public static final String MESSAGE = "com.solvegam.teamphotodairy.MESSAGE_TO_FRAGMENT";

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        fm = getFragmentManager();

    //        запускается сплэш скрин
    //        runStartScreen();

        FragmentTransaction ft = fm.beginTransaction();

        ProjectList list = new ProjectList();
        ProjectContent content = new ProjectContent();



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ft.add(R.id.project_list_layout_horizontal, list);
            ft.add(R.id.project_content_layout_horizontal, content).addToBackStack(null);
        }
        else
        {
            ft.add(R.id.project_list_layout_vertical, list);
        }

        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void runStartScreen ()
    {
        StartScreen ss = new StartScreen();

        fm.beginTransaction().replace(R.id.main_container,ss).addToBackStack(null).commit();

    }

    public void textClick (String message) {

        if (getResources().getConfiguration().orientation ==Configuration.ORIENTATION_LANDSCAPE)
        {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.project_content_layout_horizontal);
                ((TextView) fragment.getView().findViewById(R.id.description_of_project)).setText(message);

        }
        else {
            Intent intent = new Intent(this,ProjectsDetailsActivity.class);
            intent.putExtra(MESSAGE,message);
            startActivity(intent);
        }
    }

}

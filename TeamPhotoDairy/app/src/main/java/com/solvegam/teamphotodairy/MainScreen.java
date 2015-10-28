package com.solvegam.teamphotodairy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        fm = getFragmentManager();
//        runStartScreen();  запуск сплэш скрина

        FragmentTransaction ft = fm.beginTransaction();

        ProjectList list = new ProjectList();
        ProjectContent content = new ProjectContent();

        ft.add(R.id.project_list_layout,list);
        ft.add(R.id.project_content_layout,content).addToBackStack(null);

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
        Fragment fragment = getFragmentManager().findFragmentById(R.id.project_content_layout);
        if (fragment != null) {
            ((TextView) fragment.getView().findViewById(R.id.description_of_project)).setText(message);
        }
    }

}

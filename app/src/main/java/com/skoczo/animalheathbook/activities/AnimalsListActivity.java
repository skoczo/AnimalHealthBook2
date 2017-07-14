package com.skoczo.animalheathbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.skoczo.animalheathbook.AnimalsGridAdapter;
import com.skoczo.animalheathbook.R;
import com.skoczo.animalheathbook.db.entieties.Animal;
import com.skoczo.animalheathbook.dbgen.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

public class AnimalsListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private GridView animalsGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseHelper(getApplicationContext());

        try {
            List<Animal> allAnimals = dbHelper.getAnimalDao().queryForAll();
            Log.i(getClass().getName(), "Num of animals: " + allAnimals.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_animals_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            animalsGrid = (GridView) findViewById(R.id.animals_grid);
            animalsGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
            animalsGrid.setAdapter(new AnimalsGridAdapter(this));
        } catch (SQLException e) {
            Log.e(getClass().getName(), "Error during sql query: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Error during sql query: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAnimalActivity = new Intent(AnimalsListActivity.this, AnimalAddActivity.class);
                AnimalsListActivity.this.startActivity(addAnimalActivity);
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animals_list, menu);
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

//    @Override
//    protected void onResume() {
//        try {
////            ((AnimalsGridAdapter) animalsGrid.getAdapter()).refresh();
//            animalsGrid.setAdapter(new AnimalsGridAdapter(this));
//        } catch (SQLException e) {
//            Log.e(getClass().getName(), "Error during refresh", e);
//        }
//        Log.i(getClass().getName(), "onResume");
//        super.onResume();
//    }

//    @Override
//    protected void onRestart() {
//        try {
////            ((AnimalsGridAdapter) animalsGrid.getAdapter()).refresh();
//            animalsGrid.setAdapter(new AnimalsGridAdapter(this));
//        } catch (SQLException e) {
//            Log.e(getClass().getName(), "Error during refresh", e);
//        }
//        Log.i(getClass().getName(), "onRestart");
//        super.onRestart();
//    }
}

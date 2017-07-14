package com.skoczo.animalheathbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skoczo.animalheathbook.activities.AnimalViewActivity;
import com.skoczo.animalheathbook.db.entieties.Animal;
import com.skoczo.animalheathbook.dbgen.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by skoczo on 10.07.17.
 */

public class AnimalsGridAdapter extends BaseAdapter {
    public static String[] colours = {"#e1f7d5","#ffbdbd","#c9c9ff","#f1cbff"};

    private final DatabaseHelper dbHelper;
    private List<Animal> animals;
    private final Activity activity;

    public AnimalsGridAdapter(Activity activity) throws SQLException {
        this.activity = activity;
        dbHelper = new DatabaseHelper(activity.getApplicationContext());
        refresh();
    }

    public void refresh() throws SQLException {
        animals = dbHelper.getAnimalDao().queryForAll();
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int i) {
        return animals.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Animal animal = animals.get(i);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.item_animal_list, null, true);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                view.getBackground().setAlpha(127);
                Intent myIntent = new Intent(activity, AnimalViewActivity.class);

//                myIntent.putExtra("events", events);
                activity.startActivity(myIntent);
            }
        });

        rowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(getClass().getName(), event.toString());

                if(v.getBackground() == null) {
                    return false;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        if(animal.getPicture() != null) {
            BitmapDrawable bd = new BitmapDrawable(view.getContext().getResources(), BitmapFactory.decodeByteArray(animal.getPicture(),0,animal.getPicture().length));
            rowView.setBackground(bd);
        } else {
            rowView.getBackground().setColorFilter(Color.parseColor(colours[i%colours.length]), PorterDuff.Mode.DARKEN);
        }


        TextView name = (TextView) rowView.findViewById(R.id.animal_name);
        name.setText(animal.getName());

        return rowView;
    }
}

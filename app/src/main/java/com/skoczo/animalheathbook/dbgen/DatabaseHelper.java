package com.skoczo.animalheathbook.dbgen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.skoczo.animalheathbook.R;
import com.skoczo.animalheathbook.db.entieties.Animal;
import com.skoczo.animalheathbook.db.entieties.Breed;
import com.skoczo.animalheathbook.db.entieties.Event;
import com.skoczo.animalheathbook.db.entieties.EventName;
import com.skoczo.animalheathbook.db.entieties.Weigth;

import java.sql.SQLException;

/**
 * Created by skoczo on 09.07.17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormlite.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Animal, Integer> mAnimalDao = null;
    private Dao<Breed, Integer> mBreedDao = null;
    private Dao<Weigth, Integer> mWeigthDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(getClass().getName(), "database creation");
            TableUtils.createTable(connectionSource, Breed.class);
            TableUtils.createTable(connectionSource, Animal.class);
            TableUtils.createTable(connectionSource, Weigth.class);
            TableUtils.createTable(connectionSource, Event.class);
            TableUtils.createTable(connectionSource, EventName.class);

            Breed breed = new Breed();
            breed.setName("Cavalier");

            getBreedDao().createOrUpdate(breed);

            Animal test = new Animal();
            test.setName("Axel");
            test.setBreed(breed);

            getAnimalDao().createOrUpdate(test);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        Log.e(getClass().getName(), "upgrade not supported");
//            TableUtils.dropTable(connectionSource, Animal.class, true);
//            onCreate(db, connectionSource);
    }

    /* User */

    public Dao<Animal, Integer> getAnimalDao() throws SQLException {
        if (mAnimalDao == null) {
            mAnimalDao = getDao(Animal.class);
        }

        return mAnimalDao;
    }

    public Dao<Weigth, Integer> getWeigthDao() throws SQLException {
        if (mWeigthDao == null) {
            mWeigthDao = getDao(Weigth.class);
        }

        return mWeigthDao;
    }

    public Dao<Breed, Integer> getBreedDao() throws SQLException {
        if (mBreedDao == null) {
            mBreedDao = getDao(Breed.class);
        }

        return mBreedDao;
    }

    @Override
    public void close() {
        mAnimalDao = null;

        super.close();
    }
}

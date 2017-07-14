package com.skoczo.animalheathbook.db;

import android.content.Context;

/**
 * Created by skoczo on 09.07.17.
 */

public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager();
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

}

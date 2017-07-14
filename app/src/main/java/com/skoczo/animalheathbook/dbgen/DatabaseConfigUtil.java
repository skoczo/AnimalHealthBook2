package com.skoczo.animalheathbook.dbgen;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.skoczo.animalheathbook.db.entieties.Animal;
import com.skoczo.animalheathbook.db.entieties.Breed;
import com.skoczo.animalheathbook.db.entieties.Event;
import com.skoczo.animalheathbook.db.entieties.EventName;
import com.skoczo.animalheathbook.db.entieties.Weigth;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by skoczo on 09.07.17.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {
            Animal.class, Breed.class, Event.class, EventName.class, Weigth.class
    };

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile(new File("app/src/main/res/raw/ormlite_config.txt"), classes);
    }
}

package com.skoczo.animalheathbook.db.entieties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by skoczo on 11.07.17.
 */

@DatabaseTable(tableName = "event_names")
public class EventName {
    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField(unique = true, canBeNull = false)
    private String name;

    public EventName() {}


    public EventName(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

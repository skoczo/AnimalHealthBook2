package com.skoczo.animalheathbook.db.entieties;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by skoczo on 07.07.17.
 */

@DatabaseTable(tableName = "breeds")
public class Breed {

    @DatabaseField(id = true, columnName = "name",canBeNull = false)
    private  String name;

    public Breed() {}

    public Breed( String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

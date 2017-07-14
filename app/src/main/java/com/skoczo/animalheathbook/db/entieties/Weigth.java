package com.skoczo.animalheathbook.db.entieties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by skoczo on 13.07.17.
 */
@DatabaseTable(tableName = "weigth")
public class Weigth {
    @DatabaseField(generatedId = true, columnName = "id")
    private long id;

    @DatabaseField
    private int weigth;

    @DatabaseField
    private Date date;

    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Animal animal;


    public Weigth() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
